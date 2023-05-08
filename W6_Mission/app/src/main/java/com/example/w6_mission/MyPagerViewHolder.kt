package com.example.w6_mission

import android.view.View
import android.widget.ImageView
import android.widget.LinearLayout
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.w6_mission.databinding.LayoutIntroPagerItemBinding

//MyPagerViewHolder가 layout_intro_pager_item을 품게 될 것
class MyPagerViewHolder(var binding: LayoutIntroPagerItemBinding): RecyclerView.ViewHolder(binding.root) {
    //layout_intro_pager_item.xml에서 가져옴
    private val itemImage: ImageView = binding.pagerItemImage
    private val itemContent: TextView = binding.pagerItemText
    private val itemBg: LinearLayout = binding.pagerItemBg

    //가져온 데이터와 뷰를 연결시킴
    //데이터 넣어줌
    fun bindWithView(pageItem: PageItem){
        itemImage.setImageResource(pageItem.imageSrc) //이미지 바꿔줌
        itemContent.text = pageItem.content //텍스트 바꿔줌
        itemBg.setBackgroundResource(pageItem.backgroundColor)  //백그라운드 색상 바꿔줌
    }
}