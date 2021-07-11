package com.mfahmi.screentest1.fragment

import android.app.AlertDialog
import android.os.Bundle
import android.view.View
import android.viewbinding.library.fragment.viewBinding
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.findNavController
import com.mfahmi.screentest1.R
import com.mfahmi.screentest1.databinding.FragmentHomeBinding
import com.mfahmi.screentest1.utils.isPalindromeWord

class HomeFragment : Fragment(R.layout.fragment_home) {

    private val binding: FragmentHomeBinding by viewBinding()

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        with(binding) {
            btnAddName.setOnClickListener {
                if (edtAddTitle.text!!.isNotEmpty()) {
                    showDialog(edtAddTitle.text.toString().isPalindromeWord())
                } else {
                    Toast.makeText(requireContext(), "Please Enter your name", Toast.LENGTH_SHORT)
                        .show()
                }
            }
        }
    }

    private fun showDialog(dialogMsg: String) {
        AlertDialog.Builder(requireContext())
            .setMessage(dialogMsg)
            .setPositiveButton("Next") { _, _ ->
                findNavController()
                    .navigate(
                        HomeFragmentDirections.actionHomeFragmentToChooseFragment(
                            binding.edtAddTitle.text.toString(), null, null, null
                        )
                    )
            }
            .setNegativeButton("Cancel") { _, _ -> }
            .show()
    }

}