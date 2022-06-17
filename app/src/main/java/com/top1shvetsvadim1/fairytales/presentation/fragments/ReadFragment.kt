package com.top1shvetsvadim1.fairytales.presentation.fragments

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.squareup.picasso.Picasso
import com.top1shvetsvadim1.fairytales.R
import com.top1shvetsvadim1.fairytales.databinding.FragmentDetailBinding
import com.top1shvetsvadim1.fairytales.databinding.FragmentReadBinding
import java.lang.RuntimeException

class ReadFragment : Fragment() {


    private var _binding: FragmentReadBinding? = null
    private val binding: FragmentReadBinding
        get() = _binding ?: throw RuntimeException("FragmentCatalogBinding == null")


    private lateinit var text : String
    private lateinit var name : String
    private lateinit var imageUrl : String

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        name = arguments?.getString(KEY_NAMEBOCK, "") ?: ""
        text = arguments?.getString(KEY_TEXT, "") ?: ""
        imageUrl = arguments?.getString(KEY_IMAGE_URL, "") ?: ""
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentReadBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding.tvNameBock.text = name
        binding.tvText.text = text.replace("+", "\n\t- \t")
        binding.innerToolbarMenu.title = name
        Picasso.get().load(imageUrl).into(binding.ivLogo)

        binding.innerToolbarMenu.setNavigationOnClickListener {
            requireActivity().onBackPressed()
        }
    }

    companion object {

        const val KEY_TEXT = "text"
        const val KEY_NAMEBOCK = "namebock"
        const val KEY_IMAGE_URL = "imageUrl"

        @JvmStatic
        fun newInstance(text: String, name: String, imageUrl : String) =
            ReadFragment().apply {
                arguments = Bundle().apply {
                    putString(KEY_TEXT, text)
                    putString(KEY_NAMEBOCK, name)
                    putString(KEY_IMAGE_URL, imageUrl)
                }
            }
    }
}