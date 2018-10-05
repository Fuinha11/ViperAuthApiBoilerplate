package com.slb.gt.bluesilo.utils.extensions

import android.support.v7.widget.RecyclerView
import com.slb.gt.bluesilo.utils.interfaces.TopBottonListener

fun RecyclerView.setTopBottonListener(listener: TopBottonListener) {
    this.addOnScrollListener(object: RecyclerView.OnScrollListener() {

        override fun onScrollStateChanged(recyclerView: RecyclerView, newState: Int) {
            super.onScrollStateChanged(recyclerView, newState)

            if (!recyclerView.canScrollVertically(-1))
                listener.onTopReached()
            else if (!recyclerView.canScrollVertically(1))
                listener.onBottonReached()
        }
    })
}