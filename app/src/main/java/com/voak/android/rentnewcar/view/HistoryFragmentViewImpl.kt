package com.voak.android.rentnewcar.view

import android.os.Bundle
import android.util.Log
import android.view.Gravity
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.ProgressBar
import android.widget.TextView
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.voak.android.rentnewcar.MyApplication
import com.voak.android.rentnewcar.R
import com.voak.android.rentnewcar.adapter.HistoryListAdapter
import com.voak.android.rentnewcar.di.components.DaggerHistoryFragmentComponent
import com.voak.android.rentnewcar.di.modules.HistoryFragmentModule
import com.voak.android.rentnewcar.model.History
import com.voak.android.rentnewcar.presenter.HistoryFragmentPresenter
import java.util.*
import javax.inject.Inject

class HistoryFragmentViewImpl : Fragment(), HistoryFragmentView {

    companion object {
        fun newInstance(): HistoryFragmentViewImpl {
            return HistoryFragmentViewImpl()
        }
    }

    private lateinit var recyclerView: RecyclerView
    private lateinit var progress: ProgressBar

    @Inject lateinit var presenter: HistoryFragmentPresenter
    private lateinit var adapter: HistoryListAdapter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        DaggerHistoryFragmentComponent.builder()
            .appComponent(MyApplication.instance.getAppComponent())
            .historyFragmentModule(HistoryFragmentModule(this))
            .build()
            .inject(this)
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val view = inflater.inflate(R.layout.fragment_history, container, false)

        adapter = HistoryListAdapter(Collections.emptyList(), resources)

        progress = view.findViewById(R.id.history_progress)
        recyclerView = view.findViewById(R.id.history_recycler_view)
        recyclerView.layoutManager = LinearLayoutManager(context)
        recyclerView.adapter = adapter
        presenter.onCreateView()

        return view
    }

    override fun setData(histories: List<History>) {
        adapter.setHistoryList(histories)
    }

    override fun showRecyclerView() {
        recyclerView.visibility = View.VISIBLE
    }

    override fun hideRecyclerView() {
        recyclerView.visibility = View.GONE
    }

    override fun showProgress() {
        progress.visibility = View.VISIBLE
    }

    override fun hideProgress() {
        progress.visibility = View.GONE
    }

    override fun showToastMessage(message: String) {
        val toast = Toast.makeText(context, message, Toast.LENGTH_LONG)
        toast.setGravity(Gravity.BOTTOM, 0, 20)
        toast.show()
    }
}