package com.batzalcancia.dashboard.data.paging

import androidx.paging.PagingSource
import com.batzalcancia.core.domain.local.entities.InstagramPicture
import com.batzalcancia.core.domain.usecase.GetAllPictures
import javax.inject.Inject

class InstagramPicturesPager @Inject constructor(
        private val getAllPictures: GetAllPictures
)  {

//    override suspend fun load(params: LoadParams<Int>): LoadResult<Int, InstagramPicture> {
//        return try {
//            val response = getAllPictures()
//
//            LoadResult.Page(
//                    data = response,
//                    prevKey = null,
//                    nextKey = if(response.isEmpty()) null else response.last().id
//            )
//        } catch (e: Exception) {
//            LoadResult.Error(e)
//        }
//    }

}