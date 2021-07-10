package com.mfahmi.screentest1.fragment

import android.os.Build
import android.os.Bundle
import android.view.View
import android.viewbinding.library.fragment.viewBinding
import android.widget.Toast
import androidx.annotation.RequiresApi
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.findNavController
import androidx.navigation.fragment.navArgs
import com.mfahmi.screentest1.R
import com.mfahmi.screentest1.databinding.FragmentChooseBinding
import java.time.LocalDate
import java.time.format.DateTimeFormatter

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
            tvNameChoose.text = args.nameInput
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

    @RequiresApi(Build.VERSION_CODES.O)
    private fun getDescFromDate(dateString: String): String {
        val date = LocalDate.parse(dateString, DateTimeFormatter.ISO_DATE).dayOfMonth
        return when {
            (date % 3 == 0) && (date % 2 == 0) -> "iOS"
            date % 2 == 0 -> "Blackberry"
            date % 3 == 0 -> "Android"
            else -> "Phone"
        }
    }

}