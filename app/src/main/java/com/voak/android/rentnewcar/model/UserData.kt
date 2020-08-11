package com.voak.android.rentnewcar.model

import com.google.gson.annotations.SerializedName

data class UserData(
    @SerializedName("id_client")
    val id: Int,
    @SerializedName("login")
    val login: String,
    @SerializedName("password")
    val password: String,
    @SerializedName("first_name")
    val firstName: String,
    @SerializedName("second_name")
    val secondName: String,
    @SerializedName("middle_name")
    val middleName: String,
    @SerializedName("address")
    val address: String,
    @SerializedName("phone")
    val phone: String,
    @SerializedName("result")
    val result: String
)