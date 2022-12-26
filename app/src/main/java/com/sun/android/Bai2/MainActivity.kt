package com.sun.android.Bai2

import android.content.Intent
import android.net.Uri
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.webkit.URLUtil
import com.sun.android.databinding.ActivityMain2Binding

class MainActivity : AppCompatActivity() {
    val binding by lazy { ActivityMain2Binding.inflate(layoutInflater) }
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(binding.root)
        binding.buttonOpenWebsite.setOnClickListener {
            val uri: Uri = Uri.parse("https://${binding.editTextWebsite.text}")

            if (URLUtil.isValidUrl("https://${binding.editTextWebsite.text}")) {
                binding.editTextWebsite.text = null
                val intent = Intent(Intent.ACTION_VIEW, uri)
                startActivity(intent)
            }

        }
        binding.buttonOpenLocation.setOnClickListener {
            val gmmIntentUri = Uri.parse("geo:0,0?q=${binding.editTextLocation.text}")
            binding.editTextLocation.text = null
            val mapIntent = Intent(Intent.ACTION_VIEW, gmmIntentUri)
            mapIntent.setPackage("com.google.android.apps.maps")
            startActivity(mapIntent)


        }
        binding.buttonShareText.setOnClickListener {
            val sendIntent: Intent = Intent().apply {

                action = Intent.ACTION_SEND
                putExtra(Intent.EXTRA_TEXT, "${binding.editTextShare.text} \n-Auto message from Duybe")
                binding.editTextShare.text = null
                type = "text/plain"
            }
            startActivity(sendIntent)
//            val gmmIntentUri = Uri.parse("com.facebook.katana")
//            val mapIntent = Intent(Intent.ACTION_VIEW, gmmIntentUri)
//            val mapIntent = Intent(Intent.ACTION_VIEW)
//            mapIntent.setPackage("com.google.android.apps.maps")
//            startActivity(mapIntent)
        }

    }
}
