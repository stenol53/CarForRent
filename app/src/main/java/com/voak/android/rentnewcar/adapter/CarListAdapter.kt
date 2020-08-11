package com.voak.android.rentnewcar.adapter

import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.squareup.picasso.LruCache
import com.squareup.picasso.OkHttp3Downloader
import com.squareup.picasso.Picasso
import com.voak.android.rentnewcar.MyApplication
import com.voak.android.rentnewcar.R
import com.voak.android.rentnewcar.model.Car
import com.voak.android.rentnewcar.utils.IMAGE_BASE_URL
import com.voak.android.rentnewcar.utils.PicassoObj
import com.voak.android.rentnewcar.utils.UnsafeOkHttpClient
import com.voak.android.rentnewcar.utils.UnsafeOkHttpClient.getUnsafeOkHttpClient
import okhttp3.OkHttpClient
import java.util.concurrent.Executors

class CarListAdapter(
    private var carList: List<Car>,
    private val holderCallBack: (Int) -> Unit
) : RecyclerView.Adapter<CarListAdapter.CarHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): CarHolder {
        val view = LayoutInflater.from(parent.context)
            .inflate(R.layout.car_item, parent, false)

        return CarHolder(view)
    }

    override fun getItemCount(): Int = carList.size

    override fun onBindViewHolder(holder: CarHolder, position: Int) {
        holder.bind(carList[position])
    }

    fun setCars(carList: List<Car>) {
        this.carList = carList
        notifyDataSetChanged()
    }

    inner class CarHolder(itemView: View) : RecyclerView.ViewHolder(itemView), View.OnClickListener {
        private val brand: TextView = itemView.findViewById(R.id.car_brand)
        private val image: ImageView = itemView.findViewById(R.id.car_image)
        private val price: TextView = itemView.findViewById(R.id.car_price)
        private lateinit var car: Car

        init {
            itemView.setOnClickListener(this)
        }

        fun bind(car: Car) {
            this.car = car
            brand.text = car.brand
            price.text = "${car.price} рублей/день"

//            val downloader = OkHttp3Downloader(getUnsafeOkHttpClient())
//            Picasso.Builder(MyApplication.instance.applicationContext)
//                .downloader(downloader)
//                .memoryCache(LruCache(1048576))
//                .executor(Executors.newSingleThreadExecutor())
//                .indicatorsEnabled(true)
//                .build()
//                .load(IMAGE_BASE_URL + car.imageUrl)
//                .into(image)

            PicassoObj.getPicasso()
                .load(IMAGE_BASE_URL + car.imageUrl)
                .into(image)
        }

        override fun onClick(p0: View?) {
            Log.i("ADAPTER", "clicked")
            holderCallBack.invoke(car.id)
        }
    }
}