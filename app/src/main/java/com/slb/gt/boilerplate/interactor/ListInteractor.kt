package com.slb.gt.boilerplate.interactor

import com.slb.gt.boilerplate.contracts.ListContracts
import com.slb.gt.boilerplate.data.models.Driver
import com.slb.gt.boilerplate.services.DatabaseService
import com.slb.gt.boilerplate.utils.interfaces.SuccessFailCallback
import org.androidannotations.annotations.Bean
import org.androidannotations.annotations.EBean

@EBean
open class ListInteractor : BaseInteractor(), ListContracts.Interactor {

    @Bean
    lateinit var dbService: DatabaseService

    var fetchingDrivers = false
    override fun getMoreData(success: (data: List<Driver>) -> Unit, fail: (e: Exception) -> Unit) {
        if (!fetchingDrivers) {
            fetchingDrivers = true
            dbService.getMoreDrivers(object : SuccessFailCallback<List<Driver>, Exception> {
                override fun onFailure(data: Exception) {
                    fetchingDrivers = false
                    fail(data)
                }

                override fun onSuccess(data: List<Driver>) {
                    fetchingDrivers = false
                    success(data)
                }
            })
        }
    }

    override fun getInitialData(success: (data: List<Driver>) -> Unit, fail: (e: Exception) -> Unit) {
        dbService.getDrivers(object: SuccessFailCallback<List<Driver>, Exception> {
            override fun onFailure(data: Exception) {
                fail(data)
            }

            override fun onSuccess(data: List<Driver>) {
                success(data)
            }
        })
    }

}
