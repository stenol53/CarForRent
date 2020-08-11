package com.voak.android.rentnewcar.presenter

import com.voak.android.rentnewcar.model.UserData
import com.voak.android.rentnewcar.repository.RegisterFragmentRepository
import com.voak.android.rentnewcar.view.RegisterFragmentView

class RegisterFragmentPresenterImpl(
    private val view: RegisterFragmentView,
    private val repository: RegisterFragmentRepository
) : RegisterFragmentPresenter {

    override fun onRegisterButtonClicked(
        login: String,
        password: String,
        firstName: String,
        secondName: String,
        middleName: String,
        phone: String,
        address: String
    ) {
        if (login.isEmpty() || password.isEmpty() || firstName.isEmpty() || firstName.isEmpty() ||
                secondName.isEmpty() || middleName.isEmpty() || phone.isEmpty() || address.isEmpty())
        {
            view.showToastMessage("Заполните все данные!")
        } else {
            val data = UserData(-1, login, password, firstName, secondName, middleName, address, phone, "")
            repository.makeRegister(
                data,
                {
                    view.showToastMessage("Вы успешно зарегестрировались!")
                    view.navigateToLoginFragment()
                },
                {
                    view.showToastMessage(it)
                }
            )
        }
    }
}