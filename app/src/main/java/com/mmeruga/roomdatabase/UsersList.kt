package com.mmeruga.roomdatabase

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.navigation.fragment.findNavController
import com.mmeruga.roomdatabase.databinding.FragmentUsersListBinding

/**
 * A simple [Fragment] subclass.
 */
class UsersList : Fragment(), View.OnClickListener {

    private lateinit var binding: FragmentUsersListBinding

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentUsersListBinding.inflate(layoutInflater)
        binding.floatingActionButton.setOnClickListener(this)
        // Inflate the layout for this fragment
        return binding.root
    }

    override fun onClick(v: View?) {
        findNavController().navigate(R.id.action_usersList_to_addUser)
    }
}