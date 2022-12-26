package com.sun.android.bai3_Fragment

import com.sun.android.R
import android.app.Activity
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.RadioGroup
import android.widget.Toast
import androidx.fragment.app.Fragment
import com.sun.android.databinding.FragmentSimpleBinding


class SimpleFragment : Fragment(com.sun.android.R.layout.fragment_simple) {
    private val YES: Int = 0
    private val NO: Int = 1
    private var simpleFragment: FragmentSimpleBinding? = null
    private val binding get() = simpleFragment!!


    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        simpleFragment = FragmentSimpleBinding.inflate(inflater, container, false)
        val activityobj: Activity? = this.activity
        binding.radioGroup.setOnCheckedChangeListener(RadioGroup.OnCheckedChangeListener { radioGroup, id ->


            when (id) {
                binding.radioButtonYes.id -> {
                    binding.fragmentHeader.text = getString(R.string.yes_message)
                    Toast.makeText(activityobj, "Yes!", Toast.LENGTH_SHORT).show();
                }
                binding.radioButtonNo.id -> {
                    binding.fragmentHeader.text = getString(R.string.no_message)
                    Toast.makeText(activityobj, "No!", Toast.LENGTH_SHORT).show();
                }
            }
        })

        binding.ratingBar.setOnRatingBarChangeListener { ratingBar, fl, b ->
            val myRating: String = (getString(R.string.my_rating) + ratingBar.rating.toString());
            Toast.makeText(activityobj, myRating, Toast.LENGTH_SHORT).show();
        }

        return binding.root
    }


}
