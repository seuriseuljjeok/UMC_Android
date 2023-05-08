package com.example.w6_mission

import android.os.Bundle
import android.util.Log
import com.google.android.material.bottomnavigation.BottomNavigationView
import androidx.appcompat.app.AppCompatActivity
import androidx.navigation.findNavController
import androidx.navigation.ui.AppBarConfiguration
import androidx.navigation.ui.setupActionBarWithNavController
import androidx.navigation.ui.setupWithNavController
import androidx.viewpager2.widget.ViewPager2
import com.example.w6_mission.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {

    //상수 처리해서 로그 찍기
    companion object{
        const val  TAG: String = "로그"
    }

    //데이터 배열 선언
    private var pageItemList = ArrayList<PageItem>()
    //어댑터 선언
    private lateinit var myIntroPagerRecyclerAdapter: MyIntroPagerRecyclerAdapter

    private lateinit var binding: ActivityMainBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        Log.d(TAG, "mainActivity - onCreate() called")

        //선언한 데이터 배열 준비 => 배열에 추가
        pageItemList.add(PageItem(R.color.one, R.drawable.one, "In 연남동"))
        pageItemList.add(PageItem(R.color.two, R.drawable.two, "In 동교동"))
        pageItemList.add(PageItem(R.color.three, R.drawable.three, "In 서교동"))

        //어댑터 인스턴스 생성 => 즉, 메모리에 올리는 것
        //준비한 데이터 배열을 넣어줌
        myIntroPagerRecyclerAdapter = MyIntroPagerRecyclerAdapter(pageItemList)

        //viewPager2에 접근해서 apply를 통해 적용
        binding.myIntroViewPager.apply {
            adapter = myIntroPagerRecyclerAdapter   //viewPager2에 대한 어댑터 설정
            orientation = ViewPager2.ORIENTATION_HORIZONTAL //viewPager2를 수평으로 작동하도록 설정
        }

        binding.circleIndicator.setViewPager(binding.myIntroViewPager)
        binding.circleIndicator.createIndicators(3,0)

        //PanelViewpager가 변경될 때마다 우리가 알 수 있도록 Calback호출 ->여기서 indicator 위치 바꿔줌
        binding.myIntroViewPager.registerOnPageChangeCallback(object : ViewPager2.OnPageChangeCallback(){
            override fun onPageSelected(position: Int) {
                super.onPageSelected(position)
                binding.circleIndicator.animatePageSelected(position)
            }
        })
//        val navView: BottomNavigationView = binding.navView
//
//        val navController = findNavController(R.id.nav_host_fragment_activity_main)
//        // Passing each menu ID as a set of Ids because each
//        // menu should be considered as top level destinations.
//        val appBarConfiguration = AppBarConfiguration(
//            setOf(
//                R.id.navigation_home, R.id.navigation_dashboard, R.id.navigation_notifications
//            )
//        )
//        setupActionBarWithNavController(navController, appBarConfiguration)
//        navView.setupWithNavController(navController)
    }
}