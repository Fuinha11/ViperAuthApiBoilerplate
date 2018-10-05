package com.slb.gt.boilerplate.router

import android.app.Activity
import android.content.Intent
import com.slb.gt.boilerplate.activities.LoginActivity_
import com.slb.gt.boilerplate.contracts.BaseContracts

open class BaseRouter(val activity: Activity) : BaseContracts.Router {
    override fun goToLogin() {
        val i = Intent(activity, LoginActivity_::class.java)
        i.flags = Intent.FLAG_ACTIVITY_NEW_TASK or Intent.FLAG_ACTIVITY_CLEAR_TASK
        activity.startActivity(i)
    }
}