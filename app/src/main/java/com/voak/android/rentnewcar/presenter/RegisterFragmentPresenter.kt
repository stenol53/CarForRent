package com.voak.android.rentnewcar.presenter

interface RegisterFragmentPresenter {
    fun onRegisterButtonClicked(
        login: String,
        password: String,
        firstName: String,
        secondName: String,
        middleName: String,
        phone: String,
        address: String
    )
}