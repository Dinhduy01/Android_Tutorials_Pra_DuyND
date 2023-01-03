package com.sun.android.bai12_Internet

import android.content.Context
import android.util.Log
import androidx.loader.content.AsyncTaskLoader

class BookLoader(context: Context, queryString: String) : AsyncTaskLoader<String>(context) {
    private var mqueryString: String = queryString


    override fun loadInBackground(): String? {
        val networkUtils = NetworkUtils()
        return networkUtils.getBookInfo(mqueryString)
    }

    override fun onStartLoading() {
        super.onStartLoading()
        forceLoad()
    }
}
