package com.mmeruga.roomdatabase.ui

import android.app.AlertDialog
import android.os.Bundle
import android.view.*
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import com.mmeruga.roomdatabase.R
import com.mmeruga.roomdatabase.adapter.UserDataAdapter
import com.mmeruga.roomdatabase.database.UserDatabase
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
        setHasOptionsMenu(true)
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

    override fun onCreateOptionsMenu(menu: Menu, inflater: MenuInflater) {
        inflater.inflate(R.menu.delete_menu, menu)
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        if (item.itemId == R.id.delete_item) {
            deleteAllUsers()
        }
        return super.onOptionsItemSelected(item)
    }

    private fun deleteAllUsers() {
        val builder = AlertDialog.Builder(requireContext())
        builder.setPositiveButton("Yes") { _, _ ->
            userViewModel.deleteAllUsers();
            Toast.makeText(requireContext(),
                "Successfully deleted all users from database",
                Toast.LENGTH_SHORT).show()
        }
        builder.setNegativeButton("No") { _, _ ->}
        builder.setTitle("Delete all users?")
        builder.setMessage("Are you sure want to delete all users?")
    }
}