package com.voak.android.rentnewcar.presenter

import android.util.Log
import com.voak.android.rentnewcar.model.UserData
import com.voak.android.rentnewcar.repository.LoginFragmentRepository
import com.voak.android.rentnewcar.utils.QueryPreferences
import com.voak.android.rentnewcar.view.LoginFragmentView

class LoginFragmentPresenterImpl(
    private val view: LoginFragmentView,
    private val repository: LoginFragmentRepository
) : LoginFragmentPresenter
{

    override fun onCreateView() {
        val login = QueryPreferences.getClientLogin()
        val password = QueryPreferences.getClientPassword()

        if (!login.isNullOrEmpty() && !password.isNullOrEmpty()) {
            view.setLogin(login)
            view.setPassword(password)
        }
    }

    override fun onEnterButtonClicked(login: String, password: String) {
        if (login.isNotEmpty() && password.isNotEmpty()) {
            view.showProgress()
            repository.makeAuth(
                login,
                password,
                {
                    view.hideProgress()
                    Log.i("TAG", it.toString())
                    QueryPreferences.setClientAuthData(it.login, it.password, it.id)
                    view.navigateToBottomNavigationActivity()
                },
                {
                    view.hideProgress()
                    Log.i("TAG", it)
                    view.showToastMessage(it)
                }
            )
        } else {
            view.showToastMessage("Заполните все поля!")
        }
    }

    override fun onRegisterAccountClicked() {
        view.navigateToRegisterFragment()
    }
}