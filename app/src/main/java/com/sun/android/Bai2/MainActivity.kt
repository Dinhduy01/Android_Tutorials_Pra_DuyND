package com.sun.android.Bai2

import android.content.Intent
import android.net.Uri
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.Menu
import android.view.MenuItem
import android.webkit.URLUtil
import androidx.appcompat.app.AppCompatDelegate
import com.sun.android.R
import com.sun.android.databinding.ActivityMain2Binding

class MainActivity : AppCompatActivity() {
    val binding by lazy { ActivityMain2Binding.inflate(layoutInflater) }
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(binding.root)

        binding.buttonOpenWebsite.setOnClickListener {
            val uri: Uri = Uri.parse("https://${binding.editTextWebsite.text}")

            if (URLUtil.isValidUrl("https://${binding.editTextWebsite.text}")) {
                binding.editTextWebsite.text = null
                val intent = Intent(Intent.ACTION_VIEW, uri)
                startActivity(intent)
            }

        }
        binding.buttonOpenLocation.setOnClickListener {
            val gmmIntentUri = Uri.parse("geo:0,0?q=${binding.editTextLocation.text}")
            binding.editTextLocation.text = null
            val mapIntent = Intent(Intent.ACTION_VIEW, gmmIntentUri)
            mapIntent.setPackage("com.google.android.apps.maps")
            startActivity(mapIntent)


        }
        binding.buttonShareText.setOnClickListener {
            val sendIntent: Intent = Intent().apply {

                action = Intent.ACTION_SEND
                putExtra(Intent.EXTRA_TEXT, "${binding.editTextShare.text} \n-Auto message from Duybe")
                binding.editTextShare.text = null
                type = "text/plain"
            }
            startActivity(sendIntent)

        }

    }

    override fun onCreateOptionsMenu(menu: Menu): Boolean {
        menuInflater.inflate(R.menu.main_menu, menu)
        val nightMode: Int = AppCompatDelegate.getDefaultNightMode()
        if (nightMode == AppCompatDelegate.MODE_NIGHT_YES) {
            menu.findItem(R.id.night_mode).setTitle(R.string.day_mode)
        } else {
            menu.findItem(R.id.night_mode).setTitle(R.string.night_mode)
        }
        val item: MenuItem = menu.findItem(R.id.bai_2)
        item.isVisible = false
        return true
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        when(item.itemId){
            R.id.night_mode->{
                val nightMode: Int = AppCompatDelegate.getDefaultNightMode()
                if (nightMode == AppCompatDelegate.MODE_NIGHT_YES) {
                    AppCompatDelegate.setDefaultNightMode(AppCompatDelegate.MODE_NIGHT_NO)

                } else {
                    AppCompatDelegate.setDefaultNightMode(AppCompatDelegate.MODE_NIGHT_YES)
                }
                recreate()
            }
            R.id.bai_2->{
                val intent = Intent(this,com.sun.android.Bai2.MainActivity::class.java)
                startActivity(intent)
            }
            R.id.bai_3->{
                val intent = Intent(this,com.sun.android.bai3_Fragment.MainActivity::class.java)
                startActivity(intent)
            }
            R.id.bai_4->{
                val intent = Intent(this,com.sun.android.bai4_Drawable.MainActivity::class.java)
                startActivity(intent)
            }
            R.id.bai_5->{
                val intent = Intent(this,com.sun.android.bai5_menu.MainActivity::class.java)
                startActivity(intent)
            }
            R.id.bai_6->{
                val intent = Intent(this,com.sun.android.bai6_dialog_date_time.MainActivity::class.java)
                startActivity(intent)
            }
            R.id.bai_7->{
                val intent = Intent(this,com.sun.android.bai7_Order.MainActivity::class.java)
                startActivity(intent)
            }
        }

        return true
    }

}

