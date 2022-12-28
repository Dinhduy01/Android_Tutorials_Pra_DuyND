package com.sun.android.bai8_tab

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.viewpager2.widget.ViewPager2
import com.google.android.material.tabs.TabLayout
import com.google.android.material.tabs.TabLayoutMediator
import com.sun.android.R
import com.sun.android.databinding.ActivityMain8Binding


class MainActivity : AppCompatActivity() {
    val binding by lazy { ActivityMain8Binding.inflate(layoutInflater) }
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(binding.root)

        val adapter = ViewPagerAdapter(this)

        adapter.addFragment( tab_fragment1.newInstance(),getString(R.string.tab_label1))
        adapter.addFragment( tab_fragment2.newInstance(),getString(R.string.tab_label2))
        adapter.addFragment( tab_fragment3.newInstance(),getString(R.string.tab_label3))
        val viewPager: ViewPager2 = binding.pager
        viewPager.adapter = adapter
        viewPager.currentItem = 0
        val tabs: TabLayout = binding.tabLayout
        TabLayoutMediator(tabs, viewPager) { tab, position ->
            tab.text = adapter.getTabTitle(position)
        }.attach()
    }
}
