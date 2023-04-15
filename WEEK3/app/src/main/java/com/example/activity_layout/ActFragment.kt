package com.example.activity_layout

import android.annotation.SuppressLint
import android.content.Context
import androidx.lifecycle.ViewModelProvider
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.EditText

class ActFragment : Fragment() {

    private lateinit var editText: EditText
    private lateinit var button: Button

    interface OnDataPass {
        fun onDataPass(data: String)
    }

    var dataPasser: OnDataPass? = null

    @SuppressLint("MissingInflatedId")
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val view = inflater.inflate(R.layout.fragment_act, container, false)

        editText = view.findViewById(R.id.editText)
        button = view.findViewById(R.id.button)

        button.setOnClickListener {
            val data = editText.text.toString()
            dataPasser?.onDataPass(data)
        }

        return view
    }

    override fun onAttach(context: Context) {
        super.onAttach(context)
        dataPasser = context as OnDataPass
    }

    override fun onDetach() {
        super.onDetach()
        dataPasser = null
    }

}