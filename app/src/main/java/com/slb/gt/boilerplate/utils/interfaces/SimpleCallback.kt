package com.slb.gt.boilerplate.utils.interfaces

import java.io.Serializable

interface SimpleCallback<T> : Serializable{
    fun onSuccess(data: T)
}