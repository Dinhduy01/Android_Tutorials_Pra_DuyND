package com.sun.android.bai7_Order

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.Menu
import android.view.MenuItem
import android.view.View
import android.widget.Toast
import androidx.appcompat.app.AppCompatDelegate
import com.sun.android.R
import com.sun.android.databinding.ActivityMain7Binding

class MainActivity : AppCompatActivity() {
    val binding by lazy { ActivityMain7Binding.inflate(layoutInflater) }
    var mOrderMessage: String = ""
    val EXTRA_MESSAGE: String = "MESSAGE"
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(binding.root)
        binding.fab.setOnClickListener(View.OnClickListener {
            val intent = Intent(this, OrderActivity::class.java)
            intent.putExtra(EXTRA_MESSAGE, mOrderMessage)
            startActivity(intent)
        })
    }

    override fun onCreateOptionsMenu(menu: Menu): Boolean {
        menuInflater.inflate(R.menu.menu_main, menu)
        val nightMode: Int = AppCompatDelegate.getDefaultNightMode()
        if (nightMode == AppCompatDelegate.MODE_NIGHT_YES) {
            menu.findItem(R.id.night_mode).setTitle(R.string.day_mode)
        } else {
            menu.findItem(R.id.night_mode).setTitle(R.string.night_mode)
        }
        val item: MenuItem = menu.findItem(R.id.bai_7)
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

    fun displayToast(message: String) {
        Toast.makeText(applicationContext, message, Toast.LENGTH_SHORT).show()
    }

    fun showDonutOrder(view: View) {
        mOrderMessage = getString(R.string.donut_order_message)
        displayToast(mOrderMessage)
    }

    fun showIceCreamOrder(view: View) {
        mOrderMessage = getString(R.string.ice_cream_order_message)
        displayToast(mOrderMessage)
    }

    fun showFroyoOrder(view: View) {
        mOrderMessage = getString(R.string.froyo_order_message)
        displayToast(mOrderMessage)
    }

}
