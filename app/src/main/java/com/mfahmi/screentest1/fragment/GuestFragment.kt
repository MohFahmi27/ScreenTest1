package com.mfahmi.screentest1.fragment

import android.os.Bundle
import android.view.View
import android.viewbinding.library.fragment.viewBinding
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.findNavController
import androidx.navigation.fragment.navArgs
import androidx.recyclerview.widget.GridLayoutManager
import com.mfahmi.screentest1.R
import com.mfahmi.screentest1.data.network.ApiConfig
import com.mfahmi.screentest1.data.network.Guest
import com.mfahmi.screentest1.databinding.FragmentGuestBinding
import com.mfahmi.screentest1.utils.GuestAdapter
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class GuestFragment : Fragment(R.layout.fragment_guest) {
    private val binding: FragmentGuestBinding by viewBinding()
    private val guestAdapter by lazy { GuestAdapter() }
    private val args: GuestFragmentArgs by navArgs()

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        getDataGuest()

        guestAdapter.onItemClick = { guest ->
            findNavController().navigate(
                GuestFragmentDirections.actionGuestFragmentToChooseFragment(
                    args.nameInput, args.eventName, guest.name, guest.birthdate
                )
            )
        }

        with(binding.rvGuest) {
            layoutManager = GridLayoutManager(requireContext(), 2)
            adapter = guestAdapter
        }

        with(binding.swrLyt) {
            setOnRefreshListener {
                binding.pbGuest.visibility = View.VISIBLE
                getDataGuest()
            }
        }
    }

    private fun getDataGuest() {
        ApiConfig.apiService.getGuestName().enqueue(object : Callback<List<Guest>> {
            override fun onResponse(call: Call<List<Guest>>, response: Response<List<Guest>>) {
                guestAdapter.guests = response.body() as ArrayList<Guest>
                binding.pbGuest.visibility = View.GONE
                binding.swrLyt.isRefreshing = false
            }

            override fun onFailure(call: Call<List<Guest>>, t: Throwable) {
                Toast.makeText(requireContext(), "Something Wrong", Toast.LENGTH_SHORT).show()
            }

        })
    }
}