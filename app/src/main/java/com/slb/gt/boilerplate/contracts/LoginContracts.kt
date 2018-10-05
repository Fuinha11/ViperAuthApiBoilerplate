package com.slb.gt.boilerplate.contracts

import android.support.annotation.CallSuper
import com.slb.gt.boilerplate.activities.LoginActivity

class LoginContracts {
    interface View : BaseContracts.View
    interface Interactor : BaseContracts.Interactor {
        fun login(email: String, password: String)
        fun register(email: String, password: String)
    }
    interface Presenter : BaseContracts.Presenter {
        fun login(email: String, password: String)
        fun loginFail(e: Exception)
        fun loginSuccess()
        fun register(email: String, password: String)
        fun registerFail(e: Exception)
        fun registerSuccess()
    }
    interface Router : BaseContracts.Router {
        fun goToList()
    }
}