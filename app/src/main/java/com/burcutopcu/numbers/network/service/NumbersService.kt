package com.burcutopcu.numbers.network.service

import com.burcutopcu.numbers.models.DateInfoModel
import com.burcutopcu.numbers.models.NumberInfoModel
import com.burcutopcu.numbers.models.YearInfoModel
import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Path

interface NumbersService {

    @GET("{year}/year")
    fun getYearInfo(@Path("year") year: Int): Call<YearInfoModel>

    @GET("{date}/date")
    fun getDateInfo(@Path("date") date: Int): Call<DateInfoModel>

    @GET("{number}/math")
    fun getMathInfo(@Path("number") number: Int): Call<NumberInfoModel>

}