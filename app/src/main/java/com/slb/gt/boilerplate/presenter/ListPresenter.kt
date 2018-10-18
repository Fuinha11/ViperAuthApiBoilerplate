package com.slb.gt.boilerplate.presenter

import com.slb.gt.boilerplate.activities.BaseActivity
import com.slb.gt.boilerplate.contracts.ListContracts
import com.slb.gt.boilerplate.data.models.Driver
import com.slb.gt.boilerplate.interactor.ListInteractor
import com.slb.gt.boilerplate.interactor.ListInteractor_
import com.slb.gt.boilerplate.router.ListRouter
import org.androidannotations.annotations.Bean
import org.androidannotations.annotations.EBean

@EBean
open class ListPresenter

    : BasePresenter
<
        ListContracts.View,
        ListContracts.Interactor,
        ListContracts.Router>(),

        ListContracts.Presenter {

    open fun initBeans(@Bean r: ListRouter, @Bean i: ListInteractor){
        router = r
        interactor = i
    }

    override fun onBottomReached() {
        if (hasMoreData) {
            view.showLoading()
            interactor.getMoreData({data -> onDataReceived(data)}, {e -> onError(e)})
        }
    }

    override fun onDataReceived(data: List<Driver>) {
        view.hideLoading()
        view.addData(data)
    }

    override fun loadInitialData() {
        view.showLoading()
        interactor.getInitialData({data -> onDataReceived(data)}, {e -> onError(e)})
    }

    private var hasMoreData = true
    override fun onDataFail(e: Exception) {
        hasMoreData = e.message != "No more documents"
        view.hideLoading()
        view.showSnackbar(e.localizedMessage)
    }
}