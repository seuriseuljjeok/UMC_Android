package com.example.activity_layout

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import androidx.fragment.app.Fragment
import com.example.activity_layout.databinding.Activity3Binding

class Activity3 : AppCompatActivity(), ActFragment.OnDataPass {
    private lateinit var binding: Activity3Binding
    private lateinit var fragment1: Fragment
    private lateinit var fragment2: Fragment

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_3)

        val fragment1 = ActFragment()

        supportFragmentManager.beginTransaction()
            .replace(R.id.container, fragment1)
            .commit()

        fragment1.dataPasser = this

    //second mission
    //        binding = Activity3Binding.inflate(layoutInflater)
//        setContentView(binding.root)
//
//        fragment1 = Fragment1()
//        fragment2 = Fragment2()
//
//        supportFragmentManager.beginTransaction()
//            .add(R.id.container, fragment1)
//            .add(R.id.container, fragment2)
//            .hide(fragment2)
//            .commit()
//
//        binding.btnFragment1.setOnClickListener {
//            supportFragmentManager.beginTransaction()
//                .show(fragment1)
//                .hide(fragment2)
//                .commit()
//        }
//
//        binding.btnFragment2.setOnClickListener {
//            supportFragmentManager.beginTransaction()
//                .show(fragment2)
//                .hide(fragment1)
//                .commit()
//        }
    }

    override fun onDataPass(data: String) {
        Toast.makeText(this, data, Toast.LENGTH_SHORT).show()
    }
}