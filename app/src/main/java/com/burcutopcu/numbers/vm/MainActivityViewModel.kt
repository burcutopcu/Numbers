package com.burcutopcu.numbers.vm

import android.app.Activity
import android.app.AlertDialog
import android.content.Context
import android.graphics.Color
import android.view.LayoutInflater
import android.view.View
import android.view.inputmethod.InputMethodManager
import android.widget.Button
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.burcutopcu.numbers.app.IServiceResponseCallback
import com.burcutopcu.numbers.models.DateInfoModel
import com.burcutopcu.numbers.models.NumberInfoModel
import com.burcutopcu.numbers.models.YearInfoModel
import com.burcutopcu.numbers.network.repo.NumbersRepo
import com.google.android.material.snackbar.Snackbar
import com.wang.avi.AVLoadingIndicatorView
import kotlinx.android.synthetic.main.dialog_buttons.view.*
import javax.inject.Inject
import androidx.core.content.ContextCompat.getSystemService
import android.R

class MainActivityViewModel @Inject
constructor(private val numbersRepo: NumbersRepo): ViewModel() {

    private val numberText = MutableLiveData<String>()
    private var enteredNumber: Int = 0

    fun getNumberText(): LiveData<String> {
        return numberText
    }

    fun setEnteredNumber(number: Int){
        enteredNumber=number
    }

    private fun putNumberInfo(number: Int, loadingIndicator: AVLoadingIndicatorView) {

        loadingIndicator.show()
        numbersRepo.getMathInfo(number, object : IServiceResponseCallback<NumberInfoModel> {

            override fun onServerCompleted(t: NumberInfoModel) {
                numberText.value= t.text
                loadingIndicator.hide()
            }

            override fun onServerError(error: String?) {
                numberText.value=error
                loadingIndicator.hide()
            }
        })
    }

    private fun putDateInfo(date: Int, loadingIndicator: AVLoadingIndicatorView) {

        loadingIndicator.show()
        numbersRepo.getDateInfo(date, object : IServiceResponseCallback<DateInfoModel> {

            override fun onServerCompleted(t: DateInfoModel) {
                numberText.value= t.text
                loadingIndicator.hide()
            }

            override fun onServerError(error: String?) {
                numberText.value=error
                loadingIndicator.hide()
            }
        })
    }

    private fun putYearInfo(year: Int, loadingIndicator: AVLoadingIndicatorView) {

        loadingIndicator.show()
        numbersRepo.getYearInfo(year, object : IServiceResponseCallback<YearInfoModel> {

            override fun onServerCompleted(t: YearInfoModel) {
                numberText.value= t.text
                loadingIndicator.hide()


            }

            override fun onServerError(error: String?) {
                numberText.value=error
                loadingIndicator.hide()
            }
        })
    }

    fun onSnack(view: View){

        val snackbar = Snackbar.make(view, "Please enter a valid number",
            Snackbar.LENGTH_LONG).setAction("Action", null)
        snackbar.setActionTextColor(Color.BLUE)
        val snackbarView = snackbar.view
        snackbarView.setBackgroundColor(Color.parseColor("#000000"))
        snackbar.show()
    }

    fun getDialogView(context: Context, loadingIndicator: AVLoadingIndicatorView){

        val mDialogView = LayoutInflater.from(context).inflate(com.burcutopcu.numbers.R.layout.dialog_buttons, null)
        //AlertDialogBuilder
        val mBuilder = AlertDialog.Builder(context)
            .setView(mDialogView)
        //show dialog
        val  mAlertDialog = mBuilder.show()

        mDialogView.buttonGetDateInfo.setOnClickListener {
            numberText.value=""
            loadingIndicator.show()
            mAlertDialog.hide()
            putDateInfo(enteredNumber,loadingIndicator)
        }

        mDialogView.buttonGetNumberInfo.setOnClickListener {
            numberText.value=""
            loadingIndicator.show()
            mAlertDialog.hide()
            putNumberInfo(enteredNumber,loadingIndicator)
        }

        mDialogView.buttonGetYearInfo.setOnClickListener {
            numberText.value=""
            loadingIndicator.show()
            mAlertDialog.hide()
            putYearInfo(enteredNumber,loadingIndicator)
        }
    }

    fun hideKeyboard(context: Context, view: View) {
        try {
            val imm = context.getSystemService(Context.INPUT_METHOD_SERVICE) as InputMethodManager
            imm.hideSoftInputFromWindow(view.windowToken, InputMethodManager.HIDE_NOT_ALWAYS)
        } catch (e: Exception) {
            // TODO: handle exception
            e.printStackTrace()
        }
    }
}