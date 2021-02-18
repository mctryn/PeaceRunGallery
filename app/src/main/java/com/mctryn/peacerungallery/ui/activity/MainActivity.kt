package com.mctryn.peacerungallery.ui.activity

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.mctryn.peacerungallery.R
import com.mctryn.peacerungallery.databinding.ActivityMainBinding


class MainActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMainBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        setTheme(R.style.Theme_PeaceRunGallery);
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)
    }
}