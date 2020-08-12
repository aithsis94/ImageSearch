package com.ajitesh.imagesearch.view

import android.os.Bundle
import android.widget.Toast
import androidx.annotation.NonNull
import androidx.appcompat.app.AppCompatActivity
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProviders
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.ajitesh.imagesearch.R
import com.ajitesh.imagesearch.databinding.ActivityMainBinding
import com.ajitesh.imagesearch.util.Util
import com.ajitesh.imagesearch.viewmodel.MainViewModel
import com.ajitesh.imagesearch.viewmodel.factory.AppViewModelFactory


class MainActivity : AppCompatActivity() {

    private lateinit var dataBinding: ActivityMainBinding
    private lateinit var viewModel: MainViewModel

    override fun onCreate(savedInstanceState: Bundle?) {

        super.onCreate(savedInstanceState)

        this.dataBinding = DataBindingUtil.setContentView(this, R.layout.activity_main)
        this.viewModel = ViewModelProviders.of(this, AppViewModelFactory(application))
            .get(MainViewModel::class.java)
        this.dataBinding.lifecycleOwner = this
        this.dataBinding.viewModel = viewModel

        setupUI()
    }

    private fun setupUI() {

        dataBinding.rvSearchResults.layoutManager = GridLayoutManager(this, 2)
        //    LinearLayoutManager(this, LinearLayoutManager.VERTICAL, false);

        viewModel.getToastMessage().observe(this, Observer {
            Toast.makeText(this, it, Toast.LENGTH_LONG).show()
        })

        viewModel.isLoading().observe(this, Observer {
            Util.hideKeyboard(this)
        })

        dataBinding.rvSearchResults.addOnScrollListener(object : RecyclerView.OnScrollListener() {
            override fun onScrollStateChanged(
                @NonNull recyclerView: RecyclerView,
                newState: Int
            ) {
                super.onScrollStateChanged(recyclerView, newState)
            }

            override fun onScrolled(
                @NonNull recyclerView: RecyclerView,
                dx: Int,
                dy: Int
            ) {
                super.onScrolled(recyclerView, dx, dy)
                val layoutManager = recyclerView.layoutManager as GridLayoutManager
                //layoutManager.findLastVisibleItemPosition()
                val lastVisiblePosition = layoutManager.findLastVisibleItemPosition()
                viewModel.checkIfNeedToLoadMore(lastVisiblePosition)
            }
        })
    }

    override fun onResume() {
        super.onResume()
        Util.hideKeyboard(this)
    }
}
