package com.example.week5mission

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.week5mission.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {
    private lateinit var binding: ActivityMainBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        val dataList = arrayListOf(
            DataClass("one", switch = "1"),
            DataClass("two", switch = "2"),
            DataClass("three", switch = "3"),
            DataClass("four", switch = "4"),
            DataClass("five", switch = "5"),
            DataClass("six", switch = "6"),
            DataClass("seven", switch = "7"),
            DataClass("eight", switch = "8"),
            DataClass("nine", switch = "9"),
            DataClass("one", switch = "1"),
            DataClass("two", switch = "2"),
            DataClass("three", switch = "3"),
            DataClass("four", switch = "4"),
            DataClass("five", switch = "5"),
            DataClass("six", switch = "6"),
            DataClass("seven", switch = "7"),
            DataClass("eight", switch = "8"),
            DataClass("nine", switch = "9"),
            DataClass("one", switch = "1"),
            DataClass("two", switch = "2"),
            DataClass("three", switch = "3"),
            DataClass("four", switch = "4"),
            DataClass("five", switch = "5"),
            DataClass("six", switch = "6"),
            DataClass("seven", switch = "7"),
            DataClass("eight", switch = "8"),
            DataClass("nine", switch = "9")
        )

        binding.recyclerView.layoutManager = LinearLayoutManager(this, LinearLayoutManager.VERTICAL, false)
        binding.recyclerView.setHasFixedSize(true)
        binding.recyclerView.adapter=Adapter(dataList)
    }


}