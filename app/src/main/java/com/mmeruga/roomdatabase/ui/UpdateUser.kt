package com.mmeruga.roomdatabase.ui

import android.app.AlertDialog
import android.os.Bundle
import android.text.TextUtils
import android.view.*
import android.widget.Toast
import androidx.fragment.app.Fragment
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

        // Add menu
        setHasOptionsMenu(true)
        return view
    }

    override fun onCreateOptionsMenu(menu: Menu, inflater: MenuInflater) {
        inflater.inflate(R.menu.delete_menu, menu)
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        if (item.itemId == R.id.delete_item) {
            deleteUser()
        }
        return super.onOptionsItemSelected(item)
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

    private fun deleteUser() {
        val builder = AlertDialog.Builder(requireContext())
        builder.setPositiveButton("Yes") { _, _ ->
            userViewModel.deleteUser(args.currentUser)
            Toast.makeText(requireContext(),
                "Successfully removed: ${args.currentUser.name}",
                Toast.LENGTH_SHORT).show()
        }
        builder.setNegativeButton("No") {_, _ -> }
        builder.setTitle("Delete ${args.currentUser.name} ?")
        builder.setMessage("Are you sure want to delete ${args.currentUser.name} ?")
        builder.create().show()
    }
}