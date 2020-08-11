package com.voak.android.rentnewcar.presenter

interface CarsFragmentPresenter {
    fun onCreateView()
    fun onItemClicked(carId: Int)
}