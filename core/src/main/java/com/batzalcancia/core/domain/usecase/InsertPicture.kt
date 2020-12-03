package com.batzalcancia.core.domain.usecase

import com.batzalcancia.core.domain.repository.InstagramDuplicateRepository
import com.batzalcancia.core.domain.local.entities.InstagramPicture
import javax.inject.Inject

class InsertPicture @Inject constructor(private val instagramDuplicateRepository: InstagramDuplicateRepository) {
    suspend operator fun invoke(picture: InstagramPicture) {
        instagramDuplicateRepository.insertPicture(picture)
    }
}