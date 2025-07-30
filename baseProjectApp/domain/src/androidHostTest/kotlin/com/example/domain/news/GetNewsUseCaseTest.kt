package com.example.domain.news

import com.example.data.news.model.News
import com.example.data.news.repository.INewsRepository
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.async
import kotlinx.coroutines.awaitAll
import kotlinx.coroutines.cancelAndJoin
import kotlinx.coroutines.coroutineScope
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch
import kotlinx.coroutines.test.StandardTestDispatcher
import kotlinx.coroutines.test.advanceTimeBy
import kotlinx.coroutines.test.resetMain
import kotlinx.coroutines.test.runTest
import kotlinx.coroutines.test.setMain
import org.junit.Test

import kotlin.test.*

class GetNewsUseCaseTest {

    private val testDispatcher = StandardTestDispatcher()

    @BeforeTest
    fun setup() {
        Dispatchers.setMain(testDispatcher)
    }

    @AfterTest
    fun tearDown() {
        Dispatchers.resetMain()
    }

    @Test
    fun `Successful news retrieval`() = runTest {
        val expectedNews = listOf(
            News("1"),
            News("2")
        )
        val fakeRepo = object : INewsRepository {
            override suspend fun getNews(): List<News> = expectedNews
        }
        val useCase = GetNewsUseCase(fakeRepo)

        val result = useCase()

        assertEquals(expectedNews, result)
    }

    @Test
    fun `Empty news list`() = runTest {
        val fakeRepo = object : INewsRepository {
            override suspend fun getNews(): List<News> = emptyList()
        }
        val useCase = GetNewsUseCase(fakeRepo)

        val result = useCase()

        assertTrue(result.isEmpty())
    }

    @Test
    fun `Repository throws exception`() = runTest {
        val fakeRepo = object : INewsRepository {
            override suspend fun getNews(): List<News> {
                throw RuntimeException("Network error")
            }
        }
        val useCase = GetNewsUseCase(fakeRepo)

        val exception = assertFailsWith<RuntimeException> {
            useCase()
        }

        assertEquals("Network error", exception.message)
    }

    @Test
    fun `Concurrent invocations`() = runTest {
        val expected = listOf(News("1"))
        val fakeRepo = object : INewsRepository {
            override suspend fun getNews(): List<News> {
                delay(100)
                return expected
            }
        }
        val useCase = GetNewsUseCase(fakeRepo)

        val results = coroutineScope {
            listOf(
                async { useCase() },
                async { useCase() },
                async { useCase() }
            ).awaitAll()
        }

        results.forEach { result ->
            assertEquals(expected, result)
        }
    }

    @Test
    fun `Cancellation of coroutine`() = runTest {
        val fakeRepo = object : INewsRepository {
            override suspend fun getNews(): List<News> {
                delay(1000)
                return listOf(News("1"))
            }
        }
        val useCase = GetNewsUseCase(fakeRepo)

        val job = launch {
            useCase()
        }

        advanceTimeBy(100)
        job.cancelAndJoin()

        assertTrue(job.isCancelled)
    }

    @Test
    fun `Repository returns list with malformed news data`() = runTest {
        val fakeRepo = object : INewsRepository {
            override suspend fun getNews(): List<News> {
                return listOf(
                    News("title 1"),
                    News("") // Malformed (blank title)
                )
            }
        }
        val useCase = GetNewsUseCase(fakeRepo)

        val result = useCase()

        assertEquals(2, result.size)
        assertTrue(result.any { it.title.isBlank() })
    }
}