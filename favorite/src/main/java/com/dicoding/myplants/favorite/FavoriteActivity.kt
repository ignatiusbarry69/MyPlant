package com.dicoding.myplants.favorite

import android.content.Intent
import android.net.Uri
import android.os.Bundle
import android.view.View
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.StaggeredGridLayoutManager
import com.dicoding.myplants.core.ui.PlantAdapter
import com.dicoding.myplants.di.FavoriteModuleDependencies
import com.dicoding.myplants.favorite.databinding.ActivityFavoriteBinding
import dagger.hilt.android.EntryPointAccessors
import javax.inject.Inject

class FavoriteActivity: AppCompatActivity() {

    @Inject
    lateinit var factory: ViewModelFactory

    private val favoriteViewModel: FavoriteViewModel by viewModels {
        factory
    }

    private lateinit var binding: ActivityFavoriteBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        DaggerFavoriteComponent.builder()
            .context(this)
            .appDependencies(
                EntryPointAccessors.fromApplication(
                    applicationContext,
                    FavoriteModuleDependencies::class.java
                )
            )
            .build()
            .inject(this)
        super.onCreate(savedInstanceState)
        binding = ActivityFavoriteBinding.inflate(layoutInflater)
        setContentView(binding.root)

        showFavoritePlant()
    }

    private fun showFavoritePlant() {
        val tourismAdapter = PlantAdapter()
        tourismAdapter.onItemClick = { selectedData ->
            val uri = Uri.parse("myplants://app/detail")
            val intent = Intent(Intent.ACTION_VIEW, uri)
            val b = Bundle()
            b.putParcelable("plant", selectedData)
            intent.putExtras(b)
            startActivity(intent)
        }


        favoriteViewModel.favoritePlant.observe(this) { dataPlant ->
            tourismAdapter.setData(dataPlant)
            binding.viewEmpty.root.visibility =
                if (dataPlant.isNotEmpty()) View.GONE else View.VISIBLE
        }

        with(binding.rvPlant) {
            layoutManager = StaggeredGridLayoutManager(2, StaggeredGridLayoutManager.VERTICAL)
            setHasFixedSize(true)
            adapter = tourismAdapter
        }
    }
}