package com.rikkeisoft.pagindandroid.ui.a

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.rikkeisoft.pagindandroid.R
import com.rikkeisoft.pagindandroid.ui.adapter.TestAdapter
import kotlinx.android.synthetic.main.activity_aa.*

class AActivity : AppCompatActivity() {

    private lateinit var testAdapter: TestAdapter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_aa)
        testAdapter = TestAdapter(mutableListOf("a", "b", "c", "d", "e", "f", "g", "h"))
        recyclerAA.adapter = testAdapter
    }

}
