package com.slb.gt.bluesilo.activities

import android.support.v4.app.Fragment
import android.support.v4.app.FragmentPagerAdapter
import android.support.v7.app.AppCompatActivity
import com.slb.gt.bluesilo.R
import com.slb.gt.bluesilo.ui.fragments.PermissionFragment_
import com.slb.gt.bluesilo.utils.extensions.hasPermissions
import com.slb.gt.bluesilo.utils.interfaces.SimpleCallback
import kotlinx.android.synthetic.main.activity_permissions.*
import org.androidannotations.annotations.AfterViews
import org.androidannotations.annotations.EActivity


val permissionList = arrayOf(
        android.Manifest.permission.CAMERA,
        android.Manifest.permission.ACCESS_FINE_LOCATION,
        android.Manifest.permission.INTERNET
        )


@EActivity(R.layout.activity_permissions)
open class PermissionsActivity : AppCompatActivity() {

    val permissionReason = arrayOf(
            "This is a example text explaining why you should grant me permission for camera or something",
            "This is a different example text explaining why you should grant me permission for, let's say GPS",
            "This is just in case we need to access the Internet"
    )

    private lateinit var adapter: FragmentPagerAdapter

    @AfterViews
    open fun initPager() {
        adapter = object: FragmentPagerAdapter(supportFragmentManager) {
            override fun getItem(p0: Int): Fragment {
                        return PermissionFragment_.builder()
                                .textContent(permissionReason[p0])
                                .permissionName(permissionList[p0])
                                .callback(object : SimpleCallback<String> {
                                    override fun onSuccess(data: String) {
                                        validateGrants()
                                        moveTo(p0+1)
                                    }
                                })
                                .build()
            }

            override fun getCount(): Int {
                return permissionList.size
            }

        }
        permissionsPager.adapter = adapter
    }

    private fun validateGrants() {
        if (this.hasPermissions(permissionList))
            SplashActivity_.intent(this).start()
    }

    private fun moveTo(i: Int) {
        if (i < permissionsPager.childCount)
            permissionsPager.currentItem = i
        else
            permissionsPager.currentItem = 0
    }
}
