package com.batzalcancia.core.domain.local.entities

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey
import kotlinx.serialization.Serializable

@Entity(tableName = "pictures")
@Serializable
data class InstagramPicture(
    @PrimaryKey(autoGenerate = true)
    val id: Int = 0,
    val caption: String,
    @ColumnInfo(typeAffinity = ColumnInfo.BLOB)
    val picture: ByteArray,
    val createdAt: Long
) {
    override fun equals(other: Any?): Boolean {
        if (this === other) return true
        if (javaClass != other?.javaClass) return false

        other as InstagramPicture

        if (id != other.id) return false
        if (caption != other.caption) return false
        if (!picture.contentEquals(other.picture)) return false
        if (createdAt != other.createdAt) return false

        return true
    }

    override fun hashCode(): Int {
        var result = id
        result = 31 * result + caption.hashCode()
        result = 31 * result + picture.contentHashCode()
        result = 31 * result + createdAt.hashCode()
        return result
    }
}
