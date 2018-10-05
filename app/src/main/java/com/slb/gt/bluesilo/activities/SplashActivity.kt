package com.slb.gt.bluesilo.activities

import android.app.Activity
import android.support.v7.app.AppCompatActivity
import com.slb.gt.bluesilo.R
import com.slb.gt.bluesilo.contracts.BaseContracts
import com.slb.gt.bluesilo.services.auth.AuthService
import org.androidannotations.annotations.AfterViews
import org.androidannotations.annotations.Bean
import org.androidannotations.annotations.EActivity
import android.content.Intent
import android.os.Handler
import android.support.v4.os.HandlerCompat.postDelayed
import com.slb.gt.bluesilo.utils.extensions.hasPermissions


@EActivity(R.layout.activity_splash)
open class SplashActivity : Activity() {

    @Bean
    lateinit var authService: AuthService

    @AfterViews
    open fun authCheck() {
        Handler().postDelayed(Runnable {
            if (!this.hasPermissions(permissionList))
                PermissionsActivity_.intent(this).start()
            else {
                if (!authService.isLoggedIn())
                    goToLogin()
                else
                    goToMain()
            }
        }, 3000)
    }

    private fun goToMain() {
        ListActivity_.intent(this).start()
    }

    private fun goToLogin() {
        LoginActivity_.intent(this).start()
    }
}
