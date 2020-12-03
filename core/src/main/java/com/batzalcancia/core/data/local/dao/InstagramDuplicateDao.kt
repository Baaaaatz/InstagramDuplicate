package com.batzalcancia.core.data.local.dao

import androidx.paging.PagingSource
import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.batzalcancia.core.domain.local.entities.InstagramPicture

@Dao
interface InstagramDuplicateDao {

    @Query("SELECT * FROM pictures ORDER BY id DESC")
    fun getAllPictures(): PagingSource<Int, InstagramPicture>

    @Insert(onConflict = OnConflictStrategy.ABORT)
    fun insertPicture(picture: InstagramPicture)

}