package com.indramahkota.app.viewmodel

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import androidx.lifecycle.Observer
import com.indramahkota.app.fake.FakeMovieUseCase
import com.indramahkota.domain.model.Movie
import com.indramahkota.domain.utils.Resource
import junit.framework.TestCase
import org.junit.Before
import org.junit.Rule
import org.junit.Test
import org.mockito.Mock
import org.mockito.MockitoAnnotations

class MovieViewModelTest : TestCase() {
    @get:Rule
    var instantExecutorRule = InstantTaskExecutorRule()

    @Mock
    lateinit var useCase: FakeMovieUseCase

    @Mock
    lateinit var observer: Observer<Resource<List<Movie>>>

    lateinit var viewModel: MovieViewModel

    @Before
    override fun setUp() {
        MockitoAnnotations.openMocks(this)
        viewModel = MovieViewModel(useCase)
        viewModel.movies.observeForever(observer)
    }

    @Test
    fun testgetMovies() {
        /*val resourceMovie = Resource.Success(FakeData.fakeListMovie())
        val liveData = MutableLiveData<Resource<List<Movie>>>()
        liveData.value = resourceMovie

        Mockito.`when`(useCase.getAllMovies("Newest")).thenReturn(liveData)

        viewModel.getMovies("Newest")

        Mockito.verify(observer).onChanged(resourceMovie)*/
    }

    /*@Test
    fun testgetTv() {
        movieViewModel.getTv.observeForever(observer)
        val resourceMovie = Resource.Success(FakeData.fakeListMovie())

    }*/
}