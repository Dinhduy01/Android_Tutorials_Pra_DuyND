package com.sun.android

import android.content.Intent
import android.os.Bundle
import android.view.View
import android.widget.EditText
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity

class newA : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_new)
        val actionBar = getSupportActionBar()
        actionBar?.setDisplayHomeAsUpEnabled(true)
        val receivedMsg = findViewById<TextView>(R.id.text_message)
        val value:Bundle = intent.extras!!
        val msg:String = value.getString("msg").toString()
        receivedMsg.text=msg



    }
    fun returnReply(view: View?){
        var mReply =  findViewById<EditText>(R.id.editText_second);
        var reply:String = "${mReply?.text}"
        val intent = Intent(this,MainActivity::class.java)
        intent.putExtra("msgBack",reply)
        mReply.text=null
        setResult(RESULT_OK,intent)
        finish()
    }

    override fun onSupportNavigateUp(): Boolean {
        onBackPressed()
        return true
    }
}
