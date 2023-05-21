package com.example.database

import androidx.room.Entity
import androidx.room.PrimaryKey
import java.io.Serializable

@Entity
data class Memo(
    var memo: String = "",
    var like: Boolean = false,
    var time: String = ""
): Serializable
{
    @PrimaryKey(autoGenerate = true)
    var id: Int = 0 //하나의 사용자에 대한 고유 ID 값

    override fun toString(): String {
        return "id = $id | memo = $memo | like = $like | time = $time"
    }
}