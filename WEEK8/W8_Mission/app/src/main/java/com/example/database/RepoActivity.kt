package com.example.database

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.ImageButton
import android.widget.TextView
import android.widget.ToggleButton
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.database.databinding.ActivityMemoSavedBinding
import com.example.database.databinding.ActivityRepoBinding

class RepoActivity : AppCompatActivity() {
    //binding
    private lateinit var binding: ActivityRepoBinding
    private lateinit var binding2: ActivityMemoSavedBinding
    private lateinit var memoDao: MemoDao

    private val starList: MutableList<Memo> = mutableListOf()
    lateinit var memo: TextView
    lateinit var like: ToggleButton
    lateinit var time: TextView

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityRepoBinding.inflate(layoutInflater)
        binding2 = ActivityMemoSavedBinding.inflate(layoutInflater)

        setContentView(binding.root)

        memo = binding2.memo
        like = binding2.like
        time = binding2.time

        //Memo activity에서 intent로 넘겨준 값을 intent를 통해 받아옴
        //객체로 넣어주었기 때문에 getObjectExtra(키 값)를 통해 꺼내옴
        //var value: Memo = intent.getSerializableExtra("starMemoInfo") as Memo

         //memo activity에 꺼내온 값 세팅
        val memoList: MutableList<Memo> = memoDao.getAll()


        //즐겨찾기 데이터 조회 후 리스트에 담아주기
        for (i in 0..memoList.size-1){
            if(memoList.get(i).like){
                starList.add(memoList.get(i))
            }
        }



        //recyclerView
        binding.recyclerView2.layoutManager = LinearLayoutManager(this, LinearLayoutManager.VERTICAL, false)
        binding.recyclerView2.setHasFixedSize(true)
        binding.recyclerView2.adapter=Adapter(starList)

    }
}