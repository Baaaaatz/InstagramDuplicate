package com.batzalcancia.core.domain.usecase

import com.batzalcancia.core.domain.repository.InstagramDuplicateRepository
import javax.inject.Inject

class GetAllPictures @Inject constructor(private val instagramDuplicateRepository: InstagramDuplicateRepository) {
    operator fun invoke() = instagramDuplicateRepository.getAllPictures()
}