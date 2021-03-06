package com.quere.moodra.paging

import androidx.paging.PagingSource
import androidx.paging.PagingState
import com.quere.moodra.AppConstants
import com.quere.moodra.retrofit.MediaService
import com.quere.moodra.retrofit.Movie
import com.quere.moodra.retrofit.TVshowSearch
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.delay
import kotlinx.coroutines.withContext
import retrofit2.HttpException
import java.io.IOException


class MoviePagingSource(
    private val mediaService: MediaService,
    private val type: String
) : PagingSource<Int, Movie>() {

    override suspend fun load(params: LoadParams<Int>): LoadResult<Int, Movie> {
        val position = params.key ?: STARTING_PAGE_INDEX

        return try {
            val response = mediaService.getMovie(
                type,
                AppConstants.LANGUAGE,
                AppConstants.API_KEY,
                "KR"
            )
            val photos = response.results

            var currentList = listOf<Movie>()

            if (photos.size < 18) {
                currentList = photos
            } else {
                currentList = photos.subList(0, 18)
            }

            LoadResult.Page(
                data = currentList,
                prevKey = if (position == STARTING_PAGE_INDEX) null else position - 1,
                nextKey = if (photos.isEmpty()) null else position + 1
            )


        } catch (e: IOException) {
            LoadResult.Error(e)
        } catch (e: HttpException) {
            LoadResult.Error(e)
        }

    }

    override fun getRefreshKey(state: PagingState<Int, Movie>): Int? {
        TODO("Not yet implemented")
    }
}