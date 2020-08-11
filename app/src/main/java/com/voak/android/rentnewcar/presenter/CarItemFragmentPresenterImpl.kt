package com.voak.android.rentnewcar.presenter

import android.text.format.DateFormat
import android.util.Log
import com.voak.android.rentnewcar.repository.CarItemFragmentRepository
import com.voak.android.rentnewcar.utils.QueryPreferences
import com.voak.android.rentnewcar.view.CarItemFragmentView
import java.text.SimpleDateFormat
import java.time.LocalDateTime
import java.time.format.DateTimeFormatter
import java.time.temporal.ChronoUnit
import java.util.*
import java.util.concurrent.TimeUnit

class CarItemFragmentPresenterImpl(
    val view: CarItemFragmentView,
    val repository: CarItemFragmentRepository
) : CarItemFragmentPresenter {

    private var carPrice: Int = 0

    override fun onCreateView(carId: Int) {
        view.hideAllViews()
        view.showProgress()
        repository.getCar(
            carId,
            {
                /// ЗАПОЛНИТЬ ВЬЮХИ
                view.setCarBrand(it.brand)
                view.setCarPrice(it.price)
                carPrice = it.price
                view.setCarImage(it.imageUrl)
                view.setPassengersCount(it.passengers)
                view.setBagsCount(it.bags)
                view.setDoorsCount(it.doors)
                /// Доделать дату и общую стоимость
                view.setStartDate(getCurrentDate())
                view.setEndDate(getCurrentDate())
                view.setTotalCost(it.price)

                view.showAllViews()
                view.hideProgress()
            },
            {
                view.hideProgress()

            }
        )
    }

    override fun onMakeOrderButtonClicked(carId: Int, dateStart: String, dateEnd: String) {
        val date1 = SimpleDateFormat("dd.MM.yyyy").parse(dateStart).time
        val date2 = SimpleDateFormat("dd.MM.yyyy").parse(dateEnd).time

        repository.makeOrder(
            QueryPreferences.getClientId(),
            carId,
            date1,
            date2,
            {
                view.showToastMessage("Заказ успешно оформлен!")
                view.navigateToHistoryFragment()
            },
            {
                view.showToastMessage(it)
            }
        )

    }

    override fun onDateChanged(dateStart: String, dateEnd: String) {
        val cost = countTotalCost(dateStart, dateEnd)
        view.setTotalCost(cost)
    }

    override fun onDateTextViewClicked(dateString: String, callback: (String) -> Unit) {
        val date = SimpleDateFormat("dd.MM.yyyy").parse(dateString)
        view.openDatePickerFragment(date!!, callback)
    }

    private fun countTotalCost(startDate: String, endDate: String): Int {
        val date1 = SimpleDateFormat("dd.MM.yyyy").parse(startDate)
        val date2 = SimpleDateFormat("dd.MM.yyyy").parse(endDate)
        val days = TimeUnit.DAYS.convert((date2.time - date1.time), TimeUnit.MILLISECONDS).toInt()

        return (days * carPrice).toInt()
    }

    private fun getCurrentDate(): String {
        val calendar = Calendar.getInstance()

        return DateFormat.format("dd.MM.yyyy", calendar.time).toString()
    }
}