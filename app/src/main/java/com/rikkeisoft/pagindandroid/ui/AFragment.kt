package com.rikkeisoft.pagindandroid.ui

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import com.rikkeisoft.pagindandroid.R
import kotlinx.android.synthetic.main.fragment_a.*

class AFragment : Fragment() {

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.fragment_a, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        buttonA.setOnClickListener {
            //animation 2 animation is open , 2 animation after is exit
            fragmentManager?.beginTransaction()
                ?.setCustomAnimations(
                    R.anim.bottom_to_top,
                    R.anim.exit_to_left
                )?.add(R.id.testContainer, BFragment())
                ?.addToBackStack(null)
                ?.commit()
        }
    }
}