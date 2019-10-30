package com.burcutopcu.numbers.vm

import android.app.AlertDialog
import android.content.Context
import android.graphics.Color
import android.view.LayoutInflater
import android.view.View
import android.view.inputmethod.InputMethodManager
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
import androidx.cardview.widget.CardView

class MainActivityViewModel @Inject
constructor(private val numbersRepo: NumbersRepo) : ViewModel() {

    private val numberText = MutableLiveData<String>()
    private var enteredNumber: Int = 0

    fun getNumberText(): LiveData<String> {
        return numberText
    }

    fun setEnteredNumber(number: Int) {
        enteredNumber = number
    }

    private fun putNumberInfo(number: Int, loadingIndicator: AVLoadingIndicatorView) {

        loadingIndicator.show()
        numbersRepo.getMathInfo(number, object : IServiceResponseCallback<NumberInfoModel> {

            override fun onServerCompleted(t: NumberInfoModel) {
                loadingIndicator.hide()
                numberText.value = t.text
            }

            override fun onServerError(error: String?) {
                loadingIndicator.hide()
                numberText.value = "Can not found a fact"
            }
        })
    }

    private fun putDateInfo(date: Int, loadingIndicator: AVLoadingIndicatorView) {

        loadingIndicator.show()
        numbersRepo.getDateInfo(date, object : IServiceResponseCallback<DateInfoModel> {

            override fun onServerCompleted(t: DateInfoModel) {
                loadingIndicator.hide()
                numberText.value = t.text
            }

            override fun onServerError(error: String?) {
                loadingIndicator.hide()
                numberText.value = "Can not found a fact"
            }
        })
    }

    private fun putYearInfo(year: Int, loadingIndicator: AVLoadingIndicatorView) {

        loadingIndicator.show()
        numbersRepo.getYearInfo(year, object : IServiceResponseCallback<YearInfoModel> {

            override fun onServerCompleted(t: YearInfoModel) {
                loadingIndicator.hide()
                numberText.value = t.text
            }

            override fun onServerError(error: String?) {
                loadingIndicator.hide()
                numberText.value = "Can not found a fact"
            }
        })
    }

    fun onSnack(view: View) {

        val snackbar = Snackbar.make(
            view, "Please enter a valid number",
            Snackbar.LENGTH_LONG
        ).setAction("Action", null)
        snackbar.setActionTextColor(Color.BLUE)
        val snackbarView = snackbar.view
        snackbarView.setBackgroundColor(Color.parseColor("#000000"))
        snackbar.show()
    }

    fun getDialogView(
        context: Context,
        loadingIndicator: AVLoadingIndicatorView,
        cvFacts: CardView
    ) {

        val mDialogView = LayoutInflater.from(context).inflate(com.burcutopcu.numbers.R.layout.dialog_buttons, null)
        //AlertDialogBuilder
        val mBuilder = AlertDialog.Builder(context)
            .setView(mDialogView)
        //show dialog
        val mAlertDialog = mBuilder.show()

        mDialogView.buttonGetDateInfo.setOnClickListener {
            numberText.value = ""
            cvFacts.visibility = View.VISIBLE
            loadingIndicator.show()
            mAlertDialog.hide()
            putDateInfo(enteredNumber, loadingIndicator)
        }

        mDialogView.buttonGetNumberInfo.setOnClickListener {
            numberText.value = ""
            cvFacts.visibility = View.VISIBLE
            loadingIndicator.show()
            mAlertDialog.hide()
            putNumberInfo(enteredNumber, loadingIndicator)
        }

        mDialogView.buttonGetYearInfo.setOnClickListener {
            numberText.value = ""
            cvFacts.visibility = View.VISIBLE
            loadingIndicator.show()
            mAlertDialog.hide()
            putYearInfo(enteredNumber, loadingIndicator)
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