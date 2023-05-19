package com.example.week5mission

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.week5mission.databinding.ItemSwitchBinding

//RecyclerView.Adapter 상속하여 구현
class Adapter(val itemList: ArrayList<DataClass>)
    : RecyclerView.Adapter<Adapter.CustomViewHolder>() {
    //viewType 형태의 아아템 뷰를 위한 뷰홀더 객체 생성
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): CustomViewHolder {
        val inflater = LayoutInflater.from(parent.context)
        val binding = ItemSwitchBinding.inflate(inflater, parent, false)
        return CustomViewHolder(binding)
    }

    override fun onBindViewHolder(holder: CustomViewHolder, position: Int) {
        holder.name.text = itemList.get(position).name
        holder.switch.text = itemList.get(position).switch
    }

    override fun getItemCount(): Int {
        return itemList.size
    }

    inner class CustomViewHolder(val binding: ItemSwitchBinding) :
        RecyclerView.ViewHolder(binding.root){
            var name = binding.text
            var switch = binding.s1
    }


}