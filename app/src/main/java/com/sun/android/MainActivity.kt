package com.sun.android

import android.app.Activity
import android.content.Intent
import android.os.Bundle
import android.view.View
import androidx.activity.result.contract.ActivityResultContracts
import androidx.appcompat.app.AppCompatActivity
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
        val intent = Intent(this, SecondActivity::class.java)
        val txtMsg: String = "${binding.editTextMain?.text}"
        intent.putExtra("msg", txtMsg)
        binding.editTextMain.text= null
        startForResult.launch(intent)
    }


}
