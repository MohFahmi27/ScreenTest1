package com.mfahmi.screentest1.fragment

import android.os.Bundle
import android.view.View
import android.viewbinding.library.fragment.viewBinding
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.findNavController
import androidx.navigation.fragment.navArgs
import androidx.recyclerview.widget.LinearLayoutManager
import com.mfahmi.screentest1.R
import com.mfahmi.screentest1.data.dummy.EventDummyData
import com.mfahmi.screentest1.databinding.FragmentEventBinding
import com.mfahmi.screentest1.utils.EventAdapter

class EventFragment : Fragment(R.layout.fragment_event) {
    private val binding: FragmentEventBinding by viewBinding()
    private val eventAdapter by lazy { EventAdapter(EventDummyData.getEventDataDummy()) }
    private val args: EventFragmentArgs by navArgs()

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding.btnBackEvent.setOnClickListener { requireActivity().onBackPressed() }
        with(binding.toolbarEvent) {
            inflateMenu(R.menu.menut_event)
            setOnMenuItemClickListener {
                when (it.itemId) {
                    R.id.action_new_media -> {
                        Toast.makeText(requireContext(), "test", Toast.LENGTH_SHORT).show()
                        true
                    }
                    else -> {
                        super.onOptionsItemSelected(it)
                    }
                }
            }
        }

        eventAdapter.onItemClick = { event ->
            findNavController().navigate(
                EventFragmentDirections.actionEventFragmentToChooseFragment(
                    args.nameInput, event.name, args.guestName, null
                )
            )
        }

        with(binding.rvEvent) {
            layoutManager = LinearLayoutManager(requireContext())
            setHasFixedSize(true)
            adapter = eventAdapter
        }
    }

}