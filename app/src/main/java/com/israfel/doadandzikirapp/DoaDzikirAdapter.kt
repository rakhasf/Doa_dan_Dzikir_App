package com.israfel.doadandzikirapp

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.israfel.doadandzikirapp.model.DoaDzikirItem

// Adapter is a subclass from RecyclerView which take responsibility
// for providing views that represent items (layout items) in a data set (kumpulan data).
class DoaDzikirAdapter : RecyclerView.Adapter<DoaDzikirAdapter.DzikirViewHolder>() {

    private val listData = ArrayList<DoaDzikirItem>()

    fun setData(list: List<DoaDzikirItem>) {
        listData.clear()
        listData.addAll(list)
    }

    // ViewHolder take responsibility for initializing item from layout
    // in this class we will describe our view from layout
    inner class DzikirViewHolder(view: View) : RecyclerView.ViewHolder(view){
        val itemTitle = view.findViewById<TextView>(R.id.item_title)
        val itemArabic = view.findViewById<TextView>(R.id.item_arabic)
        val itemTranslate = view.findViewById<TextView>(R.id.item_translate)

    }

    // onCreateViewHolder provides layout to be used by ViewHolder
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int)=DzikirViewHolder(
            // LayoutInflater is a class to inflate a layout/view
            LayoutInflater.from(parent.context).inflate(R.layout.item_doa, parent, false)
    )

    // getItemCount is counting the size of data set will be displayed on RecyclerView
    override fun getItemCount() = listData.size

    // onBindViewHolder distributed data in their position on item view
    override fun onBindViewHolder(holder: DzikirViewHolder, position: Int) {
        holder.apply {
            itemTitle.text = listData[position].title
            itemArabic.text = listData[position].arabic
            itemTranslate.text = listData[position].translate
        }
    }
}