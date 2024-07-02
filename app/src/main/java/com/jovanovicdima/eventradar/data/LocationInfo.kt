package com.jovanovicdima.eventradar.data

import android.content.Context
import android.content.Intent
import android.location.Location
import android.util.Log
import com.jovanovicdima.eventradar.services.LocationService

object LocationInfo {
    var location: Location? = null
    var locationServiceStatus = true

    private var list: MutableList<(Location?) -> Unit> = mutableListOf()

    fun alert() {
        for(item in list) {
            item(location)
        }
    }

    fun subscribe(callback: (Location?) -> Unit) {
        if(!list.contains(callback)) {
            list.add(callback)
        }
    }

    fun enableLocation(context: Context) {
        val locationServiceIntent = Intent(context, LocationService::class.java)
        context.startService(locationServiceIntent)
        locationServiceStatus = true
    }
    fun disableLocation(context: Context) {
        val locationServiceIntent = Intent(context, LocationService::class.java)
        context.stopService(locationServiceIntent)
        locationServiceStatus = false
    }

}