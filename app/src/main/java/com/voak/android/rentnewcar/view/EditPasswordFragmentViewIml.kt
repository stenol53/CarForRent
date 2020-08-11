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
import com.voak.android.rentnewcar.di.components.DaggerEditPasswordFragmentComponent
import com.voak.android.rentnewcar.di.modules.EditPasswordFragmentModule
import com.voak.android.rentnewcar.presenter.EditPasswordFragmentPresenter
import javax.inject.Inject

class EditPasswordFragmentViewIml : Fragment(), EditPasswordFragmentView {

    private lateinit var oldPasswordEditText: EditText
    private lateinit var newPasswordEditText: EditText
    private lateinit var repeatPasswordEditText: EditText
    private lateinit var acceptButton: Button
    private lateinit var progress: ProgressBar

    @Inject lateinit var presenter: EditPasswordFragmentPresenter
    private lateinit var navigationCallback: NavigationCallback

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        DaggerEditPasswordFragmentComponent.builder()
            .appComponent(MyApplication.instance.getAppComponent())
            .editPasswordFragmentModule(EditPasswordFragmentModule(this))
            .build()
            .inject(this)
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val view = inflater.inflate(R.layout.fragment_edit_password, container, false)

        oldPasswordEditText = view.findViewById(R.id.edit_old_password)
        newPasswordEditText = view.findViewById(R.id.edit_new_password)
        repeatPasswordEditText = view.findViewById(R.id.edit_repeat_password)
        acceptButton = view.findViewById(R.id.accept_change_password_button)
        progress = view.findViewById(R.id.edit_password_progress)

        acceptButton.setOnClickListener {
            presenter.onAcceptButtonClicked(
                oldPasswordEditText.text.toString(),
                newPasswordEditText.text.toString(),
                repeatPasswordEditText.text.toString()
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

    override fun hideProgress() {
        progress.visibility = View.GONE
    }

    override fun showProgress() {
        progress.visibility = View.VISIBLE
    }

    override fun hideEditButton() {
        acceptButton.visibility = View.GONE
    }

    override fun showEditButton() {
        acceptButton.visibility = View.VISIBLE
    }

    fun setNavigationCallback(callback: NavigationCallback) {
        navigationCallback = callback
    }

    interface NavigationCallback {
        fun navigateToProfileFragment()
    }
}