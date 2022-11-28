package com.mmeruga.roomdatabase

import android.os.Bundle
import android.text.TextUtils
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.ViewModelProvider
import com.mmeruga.roomdatabase.database.User
import com.mmeruga.roomdatabase.databinding.FragmentAddUserBinding
import com.mmeruga.roomdatabase.repository.UserViewModel

/**
 * A simple [Fragment] subclass.
 */
class AddUser : Fragment() {

    private lateinit var binding: FragmentAddUserBinding
    private lateinit var userViewModel: UserViewModel

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        binding = FragmentAddUserBinding.inflate(layoutInflater)
        userViewModel = ViewModelProvider(this).get(UserViewModel::class.java)
        binding.button.setOnClickListener {
            insertDataToDatabase()
        }
        return binding.root
    }

    private fun insertDataToDatabase() {
        val userName = binding.editTextTextPersonName.text.toString()
        val emailId = binding.editTextTextEmailAddress.text.toString()

        if (!TextUtils.isEmpty(userName) && !TextUtils.isEmpty(emailId)) {
            val user = User(0, userName, emailId)
            userViewModel.addUser(user)
        }
    }


}