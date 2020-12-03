package com.batzalcancia.core.data.repository

import com.batzalcancia.core.data.local.dao.InstagramDuplicateDao
import com.batzalcancia.core.domain.local.entities.InstagramPicture
import com.batzalcancia.core.domain.repository.InstagramDuplicateRepository
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import javax.inject.Inject

class InstagramDuplicateRepositoryImpl @Inject constructor(
    private val instagramDuplicateDao: InstagramDuplicateDao
) : InstagramDuplicateRepository {

    override fun getAllPictures() = instagramDuplicateDao.getAllPictures()

    override suspend fun insertPicture(picture: InstagramPicture) = withContext(Dispatchers.IO) {
        instagramDuplicateDao.insertPicture(picture)
    }

}