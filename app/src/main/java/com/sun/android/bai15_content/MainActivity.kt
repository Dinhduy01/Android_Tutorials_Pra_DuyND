package com.sun.android.bai15_content

import android.Manifest
import android.content.pm.PackageManager
import android.graphics.Bitmap
import android.net.Uri
import android.os.Bundle
import android.provider.ContactsContract.CommonDataKinds
import android.provider.MediaStore
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.core.app.ActivityCompat
import androidx.core.content.ContextCompat
import com.sun.android.databinding.ActivityMain15Binding


class MainActivity : AppCompatActivity() {
    val binding by lazy { ActivityMain15Binding.inflate(layoutInflater) }
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(binding.root)
        if (ContextCompat.checkSelfPermission(
                this, Manifest.permission.READ_CONTACTS
            ) !== PackageManager.PERMISSION_GRANTED
        ) {
            if (ActivityCompat.shouldShowRequestPermissionRationale(
                    this, Manifest.permission.READ_CONTACTS
                )
            ) {
                ActivityCompat.requestPermissions(
                    this, arrayOf(Manifest.permission.READ_CONTACTS), 1
                )
            } else {
                ActivityCompat.requestPermissions(
                    this, arrayOf(Manifest.permission.READ_CONTACTS), 1
                )
            }
        }


        val adapter = CustomAdapter(getContacts())
        binding.listViewContacts.adapter = adapter
        binding.listViewContacts.setOnItemClickListener { parent, view, position, id ->
            val contacts = binding.listViewContacts.getItemAtPosition(position) as Contacts
            Toast.makeText(this, "${contacts.name}", Toast.LENGTH_SHORT).show()

        }
    }

    override fun onRequestPermissionsResult(
        requestCode: Int, permissions: Array<String>, grantResults: IntArray
    ) {
        when (requestCode) {
            1 -> {
                if (grantResults.isNotEmpty() && grantResults[0] == PackageManager.PERMISSION_GRANTED) {
                    if ((ContextCompat.checkSelfPermission(
                            this, Manifest.permission.READ_CONTACTS
                        ) === PackageManager.PERMISSION_GRANTED)
                    ) {
                        Toast.makeText(this, "Permission Granted", Toast.LENGTH_SHORT).show()
                    }
                } else {
                    Toast.makeText(this, "Permission Denied", Toast.LENGTH_SHORT).show()
                }
                return
            }
        }
    }


    fun getContacts(): MutableList<Contacts> {
        val cursor = contentResolver.query(CommonDataKinds.Contactables.CONTENT_URI, null, null, null, null)

        val mutableList = mutableListOf<Contacts>()

        if (cursor != null && cursor.moveToFirst()) {
            do {

                val phone = cursor.getString(cursor.getColumnIndex(CommonDataKinds.Phone.NUMBER))
                val name = cursor.getString(cursor.getColumnIndex(CommonDataKinds.Phone.DISPLAY_NAME))
                val photo_uri = cursor.getString(cursor.getColumnIndex(CommonDataKinds.Phone.PHOTO_URI))
                var image: Bitmap?=null
                if (photo_uri != null) {
                    image = MediaStore.Images.Media.getBitmap(contentResolver, Uri.parse(photo_uri))

                }
                var people = Contacts(name, phone, image)
                mutableList.add(people)

            } while (cursor.moveToNext())
            cursor.close()
        }
        return mutableList
    }


}


