package com.burcutopcu.numbers.vm

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.burcutopcu.numbers.network.repo.NumbersRepo
import javax.inject.Inject

class MainActivityViewModelFactory @Inject
constructor(private val numbersRepo: NumbersRepo) : ViewModelProvider.Factory {

    override fun <T : ViewModel?> create(modelClass: Class<T>): T {
        if (modelClass.isAssignableFrom(MainActivityViewModel::class.java)) {
            return MainActivityViewModel(numbersRepo) as T
        }
        throw IllegalArgumentException("Unknown ViewModel class")
    }
}