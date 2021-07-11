package com.mfahmi.screentest1.fragment

import android.os.Build
import android.os.Bundle
import android.view.View
import android.viewbinding.library.fragment.viewBinding
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.findNavController
import androidx.navigation.fragment.navArgs
import com.mfahmi.screentest1.R
import com.mfahmi.screentest1.databinding.FragmentChooseBinding
import com.mfahmi.screentest1.utils.getDescFromDate

class ChooseFragment : Fragment(R.layout.fragment_choose) {

    private val binding: FragmentChooseBinding by viewBinding()
    private val args: ChooseFragmentArgs by navArgs()

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val buttonTextEvent: String = args.eventName ?: getString(R.string.pilih_event)
        val buttonTextGuest: String = args.guestName ?: getString(R.string.pilih_guest)

        if (!args.guestBirthdate.isNullOrEmpty()) {
            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
                Toast.makeText(
                    requireContext(),
                    getDescFromDate(args.guestBirthdate!!),
                    Toast.LENGTH_SHORT
                ).show()
            }
        }
        with(binding) {
            tvNameChoose.text = getString(R.string.name_placeholder, args.nameInput)
            btnChooseEvent.text = buttonTextEvent
            btnChooseGuest.text = buttonTextGuest
            btnChooseEvent.setOnClickListener {
                findNavController().navigate(
                    ChooseFragmentDirections.actionChooseFragmentToEventFragment(
                        args.nameInput, args.guestName
                    )
                )
            }
            btnChooseGuest.setOnClickListener {
                findNavController().navigate(
                    ChooseFragmentDirections.actionChooseFragmentToGuestFragment(
                        args.nameInput, args.eventName
                    )
                )
            }
        }
    }

}