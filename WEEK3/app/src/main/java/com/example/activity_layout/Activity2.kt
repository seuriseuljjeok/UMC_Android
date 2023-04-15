package com.example.activity_layout

import android.app.Activity
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import com.example.activity_layout.databinding.Activity2Binding

class Activity2 : AppCompatActivity() {
    private lateinit var binding: Activity2Binding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        //first mission
//        binding = Activity2Binding.inflate(layoutInflater)
//        setContentView(binding.root)
//
//        val text = intent.getStringExtra("text")
//        binding.textView.text = text
        setContentView(R.layout.activity_2)

        val button = findViewById<Button>(R.id.btn_b)
        button.setOnClickListener {
            val rIntent = Intent()
            rIntent.putExtra("result", "Back")
            setResult(Activity.RESULT_OK, rIntent)
            finish()
        }
    }
}