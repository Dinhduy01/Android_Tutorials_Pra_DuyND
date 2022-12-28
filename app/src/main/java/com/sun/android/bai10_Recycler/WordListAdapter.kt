package com.sun.android.bai10_Recycler

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.sun.android.databinding.WordlistItemBinding

class WordListAdapter :
    RecyclerView.Adapter<WordListAdapter.WordViewHolder>() {
    private val mWordList: MutableList<String> = mutableListOf()
    private var listener: OnClickListener<Int>? = null

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): WordViewHolder {
        val binding = WordlistItemBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return WordViewHolder(binding, listener)
    }

    override fun onBindViewHolder(holder: WordViewHolder, position: Int) {
        holder.bind(mWordList[position])

    }

    override fun getItemCount(): Int {
        return mWordList.size
    }

    fun updateList(list: List<String>) {
        mWordList.clear()
        mWordList.addAll(list)
        notifyDataSetChanged()
    }

    fun updateOnclickListener(listener: OnClickListener<Int>) {
        this.listener = listener
    }

    inner class WordViewHolder(
        private val binding: WordlistItemBinding,
        private val listener: OnClickListener<Int>?
        ) : RecyclerView.ViewHolder(binding.root), View.OnClickListener {

        init {
            itemView.setOnClickListener(this)
        }

        fun bind(text: String) {
           binding.textViewItem.text = text
        }

        override fun onClick(p0: View?) {
            listener?.onClick(layoutPosition)
        }
    }

}
