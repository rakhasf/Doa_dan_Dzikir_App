package com.israfel.doadandzikirapp.adapter

import android.content.Intent
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.israfel.doadandzikirapp.databinding.ItemArticleBinding
import com.israfel.doadandzikirapp.model.ArticleItem
import com.israfel.doadandzikirapp.presentation.DetailArticleActivity
import com.israfel.doadandzikirapp.utils.OnItemCallback

class ArticleAdapter : RecyclerView.Adapter<ArticleAdapter.ArticleViewHolder>() {
    // variable to store dataset
    private var listArticle = ArrayList<ArticleItem>()
    // variable to give event click of item in activity through setOnItemClickCallback
    private var onItemCallback: OnItemCallback? = null

    fun setData(list: List<ArticleItem>){
        listArticle.clear()
        listArticle.addAll(list)
    }

    fun  setOnItemClickCallback(onItemCallback: OnItemCallback) {
        this.onItemCallback = onItemCallback
    }

    inner class ArticleViewHolder(val view: ItemArticleBinding) : RecyclerView.ViewHolder(view.root)

    override fun  onCreateViewHolder(parent: ViewGroup, viewType: Int) = ArticleViewHolder(
        ItemArticleBinding.inflate(LayoutInflater.from(parent.context), parent, false)
    )

    override fun getItemCount() = listArticle.size

    override fun onBindViewHolder(holder: ArticleViewHolder, position: Int) {
        val data = listArticle[position]
        holder.view.itemArticle.setImageResource(data.imageArticle)
        // this give click event for each item in ViewPager
        holder.itemView.setOnClickListener {
            // set event click in activity through interface
            onItemCallback?.onItemCallBack(data)

//            it.context.apply {
//                // navigate to DetailActivity
//                val intent = Intent(this, DetailArticleActivity::class.java)
//                // navigate to DetailActivity with datas using putExtra
//                intent.putExtra(DetailArticleActivity.EXTRA_DATA_TITLE, data.titleArticle)
//                intent.putExtra(DetailArticleActivity.EXTRA_DATA_CONTENT, data.contentArticle)
//                intent.putExtra(DetailArticleActivity.EXTRA_DATA_IMAGE, data.imageArticle)
//                startActivity(intent)
//            }
        }
    }
}