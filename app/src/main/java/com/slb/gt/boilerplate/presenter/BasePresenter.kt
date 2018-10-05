package com.slb.gt.boilerplate.presenter

import com.slb.gt.boilerplate.activities.BaseActivity
import com.slb.gt.boilerplate.contracts.BaseContracts

abstract class BasePresenter<
        V : BaseContracts.View,
        I : BaseContracts.Interactor,
        R : BaseContracts.Router> (
        val view: V,
        val activity: BaseActivity<*>)
    : BaseContracts.Presenter {

    val interactor: I = initiateInteractor()
    val router : R = initiateRouter()

    override fun authFail() = router.goToLogin()
    override fun logout() = interactor.logout()
    abstract fun initiateInteractor(): I
    abstract fun initiateRouter(): R
}