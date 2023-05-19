package com.example.w8_practice2

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.Query
import androidx.room.Update

//Data Access Object
@Dao
interface UserDao {

    @Insert //삽입
    fun insertUser(user: User)

    @Update //수정
    fun updateUser(user: User)

    @Delete //삭제
    fun deleteUser(user: User)

    //쿼리 : 데이터베이스에 요청하는 명령문
    @Query("SELECT * FROM User") //조회 쿼리
    fun getAll(): List<User>

    @Query("DELETE FROM User")
    fun deleteAll()

}