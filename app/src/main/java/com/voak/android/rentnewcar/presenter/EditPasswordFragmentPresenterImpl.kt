package com.voak.android.rentnewcar.presenter

import com.voak.android.rentnewcar.repository.EditPasswordFragmentRepository
import com.voak.android.rentnewcar.utils.QueryPreferences
import com.voak.android.rentnewcar.view.EditPasswordFragmentView

class EditPasswordFragmentPresenterImpl(
    val view: EditPasswordFragmentView,
    val repository: EditPasswordFragmentRepository
) : EditPasswordFragmentPresenter {

    override fun onAcceptButtonClicked(
        oldPassword: String,
        newPassword: String,
        repeatPassword: String
    ) {
        when {
            oldPassword.isEmpty() || newPassword.isEmpty() || repeatPassword.isEmpty() -> {
                view.showToastMessage("Заполните все поля!")
            }
            oldPassword == newPassword -> {
                view.showToastMessage("Старый и новый пароль совпадают!")
            }
            newPassword != repeatPassword -> {
                view.showToastMessage("Повтор пароля должен совпадать с новым!")
            }
            else -> {
                // repository
                view.hideEditButton()
                view.showProgress()
                repository.updateClientPassword(
                    QueryPreferences.getClientId(),
                    oldPassword,
                    newPassword,
                    {
                        view.hideProgress()
                        view.showEditButton()
                        view.showToastMessage("Пароль успешно изменен")
                        view.navigateToProfileFragment()
                    },
                    {
                        view.hideProgress()
                        view.showEditButton()
                        view.showToastMessage(it)
                    }
                )
            }
        }

    }
}