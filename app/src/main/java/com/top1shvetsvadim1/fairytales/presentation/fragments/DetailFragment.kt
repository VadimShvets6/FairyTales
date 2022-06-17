package com.top1shvetsvadim1.fairytales.presentation.fragments

import android.graphics.PorterDuff
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.core.content.ContextCompat
import com.squareup.picasso.Picasso
import com.top1shvetsvadim1.fairytales.R
import com.top1shvetsvadim1.fairytales.databinding.FragmentDetailBinding
import com.top1shvetsvadim1.fairytales.domain.detailDomain.DetailItem
import com.top1shvetsvadim1.fairytales.presentation.fragments.CatalogFragment.Companion.KEY_DETAIL
import jp.wasabeef.picasso.transformations.BlurTransformation
import java.lang.RuntimeException

class DetailFragment : Fragment() {

    private var _binding: FragmentDetailBinding? = null
    private val binding: FragmentDetailBinding
        get() = _binding ?: throw RuntimeException("FragmentCatalogBinding == null")

    private lateinit var detail: DetailItem
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        requireArguments().getParcelable<DetailItem>(KEY_DETAIL)?.let {
            detail = it
        }
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentDetailBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        setupViewElemnts()
        setupLisnteners()

        binding.buttonHome.apply {
            setImageResource(R.drawable.ic_button_home_active)
            setColorFilter(
                ContextCompat.getColor(context, R.color.purple_700),
                PorterDuff.Mode.SRC_IN
            )
        }
    }

    private fun setupLisnteners() {
        binding.buttonFavorite.setOnClickListener {
            isFarourite = if (!isFarourite) {
                Toast.makeText(context, "Добавлено в избраное", Toast.LENGTH_SHORT).show()
                binding.buttonFavorite.setImageResource(R.drawable.ic_heart_favourite)
                true
            } else {
                Toast.makeText(context, "Удаленно из избраного", Toast.LENGTH_SHORT).show()
                binding.buttonFavorite.setImageResource(R.drawable.ic_heart_not_active)
                false
            }
        }

        binding.innerToolbarMenu.setNavigationOnClickListener {
            requireActivity().onBackPressed()
        }

        binding.buttonRead.setOnClickListener {
            requireActivity().supportFragmentManager.beginTransaction()
                .replace(
                    R.id.fragment_container_detail_activity, ReadFragment.newInstance(
                        detail.text,
                        detail.name,
                        detail.imageUrl
                    )
                )
                .addToBackStack(null)
                .commit()
        }

        binding.buttonAudio.setOnClickListener {
            requireActivity().supportFragmentManager.beginTransaction()
                .replace(
                    R.id.fragment_container_detail_activity, AudioFragment.newInstance(
                        detail.imageUrl,
                        detail.mediaUrl,
                        detail.author,
                        detail.name
                    )
                )
                .addToBackStack(null)
                .commit()
        }
    }

    private fun setupViewElemnts() {
        Picasso.get()
            .load(detail.imageUrl).transform(
                BlurTransformation(
                    context,
                    15,
                    1
                )
            )
            .into(binding.ivBigLogo)
        Picasso.get().load(detail.imageUrl).into(binding.ivSmallLogo)
        binding.tvNameBook.text = detail.name
        binding.tvAuthor.text = detail.author
        binding.tvDescription.text = detail.description
        binding.innerToolbarMenu.title = detail.name
        binding.tvGenre.text = detail.genre
    }

    companion object {
        private var isFarourite = false
        @JvmStatic
        fun newInstance(detail: DetailItem) = DetailFragment().apply {
            arguments = Bundle().apply {
                putParcelable("detail", detail)
            }
        }
    }
}