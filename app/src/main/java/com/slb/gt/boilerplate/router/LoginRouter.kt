package com.slb.gt.boilerplate.router

import android.app.Activity
import com.slb.gt.boilerplate.activities.ListActivity_
import com.slb.gt.boilerplate.contracts.LoginContracts
import org.androidannotations.annotations.EBean

@EBean
open class LoginRouter
    : BaseRouter(),
        LoginContracts.Router {
    override fun goToList() {
        ListActivity_.intent(activity).start()
    }
}