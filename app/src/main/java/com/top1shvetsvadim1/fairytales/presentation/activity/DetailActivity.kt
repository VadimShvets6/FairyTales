package com.top1shvetsvadim1.fairytales.presentation.activity

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.top1shvetsvadim1.fairytales.R
import com.top1shvetsvadim1.fairytales.databinding.ActivityDitailBinding
import com.top1shvetsvadim1.fairytales.domain.detailDomain.DetailItem
import com.top1shvetsvadim1.fairytales.presentation.fragments.CatalogFragment.Companion.KEY_ARGUMENTS
import com.top1shvetsvadim1.fairytales.presentation.fragments.CatalogFragment.Companion.KEY_DETAIL
import com.top1shvetsvadim1.fairytales.presentation.fragments.DetailFragment

class DetailActivity : AppCompatActivity() {

    private val binding by lazy {
        ActivityDitailBinding.inflate(layoutInflater)
    }
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(binding.root)

        val arguments = intent.getBundleExtra(KEY_ARGUMENTS)
        if(arguments != null){
            val detail = arguments.getParcelable<DetailItem>(KEY_DETAIL) ?: throw RuntimeException("Arguments not found key")
            supportFragmentManager.beginTransaction()
                .replace(R.id.fragment_container_detail_activity, DetailFragment.newInstance(detail))
                .commit()
        }
    }

}