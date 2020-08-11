package com.voak.android.rentnewcar

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.fragment.app.Fragment
import com.voak.android.rentnewcar.di.components.DaggerMainActivityComponent
import com.voak.android.rentnewcar.di.modules.MainActivityModule
import com.voak.android.rentnewcar.view.LoginFragmentViewImpl
import com.voak.android.rentnewcar.view.RegisterFragmentViewImpl
import javax.inject.Inject

class MainActivity : AppCompatActivity(), LoginFragmentViewImpl.OnCreateNewAccountListener,
    RegisterFragmentViewImpl.NavigationCallback {

    @Inject lateinit var loginFragment: LoginFragmentViewImpl
    @Inject lateinit var registerFragment: RegisterFragmentViewImpl

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        DaggerMainActivityComponent.builder()
            .mainActivityModule(MainActivityModule())
            .build()
            .inject(this)

        val fm = supportFragmentManager

        fm.beginTransaction()
            .add(R.id.fragment_container, loginFragment)
            .commit()
    }

    override fun onAttachFragment(fragment: Fragment) {
        when (fragment) {
            is LoginFragmentViewImpl -> fragment.setOnCreateNewAccountListener(this)
            is RegisterFragmentViewImpl -> fragment.setNavigationCallback(this)
        }
    }

    override fun onCreateNewAccountClicked() {
        supportFragmentManager.beginTransaction()
            .replace(R.id.fragment_container, registerFragment)
            .addToBackStack(null)
            .commit()
    }

    override fun navigateToLoginFragment() {
        onBackPressed()
    }
}