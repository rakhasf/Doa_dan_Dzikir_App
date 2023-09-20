package com.israfel.doadandzikirapp.presentation

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.israfel.doadandzikirapp.R
import com.israfel.doadandzikirapp.databinding.ActivityDetailArticleBinding

class DetailArticleActivity : AppCompatActivity() {
    private var _binding: ActivityDetailArticleBinding? = null
    private val binding get() = _binding as ActivityDetailArticleBinding


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        _binding = ActivityDetailArticleBinding.inflate(layoutInflater)
        setContentView(R.layout.activity_detail_article)

        val titleArticle = intent.getStringExtra("title")
        val contentArticle = intent.getStringExtra("content")
        val imageArticle = intent.getIntExtra("image", 1)

        binding.apply {
            tvDetailTitle.text = titleArticle
            tvDetailContent.text = contentArticle
            imgDetailArticle.setImageResource(imageArticle)
        }
    }

    // object used in this activity which can access by the other classes
    companion object {
        // constant for save KEY of data transaction
        const val EXTRA_DATA_TITLE = "title"
        const val EXTRA_DATA_CONTENT = "content"
        const val EXTRA_DATA_IMAGE = "image"
    }
}