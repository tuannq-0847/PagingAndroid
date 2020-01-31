package com.rikkeisoft.pagindandroid.utils

import android.content.Context
import android.widget.Toast

fun Context.showMessage(messsage: String) {
    Toast.makeText(this, messsage, Toast.LENGTH_SHORT).show()
}