package com.example.week5

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.RecyclerView

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val DataclassList = arrayListOf(
            DataClass(R.drawable.ic_launcher_background,"RecyclerView","1"),
            DataClass(R.drawable.ic_launcher_background,"RecyclerView","2"),
            DataClass(R.drawable.ic_launcher_background,"RecyclerView","3"),
            DataClass(R.drawable.ic_launcher_background,"RecyclerView","4"),
            DataClass(R.drawable.ic_launcher_background,"RecyclerView","5")
        )

        var recycler = findViewById<RecyclerView>(R.id.recyclerView)

        recycler.setLayoutManager(GridLayoutManager(this,3))
        recycler.adapter= MyAdapter(DataclassList)
    }
}