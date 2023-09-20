package com.israfel.doadandzikirapp

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.ImageView
import android.widget.LinearLayout
import android.widget.ListAdapter
import android.widget.Toast
import androidx.appcompat.widget.LinearLayoutCompat
import androidx.core.content.ContextCompat
import androidx.core.splashscreen.SplashScreen.Companion.installSplashScreen
import androidx.viewpager2.widget.ViewPager2
import com.israfel.doadandzikirapp.adapter.ArticleAdapter
import com.israfel.doadandzikirapp.databinding.ActivityMainBinding
import com.israfel.doadandzikirapp.model.ArticleItem
import com.israfel.doadandzikirapp.presentation.DetailArticleActivity
import com.israfel.doadandzikirapp.presentation.pagipetang.DzikirHarianActivity
import com.israfel.doadandzikirapp.presentation.pagipetang.DzikirSetiapSaatActivity
import com.israfel.doadandzikirapp.presentation.PagiPetangActivity
import com.israfel.doadandzikirapp.presentation.pagipetang.QauliyahShalatActivity
import com.israfel.doadandzikirapp.utils.OnItemCallback

class MainActivity : AppCompatActivity(), View.OnClickListener {
    private var _binding: ActivityMainBinding? = null
    private val binding get() = _binding as ActivityMainBinding

    private var dotSliderIndicator = arrayOf<ImageView?>()
    private val slidingCallback = object : ViewPager2.OnPageChangeCallback() {
        override fun onPageSelected(position: Int) {
            super.onPageSelected(position)
            Toast.makeText(this@MainActivity, "Page $position", Toast.LENGTH_SHORT).show()
            for (i in initData().indices) {
                dotSliderIndicator[i]?.setImageDrawable(
                    ContextCompat.getDrawable(applicationContext, R.drawable.inactive_dot)
                )
            }

            dotSliderIndicator[0]?.setImageDrawable(
                ContextCompat.getDrawable(applicationContext, R.drawable.active_dot)
            )
        }
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        // this method is from dependencies Splash Screen API 12
        installSplashScreen()
        initData()
        initView()
        setupViewPager()

    }

    private fun initView() {
        // setContentView is use to choose and display layout in activity
        _binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        // declare variable to get in touch with view in layout activity_main
        // use findViewById to communicate with view
        val cardQauliyahShalat = findViewById<LinearLayout>(R.id.ll_dzikir_doa_shalat)
        val cardDzikir = findViewById<LinearLayout>(R.id.ll_dzikir_setiap_saat)
        val cardDzikirHarian = findViewById<LinearLayout>(R.id.ll_dzikir_doa_harian)
        val cardPagiPetang = findViewById<LinearLayout>(R.id.ll_dzikir_pagi_petang)

        // when view clicked it will be navigate to other page
        // Intent is used for navigate to other activity by clicking cardView
        cardQauliyahShalat.setOnClickListener{
            // Intent(argument context which activity you want to go)
            val intent = Intent(this, QauliyahShalatActivity::class.java)
            // start to go destination activity
            startActivity(intent)
        }

        cardDzikir.setOnClickListener (this)
        cardDzikirHarian.setOnClickListener(this)
        cardPagiPetang.setOnClickListener(this)

        val mAdapter = ArticleAdapter()
        mAdapter.setData(initData())
        mAdapter.setOnItemClickCallback(object : OnItemCallback{
            override fun onItemCallBack(item: ArticleItem) {
                // navigate to DetailActivity
                val intent = Intent(applicationContext, DetailArticleActivity::class.java)
                // navigate to DetailActivity with datas using putExtra
                intent.putExtra(DetailArticleActivity.EXTRA_DATA_TITLE, item.titleArticle)
                intent.putExtra(DetailArticleActivity.EXTRA_DATA_CONTENT, item.contentArticle)
                intent.putExtra(DetailArticleActivity.EXTRA_DATA_IMAGE, item.imageArticle)
                startActivity(intent)
            }
        })
        binding.vpArticle.adapter = mAdapter
        binding.vpArticle.registerOnPageChangeCallback(slidingCallback)
    }

    private fun setupViewPager() {
        dotSliderIndicator = arrayOfNulls(initData().size)

        for (i in initData().indices) {
            dotSliderIndicator[i] = ImageView(this)
            dotSliderIndicator[i]?.setImageDrawable(
                ContextCompat.getDrawable(this, R.drawable.inactive_dot)
            )

            val params = LinearLayoutCompat.LayoutParams(35, 35)
            params.marginStart = 8
            params.marginEnd = 8
            binding.dots.addView(dotSliderIndicator[i], params)
        }
    }

    private fun initData() : List<ArticleItem> {
        val titleArticle = resources.getStringArray(R.array.arr_title_artikel)
        val contentArticle = resources.getStringArray(R.array.arr_desc_artikel)
        val imageArticle = resources.obtainTypedArray(R.array.arr_img_artikel)

        val listData = arrayListOf<ArticleItem>()
        for (i in titleArticle.indices) {
            val data = ArticleItem(
                titleArticle[i],
                imageArticle.getResourceId(i, 0),
                contentArticle[i]
            )
            listData.add(data)
        }
        imageArticle.recycle()
        return listData
    }

    override fun onClick(view: View?) {
        when(view?.id) {
            R.id.rv_qauliyah_shalat -> startActivity(Intent(this, DzikirSetiapSaatActivity::class.java))
            R.id.ll_dzikir_setiap_saat -> startActivity(Intent(this, DzikirHarianActivity::class.java))
            R.id.ll_dzikir_pagi_petang -> startActivity(Intent(this, PagiPetangActivity::class.java))
        }
    }

    override fun onDestroy() {
        super.onDestroy()
        _binding = null
    }
}