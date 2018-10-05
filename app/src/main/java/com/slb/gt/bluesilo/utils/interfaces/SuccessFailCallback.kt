package com.slb.gt.bluesilo.utils.interfaces

interface SuccessFailCallback<T, R> : SimpleCallback<T> {
    fun onFailure(data: R)
}