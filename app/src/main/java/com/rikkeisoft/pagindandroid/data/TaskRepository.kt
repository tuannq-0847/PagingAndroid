package com.rikkeisoft.pagindandroid.data

import com.rikkeisoft.pagindandroid.data.model.User
import io.reactivex.Single

class TaskRepository(
    private val remoteDataSource: TaskDataSource.Remote
) : TaskDataSource.Remote {

    override fun getUsers(id: Long, page: Int): Single<List<User>> =
        remoteDataSource.getUsers(id, page)
}
