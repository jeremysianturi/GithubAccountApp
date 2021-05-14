package com.example.core.domain.model

import android.os.Parcelable
import kotlinx.parcelize.Parcelize

@Parcelize
data class User (
    val login: String,
    val id: Int,
    val avatarUrl: String
    ) : Parcelable