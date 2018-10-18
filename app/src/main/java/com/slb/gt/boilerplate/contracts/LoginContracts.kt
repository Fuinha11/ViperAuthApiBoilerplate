package com.slb.gt.boilerplate.contracts

import android.support.annotation.CallSuper
import com.slb.gt.boilerplate.activities.LoginActivity

class LoginContracts {
    interface View : BaseContracts.View
    interface Interactor : BaseContracts.Interactor {
        fun login(email: String, password: String, success: () -> Unit, fail: (e: Exception)-> Unit)
        fun register(email: String, password: String, success: () -> Unit, fail: (e: Exception)-> Unit)
    }
    interface Presenter : BaseContracts.Presenter {
        fun login(email: String, password: String)
        fun loginSuccess()
        fun register(email: String, password: String)
        fun registerSuccess()
    }
    interface Router : BaseContracts.Router {
        fun goToList()
    }
}