package com.mmeruga.roomdatabase

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.mmeruga.roomdatabase.databinding.FragmentAddUserBinding

/**
 * A simple [Fragment] subclass.
 */
class AddUser : Fragment() {

    private lateinit var binding: FragmentAddUserBinding

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        binding = FragmentAddUserBinding.inflate(layoutInflater)
        return binding.root
    }


}