package com.quere.moodra.paging

import androidx.paging.PagingSource
import androidx.paging.PagingState
import com.quere.moodra.AppConstants
import com.quere.moodra.retrofit.*
import retrofit2.HttpException
import java.io.IOException


class SimilarPagingSource(
    private val mediaService: MediaService,
    private val id: Int
) : PagingSource<Int, OtherContent>() {

    override suspend fun load(params: LoadParams<Int>): LoadResult<Int, OtherContent> {
        val position = params.key ?: STARTING_PAGE_INDEX

        return try {
            val response = mediaService.geSimilar(
                id,
                AppConstants.API_KEY,
                AppConstants.LANGUAGE,
                1
            )
            val photos = response.results

            LoadResult.Page(
                data = photos,
                prevKey = if (position == STARTING_PAGE_INDEX) null else null,
                nextKey = if (photos.isEmpty()) null else null
            )
        } catch (e: IOException) {
            LoadResult.Error(e)
        } catch (e: HttpException) {
            LoadResult.Error(e)
        }

    }

    override fun getRefreshKey(state: PagingState<Int, OtherContent>): Int? {
        TODO("Not yet implemented")
    }
}