package com.geekbrains.moviesearcher2.repository.movies

import com.geekbrains.moviesearcher2.model.MoviesDTO

class MoviesRepositoryImpl(private val moviesRemoteDataSource: MoviesRemoteDataSource) :
    MoviesRepository {
    override fun getMoviesFromServer(
        includeAdult: Boolean,
        query: String,
        callback: retrofit2.Callback<MoviesDTO>
    ) {
        moviesRemoteDataSource.getMovies(includeAdult, query, callback)
    }
}