package com.example.w8_practice2

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey
import java.io.Serializable

@Entity
data class User(
    var name: String="",
    var age: String="",
    var phoneNumber: String=""
    ): Serializable
{
    @PrimaryKey(autoGenerate = true)
    var id: Int = 0 //하나의 사용자에 대한 고유 ID 값

    override fun toString(): String {
        return "id = $id | name = $name | age = $age | phoneNumber = $phoneNumber"
    }
}


