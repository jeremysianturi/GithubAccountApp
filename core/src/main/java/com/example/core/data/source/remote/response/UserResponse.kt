package com.example.core.data.source.remote.response

import com.google.gson.annotations.SerializedName

data class UserResponse (

    @field:SerializedName("login")
    val login: String,

    @field:SerializedName("id")
    val id: Int,

    @field:SerializedName("avatar_url")
    val avatarUrl: String

        )