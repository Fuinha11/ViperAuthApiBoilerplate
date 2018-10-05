package com.slb.gt.bluesilo.utils.extensions

import android.app.Activity
import android.content.Context
import android.support.design.widget.Snackbar
import android.view.View
import android.view.inputmethod.InputMethodManager

fun Activity.snackbar(message: String, actionName: String = "", fn: () -> Unit = {}, length: Int = Snackbar.LENGTH_LONG) {
    val v: View = this.findViewById(android.R.id.content)
    this.runOnUiThread {
        Snackbar.make(v, message, length).setAction(actionName) {fn()}.show()
    }
}

fun Activity.hideKeyboard() {
    val imm = baseContext.getSystemService(Context.INPUT_METHOD_SERVICE) as InputMethodManager
    imm.hideSoftInputFromWindow((this.findViewById(android.R.id.content) as View).getWindowToken(), 0)
}