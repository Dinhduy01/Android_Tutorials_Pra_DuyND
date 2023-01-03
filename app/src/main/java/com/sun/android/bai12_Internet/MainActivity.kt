package com.sun.android.bai12_Internet

import android.content.Context
import android.net.ConnectivityManager
import android.net.NetworkCapabilities
import android.net.NetworkInfo
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.inputmethod.InputMethodManager
import androidx.loader.app.LoaderManager
import androidx.loader.content.Loader
import com.sun.android.R
import com.sun.android.databinding.ActivityMain12Binding
import org.json.JSONArray
import org.json.JSONObject

class MainActivity : AppCompatActivity(), LoaderManager.LoaderCallbacks<String> {
    val binding by lazy { ActivityMain12Binding.inflate(layoutInflater) }
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(binding.root)
        isOnline(this)
        if (supportLoaderManager.getLoader<String>(0) != null) {
            supportLoaderManager.initLoader(0, null, this)
        }
        binding.buttonSearch.setOnClickListener() {
            val queryString: String = binding.editTextBookInput.text.toString()
            val inputManager: InputMethodManager = getSystemService(Context.INPUT_METHOD_SERVICE) as InputMethodManager
            if (inputManager != null) {
                inputManager.hideSoftInputFromWindow(
                    binding.buttonSearch.windowToken,
                    InputMethodManager.HIDE_NOT_ALWAYS
                )

            }
            val connectivityManager: ConnectivityManager =
                getSystemService(Context.CONNECTIVITY_SERVICE) as ConnectivityManager
            var networkInfo: NetworkInfo? = null
            if (connectivityManager != null) {
                networkInfo = connectivityManager.activeNetworkInfo
            }
            if (networkInfo != null && networkInfo.isConnected && queryString.length != 0) {

                Companion.queryBundle.putString("queryString", queryString)
                supportLoaderManager.restartLoader(0, Companion.queryBundle, this)
                binding.textViewAuthor.text = ""
                binding.textViewTitle.text = getString(R.string.loading)
            } else {
                if (queryString.length == 0) {
                    binding.textViewAuthor.text = ""
                    binding.textViewTitle.text = getString(R.string.no_search_term)
                } else {
                    binding.textViewAuthor.text = ""
                    binding.textViewTitle.text = getString(R.string.no_network)
                }
            }
        }
    }

    fun isOnline(context: Context): Boolean {
        val connectivityManager =
            context.getSystemService(Context.CONNECTIVITY_SERVICE) as ConnectivityManager
        if (connectivityManager != null) {
            val capabilities =
                connectivityManager.getNetworkCapabilities(connectivityManager.activeNetwork)
            if (capabilities != null) {
                if (capabilities.hasTransport(NetworkCapabilities.TRANSPORT_CELLULAR)) {
                    Log.i("Internet", "TRANSPORT_CELLULAR")
                    return true
                } else if (capabilities.hasTransport(NetworkCapabilities.TRANSPORT_WIFI)) {
                    Log.i("Internet", "TRANSPORT_WIFI")
                    return true
                } else if (capabilities.hasTransport(NetworkCapabilities.TRANSPORT_ETHERNET)) {
                    Log.i("Internet", "TRANSPORT_ETHERNET")
                    return true
                }
            }
        }
        return false
    }

    override fun onCreateLoader(id: Int, args: Bundle?): Loader<String> {
        var queryString: String = ""
        if (args != null) {
            queryString = args.getString("queryString").toString()

        }
        return BookLoader(this, queryString)
    }

    override fun onLoadFinished(loader: Loader<String>, data: String) {
        try {
            val jsonObject = JSONObject(data)
            val itemsArray: JSONArray = jsonObject.getJSONArray("items")
            var i = 0
            var title: String? = null
            var authors: String? = null
            while (i < itemsArray.length() &&
                authors == null && title == null
            ) {
                val book = itemsArray.getJSONObject(i)
                try {
                    val volumeInfo = book.getJSONObject(volumeInfo)
                    title = volumeInfo.getString(titleInfo)
                    authors = volumeInfo.getString(authorInfo)

                } catch (e: Exception) {
                    e.printStackTrace()
                }
                i++
            }
            if (title != null && authors != null) {
                binding.textViewTitle.text = title
                binding.textViewAuthor.text = authors
            } else {
                binding.textViewTitle.text = getString(R.string.no_result)
                binding.textViewAuthor.text = ""

            }
        } catch (e: Exception) {
            binding.textViewTitle.text = getString(R.string.no_result)
            binding.textViewAuthor.text = ""
        }
    }

    override fun onLoaderReset(loader: Loader<String>) {

    }

    companion object {
        val queryBundle = Bundle()
        const val volumeInfo = "volumeInfo"
        const val titleInfo = "title"
        const val authorInfo = "authors"

    }


}
