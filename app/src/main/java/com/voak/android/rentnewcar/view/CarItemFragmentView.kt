package com.voak.android.rentnewcar.view

import java.util.*

interface CarItemFragmentView {
    fun showProgress()
    fun hideProgress()
    fun showAllViews()
    fun hideAllViews()
    fun showMakeOrderButton()
    fun hideMakeOrderButton()
    fun setCarBrand(brand: String)
    fun setCarImage(imageUrl: String)
    fun setCarPrice(price: Int)
    fun setTotalCost(cost: Int)
    fun setStartDate(date: String)
    fun setEndDate(date: String)
    fun setPassengersCount(passengers: Int)
    fun setBagsCount(bags: Int)
    fun setDoorsCount(doors: Int)
    fun openDatePickerFragment(date: Date, callback: (String) -> Unit)
    fun showToastMessage(message: String)
    fun navigateToHistoryFragment()
}