package com.voak.android.rentnewcar

import androidx.fragment.app.Fragment
import android.os.Bundle
import com.google.android.material.bottomnavigation.BottomNavigationView
import androidx.appcompat.app.AppCompatActivity
import com.voak.android.rentnewcar.view.*


class BottomNavigationActivity : AppCompatActivity(), ProfileFragmentViewImpl.NavigationCallback,
    EditInfoFragmentViewImpl.NavigationCallback, EditPasswordFragmentViewIml.NavigationCallback,
    CarsFragmentViewImpl.NavigationCallback, CarItemFragmentViewImpl.NavigationCallback {

    private lateinit var curFragment: Fragment
    private lateinit var navView: BottomNavigationView

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_bottom_navigation)
        navView = findViewById(R.id.nav_view)

        navView.setOnNavigationItemSelectedListener { item ->
            when (item.itemId) {
                R.id.navigation_cars -> {
                    val fragment = CarsFragmentViewImpl.newInstance()
                    fragment.setNavigationCallback(this)
                    curFragment = fragment
                    openFragment(curFragment)
                    true
                }
                R.id.navigation_history -> {
                    curFragment = HistoryFragmentViewImpl.newInstance()
                    openFragment(curFragment)
                    true
                }
                R.id.navigation_profile -> {
                    val fragment = ProfileFragmentViewImpl.newInstance()
                    fragment.setNavigationCallback(this)
                    curFragment = fragment
                    openFragment(curFragment)
                    true
                }
                else -> false
            }
        }
        navView.selectedItemId = R.id.navigation_cars
    }

    private fun openFragment(fragment: Fragment) {
        title = "Car4Rent"
        supportFragmentManager.beginTransaction()
            .replace(R.id.nav_host_fragment, fragment)
            .commit()
    }

    override fun onBackPressed() {
        when(curFragment) {
             is EditInfoFragmentViewImpl -> {
                 navigateToProfileFragment()
            }
            is EditPasswordFragmentViewIml -> {
                navigateToProfileFragment()
            }
            is CarItemFragmentViewImpl -> {
                val fragment = CarsFragmentViewImpl.newInstance()
                fragment.setNavigationCallback(this)
                curFragment = fragment
                openFragment(curFragment)
            }
            else -> super.onBackPressed()
        }
    }

    override fun navigateToEditPasswordFragment() {
        val fragment = EditPasswordFragmentViewIml()
        fragment.setNavigationCallback(this)
        curFragment = fragment
        supportFragmentManager.beginTransaction()
            .replace(R.id.nav_host_fragment, curFragment)
            .commit()
    }

    override fun navigateToEditInfoFragment(
        login: String,
        firstName: String,
        secondName: String,
        middleName: String,
        address: String,
        phone: String
    ) {

        val fragment = EditInfoFragmentViewImpl.newInstance(
            login,
            firstName,
            secondName,
            middleName,
            phone,
            address
        )
        fragment.setNavigationCallback(this)
        curFragment = fragment

        supportFragmentManager.beginTransaction()
            .replace(R.id.nav_host_fragment, curFragment)
            .commit()
    }

    override fun navigateToProfileFragment() {
        val fragment = ProfileFragmentViewImpl.newInstance()
        fragment.setNavigationCallback(this)
        curFragment = fragment
        openFragment(curFragment)
    }

    override fun navigateToCarItemFragment(carId: Int) {
        val fragment = CarItemFragmentViewImpl.newInstance(carId)
        fragment.setNavigationCallback(this)
        curFragment = fragment
        openFragment(curFragment)
    }

    override fun navigateToHistoryFragment() {
        navView.selectedItemId = R.id.navigation_history
    }
}