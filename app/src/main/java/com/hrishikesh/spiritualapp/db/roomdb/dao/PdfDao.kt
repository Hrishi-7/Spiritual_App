package com.hrishikesh.spiritualapp.db.roomdb.dao

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.hrishikesh.spiritualapp.db.roomdb.PdfEntity

@Dao
interface PdfDao {
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insert(pdfEntity: PdfEntity)

    @Query("SELECT * FROM pdf_table")
    suspend fun getAllEntity() : List<PdfEntity>
}