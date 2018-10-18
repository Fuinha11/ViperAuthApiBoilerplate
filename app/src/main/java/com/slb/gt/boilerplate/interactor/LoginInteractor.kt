package com.slb.gt.boilerplate.interactor

import com.slb.gt.boilerplate.contracts.LoginContracts
import com.slb.gt.boilerplate.utils.interfaces.SuccessFailCallback
import org.androidannotations.annotations.EBean

@EBean
open class LoginInteractor
    : BaseInteractor(),
        LoginContracts.Interactor {

    override fun login(email: String, password: String, success: () -> Unit, fail: (e: Exception) -> Unit) {
        authService.emailLogin(email, password, object : SuccessFailCallback<String, Exception> {
            override fun onSuccess(data: String) {
                success()
            }

            override fun onFailure(data: Exception) {
                fail(data)
            }

        })
    }

    override fun register(email: String, password: String, success: () -> Unit, fail: (e: Exception) -> Unit) {
        authService.createUser(email, password, object : SuccessFailCallback<String, Exception> {
            override fun onSuccess(data: String) {
                success()
            }

            override fun onFailure(data: Exception) {
                fail(data)
            }

        })
    }

}