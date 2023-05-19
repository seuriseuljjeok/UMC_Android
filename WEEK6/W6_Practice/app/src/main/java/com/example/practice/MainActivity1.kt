package com.example.practice

import android.os.Bundle
import android.view.MenuItem
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.FragmentActivity
import com.example.practice.databinding.ActivityMain1Binding
import com.google.android.material.bottomnavigation.BottomNavigationView
import com.google.android.material.tabs.TabLayoutMediator

class MainActivity1 : AppCompatActivity(),  BottomNavigationView.OnNavigationItemSelectedListener{
    //binding 객체 생성
    private lateinit var binding: ActivityMain1Binding

    private lateinit var homeFragment: HomeFragment
    private lateinit var cameraFragment: CameraFragment
    private lateinit var snsFragment: SnsFragment

    //메모리에 올라갔을 때
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        //ActivityMainBinding 레이아웃을 inflate 하고 root view를 사용하여 activity의 content view를 설정
        binding = ActivityMain1Binding.inflate(layoutInflater)

        //가장 처음 표시할 Fragment 설정
        var homeFragment = HomeFragment.newInstance("Home Page","")
        supportFragmentManager.beginTransaction().add(R.id.fragment_home, homeFragment).commit()

        //레이아웃과 연결
        setContentView (binding.root)

        binding.bottomNavigation.setOnItemSelectedListener(this)

        //각 아이템을 클릭했을 때 나타나는 이벤트 설정
        //BottomNavigationView를 참조하기 위해 binding.bottomNavi을 사용하고 있음.
        binding.bottomNavigation.setOnNavigationItemReselectedListener { menuItem ->
            when(menuItem.itemId){
                R.id.camera -> {true}
                R.id.home -> {true}
                R.id.sns -> {true}
                else -> false
            }
        }
//어댑터 객체를 만들어서 뷰페이저 어댑터 속성에 연결
        binding.viewPager2.apply {
            adapter = MyAdapter(context as FragmentActivity)
        }
//뷰바인딩으로 얻어진 아이디를 넣어줌
        TabLayoutMediator(binding.tab, binding.viewPager2){tabs, position ->
            tabs.text = "Title $position"
        }.attach()
    }

    override fun onNavigationItemSelected(item: MenuItem): Boolean {
        return true
    }
}
