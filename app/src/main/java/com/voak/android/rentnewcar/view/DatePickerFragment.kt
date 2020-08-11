package com.voak.android.rentnewcar.view

import android.app.Dialog
import android.content.ComponentCallbacks
import android.content.DialogInterface
import android.os.Bundle
import android.view.LayoutInflater
import android.widget.DatePicker
import androidx.appcompat.app.AlertDialog
import androidx.fragment.app.DialogFragment
import com.voak.android.rentnewcar.R
import kotlinx.android.synthetic.main.dialog_date_picker.view.*
import java.util.*

class DatePickerFragment : DialogFragment() {

    private lateinit var datePicker: DatePicker
    private lateinit var callback: (String) -> Unit

    companion object {
        private const val ARG_DATE = "date"

        fun newInstance(date: Date): DatePickerFragment {
            val args = Bundle()
            args.putSerializable(ARG_DATE, date)
            val fragment = DatePickerFragment()
            fragment.arguments = args

            return fragment
        }
    }

    override fun onCreateDialog(savedInstanceState: Bundle?): Dialog {
        val date = requireArguments().getSerializable(ARG_DATE) as Date

        val calendar = Calendar.getInstance()
        calendar.time = date
        val year = calendar.get(Calendar.YEAR)
        val month = calendar.get(Calendar.MONTH)
        val day = calendar.get(Calendar.DAY_OF_MONTH)

        val view = LayoutInflater.from(activity)
            .inflate(R.layout.dialog_date_picker, null)

        datePicker = view.findViewById(R.id.dialog_date_picker)
        datePicker.init(year, month, day, null)
        datePicker.minDate = Calendar.getInstance().timeInMillis


        return AlertDialog.Builder(requireContext())
            .setView(view)
            .setPositiveButton(android.R.string.ok) { dialog, which ->
                val year = datePicker.year
                val month = datePicker.month + 1
                val day = datePicker.dayOfMonth

                if (month >= 10) {
                    callback.invoke("$day.$month.$year")
                } else {
                    callback.invoke("$day.0$month.$year")
                }
            }
            .create()
    }

    fun setCallback(callback: (String) -> Unit) {
        this.callback = callback
    }

}