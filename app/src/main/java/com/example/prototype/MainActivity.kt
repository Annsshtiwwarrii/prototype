package com.example.prototype

import android.os.Bundle
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import androidx.lifecycle.lifecycleScope
import androidx.viewpager2.widget.CompositePageTransformer
import androidx.viewpager2.widget.MarginPageTransformer
import com.example.prototype.databinding.ActivityMainBinding
import kotlinx.coroutines.launch

class MainActivity : AppCompatActivity() {
    private lateinit var binding: ActivityMainBinding
    private lateinit var adapter: Slideradapter
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)
        adapter = Slideradapter(emptyList())
        binding.viewPager.adapter = adapter
        setupViewpager()
        fetchData()

    }

    private fun setupViewpager() {
        binding.viewPager.clipToPadding = false
        binding.viewPager.clipChildren = false
        binding.viewPager.offscreenPageLimit = 3
        val transform = CompositePageTransformer()
        transform.addTransformer(MarginPageTransformer(40))
        transform.addTransformer {page, position ->
            page.scaleY = 0.85f

        }
        binding.viewPager.setPageTransformer(transform)
    }
    private fun fetchData(){
        lifecycleScope.launch {
            try {
                val response = RetrofitInstance.api.getSliderData()
                adapter.updateData(response)
            }catch (e: Exception){
                e.printStackTrace()
            }

        }
    }
}