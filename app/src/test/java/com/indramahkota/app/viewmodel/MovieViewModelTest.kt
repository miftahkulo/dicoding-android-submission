package com.indramahkota.app.viewmodel

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import app.cash.turbine.test
import com.indramahkota.app.fake.FakeMovieUseCase
import com.indramahkota.app.testutils.MainCoroutineRule
import com.indramahkota.domain.utils.UIState
import junit.framework.TestCase
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.test.runBlockingTest
import org.junit.Before
import org.junit.Rule
import org.junit.Test

@ExperimentalCoroutinesApi
class MovieViewModelTest : TestCase() {
    @get:Rule
    var instantExecutorRule = InstantTaskExecutorRule()

    @get:Rule
    var coroutineRule = MainCoroutineRule()

    private lateinit var useCase: FakeMovieUseCase
    private lateinit var viewModel: MovieViewModel

    @Before
    override fun setUp() {
        useCase = FakeMovieUseCase()
        viewModel = MovieViewModel(useCase)
    }

    @Test
    fun testGetMovies() = runBlockingTest {
        //viewModel.getMovies("Newest")
        viewModel.movies.test {
            assertTrue(awaitItem() is UIState.Empty)
        }
    }
}