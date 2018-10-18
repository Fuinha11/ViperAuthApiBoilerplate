package com.slb.gt.boilerplate.presenter

import com.slb.gt.boilerplate.activities.BaseActivity
import com.slb.gt.boilerplate.contracts.LoginContracts
import com.slb.gt.boilerplate.interactor.LoginInteractor
import com.slb.gt.boilerplate.interactor.LoginInteractor_
import com.slb.gt.boilerplate.router.LoginRouter
import org.androidannotations.annotations.Bean
import org.androidannotations.annotations.EBean

@EBean
open class LoginPresenter
    : BasePresenter
<
        LoginContracts.View,
        LoginContracts.Interactor,
        LoginContracts.Router>(),

        LoginContracts.Presenter {

    open fun setBeans(@Bean r: LoginRouter, @Bean i: LoginInteractor) {
        router = r
        interactor = i
    }

    override fun login(email: String, password: String) {
        view.showLoading("Logging you in...")
        interactor.login(email, password, {loginSuccess()}, {e -> onError(e)})
    }

    override fun loginSuccess() {
        view.hideLoading()
        router.goToList()
    }

    override fun register(email: String, password: String) {
        view.showLoading("Registering...")
        interactor.register(email, password, {registerSuccess()}, {e -> onError(e)})
    }

    override fun registerSuccess() {
        view.hideLoading()
        view.showSnackbar("Registrado com sucesso")
    }
}