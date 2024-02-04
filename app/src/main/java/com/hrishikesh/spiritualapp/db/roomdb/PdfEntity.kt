package com.hrishikesh.spiritualapp.db.roomdb

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity("pdf_table")
data class PdfEntity(
    @PrimaryKey(autoGenerate = true)
    val id : Long = 0,
    @ColumnInfo("file_name")
    val fileName : String,
    @ColumnInfo("file_data")
    val fileData : ByteArray
) {
    override fun equals(other: Any?): Boolean {
        if (this === other) return true
        if (javaClass != other?.javaClass) return false

        other as PdfEntity

        if (id != other.id) return false
        if (fileName != other.fileName) return false
        if (!fileData.contentEquals(other.fileData)) return false

        return true
    }

    override fun hashCode(): Int {
        var result = id.hashCode()
        result = 31 * result + fileName.hashCode()
        result = 31 * result + fileData.contentHashCode()
        return result
    }
}
