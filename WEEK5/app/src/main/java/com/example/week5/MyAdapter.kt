package com.example.week5

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import org.w3c.dom.Text

class MyAdapter(val DataclassList: ArrayList<DataClass>) :
    RecyclerView.Adapter<MyAdapter.CustomViewHolder>() {

        override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): CustomViewHolder {
            val view = LayoutInflater.from(parent.context).inflate(R.layout.item_list, parent, false)
            return CustomViewHolder(view)
        }

        override fun onBindViewHolder(holder: MyAdapter.CustomViewHolder, position: Int) {
            holder.images.setImageResource(DataclassList.get(position).images)
            holder.text1.text = DataclassList.get(position).text1
            holder.text2.text = DataclassList.get(position).text2
        }

        override fun getItemCount(): Int {
            return DataclassList.size
        }

        class CustomViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
            var images = itemView.findViewById<ImageView>(R.id.images)
            var text1 = itemView.findViewById<TextView>(R.id.text1)
            var text2 = itemView.findViewById<TextView>(R.id.text2)
        }

}
