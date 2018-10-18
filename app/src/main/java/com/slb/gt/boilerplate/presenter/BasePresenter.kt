package com.slb.gt.boilerplate.presenter

import android.content.Context
import com.slb.gt.boilerplate.activities.BaseActivity
import com.slb.gt.boilerplate.activities.LoginActivity
import com.slb.gt.boilerplate.contracts.BaseContracts
import com.slb.gt.boilerplate.interactor.BaseInteractor_
import com.slb.gt.boilerplate.router.BaseRouter_
import org.androidannotations.annotations.AfterInject
import org.androidannotations.annotations.EBean
import org.androidannotations.annotations.RootContext

@EBean
open class BasePresenter<
        V : BaseContracts.View,
        I : BaseContracts.Interactor,
        R : BaseContracts.Router>
    : BaseContracts.Presenter {

    lateinit var view: V
    lateinit var activity: BaseActivity<*>

    @RootContext
    open fun setContext(context: Context) {
        view = context as V
        activity = context as BaseActivity<*>
    }

    lateinit var router: R
    lateinit var interactor: I

    override fun logout() {interactor.logout(); router.goToLogin()}
    override fun onError(e: Exception) {
        view.hideLoading()
        view.showSnackbar(e.localizedMessage)
    }

    @AfterInject
    open fun checkAuth() {
        if (!::router.isInitialized)
            router = BaseRouter_.getInstance_(activity) as R

        if (!::interactor.isInitialized)
            interactor = BaseInteractor_.getInstance_(activity) as I

        if (activity !is LoginActivity)
            interactor.validateAuth({}, {logout()})
    }
}