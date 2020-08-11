package com.voak.android.rentnewcar.adapter

import android.content.res.Resources
import android.text.format.DateFormat
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.voak.android.rentnewcar.R
import com.voak.android.rentnewcar.model.History
import com.voak.android.rentnewcar.utils.IMAGE_BASE_URL
import com.voak.android.rentnewcar.utils.PicassoObj
import java.util.*
import java.util.concurrent.TimeUnit

class HistoryListAdapter(
    private var histories: List<History>,
    private val resources: Resources
) : RecyclerView.Adapter<HistoryListAdapter.HistoryHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): HistoryHolder {
        val view = LayoutInflater.from(parent.context)
            .inflate(R.layout.history_item, parent, false)

        return HistoryHolder(view)
    }

    override fun getItemCount(): Int = histories.size

    override fun onBindViewHolder(holder: HistoryHolder, position: Int) {
        holder.bind(histories[position])
    }

    fun setHistoryList(histories: List<History>) {
        this.histories = histories
        notifyDataSetChanged()
    }

    inner class HistoryHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        private val carImage: ImageView = itemView.findViewById(R.id.history_car_image)
        private val brand: TextView = itemView.findViewById(R.id.history_car_brand)
        private val startDate: TextView = itemView.findViewById(R.id.history_star_date)
        private val endDate: TextView = itemView.findViewById(R.id.history_end_date)
        private val cost: TextView = itemView.findViewById(R.id.history_total_cost)
        private val state: TextView = itemView.findViewById(R.id.history_state)

        fun bind(history: History) {
            brand.text = resources.getString(R.string.car, history.carBrand)
            var status: String = when (history.state) {
                "active" -> "активен"
                "finished" -> "завершен"
                "canceled" -> "отменен"
                else -> "неизвестно"
            }
            state.text = resources.getString(R.string.state, status)
            val date1 = Date(history.startDate)
            val date2 = Date(history.endDate)
            startDate.text = resources.getString(R.string.start_date, DateFormat.format("dd.MM.yyyy", date1))
            endDate.text = resources.getString(R.string.end_date, DateFormat.format("dd.MM.yyyy", date2))
            val days = TimeUnit.DAYS.convert((date2.time - date1.time), TimeUnit.MILLISECONDS).toInt()
            cost.text = resources.getString(R.string.cost, days * history.carPrice)

            PicassoObj.getPicasso()
                .load(IMAGE_BASE_URL + history.imageUrl)
                .into(carImage)
        }


    }
}