package com.sun.android

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import com.sun.android.databinding.ActivitySecondBinding

class SecondActivity : AppCompatActivity() {
    val binding by lazy { ActivitySecondBinding.inflate(layoutInflater) }
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(binding.root)
        val actionBar = getSupportActionBar()
        actionBar?.setDisplayHomeAsUpEnabled(true)
        val value: Bundle = intent.extras!!
        val msg: String = value.getString("msg").toString()
        binding.textMessage.text = msg
    }

    fun returnReply(view: View?) {

        val reply: String = "${binding.editTextSecond.text}"
        val intent = Intent(this, MainActivity::class.java)
        intent.putExtra("msgBack", reply)
        binding.editTextSecond.text = null
        setResult(RESULT_OK, intent)
        finish()
    }

    override fun onSupportNavigateUp(): Boolean {
        onBackPressed()
        return true
    }
}
