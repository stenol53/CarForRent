package com.voak.android.rentnewcar.presenter

interface EditInfoFragmentPresenter {
    fun onChangeButtonClicked(
        login: String,
        firstName: String,
        secondName: String,
        middleName: String,
        phone: String,
        address: String
    )
}