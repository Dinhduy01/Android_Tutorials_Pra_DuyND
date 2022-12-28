package com.sun.android.bai7_Order

import android.content.Intent
import android.os.Bundle

import android.view.View
import android.widget.AdapterView
import android.widget.ArrayAdapter
import android.widget.RadioButton
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.sun.android.R
import com.sun.android.databinding.ActivityOrderBinding


class OrderActivity : AppCompatActivity(), AdapterView.OnItemSelectedListener {
    val binding by lazy { ActivityOrderBinding.inflate(layoutInflater) }
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(binding.root)
        val message: String? = intent.getStringExtra("MESSAGE")
        binding.orderTextview.text = message
        val spinner = binding.labelSpinner
        if (spinner != null) {
            spinner.setOnItemSelectedListener(this)
        }
        val adapter: ArrayAdapter<CharSequence> = ArrayAdapter.createFromResource(
            this,
            R.array.labels_array,
            android.R.layout.simple_spinner_item
        )
        adapter.setDropDownViewResource(
            android.R.layout.simple_spinner_dropdown_item
        )
        if (spinner != null) {
            spinner.adapter = adapter;
        }
    }

    override fun onNothingSelected(p0: AdapterView<*>?) {
        TODO("Not yet implemented")
    }

    fun onRadioButtonClicked(view: View) {
        val checked = (view as RadioButton).isChecked
        when (view.id) {
            binding.sameday.id -> {
                if (checked) {
                    displayToast(getString(R.string.same_day_messenger_service))
                }
            }
            binding.nextday.id -> {
                if (checked) {
                    displayToast(getString(R.string.next_day_ground_delivery))
                }
            }
            binding.pickup.id -> {
                if (checked) {
                    displayToast(getString(R.string.pick_up))
                }
            }
        }
    }

    fun displayToast(message: String?) {
        Toast.makeText(
            applicationContext, message,
            Toast.LENGTH_SHORT
        ).show()
    }

    override fun onItemSelected(p0: AdapterView<*>?, p1: View?, p2: Int, p3: Long) {
        val spinnerLable = p0?.getItemAtPosition(p2).toString()
        displayToast(spinnerLable)
    }
}
