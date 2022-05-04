package com.example.newsapp.ui.favorites

import androidx.lifecycle.ViewModelProvider
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import com.example.newsapp.R
import com.example.newsapp.data.database.FavoritesDatabase
import com.example.newsapp.databinding.FavoritesFragmentBinding

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

        return inflater.inflate(R.layout.favorites_fragment, container, false)
    }


}