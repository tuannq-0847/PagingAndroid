package com.rikkeisoft.pagindandroid.data.model

import com.google.gson.annotations.SerializedName

data class User(
    @SerializedName("id")
    var id: Long = 0,
    @SerializedName("login")
    var login: String = "",
    @SerializedName("avatar_url")
    var avatarUrl: String = ""
)
