package com.voak.android.rentnewcar.view

interface EditInfoFragmentView {
    fun showEditButton()
    fun hideEditButton()
    fun showProgress()
    fun hideProgress()
    fun showToastMessage(message: String)
    fun navigateToProfileFragment()
}