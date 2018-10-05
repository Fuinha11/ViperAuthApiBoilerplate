package com.slb.gt.bluesilo.ui

import android.view.KeyEvent
import android.view.View

open class EnterKeyListener(val fn: () -> Any) : View.OnKeyListener {
    override fun onKey(v: View?, keyCode: Int, event: KeyEvent?): Boolean {
        if (event?.action == KeyEvent.ACTION_DOWN &&
                keyCode == KeyEvent.KEYCODE_ENTER) {
            fn()
            return true
        }
        return false
    }
}