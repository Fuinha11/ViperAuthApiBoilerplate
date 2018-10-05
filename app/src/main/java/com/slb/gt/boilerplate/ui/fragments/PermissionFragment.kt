package com.slb.gt.boilerplate.ui.fragments

import android.content.Context
import android.content.pm.PackageManager
import android.support.v4.app.Fragment
import android.support.v4.content.ContextCompat
import android.widget.TextView
import com.slb.gt.boilerplate.R
import com.slb.gt.boilerplate.utils.interfaces.SimpleCallback
import kotlinx.android.synthetic.main.fragment_permission.*
import org.androidannotations.annotations.*


@EFragment(R.layout.fragment_permission)
open class PermissionFragment : Fragment() {

    @ViewById(R.id.textContent)
    lateinit var contentTV : TextView

    @FragmentArg("textContent")
    lateinit var textContent : String

    @FragmentArg("permissionName")
    lateinit var permissionName : String

    @FragmentArg("callback")
    lateinit var callback: SimpleCallback<String>

    @AfterViews
    open fun initView() {
        contentTV.text = textContent
        if (ContextCompat.checkSelfPermission(context as Context, permissionName) == PackageManager.PERMISSION_GRANTED) {
            grantBtn.isEnabled = false
            grantBtn.text = "Permission Granted!"
        }
    }

    @Click(R.id.grantBtn)
    open fun getPermission(){
        requestPermissions(arrayOf(permissionName), 0)
    }

    override fun onRequestPermissionsResult(requestCode: Int, permissions: Array<out String>, grantResults: IntArray) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults)
        if ((grantResults.isNotEmpty() && grantResults[0] == PackageManager.PERMISSION_GRANTED)) {
            grantBtn.isEnabled = false
            grantBtn.text = "Permission Granted!"
            callback.onSuccess(permissionName)
        }
    }
}
