package com.sun.android.bai13_BroadCast

import android.content.Intent
import android.content.IntentFilter
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.localbroadcastmanager.content.LocalBroadcastManager
import com.sun.android.BuildConfig
import com.sun.android.R
import com.sun.android.databinding.ActivityMain13Binding

class MainActivity : AppCompatActivity() {
    val binding by lazy { ActivityMain13Binding.inflate(layoutInflater) }
    private val mReceiver: CustomReceiver = CustomReceiver()
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(binding.root)
        val filter = IntentFilter()
        filter.addAction(Intent.ACTION_POWER_DISCONNECTED);
        filter.addAction(Intent.ACTION_POWER_CONNECTED);
        this.registerReceiver(mReceiver, filter);
        LocalBroadcastManager.getInstance(this)
            .registerReceiver(mReceiver, IntentFilter(ACTION_CUSTOM_BROADCAST));
        binding.sendBroadcast.setOnClickListener() {
            sendCustomBroadcast()
        }

    }

    override fun onDestroy() {
        this.unregisterReceiver(mReceiver);
        LocalBroadcastManager.getInstance(this).unregisterReceiver(mReceiver);
        super.onDestroy();
    }

    fun sendCustomBroadcast() {
        val customBroadcastIntent = Intent(ACTION_CUSTOM_BROADCAST)
        LocalBroadcastManager.getInstance(this).sendBroadcast(customBroadcastIntent);
    }
    companion object {
        const val ACTION_CUSTOM_BROADCAST: String = BuildConfig.APPLICATION_ID + R.string.action_custom_broadcast
    }


}
