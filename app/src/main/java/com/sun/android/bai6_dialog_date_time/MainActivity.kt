package com.sun.android.bai6_dialog_date_time

import android.content.DialogInterface
import android.content.Intent
import android.os.Bundle
import android.view.Menu
import android.view.MenuItem
import android.view.View
import android.widget.Toast
import androidx.appcompat.app.AlertDialog
import androidx.appcompat.app.AppCompatActivity
import androidx.appcompat.app.AppCompatDelegate
import androidx.fragment.app.DialogFragment
import com.sun.android.R
import com.sun.android.databinding.ActivityMain6Binding
import java.time.Year

class MainActivity : AppCompatActivity() {
    val binding by lazy { ActivityMain6Binding.inflate(layoutInflater) }
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(binding.root)
        binding.buttonPickTime.setOnClickListener(){
            showTimePicker()
        }
        binding.buttonPickDate.setOnClickListener(){
            showDatePicker()
        }

    }

    fun onClickShowAlert(view: View) {

        val myAlertBuilder = AlertDialog.Builder(this@MainActivity)
        myAlertBuilder.apply {
            setTitle(R.string.alert_title)
            setMessage(R.string.alert_message)
            setPositiveButton(R.string.ok_button) { _: DialogInterface?, _: Int ->
                Toast.makeText(
                    getApplicationContext(),
                    R.string.pressed_ok,
                    Toast.LENGTH_SHORT
                ).show()
            }
            setNegativeButton(R.string.cancel_button) { _: DialogInterface?, _: Int ->
                Toast.makeText(
                    getApplicationContext(),
                    R.string.pressed_cancel,
                    Toast.LENGTH_SHORT
                ).show()
            }
        }.create().show()


    }

    fun showDatePicker() {
        val newFragment: DialogFragment = DatePickerFragment()
        newFragment.show(supportFragmentManager, getString(R.string.datepicker))
    }

    fun showTimePicker() {
        val newFragment: DialogFragment = TimePickerFragment()
        newFragment.show(supportFragmentManager, "timePicker")
    }

    fun processDatePickerResult(year: Int, month: Int, day: Int) {
        val dateMessage = getString(R.string.dateMessage, day, month + 1, year)
        Toast.makeText(this, getString(R.string.date) + dateMessage, Toast.LENGTH_LONG).show()
    }

    fun processTimePickerResult(hourOfDay: Int, minute: Int) {

        val timeMessage = getString(R.string.timeMessage, hourOfDay, minute)
        Toast.makeText(this, "Time: $timeMessage", Toast.LENGTH_LONG).show()
    }

    override fun onCreateOptionsMenu(menu: Menu): Boolean {
        menuInflater.inflate(R.menu.main_menu, menu)
        val nightMode: Int = AppCompatDelegate.getDefaultNightMode()
        if (nightMode == AppCompatDelegate.MODE_NIGHT_YES) {
            menu.findItem(R.id.night_mode).setTitle(R.string.day_mode)
        } else {
            menu.findItem(R.id.night_mode).setTitle(R.string.night_mode)
        }
        val item: MenuItem = menu.findItem(R.id.bai_6)
        item.isVisible = false
        return true
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        when (item.itemId) {
            R.id.night_mode -> {
                val nightMode: Int = AppCompatDelegate.getDefaultNightMode()
                if (nightMode == AppCompatDelegate.MODE_NIGHT_YES) {
                    AppCompatDelegate.setDefaultNightMode(AppCompatDelegate.MODE_NIGHT_NO)

                } else {
                    AppCompatDelegate.setDefaultNightMode(AppCompatDelegate.MODE_NIGHT_YES)
                }
                recreate()
            }
            R.id.bai_2 -> {
                val intent = Intent(this, com.sun.android.Bai2.MainActivity::class.java)
                startActivity(intent)
            }
            R.id.bai_3 -> {
                val intent = Intent(this, com.sun.android.bai3_Fragment.MainActivity::class.java)
                startActivity(intent)
            }
            R.id.bai_4 -> {
                val intent = Intent(this, com.sun.android.bai4_Drawable.MainActivity::class.java)
                startActivity(intent)
            }
            R.id.bai_5 -> {
                val intent = Intent(this, com.sun.android.bai5_menu.MainActivity::class.java)
                startActivity(intent)
            }
            R.id.bai_6 -> {
                val intent = Intent(this, com.sun.android.bai6_dialog_date_time.MainActivity::class.java)
                startActivity(intent)
            }
            R.id.bai_7 -> {
                val intent = Intent(this, com.sun.android.bai7_Order.MainActivity::class.java)
                startActivity(intent)
            }
        }

        return true
    }
}
