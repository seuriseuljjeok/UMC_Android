package com.example.w5_mission2

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.LayoutInflater
import android.widget.EditText
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.w5_mission2.databinding.ActivityMainBinding
import com.example.w5_mission2.databinding.ActivityMemoBinding

class MainActivity : AppCompatActivity() {
    private lateinit var binding: ActivityMainBinding
    private lateinit var binding2: ActivityMemoBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        binding2 = ActivityMemoBinding.inflate(layoutInflater)
        setContentView(binding.root)

        val addBtn = binding.addBtn //addBtn을 받을 변수

        //버튼 비활성화로 초기화
        addBtn.isEnabled = false
        //TextWatcher => 텍스트 변화를 탐지하는 함수. 탐지하면 호출


        binding.recyclerView.layoutManager = LinearLayoutManager(this, LinearLayoutManager.VERTICAL,false)
        binding.recyclerView.setHasFixedSize(true)
        //binding.recyclerView.adapter=MemoAdapter()
    }

}