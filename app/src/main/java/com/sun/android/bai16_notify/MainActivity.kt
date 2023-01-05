package com.sun.android.bai16_notify

import android.app.NotificationChannel
import android.app.NotificationManager
import android.app.PendingIntent
import android.content.BroadcastReceiver
import android.content.Context
import android.content.Intent
import android.content.IntentFilter
import android.graphics.Bitmap
import android.graphics.BitmapFactory
import android.graphics.Color
import android.os.Build
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.core.app.NotificationCompat
import com.sun.android.R
import com.sun.android.databinding.ActivityMain16Binding


class MainActivity : AppCompatActivity() {

    private val mNotifyManager: NotificationManager by lazy { getSystemService(NOTIFICATION_SERVICE) as NotificationManager }
    private val mReceiver = NotificationReceiver()
    val binding by lazy { ActivityMain16Binding.inflate(layoutInflater) }
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(binding.root)
        createNotificationChannel()
        registerReceiver(
            mReceiver, IntentFilter(ACTION_UPDATE_NOTIFICATION)
        )
        binding.buttonNotify.setOnClickListener {
            sendNotification()
        }
        binding.buttonUpdate.setOnClickListener {
            updateNotification()
        }
        binding.buttonCancel.setOnClickListener {
            cancelNotification()
        }
        setNotificationButtonState(isNotifyEnabled = true, isUpdateEnabled = false, isCancelEnabled = false)

    }

    override fun onDestroy() {
        unregisterReceiver(mReceiver)
        super.onDestroy()
    }

    fun setNotificationButtonState(isNotifyEnabled: Boolean, isUpdateEnabled: Boolean, isCancelEnabled: Boolean) {
        binding.buttonNotify.isEnabled = isNotifyEnabled
        binding.buttonUpdate.isEnabled = isUpdateEnabled
        binding.buttonCancel.isEnabled = isCancelEnabled
    }

    fun createNotificationChannel() {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
            val notificationChannel = NotificationChannel(
                PRIMARY_CHANNEL_ID, getString(R.string.notification_channel_name), NotificationManager.IMPORTANCE_HIGH
            )
            notificationChannel.enableLights(true)
            notificationChannel.lightColor = Color.RED
            notificationChannel.enableVibration(true)
            notificationChannel.description = getString(R.string.notification_channel_description)
            mNotifyManager.createNotificationChannel(notificationChannel)
        }
    }

    fun cancelNotification() {
        mNotifyManager.cancel(NOTIFICATION_ID)
        setNotificationButtonState(isNotifyEnabled = true, isUpdateEnabled = false, isCancelEnabled = false)
    }


    fun sendNotification() {
        val updateIntent = Intent(ACTION_UPDATE_NOTIFICATION)
        val updatePendingIntent = PendingIntent.getBroadcast(
            this, NOTIFICATION_ID, updateIntent, PendingIntent.FLAG_ONE_SHOT
        )
        val notifyBuilder: NotificationCompat.Builder = getNotificationBuilder()
        notifyBuilder.addAction(
            R.drawable.ic_update, getString(R.string.update), updatePendingIntent
        )
        mNotifyManager.notify(NOTIFICATION_ID, notifyBuilder.build())
        setNotificationButtonState(isNotifyEnabled = false, isUpdateEnabled = true, isCancelEnabled = true)
    }

    fun updateNotification() {
        val androidImage: Bitmap = BitmapFactory.decodeResource(resources, R.drawable.bg)
        val notifyBuilder = getNotificationBuilder()
        notifyBuilder.setStyle(
            NotificationCompat.BigPictureStyle().bigPicture(androidImage)
                .setBigContentTitle(getString(R.string.notification_updated))
                .setSummaryText(getString(R.string.notification_lyric))

        )
        mNotifyManager.notify(NOTIFICATION_ID, notifyBuilder.build())
        setNotificationButtonState(isNotifyEnabled = false, isUpdateEnabled = false, isCancelEnabled = true)
    }

    fun getNotificationBuilder(): NotificationCompat.Builder {
        val notificationIntent = Intent(this, MainActivity::class.java)
        val notificationPendingIntent = PendingIntent.getActivity(
            this, NOTIFICATION_ID, notificationIntent, PendingIntent.FLAG_UPDATE_CURRENT
        )

        return NotificationCompat.Builder(this, PRIMARY_CHANNEL_ID)
            .setContentTitle(getString(R.string.notification_title))
            .setContentText(getString(R.string.notification_text))
            .setSmallIcon(R.drawable.messenger)
            .setAutoCancel(true).setContentIntent(notificationPendingIntent)
            .setPriority(NotificationCompat.PRIORITY_HIGH)
            .setDefaults(NotificationCompat.DEFAULT_ALL)
    }

    companion object {
        const val ACTION_UPDATE_NOTIFICATION = "com.android.example.notifyme.ACTION_UPDATE_NOTIFICATION"
        const val PRIMARY_CHANNEL_ID = "primary_notification_channel"
        const val NOTIFICATION_ID = 0
    }


    inner class NotificationReceiver() : BroadcastReceiver() {

        override fun onReceive(context: Context?, intent: Intent?) {
            updateNotification()
        }
    }
}
