package com.voak.android.rentnewcar.view

interface LoginFragmentView {
    fun navigateToRegisterFragment()
    fun showProgress()
    fun hideProgress()
    fun showToastMessage(message: String)
    fun navigateToBottomNavigationActivity()
    fun setLogin(login: String)
    fun setPassword(password: String)
}