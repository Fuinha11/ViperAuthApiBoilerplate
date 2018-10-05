package com.slb.gt.bluesilo.presenter

import com.slb.gt.bluesilo.activities.BaseActivity
import com.slb.gt.bluesilo.contracts.LoginContracts
import com.slb.gt.bluesilo.interactor.LoginInteractor_
import com.slb.gt.bluesilo.router.LoginRouter

open class LoginPresenter (
        view: LoginContracts.View,
        activity: BaseActivity<*>)
    : BasePresenter
<
        LoginContracts.View,
        LoginContracts.Interactor,
        LoginContracts.Router>
(
        view,
        activity),

        LoginContracts.Presenter {

    override fun login(email: String, password: String) {
        view.showLoading("Logging you in...")
        interactor.login(email, password)
    }

    override fun loginFail(e: Exception) {
        view.hideLoading()
        view.showSnackbar(e.localizedMessage)
    }

    override fun loginSuccess() {
        view.hideLoading()
        router.goToList()
    }

    override fun register(email: String, password: String) {
        view.showLoading("Registering...")
        interactor.register(email, password)
    }

    override fun registerFail(e: Exception) {
        view.hideLoading()
        view.showSnackbar(e.localizedMessage)
    }

    override fun registerSuccess() {
        view.hideLoading()
        view.showSnackbar("Registrado com sucesso")
    }


    override fun initiateInteractor(): LoginContracts.Interactor {
        val i = LoginInteractor_.getInstance_(activity)
        i.initiate(activity,this)
        return i
    }

    override fun initiateRouter(): LoginContracts.Router {
        return LoginRouter(activity)
    }
}