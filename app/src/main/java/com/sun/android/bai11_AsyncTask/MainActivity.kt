package com.sun.android.bai11_AsyncTask

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.sun.android.R
import com.sun.android.databinding.ActivityMain11Binding
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.MainScope

class MainActivity : AppCompatActivity(), CoroutineScope by MainScope() {
    private lateinit var binding: ActivityMain11Binding
    private val TEXT_STATE = "currentText"
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMain11Binding.inflate(layoutInflater)
        setContentView(binding.root)
        if (savedInstanceState != null) {
            binding.textMessage.text = savedInstanceState.getString(TEXT_STATE)
        }
        binding.startTaskButton.setOnClickListener {
            binding.textMessage.text = getString(R.string.napping)

            SimpleAsyncTask(binding.textMessage, binding.progressBarAsync).execute()
        }
    }

    override fun onSaveInstanceState(outState: Bundle) {
        super.onSaveInstanceState(outState)
        outState.putString(TEXT_STATE, binding.textMessage.text.toString())
    }


}
