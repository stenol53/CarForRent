package com.voak.android.rentnewcar.view

import android.os.Bundle
import android.util.Log
import android.view.Gravity
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ProgressBar
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.voak.android.rentnewcar.MyApplication
import com.voak.android.rentnewcar.R
import com.voak.android.rentnewcar.adapter.CarListAdapter
import com.voak.android.rentnewcar.di.components.DaggerCarsFragmentComponent
import com.voak.android.rentnewcar.di.modules.CarsFragmentModule
import com.voak.android.rentnewcar.model.Car
import com.voak.android.rentnewcar.presenter.CarsFragmentPresenter
import java.util.*
import javax.inject.Inject

class CarsFragmentViewImpl : Fragment(), CarsFragmentView {

    companion object {
        fun newInstance(): CarsFragmentViewImpl {
            return CarsFragmentViewImpl()
        }
    }

    private lateinit var recyclerView: RecyclerView
    private lateinit var progress: ProgressBar
    private val adapter = CarListAdapter(Collections.emptyList()) { presenter.onItemClicked(it) }

    @Inject lateinit var presenter: CarsFragmentPresenter
    private lateinit var navigationCallback: NavigationCallback

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        DaggerCarsFragmentComponent.builder()
            .appComponent(MyApplication.instance.getAppComponent())
            .carsFragmentModule(CarsFragmentModule(this))
            .build()
            .inject(this)
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val view = inflater.inflate(R.layout.fragment_cars, container, false)
        progress = view.findViewById(R.id.car_list_progress)
        recyclerView = view.findViewById(R.id.car_recycler_view)
        recyclerView.layoutManager = LinearLayoutManager(context)
        recyclerView.adapter = adapter

        presenter.onCreateView()

        return view
    }

    override fun navigateToCarItemFragment(carId: Int) {
        navigationCallback.navigateToCarItemFragment(carId)
    }

    override fun showToastMessage(message: String) {
        val toast = Toast.makeText(context, message, Toast.LENGTH_LONG)
        toast.setGravity(Gravity.BOTTOM, 0, 20)
        toast.show()
    }

    override fun updateCarList(cars: List<Car>) {
        adapter.setCars(cars)
    }

    override fun showProgress() {
        progress.visibility = View.VISIBLE
    }

    override fun hideProgress() {
        progress.visibility = View.GONE
    }

    override fun showCarList() {
        recyclerView.visibility = View.VISIBLE
    }

    override fun hideCarList() {
        recyclerView.visibility = View.GONE
    }

    fun setNavigationCallback(callback: NavigationCallback) {
        navigationCallback = callback
    }

    interface NavigationCallback {
        fun navigateToCarItemFragment(carId: Int)
    }
}