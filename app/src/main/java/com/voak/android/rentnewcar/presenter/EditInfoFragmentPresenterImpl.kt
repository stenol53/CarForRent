package com.voak.android.rentnewcar.presenter

import android.service.autofill.UserData
import com.voak.android.rentnewcar.repository.EditInfoFragmentRepository
import com.voak.android.rentnewcar.utils.QueryPreferences
import com.voak.android.rentnewcar.view.EditInfoFragmentView

class EditInfoFragmentPresenterImpl(
    val view: EditInfoFragmentView,
    val repository: EditInfoFragmentRepository
) : EditInfoFragmentPresenter {

    override fun onChangeButtonClicked(
        login: String,
        firstName: String,
        secondName: String,
        middleName: String,
        phone: String,
        address: String
    ) {
        val data = com.voak.android.rentnewcar.model.UserData(
            QueryPreferences.getClientId(),
            login,
            "",
            firstName,
            secondName,
            middleName,
            phone,
            address,
            ""
        )

        view.hideEditButton()
        view.showProgress()
        repository.updateClientInfo(
            data,
            {
                view.hideProgress()
                view.showEditButton()
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