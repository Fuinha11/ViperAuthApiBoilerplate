package com.slb.gt.bluesilo.adapters

import android.content.Context
import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.slb.gt.bluesilo.R
import com.slb.gt.bluesilo.data.models.Driver
import kotlinx.android.synthetic.main.driver_list_cell.view.*


class DriverListAdapter(val items : ArrayList<Driver>, val context: Context) : RecyclerView.Adapter<ViewHolder>() {
    // Gets the number of animals in the list
    override fun getItemCount(): Int {
        return items.size
    }

    // Inflates the item views
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        return ViewHolder(LayoutInflater.from(context).inflate(R.layout.driver_list_cell, parent, false))
    }

    // Binds each animal in the ArrayList to a view
    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val d = items.get(position)
        holder.dName?.text = d.fullName()
        holder.dAlias?.text = d.ldapAlias
        holder.dCarrier?.text = d.carrier
    }
}

class ViewHolder (view: View) : RecyclerView.ViewHolder(view) {
    // Holds the TextView that will add each animal to
    val dName = view.driverName
    val dAlias = view.driverAlias
    val dCarrier = view.driverCarrier
}