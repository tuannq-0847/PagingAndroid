package com.rikkeisoft.pagindandroid.ui.a

import com.rikkeisoft.pagindandroid.ui.dialog.CommonDialog
import java.util.*

object MessageView {

    val queue: Queue<CommonDialog> = LinkedList()
    fun checkMessageDialog(commonDialog: CommonDialog): CommonDialog {
        queue.add(commonDialog)
        return queue.remove()
    }
}