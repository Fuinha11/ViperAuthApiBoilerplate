package com.slb.gt.bluesilo.contracts

import android.app.Activity
import android.support.design.widget.Snackbar
import android.widget.Toast
import com.slb.gt.bluesilo.activities.BaseActivity
import org.androidannotations.annotations.AfterInject

class BaseContracts {
    interface View {
        fun showSnackbar(message: String, actionName: String = "", fn: () -> Unit = {}, length: Int = Snackbar.LENGTH_LONG)
        fun showToast(message: String, length: Int = Toast.LENGTH_SHORT)
        fun dismissKeyboard()
        fun showLoading(message: String = "Loading...")
        fun hideLoading()
    }

    interface Interactor {
        fun initiate(activity: BaseActivity<*>, presenter: Presenter)
        fun validateAuth()
        fun logout()
    }

    interface Presenter {
        fun authFail()
        fun logout()
    }

    interface Router {
        fun goToLogin()
    }
}