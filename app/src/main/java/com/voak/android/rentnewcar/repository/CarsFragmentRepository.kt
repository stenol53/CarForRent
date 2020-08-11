package com.voak.android.rentnewcar.repository

import com.voak.android.rentnewcar.model.Car

interface CarsFragmentRepository {
    fun getCars(resultOk: (List<Car>) -> Unit, resultError: (String) -> Unit)
}