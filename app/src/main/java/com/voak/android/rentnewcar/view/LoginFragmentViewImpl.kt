package com.voak.android.rentnewcar.view

import android.content.Intent
import android.os.Bundle
import android.view.Gravity
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.*
import androidx.fragment.app.Fragment
import com.voak.android.rentnewcar.BottomNavigationActivity
import com.voak.android.rentnewcar.MyApplication
import com.voak.android.rentnewcar.R
import com.voak.android.rentnewcar.di.components.DaggerLoginFragmentComponent
import com.voak.android.rentnewcar.di.modules.LoginFragmentModule
import com.voak.android.rentnewcar.presenter.LoginFragmentPresenter
import javax.inject.Inject

class LoginFragmentViewImpl : Fragment(), LoginFragmentView {

    private lateinit var loginEditText : EditText
    private lateinit var passwordEditText : EditText
    private lateinit var enterButton: Button
    private lateinit var registerTextView: TextView
    private lateinit var progressBar: ProgressBar

    @Inject lateinit var presenter: LoginFragmentPresenter

    private lateinit var callback: OnCreateNewAccountListener

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        DaggerLoginFragmentComponent.builder()
            .appComponent(MyApplication.instance.getAppComponent())
            .loginFragmentModule(LoginFragmentModule(this))
            .build()
            .inject(this)
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val view = inflater.inflate(R.layout.login_fragment, container, false)

        progressBar = view.findViewById(R.id.login_progress)
        loginEditText = view.findViewById(R.id.login_edit_text)
        passwordEditText = view.findViewById(R.id.password_edit_text)
        enterButton = view.findViewById(R.id.login_button)
        registerTextView = view.findViewById(R.id.register_text_view)

        enterButton.setOnClickListener {
            presenter.onEnterButtonClicked(loginEditText.text.toString(), passwordEditText.text.toString())
        }

        registerTextView.setOnClickListener {
            presenter.onRegisterAccountClicked()
        }

        presenter.onCreateView()

        return view
    }

    override fun navigateToRegisterFragment() {
        callback.onCreateNewAccountClicked()
    }

    override fun navigateToBottomNavigationActivity() {
        val intent = Intent(context, BottomNavigationActivity::class.java)
        startActivity(intent)
    }

    override fun setLogin(login: String) {
        loginEditText.setText(login)
    }

    override fun setPassword(password: String) {
        passwordEditText.setText(password)
    }

    override fun showProgress() {
        progressBar.visibility = View.VISIBLE
        enterButton.visibility = View.GONE
    }

    override fun hideProgress() {
        progressBar.visibility = View.GONE
        enterButton.visibility = View.VISIBLE
    }

    override fun showToastMessage(message: String) {
        val toast = Toast.makeText(context, message, Toast.LENGTH_LONG)
        toast.setGravity(Gravity.BOTTOM, 0, 20)
        toast.show()
    }

    fun setOnCreateNewAccountListener(callback: OnCreateNewAccountListener) {
        this.callback = callback
    }

    interface OnCreateNewAccountListener {
        fun onCreateNewAccountClicked()
    }
}