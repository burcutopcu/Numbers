package com.burcutopcu.numbers.models

import java.util.*

class YearInfoModel: Observable() {

    var date:String=""
        set(value) {
            field=value
            setChangedAndNotify("date")
        }

    var text:String=""
        set(value) {
            field=value
            setChangedAndNotify("text")
        }

    var number:Int=0
        set(value) {
            field=value
            setChangedAndNotify("number")
        }

    var found:Boolean=false
        set(value) {
            field=value
            setChangedAndNotify("found")
        }

    var type:String=""
        set(value) {
            field=value
            setChangedAndNotify("type")
        }

    private fun setChangedAndNotify(field:Any) {
        setChanged()
        notifyObservers(field)
    }

}