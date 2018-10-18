package com.slb.gt.boilerplate.contracts

import com.slb.gt.boilerplate.data.models.Driver

class ListContracts {
    interface View: BaseContracts.View {
        fun logoutClick()
        fun updateUi()
        fun addData(data: List<Driver>)
    }
    interface Interactor: BaseContracts.Interactor {
        fun getMoreData(success: (data: List<Driver>) -> Unit, fail: (e: Exception)-> Unit)
        fun getInitialData(success: (data: List<Driver>) -> Unit, fail: (e: Exception)-> Unit)
    }
    interface Presenter: BaseContracts.Presenter {
        fun onBottomReached()
        fun onDataReceived(data: List<Driver>)
        fun onDataFail(e: Exception)
        fun loadInitialData()
    }
    interface Router: BaseContracts.Router
}