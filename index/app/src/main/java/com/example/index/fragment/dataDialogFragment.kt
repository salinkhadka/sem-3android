package com.example.index.fragment

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.DialogFragment
import com.example.index.R
import com.example.index.databinding.ActivityBaseButtonBinding
import com.example.index.databinding.FragmentDataDialogBinding

class dataDialogFragment : DialogFragment() {
    lateinit var fragBinding: FragmentDataDialogBinding




    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {


        // Inflate the layout for this fragment
        fragBinding=FragmentDataDialogBinding.inflate(inflater,container,false)
        fragBinding.button5.setOnClickListener {
            dialog!!.dismiss()
        }
        fragBinding.button6.setOnClickListener {
            var name:String=fragBinding.namePlainText.text.toString()
            var age:Int=fragBinding.agePlainText.text.toString().toInt()
        }
        fun SetData(Username:String,age:Int){
            Username=fragBinding.namePlainText.text.toString()
        }





        return fragBinding.root

    }

}