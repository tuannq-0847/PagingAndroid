package com.rikkeisoft.pagindandroid.ui

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProviders
import androidx.recyclerview.widget.LinearLayoutManager
import com.rikkeisoft.pagindandroid.R.layout
import kotlinx.android.synthetic.main.activity_main.usersRecyclerView

class MainActivity : AppCompatActivity() {

    private val viewModel by lazy { ViewModelProviders.of(this).get(MainViewModel::class.java) }
    private val userAdapter by lazy {
        UserAdapter()
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(layout.activity_main)
        initAdapter()
    }

    private fun initAdapter() {
        usersRecyclerView.layoutManager = LinearLayoutManager(this)
        usersRecyclerView.adapter = userAdapter
        viewModel.users.observe(this, Observer {
            userAdapter.submitList(it)
        })
        viewModel.getLoading().observe(this, Observer {
            userAdapter.setLoading(it)
        })
    }
}
