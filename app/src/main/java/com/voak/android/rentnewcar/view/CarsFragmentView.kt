package com.voak.android.rentnewcar.view

import com.voak.android.rentnewcar.model.Car

interface CarsFragmentView {
    fun showProgress()
    fun hideProgress()
    fun showCarList()
    fun hideCarList()
    fun showToastMessage(message: String)
    fun updateCarList(cars: List<Car>)
    fun navigateToCarItemFragment(carId: Int)
}