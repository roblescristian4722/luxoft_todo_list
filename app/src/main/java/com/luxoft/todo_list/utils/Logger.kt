package com.luxoft.todo_list.utils

import android.util.Log

object Alert {
    operator fun invoke(msg: String) {
        Log.d("ALERT", msg)
    }
}

object Status {
    operator fun invoke(msg: String) {
        Log.d("STATUS", msg)
    }
}

object Warning {
    operator fun invoke(msg: String) {
        Log.d("WARNING", msg)
    }
}