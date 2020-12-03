package com.batzalcancia.core.di

import android.content.Context
import androidx.room.Room
import com.batzalcancia.core.data.local.dao.InstagramDuplicateDao
import com.batzalcancia.core.data.local.database.InstagramDuplicateDatabase
import com.batzalcancia.core.data.repository.InstagramDuplicateRepositoryImpl
import com.batzalcancia.core.domain.repository.InstagramDuplicateRepository
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.components.ApplicationComponent
import dagger.hilt.android.qualifiers.ApplicationContext
import javax.inject.Singleton

@Module
@InstallIn(ApplicationComponent::class)
object CoreModule {
    @Singleton
    @Provides
    fun providesInstagramDuplicateDatabase(@ApplicationContext app: Context) =
        Room.databaseBuilder(app, InstagramDuplicateDatabase::class.java, "Instagram Duplicate")
            .build()

    @Singleton
    @Provides
    fun providesInstagramDuplicateDao(database: InstagramDuplicateDatabase) =
        database.getInstagramDuplicateDao()

    @Singleton
    @Provides
    fun providesInstagramDuplicateRepository(dao: InstagramDuplicateDao): InstagramDuplicateRepository =
        InstagramDuplicateRepositoryImpl(dao)
}