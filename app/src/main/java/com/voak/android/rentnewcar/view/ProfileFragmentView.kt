package com.voak.android.rentnewcar.view

interface ProfileFragmentView {

    fun setLogin(login: String)
    fun setName(name: String)
    fun setPhone(phone: String)
    fun setAddress(address: String)
    fun setErrorDescription(error: String)
    fun showProgress()
    fun hideProgress()
    fun showUserData()
    fun hideUserData()
    fun showButtons()
    fun hideButtons()
    fun showErrorMessage()
    fun hideErrorMessage()
    fun navigateToEditInfoFragment()
    fun navigateToEditPasswordFragment()
}