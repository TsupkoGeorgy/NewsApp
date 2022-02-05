package com.example.newsapp.ui.overview

import android.os.Bundle
import android.view.*
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.lifecycleScope
import androidx.navigation.findNavController
import androidx.navigation.fragment.findNavController
import androidx.navigation.ui.NavigationUI
import androidx.recyclerview.widget.GridLayoutManager
import com.example.androidcentranewsapp.ui.overview.LoaderStateAdapter
import com.example.androidcentranewsapp.ui.overview.PagingAdapter
import com.example.newsapp.R
import com.example.newsapp.databinding.FragmentOverviewBinding
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.launch


class OverviewFragment : Fragment() {



    private val viewModel: OverviewViewModel by lazy {
        ViewModelProvider(this).get(OverviewViewModel::class.java)
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?,
    ): View? {
        val binding: FragmentOverviewBinding =
            DataBindingUtil.inflate(
                inflater,
                R.layout.fragment_overview,
                container,
                false
            )

        binding.lifecycleOwner = this
        binding.viewModel = viewModel

        val pagingAdapter = PagingAdapter(PagingAdapter.OnClickListener {
            if (it != null) {
                viewModel.displayPropertyDetails(it)
            }
        })

        viewModel.filterUpdate.observe(viewLifecycleOwner, Observer {
            pagingAdapter.refresh()
        })

        binding.grid.adapter = pagingAdapter

        binding.grid.adapter =
            pagingAdapter.withLoadStateFooter(LoaderStateAdapter { pagingAdapter.retry() })


        viewModel.flow.observe(viewLifecycleOwner, Observer {
            viewLifecycleOwner.lifecycleScope.launch {
                pagingAdapter.submitData(viewModel.flow.value!!)
            }
        })


//        viewLifecycleOwner.lifecycleScope.launch {
//            viewModel.flow.collectLatest {  pagingData ->
//                pagingAdapter.submitData(pagingData)
//            }
//        }

        viewLifecycleOwner.lifecycleScope.launch {
            pagingAdapter.loadStateFlow.collectLatest { loadState ->
                loadState.refresh
            }
        }

        viewModel.navigateToSelectedProperty.observe(viewLifecycleOwner, Observer {
            if (null != it) {
                this.findNavController()
                    .navigate(OverviewFragmentDirections.actionOverviewFragmentToWebViewFragment(it.url))
                viewModel.displayWebViewComplete()
            }
        })

        binding.grid.layoutManager = GridLayoutManager(context, 1)


        setHasOptionsMenu(true)
        return binding.root
    }

    override fun onCreateOptionsMenu(menu: Menu, inflater: MenuInflater) {
        inflater.inflate(R.menu.overflow_menu, menu)
        super.onCreateOptionsMenu(menu, inflater)
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        viewModel.updateLanguage(
            when (item.itemId) {
                R.id.show_eu_responce -> "en"
                R.id.show_ru_responce -> "ru"
                else -> "en"
            }

        )
        viewModel.updateFilterComplete()
        return NavigationUI.onNavDestinationSelected(item, requireView().findNavController())
                || super.onOptionsItemSelected(item)

    }


}