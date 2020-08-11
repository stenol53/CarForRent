package com.voak.android.rentnewcar.presenter

interface LoginFragmentPresenter {
    fun onCreateView()
    fun onEnterButtonClicked(login: String, password: String)
    fun onRegisterAccountClicked()
}