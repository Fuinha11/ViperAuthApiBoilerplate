package com.slb.gt.bluesilo.services.auth

import com.google.android.gms.tasks.OnCompleteListener
import com.google.android.gms.tasks.Task
import com.google.firebase.auth.AuthResult
import com.google.firebase.auth.FirebaseAuth
import com.slb.gt.bluesilo.services.api.ApiService
import com.slb.gt.bluesilo.utils.interfaces.SuccessFailCallback

class LoginCallback(val firebase: FirebaseAuth, val apiService: ApiService, val callback: SuccessFailCallback<String, Exception>) : OnCompleteListener<AuthResult> {
    override fun onComplete(task: Task<AuthResult>) {
        if (task.isSuccessful) {
            val user = firebase.currentUser
            user?.getIdToken(true)?.addOnCompleteListener {
                if (it.isSuccessful) {
                    val token = it.result.token
                    if (token != null) {
                        apiService.authToken = token
                        callback.onSuccess("")
                    }
                    else
                        callback.onFailure(Exception("Unable to retrieve UID Token"))
                } else
                    callback.onFailure(Exception(it.exception))
            }
        } else
            callback.onFailure(Exception(task.exception))
    }
}