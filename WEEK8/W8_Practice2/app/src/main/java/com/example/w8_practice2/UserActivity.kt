package com.example.w8_practice2

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.TextView
import com.example.w8_practice2.databinding.ActivityMainBinding
import com.example.w8_practice2.databinding.ActivityUserBinding
import java.util.logging.Logger

class UserActivity : AppCompatActivity() {
    lateinit var binding2: ActivityUserBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding2 = ActivityUserBinding.inflate(layoutInflater)
        var userName = binding2.setName
        var age = binding2.setAge
        var phoneNum = binding2.setNum
        var moveBtn = binding2.moveBtn
        setContentView(binding2.root)

        val user = intent.getSerializableExtra("userInfo") as User
        userName.text = user.name
        age.text = user.age
        phoneNum.text = user.phoneNumber

        moveBtn.setOnClickListener {
            finish()
        }
    }
}