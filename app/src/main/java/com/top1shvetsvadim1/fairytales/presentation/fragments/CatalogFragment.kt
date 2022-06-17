package com.top1shvetsvadim1.fairytales.presentation.fragments

import android.content.Intent
import android.graphics.PorterDuff
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.core.content.ContextCompat
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import com.top1shvetsvadim1.fairytales.R
import com.top1shvetsvadim1.fairytales.databinding.FragmentCatalogBinding
import com.top1shvetsvadim1.fairytales.domain.detailDomain.DetailItem
import com.top1shvetsvadim1.fairytales.presentation.activity.DetailActivity
import com.top1shvetsvadim1.fairytales.presentation.adapters.AudioBookAdapter
import com.top1shvetsvadim1.fairytales.presentation.adapters.CatalogAdapter
import com.top1shvetsvadim1.fairytales.presentation.state.Loading
import com.top1shvetsvadim1.fairytales.presentation.viewModel.CatalogViewModel
import java.lang.RuntimeException


class CatalogFragment : Fragment() {


    private var _binding: FragmentCatalogBinding? = null
    private val binding: FragmentCatalogBinding
        get() = _binding ?: throw RuntimeException("FragmentCatalogBinding == null")


    private lateinit var listAdapterCatalog: CatalogAdapter
    private lateinit var listAdapterAudioBook: AudioBookAdapter

    private val viewModel by lazy {
        ViewModelProvider(this)[CatalogViewModel::class.java]
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentCatalogBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        setupRecyclerView()
        bindingListener()
        viewModelMethods()
        viewModelObserve()
    }

    private fun viewModelMethods() {
        viewModel.getChoiceList()
        viewModel.getAudioList()
    }

    private fun bindingListener() {
        binding.buttonHome.apply {
            setImageResource(R.drawable.ic_button_home_active)
            setColorFilter(
                ContextCompat.getColor(context, R.color.purple_700),
                PorterDuff.Mode.SRC_IN
            )
        }
        listAdapterAudioBook.onAudioBookClickListener =
            object : AudioBookAdapter.OnAudioBookClickListener {
                override fun onItemClick(detail: DetailItem) {
                    Toast.makeText(
                        context,
                        detail.name,
                        Toast.LENGTH_SHORT
                    ).show()

                    Intent(context, DetailActivity::class.java).apply {
                        arguments = Bundle().apply {
                            putParcelable(KEY_DETAIL, detail)
                        }
                        putExtra(KEY_ARGUMENTS, arguments)
                        startActivity(this)
                    }
                }
            }
    }

    private fun viewModelObserve() {
        viewModel.choiceList.observe(viewLifecycleOwner) {
            Log.d("TEST", it.toString())
            listAdapterCatalog.submitList(it)
        }
        viewModel.audioBookList.observe(viewLifecycleOwner) {
            listAdapterAudioBook.submitList(it)
        }
        viewModel.state.observe(viewLifecycleOwner) {
            when (it) {
                is Loading -> {
                    if (it.isLoad) {
                        binding.progressBarLoad.visibility = View.VISIBLE
                    }
                    if (!it.isLoad) {
                        binding.progressBarLoad.visibility = View.GONE
                    }
                }
            }
        }
    }


    private fun setupRecyclerView() {
        with(binding.rvCatalogList) {
            listAdapterCatalog = CatalogAdapter(requireContext())
            adapter = listAdapterCatalog
        }
        with(binding.rvCatalogAudioBook) {
            listAdapterAudioBook = AudioBookAdapter()
            adapter = listAdapterAudioBook
        }
    }


    companion object {

        const val KEY_ARGUMENTS = "arguments"
        const val KEY_DETAIL = "detail"
        @JvmStatic
        fun newInstance() = CatalogFragment()

    }
}