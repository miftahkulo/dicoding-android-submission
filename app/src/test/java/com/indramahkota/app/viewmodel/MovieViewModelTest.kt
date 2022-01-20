package com.indramahkota.app.viewmodel

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import app.cash.turbine.test
import com.indramahkota.app.fake.FakeData
import com.indramahkota.app.fake.FakeMovieUseCase
import com.indramahkota.app.testutils.MainCoroutineRule
import com.indramahkota.app.testutils.runBlockingTest
import com.indramahkota.domain.model.Movie
import com.indramahkota.domain.utils.UIState
import junit.framework.TestCase
import kotlinx.coroutines.ExperimentalCoroutinesApi
import org.junit.Before
import org.junit.Rule
import org.junit.Test

@ExperimentalCoroutinesApi
class MovieViewModelTest : TestCase() {
    // Executes tasks in the Architecture Components in the same thread
    @get:Rule
    var instantExecutorRule = InstantTaskExecutorRule()

    // Overrides Dispatchers.Main used in Coroutines
    @get:Rule
    var coroutineRule = MainCoroutineRule()

    private val testDispatcher = coroutineRule.testDispatcher

    private lateinit var useCase: FakeMovieUseCase
    private lateinit var viewModel: MovieViewModel

    @Before
    override fun setUp() {
        useCase = FakeMovieUseCase()
        viewModel = MovieViewModel(useCase, testDispatcher)
    }

    @Test
    fun testGetMovies() = coroutineRule.runBlockingTest {
        viewModel.movies.test {
            assertTrue(awaitItem() is UIState.Empty)
        }

        viewModel.getMovies("Newest")
        viewModel.movies.test {
            checkSuccessData(awaitItem())
        }
    }

    private fun checkSuccessData(data: UIState<List<Movie>>) {
        assertTrue(data is UIState.Success)
        assertEquals(FakeData.fakeListMovie().size, data.data!!.size)
    }
}