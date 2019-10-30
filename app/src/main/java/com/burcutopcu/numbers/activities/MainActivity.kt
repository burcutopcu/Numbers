package com.burcutopcu.numbers.activities

import android.graphics.Color
import android.os.Build
import android.os.Bundle
import android.view.View
import android.view.WindowManager
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.Observer
import com.burcutopcu.numbers.R
import com.burcutopcu.numbers.di.DaggerMainActivityComponent
import com.burcutopcu.numbers.vm.MainActivityViewModel
import kotlinx.android.synthetic.main.activity_main.*
import javax.inject.Inject



class MainActivity : AppCompatActivity() {

    @Inject
    lateinit var numberDataLiveModel: MainActivityViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        if (Build.VERSION.SDK_INT in 19..20) {
            setWindowFlag(WindowManager.LayoutParams.FLAG_TRANSLUCENT_STATUS, true)
        }
        if (Build.VERSION.SDK_INT >= 19) {
            window.decorView.systemUiVisibility =
                View.SYSTEM_UI_FLAG_LAYOUT_STABLE or View.SYSTEM_UI_FLAG_LAYOUT_FULLSCREEN
        }
        if (Build.VERSION.SDK_INT >= 21) {
            setWindowFlag(WindowManager.LayoutParams.FLAG_TRANSLUCENT_STATUS, false)
            window.statusBarColor = Color.TRANSPARENT
        }

        DaggerMainActivityComponent.builder().application(this).build().inject(this)

        numberDataLiveModel.getNumberText().observe(this, Observer<String> {
            textNumber.text = it
        })

        etNumber.onFocusChangeListener = View.OnFocusChangeListener { v, hasFocus ->
            if (!hasFocus) {
                numberDataLiveModel.hideKeyboard(this, rlContent)
            }
        }

        buttonGetFact.setOnClickListener {

            if (etNumber.text.toString() != "") {
                numberDataLiveModel.setEnteredNumber(etNumber.text.toString().toInt())
                numberDataLiveModel.getDialogView(this, loadingIndicator, cvFacts)
            } else
                numberDataLiveModel.onSnack(rlContent)
        }
    }

    private fun setWindowFlag(bits: Int, on: Boolean) {
        val win = window
        val winParams = win.attributes
        if (on) {
            winParams.flags = winParams.flags or bits
        } else {
            winParams.flags = winParams.flags and bits.inv()
        }
        win.attributes = winParams
    }
}
