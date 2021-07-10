package com.mfahmi.screentest1.fragment

import android.os.Bundle
import android.view.View
import android.viewbinding.library.fragment.viewBinding
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.navigation.findNavController
import com.mfahmi.screentest1.R
import com.mfahmi.screentest1.databinding.FragmentHomeBinding

class HomeFragment : Fragment(R.layout.fragment_home) {

    private val binding: FragmentHomeBinding by viewBinding()

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        with(binding) {
            btnAddName.setOnClickListener {
                if (edtAddTitle.text!!.isNotEmpty()) {
                    view.findNavController()
                        .navigate(
                            HomeFragmentDirections.actionHomeFragmentToChooseFragment(
                                edtAddTitle.text.toString(), null, null, null
                            )
                        )
                } else {
                    Toast.makeText(requireContext(), "Please Enter your name", Toast.LENGTH_SHORT)
                        .show()
                }
            }
        }
    }

}