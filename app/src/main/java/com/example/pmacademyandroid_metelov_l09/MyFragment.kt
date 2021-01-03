package com.example.pmacademyandroid_metelov_l09

import android.content.BroadcastReceiver
import android.content.Context
import android.content.Intent
import android.content.IntentFilter
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.fragment.app.Fragment

class MyFragment : Fragment(R.layout.my_fragment) {

    companion object {
        fun newInstance() = MyFragment()
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?
    ): View? {
        return super.onCreateView(inflater, container, savedInstanceState)
    }

    override fun onResume() {
        super.onResume()
        activity?.registerReceiver(receiver, IntentFilter(R.string.BROADCAST_ACTION.toString()))
    }

    override fun onPause() {
        super.onPause()
        activity?.unregisterReceiver(receiver)
    }

    override fun onDestroyView() {
        super.onDestroyView()
    }

    private val receiver = object : BroadcastReceiver() {
        override fun onReceive(context: Context, intent: Intent) {
            val resultUAH = intent.getDoubleExtra("valueUAH", 42.42)
            Log.d("serviceResultserviceRe", "$resultUAH")
            view?.findViewById<TextView>(R.id.tvResultFragment)?.text = resultUAH.toString()
        }
    }
}