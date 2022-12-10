package com.mmeruga.roomdatabase.ui

import android.os.Bundle
import android.text.TextUtils
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.findNavController
import androidx.navigation.fragment.navArgs
import com.mmeruga.roomdatabase.R
import com.mmeruga.roomdatabase.database.User
import com.mmeruga.roomdatabase.repository.UserViewModel
import kotlinx.android.synthetic.main.fragment_update_user.*
import kotlinx.android.synthetic.main.fragment_update_user.view.*

/**
 * A simple [Fragment] subclass.
 */
class UpdateUser : Fragment() {

    private val args by navArgs<UpdateUserArgs>()
    private lateinit var userViewModel: UserViewModel

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        val view = inflater.inflate(R.layout.fragment_update_user, container, false)
        view.userNameEt.setText(args.currentUser.name)
        view.userEmailEt.setText(args.currentUser.email)

        userViewModel = ViewModelProvider(this)[UserViewModel::class.java]
        view.updateBtn.setOnClickListener {
            updateUser()
        }
        return view
    }

    private fun updateUser() {
        val userName = userNameEt.text.toString()
        val userEmail = userEmailEt.text.toString()

        if (!TextUtils.isEmpty(userName) && !TextUtils.isEmpty(userEmail)) {
            val updatedUser = User(args.currentUser.id, userName, userEmail)
            userViewModel.updateUser(updatedUser)
            findNavController().navigate(R.id.action_updateUser_to_usersList)
        }
    }
}