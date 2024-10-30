package com.example.lab2.ui.main

import android.content.Intent
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import androidx.fragment.app.Fragment
import com.example.lab2.MainActivity
import com.example.lab2.R

class ThirdTabFragment : Fragment() {
    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val view = inflater.inflate(R.layout.fragment_third_tab ,container,false)
        val button = view.findViewById<Button>(R.id.button_go_to_main)
        button.setOnClickListener{
            val intent = Intent(activity, MainActivity::class.java)
            startActivity(intent)
        }
        return view
    }
}