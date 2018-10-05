package com.slb.gt.bluesilo.interactor

import com.slb.gt.bluesilo.activities.BaseActivity
import com.slb.gt.bluesilo.activities.LoginActivity
import com.slb.gt.bluesilo.contracts.BaseContracts
import com.slb.gt.bluesilo.services.auth.AuthService
import org.androidannotations.annotations.Bean
import org.androidannotations.annotations.EBean

@EBean
open class BaseInteractor<P : BaseContracts.Presenter>
    : BaseContracts.Interactor {

    lateinit var activity: BaseActivity<*>
    lateinit var presenter: P

    @Bean
    lateinit var authService: AuthService

    override fun initiate(activity: BaseActivity<*>, presenter: BaseContracts.Presenter) {
        this.activity = activity
        this.presenter = presenter as P
        validateAuth()
    }

    override fun validateAuth() {
        if (activity !is LoginActivity) {
            if (!authService.isLoggedIn())
                logout()
        }
    }

    override fun logout() {
        authService.logout()
        presenter.authFail()
    }
}