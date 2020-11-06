package com.flobiztest.assignmentapp.ui.activities

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.lifecycle.ViewModelProvider

import com.flobiztest.assignmentapp.R
import com.flobiztest.assignmentapp.db.ArticleDatabase
import com.flobiztest.assignmentapp.repository.NewsRepository
import com.flobiztest.assignmentapp.ui.NewsViewModel
import com.flobiztest.assignmentapp.ui.NewsViewModelProviderFactory

import kotlinx.android.synthetic.main.activity_news.*

class NewsActivity : AppCompatActivity() {

    lateinit var viewModel: NewsViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_news)

        val newsRepository = NewsRepository(ArticleDatabase(this))
        val viewModelProviderFactory = NewsViewModelProviderFactory(application, newsRepository)
        viewModel = ViewModelProvider(this, viewModelProviderFactory).get(NewsViewModel::class.java)
      //  bottomNavigationView.setupWithNavController(newsNavHostFragment.findNavController())
    }
}
