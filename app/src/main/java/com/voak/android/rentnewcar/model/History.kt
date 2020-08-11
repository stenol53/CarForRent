package com.voak.android.rentnewcar.model

import com.google.gson.annotations.SerializedName

data class History(
    @SerializedName("brand")
    val carBrand: String,
    @SerializedName("cost")
    val carPrice: Int,
    @SerializedName("icon")
    val imageUrl: String,
    @SerializedName("date_issue")
    val startDate: Long,
    @SerializedName("date_return")
    val endDate: Long,
    @SerializedName("state")
    val state: String
)