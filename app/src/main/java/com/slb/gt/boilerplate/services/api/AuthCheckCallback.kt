package com.slb.gt.boilerplate.services.api

import android.app.Activity
import com.slb.gt.boilerplate.activities.BaseActivity
import com.slb.gt.boilerplate.services.api.data.response.BaseResponse
import com.slb.gt.boilerplate.utils.interfaces.SuccessFailCallback
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

abstract class AuthCheckCallback<T: BaseResponse>(private val activity : BaseActivity<*>) : Callback<T> , SuccessFailCallback<T, Exception> {
    override fun onFailure(call: Call<T>, t: Throwable) {
        if (activity.isDestroyed)
            return

        onFailure(Exception(t))
    }

    override fun onResponse(call: Call<T>, response: Response<T>) {
        if (activity.isDestroyed)
            return

        if (response.isSuccessful && response.code() == 403)
            onAuthFail()
        val body : T? = response.body()
        if (body != null)
            onSuccess(body)
    }

    protected fun onAuthFail() {
        if (!activity.isDestroyed)
            activity.presenter.logout()
    }
}