package com.voak.android.rentnewcar.view

import android.os.Bundle
import android.text.format.DateFormat
import android.util.Log
import android.view.Gravity
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.*
import androidx.fragment.app.Fragment
import com.voak.android.rentnewcar.MyApplication
import com.voak.android.rentnewcar.R
import com.voak.android.rentnewcar.di.components.DaggerCarItemFragmentComponent
import com.voak.android.rentnewcar.di.modules.CarItemFragmentModule
import com.voak.android.rentnewcar.presenter.CarItemFragmentPresenter
import com.voak.android.rentnewcar.utils.IMAGE_BASE_URL
import com.voak.android.rentnewcar.utils.PicassoObj
import java.util.*
import javax.inject.Inject

class CarItemFragmentViewImpl : Fragment(), CarItemFragmentView {

    companion object {
        private const val REQUEST_DATE = 0
        private const val DIALOG_DATE = "DialogDate"
        private const val ARG_CAR_ID = "carId"

        fun newInstance(carId: Int): CarItemFragmentViewImpl {
            val fragment = CarItemFragmentViewImpl()

            val args = Bundle()
            args.putInt(ARG_CAR_ID, carId)
            fragment.arguments = args

            return fragment
        }
    }

    private lateinit var carImage: ImageView
    private lateinit var carPrice: TextView
    private lateinit var passengers: TextView
    private lateinit var bags: TextView
    private lateinit var doors: TextView
    private lateinit var totalCost: TextView
    private lateinit var startDate: TextView
    private lateinit var endDate: TextView
    private lateinit var makeOrderButton: Button
    private lateinit var progress: ProgressBar

    @Inject lateinit var presenter: CarItemFragmentPresenter
    private lateinit var navigationCallback: NavigationCallback

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        DaggerCarItemFragmentComponent.builder()
            .appComponent(MyApplication.instance.getAppComponent())
            .carItemFragmentModule(CarItemFragmentModule(this))
            .build()
            .inject(this)
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val view = inflater.inflate(R.layout.fragment_car_item, container, false)

        carImage = view.findViewById(R.id.car_item_image)
        carPrice = view.findViewById(R.id.car_item_price)
        passengers = view.findViewById(R.id.passengers)
        bags = view.findViewById(R.id.bags)
        doors = view.findViewById(R.id.doors)
        totalCost = view.findViewById(R.id.total_cost)
        startDate = view.findViewById(R.id.date_start)
        endDate = view.findViewById(R.id.date_end)
        makeOrderButton = view.findViewById(R.id.make_order_button)
        progress = view.findViewById(R.id.car_item_progress)

        startDate.setOnClickListener {
            presenter.onDateTextViewClicked(
                startDate.text.split(": ")[1]) {
                setStartDate(it)
                presenter.onDateChanged(it, endDate.text.split(": ")[1])
            }
        }

        endDate.setOnClickListener {
            presenter.onDateTextViewClicked(
                endDate.text.split(": ")[1]) {
                setEndDate(it)
                presenter.onDateChanged(startDate.text.split(": ")[1], it)
            }
        }

        makeOrderButton.setOnClickListener {
            presenter.onMakeOrderButtonClicked(
                requireArguments().getInt(ARG_CAR_ID),
                startDate.text.split(": ")[1],
                endDate.text.split(": ")[1]
            )
        }

        presenter.onCreateView(requireArguments().getInt(ARG_CAR_ID))

        return view
    }

    override fun showToastMessage(message: String) {
        val toast = Toast.makeText(context, message, Toast.LENGTH_LONG)
        toast.setGravity(Gravity.BOTTOM, 0, 20)
        toast.show()
    }

    override fun openDatePickerFragment(date: Date, callback: (String) -> Unit) {
        val dialog = DatePickerFragment.newInstance(date)
        dialog.setCallback(callback)
        dialog.setTargetFragment(this, REQUEST_DATE)
        dialog.show(parentFragmentManager, DIALOG_DATE)
    }

    override fun setCarBrand(brand: String) {
        requireActivity().title = brand
    }

    override fun setCarImage(imageUrl: String) {
        PicassoObj.getPicasso()
            .load(IMAGE_BASE_URL + imageUrl)
            .into(carImage)
    }

    override fun setCarPrice(price: Int) {
        carPrice.text = getString(R.string.car_price, price)
    }

    override fun setTotalCost(cost: Int) {
        totalCost.text = getString(R.string.total_cost, cost)
    }

    override fun setPassengersCount(passengers: Int) {
        this.passengers.text = resources.getQuantityString(R.plurals.passengers_plural, passengers, passengers)
    }

    override fun setBagsCount(bags: Int) {
        this.bags.text = resources.getQuantityString(R.plurals.bags_plural, bags, bags)
    }

    override fun setDoorsCount(doors: Int) {
        this.doors.text = resources.getQuantityString(R.plurals.doors_plural, doors, doors)
    }

    override fun setStartDate(date: String) {
        startDate.text = getString(R.string.start_date, date)
    }

    override fun setEndDate(date: String) {
        endDate.text = getString(R.string.end_date, date)
    }

    override fun showProgress() {
        progress.visibility = View.VISIBLE
    }

    override fun hideProgress() {
        progress.visibility = View.GONE
    }

    override fun showAllViews() {
        carImage.visibility = View.VISIBLE
        carPrice.visibility = View.VISIBLE
        passengers.visibility = View.VISIBLE
        bags.visibility = View.VISIBLE
        doors.visibility = View.VISIBLE
        totalCost.visibility = View.VISIBLE
        startDate.visibility = View.VISIBLE
        endDate.visibility = View.VISIBLE
        makeOrderButton.visibility = View.VISIBLE
    }

    override fun hideAllViews() {
        carImage.visibility = View.GONE
        carPrice.visibility = View.GONE
        passengers.visibility = View.GONE
        bags.visibility = View.GONE
        doors.visibility = View.GONE
        totalCost.visibility = View.GONE
        startDate.visibility = View.GONE
        endDate.visibility = View.GONE
        makeOrderButton.visibility = View.GONE
    }

    override fun showMakeOrderButton() {
        makeOrderButton.visibility = View.VISIBLE
    }

    override fun hideMakeOrderButton() {
        makeOrderButton.visibility = View.GONE
    }

    override fun navigateToHistoryFragment() {
        navigationCallback.navigateToHistoryFragment()
    }

    fun setNavigationCallback(callback: NavigationCallback) {
        navigationCallback = callback
    }

    interface NavigationCallback {
        fun navigateToHistoryFragment()
    }
}