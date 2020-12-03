package com.batzalcancia.core.domain.repository

import androidx.paging.PagingSource
import com.batzalcancia.core.domain.local.entities.InstagramPicture

interface InstagramDuplicateRepository {

    fun getAllPictures(): PagingSource<Int, InstagramPicture>

    suspend fun insertPicture(picture: InstagramPicture)

}