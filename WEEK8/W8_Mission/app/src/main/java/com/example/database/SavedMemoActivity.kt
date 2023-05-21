package com.example.database

import android.os.Bundle
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import com.example.database.databinding.ActivityMemoSavedBinding


class SavedMemoActivity : AppCompatActivity() {
    private lateinit var binding: ActivityMemoSavedBinding
    lateinit var memo: TextView

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMemoSavedBinding.inflate(layoutInflater)
        setContentView(binding.root)

        memo = binding.memo

        //Memo activity에서 intent로 넘겨준 값을 intent를 통해 받아옴
        //문자열로 넣어주었기 때문에 getStringExtra(키 값)를 통해 꺼내옴
        var value = intent.getStringExtra("memo")
        memo.text = value //memo activity에 꺼내온 값 세팅
    }
}