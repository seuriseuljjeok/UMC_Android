package com.example.practice

import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentActivity
import androidx.viewpager2.adapter.FragmentStateAdapter

class MyAdapter (fa: FragmentActivity) : FragmentStateAdapter(fa) {
    private val NUM_PAGES = 3

    override fun getItemCount(): Int = NUM_PAGES

    override fun createFragment(position: Int): Fragment {
        return when (position) {
            0 -> {CameraFragment.newInstance("Camera Page","")}
            1 -> {CameraFragment.newInstance("Home Page","")}
            else -> {CameraFragment.newInstance("Sns Page","")}
        }
    }
}