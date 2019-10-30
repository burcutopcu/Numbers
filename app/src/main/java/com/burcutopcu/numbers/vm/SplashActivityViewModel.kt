package com.burcutopcu.numbers.vm

import android.content.Context
import android.content.Intent
import androidx.core.content.ContextCompat.startActivity
import androidx.lifecycle.ViewModel
import com.burcutopcu.numbers.activities.MainActivity

class SplashActivityViewModel : ViewModel() {

    fun openMainActivity(context: Context) {
        val intent = Intent(context, MainActivity::class.java)
        intent.flags = Intent.FLAG_ACTIVITY_NEW_TASK
        startActivity(context, intent, null)
    }

}