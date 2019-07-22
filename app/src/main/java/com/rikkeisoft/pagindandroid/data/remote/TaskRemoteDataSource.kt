package com.rikkeisoft.pagindandroid.data.remote

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.paging.ItemKeyedDataSource
import com.rikkeisoft.pagindandroid.data.model.User
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.disposables.CompositeDisposable
import io.reactivex.schedulers.Schedulers

class TaskRemoteDataSource(
    private val service: GithubService,
    private val compositeDisposable: CompositeDisposable
) : ItemKeyedDataSource<Long, User>() {

    private val _isLoading: MutableLiveData<Boolean> = MutableLiveData()
    val isLoading: LiveData<Boolean>
        get() = _isLoading

    override fun loadInitial(params: LoadInitialParams<Long>, callback: LoadInitialCallback<User>) {
        _isLoading.postValue(true)
        compositeDisposable.add(
            service.getUsers(1, params.requestedLoadSize)
                .observeOn(AndroidSchedulers.mainThread())
                .subscribeOn(Schedulers.io())
                .subscribe({
                    _isLoading.postValue(false)
                    callback.onResult(it)
                }, {
                    Log.d("test", it.message.toString())
                })
        )
    }

    override fun loadAfter(params: LoadParams<Long>, callback: LoadCallback<User>) {
        _isLoading.postValue(true)
        compositeDisposable.add(
            service.getUsers(params.key, params.requestedLoadSize)
                .observeOn(AndroidSchedulers.mainThread())
                .subscribeOn(Schedulers.io())
                .subscribe({
                    _isLoading.postValue(false)
                    callback.onResult(it)
                }, {
                    Log.d("test", it.message.toString())
                })
        )
    }

    override fun loadBefore(params: LoadParams<Long>, callback: LoadCallback<User>) {
    }

    override fun getKey(item: User): Long = item.id
}