package com.slb.gt.bluesilo.activities

import android.support.v7.widget.LinearLayoutManager
import com.slb.gt.bluesilo.R
import com.slb.gt.bluesilo.adapters.DriverListAdapter
import com.slb.gt.bluesilo.contracts.ListContracts
import com.slb.gt.bluesilo.data.models.Driver
import com.slb.gt.bluesilo.presenter.ListPresenter
import com.slb.gt.bluesilo.utils.extensions.setTopBottonListener
import com.slb.gt.bluesilo.utils.interfaces.TopBottonListener
import kotlinx.android.synthetic.main.activity_list.*
import org.androidannotations.annotations.Click
import org.androidannotations.annotations.EActivity
import org.androidannotations.annotations.UiThread

@EActivity(R.layout.activity_list)
open class ListActivity : BaseActivity<ListContracts.Presenter>(), ListContracts.View {

    var drivers: ArrayList<Driver> = arrayListOf()
    lateinit var adapter: DriverListAdapter

    @UiThread
    override fun updateUi() {
        if (!::adapter.isInitialized)
            initList()
        adapter.notifyDataSetChanged()
    }

    override fun addData(data: List<Driver>) {
        drivers.addAll(data as ArrayList<Driver>)
        updateUi()
    }

    override fun initiatePresenter(): ListContracts.Presenter = ListPresenter(this, this)

    override fun initComponents() {
        presenter.loadInitialData()
    }

    open fun initList() {
        driverRV.layoutManager = LinearLayoutManager(this)
        adapter = DriverListAdapter(drivers, this)
        driverRV.adapter = adapter
        driverRV.setTopBottonListener(object: TopBottonListener{
            override fun onTopReached() {}

            override fun onBottonReached() {
                presenter.onBottonReached()
            }

        })
    }

    @Click(R.id.logoutBtn)
    override fun logutClick() {
        presenter.logout()
    }
}