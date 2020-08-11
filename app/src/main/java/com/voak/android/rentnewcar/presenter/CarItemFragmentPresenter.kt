package com.voak.android.rentnewcar.presenter

import java.util.*

interface CarItemFragmentPresenter {
    fun onCreateView(carId: Int)
    fun onDateTextViewClicked(dateString: String, callback: (String) -> Unit)
    fun onDateChanged(dateStart: String, dateEnd: String)
    fun onMakeOrderButtonClicked(carId: Int, dateStart: String, dateEnd: String)
}