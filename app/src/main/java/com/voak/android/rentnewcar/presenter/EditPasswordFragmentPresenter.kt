package com.voak.android.rentnewcar.presenter

interface EditPasswordFragmentPresenter {
    fun onAcceptButtonClicked(
        oldPassword: String,
        newPassword: String,
        repeatPassword: String
    )
}