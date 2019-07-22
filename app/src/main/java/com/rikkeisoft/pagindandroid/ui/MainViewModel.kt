package com.rikkeisoft.pagindandroid.ui

import androidx.lifecycle.LiveData
import androidx.lifecycle.Transformations
import androidx.lifecycle.ViewModel
import androidx.paging.LivePagedListBuilder
import androidx.paging.PagedList
import com.rikkeisoft.pagindandroid.data.model.User
import com.rikkeisoft.pagindandroid.data.remote.GithubService
import com.rikkeisoft.pagindandroid.data.remote.TaskDataSourceFactory
import com.rikkeisoft.pagindandroid.data.remote.TaskRemoteDataSource
import io.reactivex.disposables.CompositeDisposable

class MainViewModel : ViewModel() {

    private val compositeDispose by lazy { CompositeDisposable() }
    private val pageSize = 5
    private val taskDataSourceFactory by lazy { TaskDataSourceFactory(GithubService.getService(), compositeDispose) }
    var users: LiveData<PagedList<User>>

    init {
        val config = PagedList.Config.Builder()
            .setPageSize(pageSize)
            .setInitialLoadSizeHint(pageSize * 2)
            .setEnablePlaceholders(false)
            .build()
        users = LivePagedListBuilder(taskDataSourceFactory, config).build()
    }

    fun getLoading() = Transformations.switchMap<TaskRemoteDataSource, Boolean>(
        taskDataSourceFactory.userDataLiveData
    ) {
        it.isLoading
    }
}
