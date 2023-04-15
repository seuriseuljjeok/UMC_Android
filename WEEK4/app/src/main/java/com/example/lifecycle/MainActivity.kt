package com.example.lifecycle

import android.annotation.SuppressLint
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.View
import android.widget.Button
import com.example.lifecycle.databinding.ActivityMainBinding


class MainActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMainBinding
    @SuppressLint("MissingInflatedId")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        var result = binding.root
        setContentView(result)

        binding.btnNext.setOnClickListener {
            var intent = Intent(this, SecondActivity::class.java)
            intent.putExtra("message","This is Main Message")
            startActivity(intent)
        }

        Log.i("act", "Main-onCreate()")
    }//onCreate end

    override fun onStart() {
        super.onStart()
        Log.i("act", "Main-onStart()")
    }

    override fun onResume() {
        super.onResume()
        Log.i("act", "Main-onResume()")
    }

    override fun onPause() {
        super.onPause()
        Log.i("act", "Main-onPause()")
    }

    override fun onStop() {
        super.onStop()
        Log.i("act", "Main-onStop()")
    }

    override fun onRestart() {
        super.onRestart()
        Log.i("act", "Main-onRestart()")
    }

    override fun onDestroy() {
        super.onDestroy()
        Log.i("act", "Main-onDestroy()")
    }
}