package com.voak.android.rentnewcar.view

import android.os.Bundle
import android.view.Gravity
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.EditText
import android.widget.ProgressBar
import android.widget.Toast
import androidx.fragment.app.Fragment
import com.voak.android.rentnewcar.MyApplication
import com.voak.android.rentnewcar.R
import com.voak.android.rentnewcar.di.components.DaggerEditInfoFragmentComponent
import com.voak.android.rentnewcar.di.modules.EditInfoFragmentModule
import com.voak.android.rentnewcar.presenter.EditInfoFragmentPresenter
import javax.inject.Inject

class EditInfoFragmentViewImpl : Fragment(), EditInfoFragmentView {

    companion object {
        const val ARG_LOGIN = "login"
        const val ARG_FIRST_NAME = "first_name"
        const val ARG_SECOND_NAME = "second_name"
        const val ARG_MIDDLE_NAME = "middle_name"
        const val ARG_PHONE = "phone"
        const val ARG_ADDRESS = "address"

        fun newInstance(
            login: String,
            firstName: String,
            secondName: String,
            middleName: String,
            phone: String,
            address: String
        ): EditInfoFragmentViewImpl {
            val args = Bundle()
            args.putString(ARG_LOGIN, login)
            args.putString(ARG_FIRST_NAME, firstName)
            args.putString(ARG_SECOND_NAME, secondName)
            args.putString(ARG_MIDDLE_NAME, middleName)
            args.putString(ARG_PHONE, phone)
            args.putString(ARG_ADDRESS, address)

            val fragment = EditInfoFragmentViewImpl()
            fragment.arguments = args
            return fragment
        }
    }

    private lateinit var nameEditText: EditText
    private lateinit var surnameEditText: EditText
    private lateinit var fatherNameEditText: EditText
    private lateinit var phoneEditText: EditText
    private lateinit var addressEditText: EditText
    private lateinit var loginEditText: EditText
    private lateinit var changeButton: Button
    private lateinit var progress: ProgressBar

    @Inject lateinit var presenter: EditInfoFragmentPresenter
    private lateinit var navigationCallback: NavigationCallback

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        DaggerEditInfoFragmentComponent.builder()
            .appComponent(MyApplication.instance.getAppComponent())
            .editInfoFragmentModule(EditInfoFragmentModule(this))
            .build()
            .inject(this)
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val view = inflater.inflate(R.layout.fragment_edit_info, container, false)

        loginEditText = view.findViewById(R.id.edit_login)
        nameEditText = view.findViewById(R.id.edit_name)
        surnameEditText = view.findViewById(R.id.edit_surname)
        fatherNameEditText = view.findViewById(R.id.edit_father_name)
        phoneEditText = view.findViewById(R.id.edit_phone)
        addressEditText = view.findViewById(R.id.edit_address)
        changeButton = view.findViewById(R.id.change_info_button)
        progress = view.findViewById(R.id.edit_progress)

        loginEditText.setText(arguments?.getString(ARG_LOGIN))
        nameEditText.setText(arguments?.getString(ARG_FIRST_NAME))
        surnameEditText.setText(arguments?.getString(ARG_SECOND_NAME))
        fatherNameEditText.setText(arguments?.getString(ARG_MIDDLE_NAME))
        phoneEditText.setText(arguments?.getString(ARG_PHONE))
        addressEditText.setText(arguments?.getString(ARG_ADDRESS))


        changeButton.setOnClickListener {
            presenter.onChangeButtonClicked(
                loginEditText.text.toString(),
                nameEditText.text.toString(),
                surnameEditText.text.toString(),
                fatherNameEditText.text.toString(),
                phoneEditText.text.toString(),
                addressEditText.text.toString()
            )
        }

        return view
    }

    override fun navigateToProfileFragment() {
        navigationCallback.navigateToProfileFragment()
    }

    override fun showToastMessage(message: String) {
        val toast = Toast.makeText(context, message, Toast.LENGTH_LONG)
        toast.setGravity(Gravity.BOTTOM, 0, 20)
        toast.show()
    }

    override fun showEditButton() {
        changeButton.visibility = View.VISIBLE
    }

    override fun hideEditButton() {
        changeButton.visibility = View.GONE
    }

    override fun showProgress() {
        progress.visibility = View.VISIBLE
    }

    override fun hideProgress() {
        progress.visibility = View.GONE
    }

    fun setNavigationCallback(callback: NavigationCallback) {
        navigationCallback = callback
    }

    interface NavigationCallback {
        fun navigateToProfileFragment()
    }
}