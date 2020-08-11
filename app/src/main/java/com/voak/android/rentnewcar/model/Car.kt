package com.voak.android.rentnewcar.model

import com.google.gson.annotations.SerializedName

data class Car(
    @SerializedName("id_car")
    val id: Int,
    @SerializedName("brand")
    val brand: String,
    @SerializedName("icon")
    val imageUrl: String,
    @SerializedName("type")
    val type: String,
    @SerializedName("cost")
    val price: Int,
    @SerializedName("passengers")
    val passengers: Int,
    @SerializedName("bags")
    val bags: Int,
    @SerializedName("doors")
    val doors: Int
)