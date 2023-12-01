package com.dicoding.myplants.detail

import android.os.Build
import android.os.Bundle
import android.text.Html
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import androidx.core.content.ContextCompat
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.recyclerview.widget.StaggeredGridLayoutManager
import com.bumptech.glide.Glide
import com.dicoding.myplants.R
import com.dicoding.myplants.core.data.Resource
import com.dicoding.myplants.core.domain.model.Plant
import com.dicoding.myplants.core.ui.PlantAdapter
import com.dicoding.myplants.databinding.FragmentDetailPlantBinding
import com.dicoding.myplants.home.HomeViewModel
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class DetailPlantFragment : Fragment() {

//    private lateinit var binding: FragmentDetailPlantBinding
//    private val detailPlantViewModel: DetailPlantViewModel by viewModels()
//
//    override fun onCreate(savedInstanceState: Bundle?) {
//        super.onCreate(savedInstanceState)
//        binding = FragmentDetailPlantBinding.inflate(layoutInflater)
//        setContentView(binding.root)
//
////        hapus kode berikut
////        val factory = ViewModelFactory.getInstance(this)
////        detailPlantViewModel = ViewModelProvider(this, factory)[DetailPlantViewModel::class.java]
//
//        val detailPlant = intent.getParcelableExtra<Plant>(EXTRA_DATA)
//        showDetailPlant(detailPlant)
//    }

    private val detailPlantViewModel: DetailPlantViewModel by viewModels()

    private var _binding: FragmentDetailPlantBinding? = null
    private val binding get() = _binding!!

//    override fun onCreate(savedInstanceState: Bundle?) {
//        super.onCreate(savedInstanceState)
//        (activity as AppCompatActivity).supportActionBar?.hide()
//    }
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentDetailPlantBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
//        (activity as AppCompatActivity).supportActionBar?.hide()

        val result = arguments?.getParcelable<Plant>("plant")
        if (result != null) {
            showDetailPlant(result)
        }
        val extras = activity?.intent?.extras
        val value = extras?.getParcelable<Plant>("plant")
//        Log.e("a", value?.imageUrl.toString())
        if (value != null) {
            showDetailPlant(value)
        }
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }

    private fun showDetailPlant(detailPlant: Plant?) {
        detailPlant?.let {
            binding.toolbar.title = detailPlant.name
//            binding.content.tvDetailDescription.text = detailPlant.description
            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.N) {
                binding.content.tvDetailDescription.text = Html.fromHtml(detailPlant.description, Html.FROM_HTML_MODE_LEGACY)
            } else {
                @Suppress("DEPRECATION")
                binding.content.tvDetailDescription.text = Html.fromHtml(detailPlant.description)
            }
            Glide.with(this)
                .load(detailPlant.imageUrl)
                .into(binding.ivDetailImage)

            var statusFavorite = detailPlant.isFavorite
            setStatusFavorite(statusFavorite)
            binding.fab.setOnClickListener {
                statusFavorite = !statusFavorite
                detailPlantViewModel.setFavoritePlant(detailPlant, statusFavorite)
                setStatusFavorite(statusFavorite)
            }
        }
    }

    private fun setStatusFavorite(statusFavorite: Boolean) {
        if (statusFavorite) {
            binding.fab.setImageDrawable(ContextCompat.getDrawable(requireContext(), R.drawable.ic_favorite))
        } else {
            binding.fab.setImageDrawable(ContextCompat.getDrawable(requireContext(), R.drawable.ic_unfavorite))
        }
    }
}
