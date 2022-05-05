package com.example.newsapp.ui.favorites

import androidx.lifecycle.ViewModelProvider
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.Observer
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.newsapp.R
import com.example.newsapp.data.database.FavoritesDatabase
import com.example.newsapp.data.model.ArticlesData
import com.example.newsapp.databinding.FavoritesFragmentBinding
import com.example.newsapp.ui.overview.NewsActionListener

class FavoritesFragment : Fragment() {
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?,
    ): View? {

        var binding: FavoritesFragmentBinding = DataBindingUtil.inflate(
            inflater,
            R.layout.favorites_fragment,
            container,
            false
        )

        val application = requireNotNull(this.activity).application
        val dataSource = FavoritesDatabase.getInstance(application).favoritesDatabaseDao

        val viewModelFactory = FavoritesViewModelFactory(dataSource, application)
        val viewModel =
            ViewModelProvider(this, viewModelFactory).get(FavoritesViewModel::class.java)
        binding.viewModel = viewModel
        binding.lifecycleOwner = this
        val favoritesAdapter = FavoritesAdapter(object :NewsActionListener {
            override fun onNewsClicked(articlesNews: ArticlesData) {
                TODO("Not yet implemented")
            }

            override fun onFavoriteClicked(articlesNews: ArticlesData) {
                TODO("Not yet implemented")
            }
        })
        binding.favoritesList.adapter = favoritesAdapter
        binding.favoritesList.layoutManager = LinearLayoutManager(context)

        viewModel.favorites.observe(viewLifecycleOwner, Observer {
            it?.let {
                favoritesAdapter.submitList(it)
            }
        })

        return binding.root
    }


}