package com.flobiztest.assignmentapp.ui.fragments

import android.content.Context
import android.content.SharedPreferences
import android.os.Bundle
import android.util.Log
import android.view.View
import android.widget.AbsListView
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.flobiztest.assignmentapp.R
import com.flobiztest.assignmentapp.adapters.NewsAdapter

import com.flobiztest.assignmentapp.ui.NewsViewModel
import com.flobiztest.assignmentapp.ui.activities.NewsActivity
import com.flobiztest.assignmentapp.util.Constants.Companion.ADs_VIEW

import com.flobiztest.assignmentapp.util.Constants.Companion.QUERY_PAGE_SIZE
import com.flobiztest.assignmentapp.util.Resource
import com.flobiztest.assignmentapp.util.SharedPrefUtils

import kotlinx.android.synthetic.main.fragment_breaking_news.*
import pub.devrel.easypermissions.BuildConfig

class BreakingNewsFragment : Fragment(R.layout.fragment_breaking_news), View.OnClickListener {

    lateinit var viewModel: NewsViewModel
    lateinit var newsAdapter: NewsAdapter
    internal var sharedPreferences: SharedPreferences? = null
    var isAdsEnabled:Boolean =true

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        viewModel = (activity as NewsActivity).viewModel
        sharedPreferences = requireActivity().getSharedPreferences(BuildConfig.APPLICATION_ID, Context.MODE_PRIVATE)
        setupRecyclerView()
        initAds()

        imgClose.setOnClickListener(this)
   /*

     //=====onClick open Article Details Page===========

      newsAdapter.setOnItemClickListener {
            val bundle = Bundle().apply {
                putSerializable("article", it)
            }
            findNavController().navigate(
                R.id.action_breakingNewsFragment_to_articleFragment,
                bundle
            )
        }
      //================================================

    */

        viewModel.breakingNews.observe(viewLifecycleOwner, Observer { response ->
            when(response) {
                is Resource.Success -> {
                    hideProgressBar()
                    response.data?.let { newsResponse ->
                        newsAdapter.differ.submitList(newsResponse.articles.toList())
                        val totalPages = newsResponse.totalResults / QUERY_PAGE_SIZE + 2
                        isLastPage = viewModel.breakingNewsPage == totalPages
                        if(isLastPage) {
                            rvBreakingNews.setPadding(0, 0, 0, 0)
                        }
                    }
                }

                is Resource.Error -> {
                    hideProgressBar()
                    response.message?.let { message ->
                        Toast.makeText(activity, "An error occured: $message", Toast.LENGTH_LONG).show()
                    }
                }
                is Resource.Loading -> {
                    showProgressBar()
                }
            }
        })
    }

    private fun hideProgressBar() {
        paginationProgressBar.visibility = View.INVISIBLE
        isLoading = false
    }

    private fun initAds() {

        isAdsEnabled = SharedPrefUtils.getBoolean(requireActivity(),ADs_VIEW,true)

        when (isAdsEnabled){
            true->{
                lyAds.visibility = View.VISIBLE
            }
            false->{
                lyAds.visibility = View.GONE
            }
        }

    }

    private fun showProgressBar() {
        paginationProgressBar.visibility = View.VISIBLE
        isLoading = true
    }

    var isLoading = false
    var isLastPage = false
    var isScrolling = false

    val scrollListener = object : RecyclerView.OnScrollListener() {
        override fun onScrolled(recyclerView: RecyclerView, dx: Int, dy: Int) {
            super.onScrolled(recyclerView, dx, dy)

            val layoutManager = recyclerView.layoutManager as LinearLayoutManager
            val firstVisibleItemPosition = layoutManager.findFirstVisibleItemPosition()
            val visibleItemCount = layoutManager.childCount
            val totalItemCount = layoutManager.itemCount

            val isNotLoadingAndNotLastPage = !isLoading && !isLastPage
            val isAtLastItem = firstVisibleItemPosition + visibleItemCount >= totalItemCount
            val isNotAtBeginning = firstVisibleItemPosition >= 0
            val isTotalMoreThanVisible = totalItemCount >= QUERY_PAGE_SIZE
            val shouldPaginate = isNotLoadingAndNotLastPage && isAtLastItem && isNotAtBeginning && isTotalMoreThanVisible && isScrolling
            if(shouldPaginate) {
                viewModel.getBreakingNews("us")
                isScrolling = false
            }
        }

        override fun onScrollStateChanged(recyclerView: RecyclerView, newState: Int) {
            super.onScrollStateChanged(recyclerView, newState)
            if(newState == AbsListView.OnScrollListener.SCROLL_STATE_TOUCH_SCROLL) {
                isScrolling = true
            }
        }
    }

    private fun setupRecyclerView() {
        newsAdapter = NewsAdapter()
        rvBreakingNews.apply {
            adapter = newsAdapter
            layoutManager = LinearLayoutManager(activity)
            addOnScrollListener(this@BreakingNewsFragment.scrollListener)
        }
        rvBreakingNews.isNestedScrollingEnabled = false;
    }

    override fun onClick(view: View?) {
        when(view?.id){
            R.id.imgClose->{
                try {
                    SharedPrefUtils.putData(requireActivity(),ADs_VIEW, false)
                    lyAds.visibility = View.GONE
                }catch (e:Exception){
                    e.printStackTrace()
                }
            }
        }
    }
}








