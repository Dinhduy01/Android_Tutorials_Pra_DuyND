package com.sun.android.Bai1

import android.app.Activity
import android.content.Intent
import android.os.Bundle
import android.view.Menu
import android.view.MenuItem
import android.view.View
import androidx.activity.result.contract.ActivityResultContracts
import androidx.appcompat.app.AppCompatActivity
import androidx.appcompat.app.AppCompatDelegate
import com.sun.android.R
import com.sun.android.databinding.ActivityMainBinding


class MainActivity : AppCompatActivity() {

    val startForResult = registerForActivityResult(ActivityResultContracts.StartActivityForResult())
    {
        if (it.resultCode == Activity.RESULT_OK) {
            //  you will get result here in result.data
            val string: String = it?.data?.extras?.get("msgBack").toString()
            binding.textMessageReply.visibility = View.VISIBLE
            binding.textHeaderReply.visibility = View.VISIBLE
            binding.textMessageReply.text = string

        }
    }
    lateinit var binding: ActivityMainBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)
    }

    fun launchSecondActivity(view: View?) {
        val sendIntent: Intent = Intent().apply {
            action = Intent.ACTION_SEND
            putExtra(Intent.EXTRA_TEXT, "This is my text to send.")
            type = "text/plain"
        }

        val shareIntent = Intent.createChooser(sendIntent, null)
        startActivity(shareIntent)
    }
    override fun onCreateOptionsMenu(menu: Menu): Boolean {
        menuInflater.inflate(R.menu.main_menu, menu)
        val nightMode: Int = AppCompatDelegate.getDefaultNightMode()
        if (nightMode == AppCompatDelegate.MODE_NIGHT_YES) {
            menu.findItem(R.id.night_mode).setTitle(R.string.day_mode)
        } else {
            menu.findItem(R.id.night_mode).setTitle(R.string.night_mode)
        }
        val item: MenuItem = menu.findItem(R.id.bai_1)
        item.isVisible = false
        return true
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        if (item.itemId == R.id.night_mode) {
            val nightMode: Int = AppCompatDelegate.getDefaultNightMode()
            if (nightMode == AppCompatDelegate.MODE_NIGHT_YES) {
                AppCompatDelegate.setDefaultNightMode(AppCompatDelegate.MODE_NIGHT_NO)

            } else {
                AppCompatDelegate.setDefaultNightMode(AppCompatDelegate.MODE_NIGHT_YES)
            }
            recreate()
        }

        else if(item.itemId == R.id.bai_2){
            val intent = Intent(this,com.sun.android.Bai2.MainActivity::class.java)
            startActivity(intent)
        }
        else if(item.itemId == R.id.bai_3){
            val intent = Intent(this,com.sun.android.bai3_Fragment.MainActivity::class.java)
            startActivity(intent)
        }
        else if(item.itemId == R.id.bai_4){
            val intent = Intent(this,com.sun.android.bai4_Drawable.MainActivity::class.java)
            startActivity(intent)
        }
        else if(item.itemId == R.id.bai_5){
            val intent = Intent(this,com.sun.android.bai5_menu.MainActivity::class.java)
            startActivity(intent)
        }
        else if(item.itemId == R.id.bai_6){
            val intent = Intent(this,com.sun.android.bai6_dialog_date_time.MainActivity::class.java)
            startActivity(intent)
        }
        else if(item.itemId == R.id.bai_7){
            val intent = Intent(this,com.sun.android.bai7_Order.MainActivity::class.java)
            startActivity(intent)
        }
        return true
    }
}



