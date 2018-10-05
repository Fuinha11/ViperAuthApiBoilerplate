package com.slb.gt.boilerplate.interactor

import com.slb.gt.boilerplate.contracts.ListContracts
import com.slb.gt.boilerplate.data.models.Driver
import com.slb.gt.boilerplate.services.DatabaseService
import com.slb.gt.boilerplate.utils.interfaces.SuccessFailCallback
import org.androidannotations.annotations.Bean
import org.androidannotations.annotations.EBean

@EBean
open class ListInteractor : BaseInteractor<ListContracts.Presenter>(), ListContracts.Interactor {

    @Bean
    lateinit var dbService: DatabaseService

    var fetchingDrivers = false
    override fun getMoreData() {
        if (!fetchingDrivers) {
            fetchingDrivers = true
            dbService.getMoreDrivers(object : SuccessFailCallback<List<Driver>, Exception> {
                override fun onFailure(data: Exception) {
                    fetchingDrivers = false
                    presenter.onDataFail(data)
                }

                override fun onSuccess(data: List<Driver>) {
                    fetchingDrivers = false
                    presenter.onDataReceived(data)
                }
            })
        }
    }

    override fun getInitialData() {
        dbService.getDrivers(object: SuccessFailCallback<List<Driver>, Exception> {
            override fun onFailure(data: Exception) {
                presenter.onDataFail(data)
            }

            override fun onSuccess(data: List<Driver>) {
                presenter.onDataReceived(data)
            }
        })
    }

}
