package com.example.prototype

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.lifecycleScope
import androidx.viewpager2.widget.CompositePageTransformer
import androidx.viewpager2.widget.MarginPageTransformer
import com.example.prototype.databinding.ActivityMainBinding
import kotlinx.coroutines.launch

class MainActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMainBinding
    private lateinit var adapter: SliderAdapter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        adapter = SliderAdapter(emptyList())
        binding.viewPager.adapter = adapter

        setupViewPager()
        fetchData()
    }

    private fun setupViewPager() {
        binding.viewPager.clipToPadding = false
        binding.viewPager.clipChildren = false
        binding.viewPager.offscreenPageLimit = 3

        val transformer = CompositePageTransformer()
        transformer.addTransformer(MarginPageTransformer(40))
        transformer.addTransformer { page, position ->
            page.scaleY = 0.85f + (1 - kotlin.math.abs(position)) * 0.15f
        }

        binding.viewPager.setPageTransformer(transformer)
    }

    private fun fetchData() {
        lifecycleScope.launch {
            try {
                val response = RetrofitInstance.api.getSliderData()
                adapter.updateData(response)
            } catch (e: Exception) {
                e.printStackTrace()
            }
        }
    }
}