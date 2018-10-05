package com.slb.gt.boilerplate.services.auth

import com.google.android.gms.tasks.OnCompleteListener
import com.google.android.gms.tasks.Task
import com.google.firebase.auth.AuthResult
import com.google.firebase.auth.FirebaseAuth
import com.slb.gt.boilerplate.services.api.ApiService
import com.slb.gt.boilerplate.utils.interfaces.SuccessFailCallback
import org.androidannotations.annotations.*
import java.lang.Exception

@EBean(scope = EBean.Scope.Singleton)
open class AuthService {

    @Bean
    lateinit var apiService: ApiService

    val firebase: FirebaseAuth = FirebaseAuth.getInstance()

    fun isLoggedIn(): Boolean {
        return firebase.currentUser != null
    }

    fun logout() {
        firebase.signOut()
        apiService.clearToken()
    }

    fun emailLogin(email: String, password: String, callback: SuccessFailCallback<String, Exception>) {
        if (email.isEmpty() || password.isEmpty())
            callback.onFailure(Exception("Email and password cannot be blank"))
        else
            firebase.signInWithEmailAndPassword(email, password).addOnCompleteListener(LoginCallback(firebase, apiService, callback))
    }

    fun createUser(email: String, password: String, callback: SuccessFailCallback<String, Exception>) {
        if (email.isEmpty() || password.isEmpty())
            callback.onFailure(Exception("Email and password cannot be blank"))
        else
            firebase.createUserWithEmailAndPassword(email, password).addOnCompleteListener(LoginCallback(firebase, apiService, callback))
    }
}
