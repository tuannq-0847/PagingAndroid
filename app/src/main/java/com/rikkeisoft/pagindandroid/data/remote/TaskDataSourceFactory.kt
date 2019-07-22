package com.rikkeisoft.pagindandroid.data.remote

import androidx.lifecycle.MutableLiveData
import androidx.paging.DataSource
import com.rikkeisoft.pagindandroid.data.model.User
import io.reactivex.disposables.CompositeDisposable

class TaskDataSourceFactory(
    private val service: GithubService,
    private val compositeDisposable: CompositeDisposable
) : DataSource.Factory<Long, User>() {

    val userDataLiveData = MutableLiveData<TaskRemoteDataSource>()

    override fun create(): DataSource<Long, User> {
        val userData = TaskRemoteDataSource(service, compositeDisposable)
        userDataLiveData.postValue(userData)
        return userData
    }
}