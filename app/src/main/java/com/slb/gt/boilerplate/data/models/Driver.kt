package com.slb.gt.boilerplate.data.models

import java.io.Serializable

class Driver (
        var firstName : String = "",
        var lastName : String = "",
        var phoneNumber : String = "",
        var ldapAlias : String = "",
        var carrier : String = "",
        var email : String = ""
) : Serializable {
    fun fullName(): String {
        return "$firstName $lastName"
    }
}