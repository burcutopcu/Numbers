package com.burcutopcu.numbers.adapters

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.RelativeLayout
import android.widget.TextView
import androidx.viewpager.widget.PagerAdapter
import com.burcutopcu.numbers.R

class SliderAdapter(context: Context) : PagerAdapter() {

    private var mContext: Context = context
    lateinit var layoutInflater: LayoutInflater
    private val headers = listOf("ENTER", "MATH", "DATE", "YEAR")
    private val descriptions = listOf(
        "Enter the number in the area",
        "If you enter the information mathematically, you'll learn what that number means in Mathematics",
        "If you want to get information as date, we will find out which day of the year is the number you entered.",
        "If you want to see what important event happened in a specific year, you can write that year as a number"
    )


    override fun isViewFromObject(view: View, `object`: Any): Boolean {
        return view == `object` as RelativeLayout
    }

    override fun getCount(): Int {
        return descriptions.size
    }

    override fun instantiateItem(container: ViewGroup, position: Int): Any {
        layoutInflater = mContext.getSystemService(Context.LAYOUT_INFLATER_SERVICE) as LayoutInflater
        val view = layoutInflater.inflate(R.layout.slide_layout, container, false)

        val headerTv = view.findViewById<TextView>(R.id.header_tv)
        val descTv = view.findViewById<TextView>(R.id.detail_tv)

        headerTv.text = headers[position]
        descTv.text = descriptions[position]

        container.addView(view)
        return view
    }

    override fun destroyItem(container: ViewGroup, position: Int, `object`: Any) {
        container.removeView(`object` as RelativeLayout)
    }
}