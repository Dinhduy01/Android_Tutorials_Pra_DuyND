package com.sun.android.bai15_content

import android.content.Intent
import android.graphics.Bitmap
import android.net.Uri
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.BaseAdapter
import android.widget.Toast
import com.sun.android.R
import com.sun.android.databinding.ListViewItemBinding


class CustomAdapter( items: MutableList<Contacts>) : BaseAdapter() {
    private var items: MutableList<Contacts> = items
    override fun getCount(): Int {
        return items.size
    }

    override fun getItem(p0: Int): Any {
        return items[p0]
    }

    override fun getItemId(p0: Int): Long {
        return p0.toLong()
    }

    override fun getView(p0: Int, p1: View?, parent: ViewGroup?): View? {
        val convertView: ListViewItemBinding
        val inflater = LayoutInflater.from(parent?.context)
        convertView = ListViewItemBinding.inflate(inflater, parent, false)
        val currentItem: Contacts = getItem(p0) as? Contacts ?: Contacts("", "",null)
        convertView.textViewName.text = currentItem.name
        convertView.textViewPhoneNumber.text = currentItem.phoneNumber
        if(currentItem.photo!=null){
            convertView.imageViewContact.setImageBitmap(currentItem.photo)
        }
        else{
            convertView.imageViewContact.setImageDrawable(parent?.context?.getDrawable(R.drawable.messenger))
        }

        convertView.buttonCall.setOnClickListener {
            Toast.makeText(parent?.context, "Calling ${currentItem.name}", Toast.LENGTH_SHORT).show()
            val intent = Intent(Intent.ACTION_DIAL)
            intent.data = Uri.parse("tel:${currentItem.phoneNumber}")
            parent?.context?.startActivity(intent)
        }
        return convertView.root
    }


}
