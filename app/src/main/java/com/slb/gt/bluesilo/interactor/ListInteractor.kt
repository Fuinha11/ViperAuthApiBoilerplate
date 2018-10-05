package com.slb.gt.bluesilo.interactor

import com.slb.gt.bluesilo.contracts.ListContracts
import com.slb.gt.bluesilo.data.models.Driver
import com.slb.gt.bluesilo.services.DatabaseService
import com.slb.gt.bluesilo.utils.interfaces.SuccessFailCallback
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
