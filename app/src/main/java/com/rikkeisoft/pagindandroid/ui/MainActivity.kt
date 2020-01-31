package com.rikkeisoft.pagindandroid.ui

import android.os.Bundle
import android.util.Log
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProviders
import androidx.recyclerview.widget.LinearLayoutManager
import com.rikkeisoft.pagindandroid.R.layout
import io.reactivex.BackpressureStrategy.MISSING
import io.reactivex.schedulers.Schedulers
import io.reactivex.subjects.PublishSubject
import kotlinx.android.synthetic.main.activity_main.usersRecyclerView

class MainActivity : AppCompatActivity() {

    private val viewModel by lazy { ViewModelProviders.of(this).get(MainViewModel::class.java) }
    private val userAdapter by lazy { UserAdapter() }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(layout.activity_main)
        initAdapter()
//        Test.callStatic()
//        Test.callNonStatic()
        //    testFlowable()
    }

    private fun testFlowable() {
        val observable = PublishSubject.create<Int>()
            .toFlowable(MISSING)
            .observeOn(Schedulers.computation())
            .subscribe({
                for (i in 0..100000) {
                    PublishSubject.just(i)
                        .toFlowable(MISSING)
                        .observeOn(Schedulers.computation())
                        .subscribe({
                            Log.d("test", it.toString())
                        }, {
                            Log.d("test", it.message.toString())
                        })
                }
            }, {

            })
    }

    private fun initAdapter() {
        usersRecyclerView.layoutManager = LinearLayoutManager(this)
        usersRecyclerView.adapter = userAdapter
        viewModel.users.observe(this, Observer {
            Log.d("userList", it.size.toString())
            userAdapter.submitList(it)
        })
        viewModel.getLoading().observe(this, Observer {
            userAdapter.setLoading(it)
        })
    }
}
