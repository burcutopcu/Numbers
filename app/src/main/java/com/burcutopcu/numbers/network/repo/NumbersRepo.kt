package com.burcutopcu.numbers.network.repo

import com.burcutopcu.numbers.app.ApiManager
import com.burcutopcu.numbers.app.IServiceResponseCallback
import com.burcutopcu.numbers.models.DateInfoModel
import com.burcutopcu.numbers.models.NumberInfoModel
import com.burcutopcu.numbers.models.YearInfoModel
import com.burcutopcu.numbers.network.service.NumbersService
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import javax.inject.Inject

class NumbersRepo @Inject constructor(apiManager: ApiManager): INumbersRepo {

    private var numbersService: NumbersService = apiManager.createRetrofitService(NumbersService::class.java)
    val UNKNOWN_ERROR: String = "Unknown Error"

    override fun getYearInfo(year: Int, callback: IServiceResponseCallback<YearInfoModel>) {

        val infoModelCall = numbersService.getYearInfo(year)
        infoModelCall.enqueue(object : Callback<YearInfoModel> {
            override fun onResponse(call: Call<YearInfoModel>, response: Response<YearInfoModel>) {
                if (response.isSuccessful && response.body() != null) {
                    callback.onServerCompleted(response.body()!!)
                } else
                    callback.onServerError(UNKNOWN_ERROR)
            }

            override fun onFailure(call: Call<YearInfoModel>, t: Throwable) {
                callback.onServerError(UNKNOWN_ERROR)
            }
        })
    }

    override fun getDateInfo(date: Int, callback: IServiceResponseCallback<DateInfoModel>) {

        val infoModelCall = numbersService.getDateInfo(date)
        infoModelCall.enqueue(object : Callback<DateInfoModel> {
            override fun onResponse(call: Call<DateInfoModel>, response: Response<DateInfoModel>) {
                if (response.isSuccessful && response.body() != null) {
                    callback.onServerCompleted(response.body()!!)
                } else
                    callback.onServerError(UNKNOWN_ERROR)
            }

            override fun onFailure(call: Call<DateInfoModel>, t: Throwable) {
                callback.onServerError(UNKNOWN_ERROR)
            }
        })
    }

    override fun getMathInfo(math: Int, callback: IServiceResponseCallback<NumberInfoModel>) {

        val infoModelCall = numbersService.getMathInfo(math)
        infoModelCall.enqueue(object : Callback<NumberInfoModel> {
            override fun onResponse(call: Call<NumberInfoModel>, response: Response<NumberInfoModel>) {
                if (response.isSuccessful && response.body() != null) {
                    callback.onServerCompleted(response.body()!!)
                } else
                    callback.onServerError(UNKNOWN_ERROR)
            }

            override fun onFailure(call: Call<NumberInfoModel>, t: Throwable) {
                callback.onServerError(UNKNOWN_ERROR)
            }
        })
    }
}