package com.slb.gt.boilerplate.utils.interfaces

interface SuccessFailCallback<T, R> : SimpleCallback<T> {
    fun onFailure(data: R)
}