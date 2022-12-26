package com.sun.android.bai3_Fragment

import android.content.Intent
import android.os.Bundle
import android.view.Menu
import android.view.MenuItem
import androidx.appcompat.app.AppCompatActivity
import androidx.appcompat.app.AppCompatDelegate
import androidx.fragment.app.FragmentManager
import androidx.fragment.app.FragmentTransaction
import com.sun.android.R

import com.sun.android.databinding.ActivityMain3Binding


class MainActivity : AppCompatActivity() {
    val binding by lazy { ActivityMain3Binding.inflate(layoutInflater) }
    var isFragmentDisplayed: Boolean = false;
    val STATE_FRAGMENT: String = "state_of_fragment"
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(binding.root)
        if (savedInstanceState != null) {
            isFragmentDisplayed = savedInstanceState.getBoolean(STATE_FRAGMENT)
            if (isFragmentDisplayed) {
                binding.openButton.text = getString(com.sun.android.R.string.close)
            }
        }
        binding.openButton.setOnClickListener {
            if (!isFragmentDisplayed) {
                displayFragment()
            } else {
                closeFragment()

            }
        }
    }

    private val fragmentManager: FragmentManager = supportFragmentManager
    fun displayFragment() {
        val simpleFragment: SimpleFragment = SimpleFragment().newInstance()
        val fragmentTransaction: FragmentTransaction = fragmentManager.beginTransaction();
        fragmentTransaction.add(binding.fragmentContainer.id, simpleFragment).addToBackStack(null).commit()
        binding.openButton.text = getString(com.sun.android.R.string.close)
        isFragmentDisplayed = true

    }

    fun closeFragment() {
        val simpleFragment: SimpleFragment =
            fragmentManager.findFragmentById(binding.fragmentContainer.id) as SimpleFragment
        if (simpleFragment != null) {
            val fragmentTransaction: FragmentTransaction = fragmentManager.beginTransaction()
            fragmentTransaction.remove(simpleFragment).commit()
        }
        binding.openButton.text = getString(com.sun.android.R.string.open)
        isFragmentDisplayed = false
    }

    override fun onSaveInstanceState(savedInstanceState: Bundle) {
        super.onSaveInstanceState(savedInstanceState)
        savedInstanceState.putBoolean(STATE_FRAGMENT, isFragmentDisplayed)

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
        } else if (item.itemId == R.id.bai_4) {
            val intent = Intent(this, com.sun.android.bai4_Drawable.MainActivity::class.java)
            startActivity(intent)
        } else if (item.itemId == R.id.bai_5) {
            val intent = Intent(this, com.sun.android.bai5_menu.MainActivity::class.java)
            startActivity(intent)
        } else if (item.itemId == R.id.bai_6) {
            val intent = Intent(this, com.sun.android.bai6_dialog_date_time.MainActivity::class.java)
            startActivity(intent)
        }
        return true
    }
}


