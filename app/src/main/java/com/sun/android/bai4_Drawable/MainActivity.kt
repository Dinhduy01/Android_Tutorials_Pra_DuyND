package com.sun.android.bai4_Drawable

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.view.Menu;
import android.view.MenuItem;
import androidx.appcompat.app.AppCompatDelegate
import com.sun.android.R
import com.sun.android.databinding.ActivityMain4Binding

class MainActivity : AppCompatActivity() {
    val binding by lazy { ActivityMain4Binding.inflate(layoutInflater) }
    private var STATE_SCORE_1: String = "Team 1 Score"
    private var STATE_SCORE_2: String = "Team 2 Score"

    var mScore1: Int = 0
    var mScore2: Int = 0
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(binding.root)

        if (savedInstanceState != null) {
            mScore1 = savedInstanceState.getInt(STATE_SCORE_1)
            mScore2 = savedInstanceState.getInt(STATE_SCORE_2)
            binding.score1.text = mScore1.toString()
            binding.score2.text = mScore2.toString()
        }
    }

    fun decreaseScore(view: View) {
        val viewID: Int = view.id
        when (viewID) {
            binding.decreaseTeam1.id -> {
                mScore1--
                binding.score1.text = mScore1.toString()
            }
            binding.decreaseTeam2.id -> {
                mScore2--
                binding.score2.text = mScore2.toString()

            }
        }
    }

    fun increaseScore(view: View) {
        val viewID: Int = view.id
        when (viewID) {
            binding.increaseTeam1.id -> {
                mScore1++
                binding.score1.text = mScore1.toString()

            }
            binding.increaseTeam2.id -> {
                mScore2++
                binding.score2.text = mScore2.toString()

            }
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
        val item: MenuItem = menu.findItem(R.id.bai_4)
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
        } else if (item.itemId == R.id.bai_1) {
            val intent = Intent(this, com.sun.android.Bai1.MainActivity::class.java)
            startActivity(intent)
        } else if (item.itemId == R.id.bai_2) {
            val intent = Intent(this, com.sun.android.Bai2.MainActivity::class.java)
            startActivity(intent)
        } else if (item.itemId == R.id.bai_3) {
            val intent = Intent(this, com.sun.android.bai3_Fragment.MainActivity::class.java)
            startActivity(intent)
        } else if (item.itemId == R.id.bai_5) {
            val intent = Intent(this, com.sun.android.bai5_menu.MainActivity::class.java)
            startActivity(intent)
        } else if (item.itemId == R.id.bai_6) {
            val intent = Intent(this, com.sun.android.bai6_dialog_date_time.MainActivity::class.java)
            startActivity(intent)
        }
        else if(item.itemId == R.id.bai_7){
            val intent = Intent(this,com.sun.android.bai7_Order.MainActivity::class.java)
            startActivity(intent)
        }
        return true
    }


    override fun onSaveInstanceState(outState: Bundle) {
        super.onSaveInstanceState(outState)
        outState.putInt(STATE_SCORE_1, mScore1)
        outState.putInt(STATE_SCORE_2, mScore2)
    }



}

