package com.quere.moodra.paging

import androidx.paging.PagingSource
import androidx.paging.PagingState
import com.quere.moodra.AppConstants
import com.quere.moodra.retrofit.MediaService
import com.quere.moodra.retrofit.Movie
import com.quere.moodra.retrofit.TVshow
import retrofit2.HttpException
import java.io.IOException

class TvGenrePagingSource(
    private val mediaService: MediaService,
    private val type: String
) : PagingSource<Int, TVshow>() {

    override suspend fun load(params: LoadParams<Int>): LoadResult<Int, TVshow> {
        val position = params.key ?: STARTING_PAGE_INDEX

        return try {
            val response = mediaService.getTVGenre(
                AppConstants.API_KEY,
                AppConstants.LANGUAGE,
                type,
                Integer(1),
                "KR"
            )
            val photos = response.results
            var currentList = listOf<TVshow>()

            if(photos.size<18){
                currentList = photos
            } else {
                currentList = photos.subList(0,18)
            }

            LoadResult.Page(
                data = currentList,
                prevKey = if (position == STARTING_PAGE_INDEX) null else null,
                nextKey = if (photos.isEmpty()) null else null
            )
        } catch (e: IOException) {
            LoadResult.Error(e)
        } catch (e: HttpException) {
            LoadResult.Error(e)
        }

    }

    override fun getRefreshKey(state: PagingState<Int, TVshow>): Int? {
        TODO("Not yet implemented")
    }
}