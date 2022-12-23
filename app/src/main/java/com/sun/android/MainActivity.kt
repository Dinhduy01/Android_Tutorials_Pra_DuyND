package com.sun.android

import android.app.Activity
import android.content.Intent
import android.os.Bundle
import android.view.View
import android.widget.EditText
import android.widget.TextView
import androidx.activity.result.contract.ActivityResultContracts
import androidx.appcompat.app.AppCompatActivity


class MainActivity : AppCompatActivity() {

    val startForResult = registerForActivityResult(ActivityResultContracts.StartActivityForResult())
    {
        if (it.resultCode == Activity.RESULT_OK) {
            //  you will get result here in result.data
            val string:String = it?.data?.extras?.get("msgBack").toString()
            val txtRev: TextView = findViewById(R.id.text_message_reply)
            val txtHead:TextView = findViewById(R.id.text_header_reply)
            txtHead.visibility=View.VISIBLE
            txtRev.visibility=View.VISIBLE
            txtRev.text=string
        }
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
    }

    fun launchSecondActivity(view: View?) {
        var  txtSend = findViewById<EditText>(R.id.editText_main)
        val intent= Intent(this,newA::class.java)
        var txtMsg:String = "${txtSend?.text}"
        intent.putExtra("msg",txtMsg)
        txtSend.text=null
        startForResult.launch(intent)
    }


}
