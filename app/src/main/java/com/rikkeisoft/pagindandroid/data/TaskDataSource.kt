package com.rikkeisoft.pagindandroid.data

import com.rikkeisoft.pagindandroid.data.model.User
import io.reactivex.Single

interface TaskDataSource {
    interface Remote {
        fun getUsers(id: Long, page: Int): Single<List<User>>
    }

    interface Local
}
