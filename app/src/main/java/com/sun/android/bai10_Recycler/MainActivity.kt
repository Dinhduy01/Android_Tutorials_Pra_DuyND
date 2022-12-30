package com.sun.android.bai10_Recycler

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.Menu
import android.view.MenuItem
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.sun.android.R
import com.sun.android.databinding.ActivityMain9Binding

class MainActivity : AppCompatActivity(), OnClickListener<Int> {

    private lateinit var mRecyclerView: RecyclerView
    private lateinit var mAdapter: WordListAdapter
    private val mWordList: MutableList<String> = mutableListOf()
    val binding by lazy { ActivityMain9Binding.inflate(layoutInflater) }
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(binding.root)
        setSupportActionBar(binding.toolbar)
        for (i in 0..20) {
            mWordList.add(i, "Word $i")
        }
        binding.fab.setOnClickListener() {
            val wordListSize: Int? = mWordList.size
            if (wordListSize != null) {
                mRecyclerView.adapter?.notifyItemInserted(wordListSize)
                mRecyclerView.smoothScrollToPosition(wordListSize)
            }
        }

        mRecyclerView = binding.recyclerview
        mAdapter = WordListAdapter()
        mRecyclerView.adapter = mAdapter
        mAdapter.updateList(mWordList)
        mAdapter.updateOnclickListener(this)
    }

    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        menuInflater.inflate(R.menu.menu_main, menu)
        return true
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        if (item.itemId == R.id.action_settings) {
            return true
        }
        return super.onOptionsItemSelected(item)
    }

    override fun onClick(position: Int) {
        mWordList[position] = if (mWordList[position].startsWith("Click")) {
            "Word $position"
        } else {
            "Click $position"
        }
        mAdapter.updateList(mWordList)
    }

}
