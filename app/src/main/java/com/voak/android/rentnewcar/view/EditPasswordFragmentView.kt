package com.voak.android.rentnewcar.view

interface EditPasswordFragmentView {
    fun hideProgress()
    fun showProgress()
    fun hideEditButton()
    fun showEditButton()
    fun showToastMessage(message: String)
    fun navigateToProfileFragment()
}