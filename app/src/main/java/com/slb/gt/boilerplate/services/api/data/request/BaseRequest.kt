package com.slb.gt.boilerplate.services.api.data.request

import java.io.Serializable

open class BaseRequest(var payload: Serializable? = null) : Serializable