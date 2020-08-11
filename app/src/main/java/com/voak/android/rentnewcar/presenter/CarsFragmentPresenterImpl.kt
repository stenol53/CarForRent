package com.voak.android.rentnewcar.presenter

import android.util.Log
import com.voak.android.rentnewcar.repository.CarsFragmentRepository
import com.voak.android.rentnewcar.view.CarsFragmentView

class CarsFragmentPresenterImpl(
    val view: CarsFragmentView,
    val repository: CarsFragmentRepository
) : CarsFragmentPresenter
{
    override fun onCreateView() {
        Log.i("PRESENTER", "123123123123")
        view.hideCarList()
        view.showProgress()
        repository.getCars(
            {
                view.updateCarList(it)
                view.hideProgress()
                view.showCarList()
            },
            {
                view.hideProgress()
                view.showToastMessage(it)
            }
        )
        Log.i("PRESENTER", "123123123123")
    }

    override fun onItemClicked(carId: Int) {
        Log.i("PRESENTER", "clicked")
        view.navigateToCarItemFragment(carId)
    }
}