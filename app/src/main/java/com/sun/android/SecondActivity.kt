package com.sun.android

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.EditText
import com.sun.android.databinding.ActivitySecondBinding

class SecondActivity : AppCompatActivity() {
    val binding by lazy {ActivitySecondBinding.inflate(layoutInflater)}
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(binding.root)
        val actionBar = getSupportActionBar()
        actionBar?.setDisplayHomeAsUpEnabled(true)
        val receivedMsg = binding.textMessage
        val value: Bundle = intent.extras!!
        val msg: String = value.getString("msg").toString()
        receivedMsg.text = msg
    }

    fun returnReply(view: View?) {
        val mReply = findViewById<EditText>(R.id.editText_second);
        val reply: String = "${mReply?.text}"
        val intent = Intent(this, MainActivity::class.java)
        intent.putExtra("msgBack", reply)
        mReply.text = null
        setResult(RESULT_OK, intent)
        finish()
    }

    override fun onSupportNavigateUp(): Boolean {
        onBackPressed()
        return true
    }
}
