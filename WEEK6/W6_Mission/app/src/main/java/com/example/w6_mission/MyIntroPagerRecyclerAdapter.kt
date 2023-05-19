package com.example.w6_mission

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.w6_mission.databinding.LayoutIntroPagerItemBinding

//생성자에서 생성할 때 아예 pageList를 만들어서 넣어줌
//:RecyclerView를 사용해 RecyclerView 상속받아 어댑터 사용
class MyIntroPagerRecyclerAdapter(private var pageList: ArrayList<PageItem>)
                                :RecyclerView.Adapter<MyPagerViewHolder>()   {
    //어떤 뷰를 사용할지 반환함.
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MyPagerViewHolder {
        //LayoutInflator을 통해 어떤 뷰를 반환할건지 알려줌. 부모의 context 넣어줌
        val inflater = LayoutInflater.from(parent.context)
        //inflate해서 어떤 xml을 사용할 지 알려줌 => layout_intro_pager_item.xml
        //viewGroup parent로 해주고
        val binding = LayoutIntroPagerItemBinding.inflate(inflater,parent,false)
        return MyPagerViewHolder(binding)
    }

    //얼만큼 보여줄 건지
    override fun getItemCount(): Int {
        return pageList.size
    }

    //하나 하나 넘갈 때마다 호출
    override fun onBindViewHolder(holder: MyPagerViewHolder, position: Int) {
        //데이터와 뷰를 묶는다.
        holder.bindWithView(pageList[position])
    }
}