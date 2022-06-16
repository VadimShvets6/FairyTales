package com.top1shvetsvadim1.fairytales.presentation.activity

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.WindowManager
import com.top1shvetsvadim1.fairytales.R
import com.top1shvetsvadim1.fairytales.databinding.ActivityMainBinding
import com.top1shvetsvadim1.fairytales.presentation.fragments.WelcomeFragment

class MainActivity : AppCompatActivity() {

    private val binding by lazy {
        ActivityMainBinding.inflate(layoutInflater)
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(binding.root)

        supportFragmentManager.beginTransaction()
            .replace(R.id.fragment_container, WelcomeFragment.newInstance())
            .commit()
    }

}