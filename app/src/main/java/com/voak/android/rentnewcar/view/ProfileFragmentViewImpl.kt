package com.voak.android.rentnewcar.view

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.ImageView
import android.widget.ProgressBar
import android.widget.TextView
import androidx.fragment.app.Fragment
import com.voak.android.rentnewcar.MyApplication
import com.voak.android.rentnewcar.R
import com.voak.android.rentnewcar.di.components.DaggerProfileFragmentComponent
import com.voak.android.rentnewcar.di.modules.ProfileFragmentModule
import com.voak.android.rentnewcar.presenter.ProfileFragmentPresenter
import javax.inject.Inject

class ProfileFragmentViewImpl : Fragment(), ProfileFragmentView {

    companion object {
        fun newInstance() : ProfileFragmentViewImpl {
            return ProfileFragmentViewImpl()
        }
    }

    private lateinit var nameTextView: TextView
    private lateinit var phoneTextView: TextView
    private lateinit var addressTextView: TextView
    private lateinit var loginTextView: TextView
    private lateinit var progress: ProgressBar
    private lateinit var errorTextView: TextView
    private lateinit var sadIcon: ImageView
    private lateinit var errorDescription: TextView
    private lateinit var editButton: Button
    private lateinit var changePasswordButton: Button

    @Inject lateinit var presenter: ProfileFragmentPresenter
    private lateinit var navigationCallback: NavigationCallback

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        DaggerProfileFragmentComponent.builder()
            .appComponent(MyApplication.instance.getAppComponent())
            .profileFragmentModule(ProfileFragmentModule(this))
            .build()
            .inject(this)

    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val view = inflater.inflate(R.layout.fragment_profile, container, false)

        loginTextView = view.findViewById(R.id.login_text_view)
        nameTextView = view.findViewById(R.id.fio_text_view)
        phoneTextView = view.findViewById(R.id.phone_text_view)
        addressTextView = view.findViewById(R.id.address_text_view)
        progress = view.findViewById(R.id.profile_progress)
        errorTextView = view.findViewById(R.id.profile_error_text_view)
        sadIcon = view.findViewById(R.id.profile_sad_icon)
        errorDescription = view.findViewById(R.id.profile_error_description)
        editButton = view.findViewById(R.id.edit_button)
        changePasswordButton = view.findViewById(R.id.change_password_button)

        editButton.setOnClickListener {
            presenter.onEditInfoButtonClicked()
        }

        changePasswordButton.setOnClickListener {
            presenter.onChangePasswordButtonClicked()
        }

        presenter.onCreateView()

        return view
    }

    override fun setLogin(login: String) {
        loginTextView.text = getString(R.string.profile_login, login)
    }

    override fun setName(name: String) {
        nameTextView.text = getString(R.string.profile_name, name)
    }

    override fun setPhone(phone: String) {
        phoneTextView.text = getString(R.string.profile_phone, phone)
    }

    override fun setAddress(address: String) {
        addressTextView.text = getString(R.string.profile_address, address)
    }

    override fun setErrorDescription(error: String) {
        errorDescription.text = error
    }

    override fun showProgress() {
        progress.visibility = View.VISIBLE
    }

    override fun hideProgress() {
        progress.visibility = View.GONE
    }

    override fun showUserData() {
        loginTextView.visibility = View.VISIBLE
        nameTextView.visibility = View.VISIBLE
        phoneTextView.visibility = View.VISIBLE
        addressTextView.visibility = View.VISIBLE
    }

    override fun hideUserData() {
        loginTextView.visibility = View.GONE
        nameTextView.visibility = View.GONE
        phoneTextView.visibility = View.GONE
        addressTextView.visibility = View.GONE
    }

    override fun showButtons() {
        editButton.visibility = View.VISIBLE
        changePasswordButton.visibility = View.VISIBLE
    }

    override fun hideButtons() {
        editButton.visibility = View.GONE
        changePasswordButton.visibility = View.GONE
    }

    override fun showErrorMessage() {
        sadIcon.visibility = View.VISIBLE
        errorTextView.visibility = View.VISIBLE
        errorDescription.visibility = View.VISIBLE
    }

    override fun hideErrorMessage() {
        sadIcon.visibility = View.GONE
        errorTextView.visibility = View.GONE
        errorDescription.visibility = View.GONE
    }

    override fun navigateToEditInfoFragment() {
        navigationCallback.navigateToEditInfoFragment(
            loginTextView.text.toString().split(" ")[1],
            nameTextView.text.toString().split(" ")[2],
            nameTextView.text.toString().split(" ")[1],
            nameTextView.text.toString().split(" ")[3],
            addressTextView.text.toString().split("Адрес: ")[1],
            phoneTextView.text.toString().split(" ")[1]
        )
    }

    override fun navigateToEditPasswordFragment() {
        navigationCallback.navigateToEditPasswordFragment()
    }

    fun setNavigationCallback(callback: NavigationCallback) {
        navigationCallback = callback
    }

    interface NavigationCallback {
        fun navigateToEditInfoFragment(
            login: String,
            firstName: String,
            secondName: String,
            middleName: String,
            address: String,
            phone: String
        )

        fun navigateToEditPasswordFragment()
    }
}