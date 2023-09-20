package com.israfel.doadandzikirapp.presentation.pagipetang

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.recyclerview.widget.LinearLayoutManager
import com.israfel.doadandzikirapp.DoaDzikirAdapter
import com.israfel.doadandzikirapp.R
import com.israfel.doadandzikirapp.databinding.ActivityDzikirSetiapSaatBinding
import com.israfel.doadandzikirapp.model.DataDoaDzikir
import com.israfel.doadandzikirapp.model.DoaDzikirItem

class DzikirHarianActivity : AppCompatActivity() {
    private var _binding: ActivityDzikirSetiapSaatBinding? = null
    private val binding get() = _binding as ActivityDzikirSetiapSaatBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        supportActionBar?.setDisplayHomeAsUpEnabled(true)

        _binding = ActivityDzikirSetiapSaatBinding.inflate(layoutInflater)
        setContentView(binding.root)

        providingDzikirDates()
        initView()

        val mAdapter = DoaDzikirAdapter()
        mAdapter.setData(DataDoaDzikir.listDzikir())
        binding.rvDzikir.adapter = mAdapter
        binding.rvDzikir.layoutManager = LinearLayoutManager(this)
    }

    private fun initView() {
        val mAdapter = DoaDzikirAdapter()
        mAdapter.setData(providingDzikirDates())
        binding.rvDzikir.adapter = mAdapter
        binding.rvDzikir.layoutManager = LinearLayoutManager(this)
    }

    private fun providingDzikirDates(): List<DoaDzikirItem> {
        // data set of dzikir harian is located in strings.xml
        // we need to get string-array from strings.xml put into a variable
        // resources is a variable from AppCompat which getting access to Resource directory
        // from resources we can call resource directory like as drawable, layout, values (strings, theme. color)
        // so, now the variable titleDzikir containing datas as Array-String arr_dzikir_doa_harian
        val titleDzikir = resources.getStringArray(R.array.arr_dzikir_doa_harian)
        val arabicDzikir = resources.getStringArray(R.array.arr_lafaz_dzikir_doa_harian)
        val translateDzikir = resources.getStringArray(R.array.arr_terjemah_dzikir_doa_harian)

        val listData =  arrayListOf<DoaDzikirItem>()
        for (i in titleDzikir.indices) {
            val data = DoaDzikirItem(
                titleDzikir[i],
                arabicDzikir[i],
                translateDzikir[i]
            )
            listData.add(data)
        }
        return listData
    }

    override fun onSupportNavigateUp(): Boolean {
        onBackPressedDispatcher.onBackPressed()
        finish()
        return super.onSupportNavigateUp()
    }

    override fun onDestroy() {
        super.onDestroy()
        _binding = null
    }
}