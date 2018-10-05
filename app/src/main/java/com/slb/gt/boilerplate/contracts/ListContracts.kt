package com.slb.gt.boilerplate.contracts

import com.slb.gt.boilerplate.data.models.Driver

class ListContracts {
    interface View: BaseContracts.View {
        fun logutClick()
        fun updateUi()
        fun addData(data: List<Driver>)
    }
    interface Interactor: BaseContracts.Interactor {
        fun getMoreData()
        fun getInitialData()
    }
    interface Presenter: BaseContracts.Presenter {
        fun onBottonReached()
        fun onDataReceived(data: List<Driver>)
        fun onDataFail(e: Exception)
        fun loadInitialData()
    }
    interface Router: BaseContracts.Router
}