package com.batzalcancia.core.data.local.database

import androidx.room.Database
import androidx.room.RoomDatabase
import com.batzalcancia.core.data.local.dao.InstagramDuplicateDao
import com.batzalcancia.core.domain.local.entities.InstagramPicture

@Database(entities = [InstagramPicture::class], version = 1)
abstract class InstagramDuplicateDatabase : RoomDatabase() {
    abstract fun getInstagramDuplicateDao(): InstagramDuplicateDao

}