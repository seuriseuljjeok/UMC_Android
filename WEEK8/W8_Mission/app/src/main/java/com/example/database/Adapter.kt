package com.example.database

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.appcompat.view.menu.MenuView.ItemView
import androidx.recyclerview.widget.RecyclerView
import com.example.database.databinding.ActivityMemoSavedBinding

//RecyclerView.Adapter를 상속하여 구현
class Adapter(private val memoList: MutableList<Memo>): RecyclerView.Adapter<Adapter.CustomViewHolder>() {


    class CustomViewHolder(var binding: ActivityMemoSavedBinding): RecyclerView.ViewHolder(binding.root){
        var memo = binding.memo
        var like = binding.like
        var time = binding.time
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): CustomViewHolder {
        //viewType 형태의 아아템 뷰를 위한 뷰홀더 객체 생성
        //savedmemo 레이아웃을 연결
        val inflater = LayoutInflater.from(parent.context)
        val binding = ActivityMemoSavedBinding.inflate(inflater, parent,false)
        return CustomViewHolder(binding)
    }

    override fun getItemCount(): Int {
        return memoList.size
    }

    override fun onBindViewHolder(holder: CustomViewHolder, position: Int) {
        //UI와 전달 받은 memoList에 데이터베이스 데이터를 연결
        val currentMemo = memoList[position]
        holder.memo.text = currentMemo.memo
        holder.like.isPressed = currentMemo.like
        holder.time.text = currentMemo.time

    }
}