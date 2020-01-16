package com.rikkeisoft.pagindandroid.ui.dialog

import android.app.Dialog
import android.content.Context
import android.os.Bundle
import com.rikkeisoft.pagindandroid.R

class CommonDialog(context: Context) : Dialog(context) {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.layout_common_dialog)
    }
}
