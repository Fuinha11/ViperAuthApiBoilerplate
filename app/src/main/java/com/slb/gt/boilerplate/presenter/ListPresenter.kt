package com.slb.gt.boilerplate.presenter

import com.slb.gt.boilerplate.activities.BaseActivity
import com.slb.gt.boilerplate.contracts.ListContracts
import com.slb.gt.boilerplate.data.models.Driver
import com.slb.gt.boilerplate.interactor.ListInteractor_
import com.slb.gt.boilerplate.router.ListRouter

class ListPresenter
(
        view: ListContracts.View,
        activity: BaseActivity<*>)

    : BasePresenter
<
        ListContracts.View,
        ListContracts.Interactor,
        ListContracts.Router>
(
        view,
        activity),
        ListContracts.Presenter {

    override fun initiateInteractor(): ListContracts.Interactor {
        val i = ListInteractor_.getInstance_(activity)
        i.initiate(activity,this)
        return i
    }

    override fun initiateRouter(): ListContracts.Router {
        return ListRouter(activity)
    }

    override fun onBottonReached() {
        if (hasMoreData) {
            view.showLoading()
            interactor.getMoreData()
        }
    }

    override fun onDataReceived(data: List<Driver>) {
        view.hideLoading()
        view.addData(data)
    }

    override fun loadInitialData() {
        view.showLoading()
        interactor.getInitialData()
    }

    private var hasMoreData = true
    override fun onDataFail(e: Exception) {
        hasMoreData = e.message != "No more documents"
        view.hideLoading()
        view.showSnackbar(e.localizedMessage)
    }
}