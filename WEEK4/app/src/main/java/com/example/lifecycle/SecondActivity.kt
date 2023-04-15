package com.example.lifecycle

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.View
import android.widget.Button
import com.example.lifecycle.databinding.ActivitySecondBinding

class SecondActivity : AppCompatActivity() {
    private lateinit var binding: ActivitySecondBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivitySecondBinding.inflate(layoutInflater)
        var result = binding.root
        setContentView(result)

        val intent2 = intent.extras
        val message = intent2!!["message"] as String

        binding.secondText.text = message

        Log.i("act", "Second-onCreate()")
    }//onCreate end

    override fun onStart() {
        super.onStart()
        Log.i("act", "Second-onStart()")
    }

    override fun onResume() {
        super.onResume()
        Log.i("act", "Second-onResume()")
    }

    override fun onPause() {
        super.onPause()
        Log.i("act", "Second-onPause()")
    }

    override fun onStop() {
        super.onStop()
        Log.i("act", "Second-onStop()")
    }

    override fun onRestart() {
        super.onRestart()
        Log.i("act", "Second-onRestart()")
    }

    override fun onDestroy() {
        super.onDestroy()
        Log.i("act", "Second-onDestroy()")
    }
}
