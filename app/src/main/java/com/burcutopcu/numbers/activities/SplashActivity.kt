package com.burcutopcu.numbers.activities

import android.os.Bundle
import android.view.View
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.ViewModelProviders
import androidx.viewpager.widget.ViewPager
import com.burcutopcu.numbers.R
import com.burcutopcu.numbers.adapters.SliderAdapter
import com.burcutopcu.numbers.vm.SplashActivityViewModel
import com.facebook.stetho.Stetho
import com.tbuonomo.viewpagerdotsindicator.WormDotsIndicator
import kotlinx.android.synthetic.main.activity_splash.*

class SplashActivity : AppCompatActivity() {

    lateinit var viewPager: ViewPager
    lateinit var sliderAdapter: SliderAdapter
    lateinit var splashViewModel: SplashActivityViewModel
    var currentPage: Int = 0

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_splash)

        splashViewModel = ViewModelProviders.of(this).get(SplashActivityViewModel::class.java)

        viewPager = findViewById(R.id.view_pager)
        sliderAdapter = SliderAdapter(this)
        viewPager.adapter = sliderAdapter

        val wormDotsIndicator = findViewById<WormDotsIndicator>(R.id.worm_dots_indicator)
        wormDotsIndicator.setViewPager(viewPager)

        nextButton.setOnClickListener {
            viewPager.currentItem = currentPage + 1
        }

        backButton.setOnClickListener {
            viewPager.currentItem = currentPage - 1
        }


        viewPager.addOnPageChangeListener(object : ViewPager.OnPageChangeListener {

            override fun onPageScrollStateChanged(state: Int) {

            }

            override fun onPageScrolled(position: Int, positionOffset: Float, positionOffsetPixels: Int) {

            }

            override fun onPageSelected(position: Int) {
                currentPage = position

                when (position) {
                    0 -> {
                        backButton.isEnabled = false
                        backButton.visibility = View.INVISIBLE
                        nextButton.isEnabled = true
                        nextButton.text = getString(R.string.next)

                    }
                    3 -> {
                        backButton.isEnabled = true
                        backButton.visibility = View.VISIBLE
                        nextButton.isEnabled = true
                        nextButton.visibility = View.VISIBLE

                        nextButton.text = getString(R.string.finish)
                        backButton.text = getString(R.string.back)
                        nextButton.setOnClickListener {
                            splashViewModel.openMainActivity(baseContext)
                        }
                    }
                    else -> {
                        backButton.isEnabled = true
                        backButton.visibility = View.VISIBLE
                        nextButton.isEnabled = true
                        nextButton.visibility = View.VISIBLE

                        nextButton.text = getString(R.string.next)
                        backButton.text = getString(R.string.back)
                    }
                }
            }
        })
    }
}