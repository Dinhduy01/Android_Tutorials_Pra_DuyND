package com.sun.android.bai13_BroadCast

import android.content.BroadcastReceiver
import android.content.Context
import android.content.Intent
import android.widget.Toast
import com.sun.android.R
import com.sun.android.bai13_BroadCast.MainActivity.Companion.ACTION_CUSTOM_BROADCAST

class CustomReceiver : BroadcastReceiver() {

    override fun onReceive(p0: Context, p1: Intent) {
        val intentAction = p1.action
        if (intentAction != null) {
            val toastMessage: String = when (intentAction) {
                Intent.ACTION_POWER_CONNECTED -> p0.getString(R.string.power_connected)

                Intent.ACTION_POWER_DISCONNECTED -> p0.getString(R.string.power_disconnected)
                ACTION_CUSTOM_BROADCAST -> p0.getString(R.string.custom_broadcast_toast)
                else -> p0.getString(R.string.unknown_action)
            }
            Toast.makeText(p0, toastMessage, Toast.LENGTH_SHORT).show()
        }

    }
}
