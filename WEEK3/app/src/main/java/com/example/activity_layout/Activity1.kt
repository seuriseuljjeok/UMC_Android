package com.example.activity_layout

import android.app.Activity
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.Toast
import com.example.activity_layout.databinding.Activity1Binding

class Activity1 : AppCompatActivity() {
    private lateinit var binding: Activity1Binding

    private val REQUEST_CODE = 1

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = Activity1Binding.inflate(layoutInflater)
    //first mission
    //        setContentView(binding.root)
//
//        binding.btnA.setOnClickListener {
//            val text = binding.textView.text.toString()
//            val intent = Intent(this, Activity2::class.java) //다음 화면으로 이동하기 위한 인텐트 객체 생성
//            intent.putExtra("text",text)
//            startActivity(intent) //intent 객체 넣어주기

            setContentView(R.layout.activity_main)

            val button = findViewById<Button>(R.id.btn_a)
            button.setOnClickListener {
                val intent = Intent(this, Activity2::class.java)
                startActivityForResult(intent, REQUEST_CODE)
            }
        }
    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)
        if (requestCode == REQUEST_CODE && resultCode == Activity.RESULT_OK) {
            val result = data?.getStringExtra("result")
            result?.let {
                Toast.makeText(this, it, Toast.LENGTH_SHORT).show()
            }
        }
    }
}