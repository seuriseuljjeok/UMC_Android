package com.example.lifecycle

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.TextView
import com.example.lifecycle.databinding.ActivityCheckBinding

class Check : AppCompatActivity() {
    private lateinit var binding: ActivityCheckBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityCheckBinding.inflate(layoutInflater)
        var view = binding.root
        setContentView(view)

        //Memo activity에서 intent로 넘겨준 값을 intent를 통해 받아옴
        //문자열로 넣어주었기 때문에 getStringExtra(키 값)를 통해 꺼내옴
        var value = intent.getStringExtra("memo")
        binding.getText.text = value //Check activity에 꺼내온 값 세팅
    }
}