package com.hrishikesh.spiritualapp.db.roomdb.repository

import com.hrishikesh.spiritualapp.db.roomdb.PdfEntity
import com.hrishikesh.spiritualapp.db.roomdb.dao.PdfDao

class PdfRepository(private val pdfDao: PdfDao) {
    suspend fun insert(pdfEntity: PdfEntity){
        pdfDao.insert(pdfEntity)
    }
    suspend fun getAllPdfEntities() : List<PdfEntity>{
        return pdfDao.getAllEntity()
    }
}