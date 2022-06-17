package com.top1shvetsvadim1.fairytales.presentation.fragments

import android.media.MediaPlayer
import android.net.Uri
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.SeekBar
import androidx.activity.OnBackPressedCallback
import androidx.lifecycle.ViewModelProvider
import com.squareup.picasso.Picasso
import com.top1shvetsvadim1.fairytales.R
import com.top1shvetsvadim1.fairytales.databinding.FragmentAudioBinding
import com.top1shvetsvadim1.fairytales.databinding.FragmentDetailBinding
import com.top1shvetsvadim1.fairytales.presentation.fragments.ReadFragment.Companion.KEY_IMAGE_URL
import com.top1shvetsvadim1.fairytales.presentation.fragments.ReadFragment.Companion.KEY_NAMEBOCK
import com.top1shvetsvadim1.fairytales.presentation.viewModel.AudioViewModel
import java.lang.RuntimeException
import java.util.*


class AudioFragment : Fragment() {

    private var _binding: FragmentAudioBinding? = null
    private val binding: FragmentAudioBinding
        get() = _binding ?: throw RuntimeException("FragmentAudioBinding == null")

    private val viewModel by lazy {
        ViewModelProvider(this)[AudioViewModel::class.java]
    }

    private lateinit var mediaUrl: String
    private lateinit var imageUrl: String
    private lateinit var author: String
    private lateinit var nameBock: String
    private var mediaPlayer = MediaPlayer()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        mediaUrl = arguments?.getString(KEY_MEDIA_URL, "") ?: ""
        imageUrl = arguments?.getString(KEY_IMAGE_URL, "") ?: ""
        author = arguments?.getString(KEY_AUTHOR, "") ?: ""
        nameBock = arguments?.getString(KEY_NAMEBOCK, "") ?: ""
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentAudioBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        setupListeners()
        seekBarTimer()
        viewModelObserves()
        binding.tvNowPLaying.text = nameBock
        binding.tvTitle.text = nameBock
        binding.tvAuthor.text = author
        Picasso.get().load(imageUrl).into(binding.ivAlbumArt)
        mediaPlayer = MediaPlayer.create(context, Uri.parse(mediaUrl))
        binding.playerSeekBar.max = mediaPlayer.duration
        viewModel.maxTimeAudio(mediaPlayer.duration)
        myOnBackPressed()
    }

    private fun viewModelObserves() {
        viewModel.timerAudio.observe(viewLifecycleOwner) {
            binding.tvCurrentTime.text = it
        }
        viewModel.maxTimerAudio.observe(viewLifecycleOwner) {
            binding.tvTotalTime.text = it
        }
    }

    private fun setupListeners() {
        binding.playerSeekBar.setOnSeekBarChangeListener(object : SeekBar.OnSeekBarChangeListener {
            override fun onProgressChanged(seekBar: SeekBar?, progress: Int, fromUser: Boolean) {
                if (fromUser) {
                    mediaPlayer.apply {
                        seekTo(progress)
                        start()
                    }
                }
                if (progress == mediaPlayer.duration) {
                    mediaPlayer.apply {
                        seekTo(0)
                        pause()
                    }
                    binding.buttonPLay.setImageResource(R.drawable.ic_play)
                }
                viewModel.secondsToMinute(progress)
            }

            override fun onStartTrackingTouch(seekBar: SeekBar?) {
            }

            override fun onStopTrackingTouch(seekBar: SeekBar?) {
            }
        })

        binding.buttonPLay.setOnClickListener{
            if (mediaPlayer.isPlaying) {
                mediaPlayer.pause()
                binding.buttonPLay.setImageResource(R.drawable.ic_play)
            } else {
                mediaPlayer.start()
                binding.buttonPLay.setImageResource(R.drawable.ic_pause)
            }
        }

        binding.buttonBack.setOnClickListener{
            mediaPlayer.stop()
            requireActivity().onBackPressed()
        }

        binding.buttonNext.setOnClickListener {
            if (mediaPlayer.currentPosition >= mediaPlayer.duration){
                mediaPlayer.seekTo(mediaPlayer.duration)
            }
            mediaPlayer.seekTo(mediaPlayer.currentPosition + TIME_NEXT_OR_PREVIOUS)
        }
        binding.buttonPrevious.setOnClickListener {
            if (mediaPlayer.currentPosition <= 0){
                mediaPlayer.seekTo(0)
            }
            mediaPlayer.seekTo(mediaPlayer.currentPosition - TIME_NEXT_OR_PREVIOUS)
        }
    }

    private fun myOnBackPressed() {
        val callback = object : OnBackPressedCallback(true) {
            override fun handleOnBackPressed() {
                mediaPlayer.stop()
                requireActivity().supportFragmentManager.popBackStack()
            }
        }
        activity?.onBackPressedDispatcher?.addCallback(viewLifecycleOwner, callback)
    }

    private fun seekBarTimer() {
        Timer().scheduleAtFixedRate(object : TimerTask() {
            override fun run() {
                binding.playerSeekBar.progress = mediaPlayer.currentPosition
            }
        }, 0, 1)
    }

    companion object {

        const val KEY_MEDIA_URL = "mediaUrl"
        const val KEY_AUTHOR = "author"
        private const val TIME_NEXT_OR_PREVIOUS = 30000
        @JvmStatic
        fun newInstance(imageUrl: String, mediaUrl: String, name: String, author: String) =
            AudioFragment().apply {
                arguments = Bundle().apply {
                    putString(KEY_IMAGE_URL, imageUrl)
                    putString(KEY_MEDIA_URL, mediaUrl)
                    putString(KEY_AUTHOR, author)
                    putString(KEY_NAMEBOCK, name)
                }
            }
    }
}