package com.sun.android.bai14_share

import android.content.SharedPreferences
import android.graphics.drawable.ColorDrawable
import android.graphics.drawable.GradientDrawable
import android.os.Bundle
import android.util.Log
import android.view.View
import android.widget.TextView
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.core.content.ContextCompat
import com.sun.android.R
import com.sun.android.databinding.ActivityMain14Binding


class MainActivity : AppCompatActivity(), View.OnClickListener {
    val binding by lazy { ActivityMain14Binding.inflate(layoutInflater) }
    private val mPreferences by lazy { getSharedPreferences(sharedPrefFile, MODE_PRIVATE) }
    private var mCount: Int = 0
    private var mColor: Int = 0

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(binding.root)
        mColor = ContextCompat.getColor(this, R.color.default_background)

        mCount = mPreferences.getInt(COUNT_KEY, 0)
        binding.textViewCount.text = String.format("%s", mCount)

        mColor = mPreferences.getInt(COLOR_KEY, mColor)
        binding.textViewCount.setBackgroundColor(mColor)

        binding.buttonBlack.setOnClickListener(this)
        binding.buttonRed.setOnClickListener(this)
        binding.buttonBlue.setOnClickListener(this)
        binding.buttonGreen.setOnClickListener(this)
        binding.buttonReset.setOnClickListener(this)
        binding.buttonCount.setOnClickListener(this)
    }

    fun reset() {
        mCount = 0
        binding.textViewCount.text = String.format("%s", mCount)
        mColor = ContextCompat.getColor(
            this, R.color.default_background
        )
        binding.textViewCount.setBackgroundColor(mColor)
        val preferencesEditor = mPreferences.edit()
        preferencesEditor.clear()
        preferencesEditor.apply()
    }

    fun countUp() {
        mCount++
        binding.textViewCount.text = String.format("%s", mCount)
    }

    override fun onPause() {
        super.onPause()
        val preferencesEditor = mPreferences.edit()
        preferencesEditor.putInt(COUNT_KEY, mCount)
        preferencesEditor.putInt(COLOR_KEY, mColor)
        preferencesEditor.apply()
    }

    fun changebackground(view: View) {

        view.backgroundTintList?.let {
            binding.textViewCount.setBackgroundColor(
                it.getColorForState(
                    intArrayOf(android.R.attr.state_enabled), 0
                )
            )
        }
        ((binding.textViewCount.background) as? ColorDrawable)?.color?.let { mColor = it }

    }

    override fun onClick(p0: View?) {
        when (p0) {
            binding.buttonBlack, binding.buttonRed, binding.buttonGreen, binding.buttonBlue -> changebackground(p0)
            binding.buttonReset -> reset()
            binding.buttonCount -> countUp()
        }
    }

    companion object {
        private const val COLOR_KEY = "color"
        private const val COUNT_KEY = "count"
        private const val sharedPrefFile = "com.sun.android.bai14_share"
    }

}

