package com.slb.gt.boilerplate.interactor

import com.slb.gt.boilerplate.contracts.LoginContracts
import com.slb.gt.boilerplate.utils.interfaces.SuccessFailCallback
import org.androidannotations.annotations.EBean

@EBean
open class LoginInteractor
    : BaseInteractor<LoginContracts.Presenter>(),
        LoginContracts.Interactor {

    override fun login(email: String, password: String) {
        authService.emailLogin(email, password, object : SuccessFailCallback<String, Exception> {
            override fun onSuccess(data: String) {
                presenter.loginSuccess()
            }

            override fun onFailure(data: Exception) {
                presenter.loginFail(Exception(data))
            }

        })
    }

    override fun register(email: String, password: String) {
        authService.createUser(email, password, object : SuccessFailCallback<String, Exception> {
            override fun onSuccess(data: String) {
                presenter.registerSuccess()
            }

            override fun onFailure(data: Exception) {
                presenter.registerFail(Exception(data))
            }

        })
    }

}