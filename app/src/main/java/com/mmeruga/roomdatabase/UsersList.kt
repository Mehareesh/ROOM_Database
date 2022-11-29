package com.mmeruga.roomdatabase

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.RelativeLayout
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import com.mmeruga.roomdatabase.adapter.UserDataAdapter
import com.mmeruga.roomdatabase.databinding.FragmentUsersListBinding
import com.mmeruga.roomdatabase.repository.UserViewModel

/**
 * A simple [Fragment] subclass.
 */
class UsersList : Fragment(), View.OnClickListener {

    private lateinit var binding: FragmentUsersListBinding
    private lateinit var userViewModel: UserViewModel

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        binding = FragmentUsersListBinding.inflate(layoutInflater)
        binding.floatingActionButton.setOnClickListener(this)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        userViewModel = ViewModelProvider(this).get(UserViewModel::class.java)
        val recyclerView = binding.recyclerView
        val adapter = UserDataAdapter()
        recyclerView.adapter = adapter
        recyclerView.layoutManager = LinearLayoutManager(context)

        userViewModel.getUserData().observe(viewLifecycleOwner) {
                user -> adapter.setUser(user)
        }
    }

    override fun onClick(v: View?) {
        findNavController().navigate(R.id.action_usersList_to_addUser)
    }
}