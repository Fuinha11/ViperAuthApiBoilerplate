package com.slb.gt.bluesilo.router

import android.app.Activity
import com.slb.gt.bluesilo.activities.ListActivity_
import com.slb.gt.bluesilo.contracts.LoginContracts

open class LoginRouter(activity: Activity)
    : BaseRouter(activity),
        LoginContracts.Router {
    override fun goToList() {
        ListActivity_.intent(activity).start()
    }
}