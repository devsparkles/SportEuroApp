package com.devsparle.sporteuroapp.domain.model

import android.os.Parcelable
import kotlinx.parcelize.Parcelize

@Parcelize
data class Sport(
    val id: Long = 0L,
    val name: String = ""
) : Parcelable