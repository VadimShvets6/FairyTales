package com.top1shvetsvadim1.fairytales.presentation.fragments

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.view.WindowManager
import com.top1shvetsvadim1.fairytales.R
import com.top1shvetsvadim1.fairytales.databinding.FragmentCatalogBinding
import com.top1shvetsvadim1.fairytales.databinding.FragmentWelcomeBinding
import com.top1shvetsvadim1.fairytales.presentation.adapters.CatalogAdapter
import java.lang.RuntimeException

class WelcomeFragment : Fragment() {

    private var _binding: FragmentWelcomeBinding? = null
    private val binding: FragmentWelcomeBinding
        get() = _binding ?: throw RuntimeException("FragmentCatalogBinding == null")

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentWelcomeBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        launchCatalogFragment()
    }


    private fun launchCatalogFragment() {
        binding.buttonStart.setOnClickListener {
            requireActivity().supportFragmentManager.beginTransaction()
                .replace(R.id.fragment_container, CatalogFragment.newInstance())
                .commit()
        }
    }


    companion object {

        @JvmStatic
        fun newInstance() = WelcomeFragment()
    }
}