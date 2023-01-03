package com.sun.android.bai11_AsyncTask

import android.os.AsyncTask
import android.os.SystemClock
import android.widget.ProgressBar
import android.widget.TextView
import java.lang.ref.WeakReference
import kotlin.random.Random

class SimpleAsyncTask(textView: TextView, progessBar: ProgressBar) : AsyncTask<Void, Int, String>() {
    private var mTextView: WeakReference<TextView> = WeakReference(textView)
    private var mProgressBar: ProgressBar = progessBar
    override fun doInBackground(vararg p0: Void?): String {
        val n = Random.nextLong(20)
        val s = n * 200
        try {
            Thread.sleep(s)
        } catch (e: InterruptedException) {
            e.printStackTrace()
        }
        for (i in 0..100) {
            SystemClock.sleep((s / 100))
            publishProgress(i)
        }
        return "Awake at last after sleeping for $s milliseconds!"
    }

    override fun onProgressUpdate(vararg values: Int?) {
        super.onProgressUpdate(*values)
        val number = values[0]
        if (number != null) {
            mProgressBar.progress = number
        }

    }

    override fun onPostExecute(result: String) {
        mTextView.get()?.text = result

    }
}
