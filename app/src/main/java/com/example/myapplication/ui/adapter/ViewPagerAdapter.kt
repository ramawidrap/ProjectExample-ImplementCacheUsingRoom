package com.example.myapplication.ui.adapter

import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentManager
import androidx.fragment.app.FragmentStatePagerAdapter
import com.example.myapplication.ui.MovieFragment

class ViewPagerAdapter(fm : FragmentManager) : FragmentStatePagerAdapter(fm) {
    override fun getItem(position: Int): Fragment {
        return when(position) {
            0 -> MovieFragment()
            else -> MovieFragment()
        }
    }

    override fun getCount(): Int {
        return 1
    }
}