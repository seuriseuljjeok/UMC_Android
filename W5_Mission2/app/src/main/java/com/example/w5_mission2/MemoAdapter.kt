package com.example.w5_mission2

import android.graphics.Color
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.w5_mission2.databinding.MemoListBinding

//RecyclerView.Adapter 상속하여 Adapter 구현
class MemoAdapter(val memoList: ArrayList<Data>)
    : RecyclerView.Adapter<MemoAdapter.MemoViewHolder>() {


    inner class MemoViewHolder(var binding: MemoListBinding) :
    RecyclerView.ViewHolder(binding.root)
    {
        var memo = binding.memo
        var isDone = binding.check
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MemoViewHolder {
        val binding = MemoListBinding.inflate(LayoutInflater.from(parent.context),parent,false)
        return MemoViewHolder(binding)
    }
    override fun onBindViewHolder(holder: MemoViewHolder, position: Int) {
        holder.memo.setText(memoList.get(position).memo)
        if (memoList.get(position).isDone == 0){
            holder.isDone.setBackgroundColor(Color.MAGENTA)
        }else{
            holder.isDone.setBackgroundColor(Color.WHITE)
        }
    }
    override fun getItemCount(): Int {
        return memoList.size
    }

    fun addMemo(memo: Data){
        memoList.add(memo)
    }
    fun removeMemo(position: Int){
        memoList.removeAt(position)
    }
}