package com.sun.android.bai5_menu

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.os.Message
import android.view.*
import android.widget.Toast
import androidx.appcompat.app.AppCompatDelegate
import com.sun.android.R
import com.sun.android.databinding.ActivityMain5Binding

class MainActivity : AppCompatActivity() {
    val binding by lazy { ActivityMain5Binding.inflate(layoutInflater) }
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(binding.root)
        registerForContextMenu(binding.article)
    }

    override fun onCreateContextMenu(menu: ContextMenu?, v: View?, menuInfo: ContextMenu.ContextMenuInfo?) {
        super.onCreateContextMenu(menu, v, menuInfo)
        val menuInflater: MenuInflater = getMenuInflater()
        menuInflater.inflate(R.menu.menu_context, menu)
    }

    override fun onContextItemSelected(item: MenuItem): Boolean {
        when (item.itemId) {
            R.id.context_edit -> {
                displayToast(getString(R.string.edit_message))
            }
            R.id.context_share -> {
                displayToast(getString(R.string.share_message))
            }
            R.id.context_delete -> {
                displayToast(getString(R.string.delete_message))
            }
        }
        return super.onContextItemSelected(item)
    }

    fun displayToast(message: String) {
        Toast.makeText(applicationContext, message, Toast.LENGTH_LONG).show()
    }

    override fun onCreateOptionsMenu(menu: Menu): Boolean {
        menuInflater.inflate(R.menu.main_menu, menu)
        val nightMode: Int = AppCompatDelegate.getDefaultNightMode()
        if (nightMode == AppCompatDelegate.MODE_NIGHT_YES) {
            menu.findItem(R.id.night_mode).setTitle(R.string.day_mode)
        } else {
            menu.findItem(R.id.night_mode).setTitle(R.string.night_mode)
        }
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
        } else if (item.itemId == R.id.bai_1) {
            val intent = Intent(this, com.sun.android.Bai1.MainActivity::class.java)
            startActivity(intent)
        } else if (item.itemId == R.id.bai_2) {
            val intent = Intent(this, com.sun.android.Bai2.MainActivity::class.java)
            startActivity(intent)
        } else if (item.itemId == R.id.bai_3) {
            val intent = Intent(this, com.sun.android.bai3_Fragment.MainActivity::class.java)
            startActivity(intent)
        } else if (item.itemId == R.id.bai_4) {
            val intent = Intent(this, com.sun.android.bai4_Drawable.MainActivity::class.java)
            startActivity(intent)
        } else if (item.itemId == R.id.bai_6) {
            val intent = Intent(this, com.sun.android.bai6_dialog_date_time.MainActivity::class.java)
            startActivity(intent)
        }
        return true
    }
}

