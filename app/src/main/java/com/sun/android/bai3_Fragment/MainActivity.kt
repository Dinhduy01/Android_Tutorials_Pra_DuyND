package com.sun.android.bai3_Fragment

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.FragmentManager
import androidx.fragment.app.FragmentTransaction

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
}

