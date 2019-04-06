package com.burcutopcu.numbers.network.repo

import com.burcutopcu.numbers.app.IServiceResponseCallback
import com.burcutopcu.numbers.models.DateInfoModel
import com.burcutopcu.numbers.models.NumberInfoModel
import com.burcutopcu.numbers.models.YearInfoModel

interface INumbersRepo {

    fun getYearInfo(year: Int, callback: IServiceResponseCallback<YearInfoModel>)
    fun getDateInfo(date: Int, callback: IServiceResponseCallback<DateInfoModel>)
    fun getMathInfo(math: Int, callback: IServiceResponseCallback<NumberInfoModel>)

}