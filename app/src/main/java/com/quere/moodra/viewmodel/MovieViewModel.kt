package com.quere.moodra.viewmodel

import androidx.hilt.lifecycle.ViewModelInject
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import androidx.paging.PagingData
import androidx.paging.cachedIn
import com.quere.moodra.AppConstants
import com.quere.moodra.repository.MovieRepository
import com.quere.moodra.retrofit.Movie
import kotlinx.coroutines.flow.Flow

class MovieViewModel @ViewModelInject constructor(
    private val rep: MovieRepository
) : ViewModel() {


    private var currentMovieGenreResult: Flow<PagingData<Movie>>? = null
    val error = MutableLiveData<String>()

    fun action_movies(): Flow<PagingData<Movie>> {
        return rep.getMovieGenre(AppConstants.ACTION).cachedIn(viewModelScope)
    }

    fun fantasy_movies(): Flow<PagingData<Movie>> {
        return rep.getMovieGenre(AppConstants.FANTASY).cachedIn(viewModelScope)
    }

    fun animation_movies(): Flow<PagingData<Movie>> {
        return rep.getMovieGenre(AppConstants.ANIMATION).cachedIn(viewModelScope)
    }

    fun comedy_movies(): Flow<PagingData<Movie>> {
        return rep.getMovieGenre(AppConstants.COMEDY).cachedIn(viewModelScope)
    }

    fun music_movies(): Flow<PagingData<Movie>> {
        return rep.getMovieGenre(AppConstants.MUSIC).cachedIn(viewModelScope)
    }

    fun romance_movies(): Flow<PagingData<Movie>> {
        return rep.getMovieGenre(AppConstants.ROMANCE).cachedIn(viewModelScope)
    }

    fun crime_movies(): Flow<PagingData<Movie>> {
        return rep.getMovieGenre(AppConstants.CRIME).cachedIn(viewModelScope)
    }

    fun mystery_movies(): Flow<PagingData<Movie>> {
        return rep.getMovieGenre(AppConstants.MYSTERY).cachedIn(viewModelScope)
    }

    fun horror_movies(): Flow<PagingData<Movie>> {
        return rep.getMovieGenre(AppConstants.HORROR).cachedIn(viewModelScope)
    }


    fun movies_detail(genre: String): Flow<PagingData<Movie>> {
        val lastResult = currentMovieGenreResult
        val newResult: Flow<PagingData<Movie>>
        if (lastResult != null) {
            return lastResult
        }

        when (genre) {

            "??????" -> {

                newResult =
                    rep.getMovieGenreDetail(AppConstants.ACTION).cachedIn(viewModelScope)

            }
            "?????????" -> {

                newResult =
                    rep.getMovieGenreDetail(AppConstants.FANTASY).cachedIn(viewModelScope)


            }
            "???????????????" -> {

                newResult =
                    rep.getMovieGenreDetail(AppConstants.ANIMATION).cachedIn(viewModelScope)


            }
            "?????????" -> {

                newResult =
                    rep.getMovieGenreDetail(AppConstants.COMEDY).cachedIn(viewModelScope)


            }
            "??????" -> {

                newResult =
                    rep.getMovieGenreDetail(AppConstants.MUSIC).cachedIn(viewModelScope)


            }
            "?????????" -> {

                newResult =
                    rep.getMovieGenreDetail(AppConstants.ROMANCE).cachedIn(viewModelScope)


            }
            "??????" -> {

                newResult =
                    rep.getMovieGenreDetail(AppConstants.CRIME).cachedIn(viewModelScope)


            }
            "????????????" -> {

                newResult =
                    rep.getMovieGenreDetail(AppConstants.MYSTERY).cachedIn(viewModelScope)


            }
            else -> {

                newResult =
                    rep.getMovieGenreDetail(AppConstants.HORROR).cachedIn(viewModelScope)


            }

        }
        currentMovieGenreResult = newResult
        return newResult
    }


}