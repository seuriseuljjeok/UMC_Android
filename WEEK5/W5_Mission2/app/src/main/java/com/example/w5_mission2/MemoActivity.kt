package com.example.w5_mission2

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.example.w5_mission2.databinding.ActivityMemoBinding

class MemoActivity : AppCompatActivity() {
    private lateinit var binding: ActivityMemoBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMemoBinding.inflate(layoutInflater)
        setContentView(binding.root)


    }
}