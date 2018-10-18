package com.slb.gt.boilerplate.interactor

import com.slb.gt.boilerplate.activities.BaseActivity
import com.slb.gt.boilerplate.activities.LoginActivity
import com.slb.gt.boilerplate.contracts.BaseContracts
import com.slb.gt.boilerplate.services.auth.AuthService
import org.androidannotations.annotations.Bean
import org.androidannotations.annotations.EBean

@EBean
open class BaseInteractor : BaseContracts.Interactor {

    @Bean
    lateinit var authService: AuthService

    override fun validateAuth(success: () -> Unit, fail: () -> Unit) {
        if (authService.isLoggedIn())
            success()
        else
            fail()
    }

    override fun logout() {
        authService.logout()
    }
}