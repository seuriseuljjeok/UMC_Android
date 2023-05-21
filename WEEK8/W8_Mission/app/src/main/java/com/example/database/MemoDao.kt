package com.example.database

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.Query
import androidx.room.Update

//Data Access Object
@Dao
interface MemoDao {
    @Insert
    fun insertMemo(memo: Memo)

    @Update
    fun updateMemo(memo: Memo)

    @Delete
    fun deleteMemo(memo: Memo)

    //쿼리 : 데이터베이스에 요청하는 명령문
    @Query("SELECT * From Memo ORDER BY id ASC") //조회 쿼리
    fun getAll(): MutableList<Memo>

    @Query("DELETE From Memo")
    fun deleteAll()
}