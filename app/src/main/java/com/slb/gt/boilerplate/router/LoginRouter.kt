package com.slb.gt.boilerplate.router

import android.app.Activity
import com.slb.gt.boilerplate.activities.ListActivity_
import com.slb.gt.boilerplate.contracts.LoginContracts

open class LoginRouter(activity: Activity)
    : BaseRouter(activity),
        LoginContracts.Router {
    override fun goToList() {
        ListActivity_.intent(activity).start()
    }
}