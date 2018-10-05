package com.slb.gt.bluesilo.utils.interfaces

import java.io.Serializable

interface SimpleCallback<T> : Serializable{
    fun onSuccess(data: T)
}