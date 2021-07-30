package com.mfahmi.screentest1.fragment

import android.graphics.BitmapFactory
import android.os.Bundle
import android.view.View
import android.viewbinding.library.fragment.viewBinding
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.LinearLayoutManager
import com.mapbox.mapboxsdk.Mapbox
import com.mapbox.mapboxsdk.camera.CameraUpdateFactory
import com.mapbox.mapboxsdk.geometry.LatLng
import com.mapbox.mapboxsdk.maps.MapView
import com.mapbox.mapboxsdk.maps.MapboxMap
import com.mapbox.mapboxsdk.maps.Style
import com.mapbox.mapboxsdk.plugins.annotation.SymbolManager
import com.mapbox.mapboxsdk.plugins.annotation.SymbolOptions
import com.mfahmi.screentest1.R
import com.mfahmi.screentest1.data.dummy.EventDummyData
import com.mfahmi.screentest1.databinding.FragmentMapBinding
import com.mfahmi.screentest1.utils.EventAdapter

class MapFragment : Fragment(R.layout.fragment_map) {
    private val binding: FragmentMapBinding by viewBinding()
    private val eventAdapter by lazy { EventAdapter(EventDummyData.getEventDataDummy()) }
    private lateinit var mapView: MapView
    private lateinit var mapboxMap: MapboxMap
    private lateinit var symbolManager: SymbolManager

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        Mapbox.getInstance(requireContext(), getString(R.string.mapbox_token_key))
    }

    override fun onSaveInstanceState(outState: Bundle) {
        super.onSaveInstanceState(outState)
        mapView.onSaveInstanceState(outState)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding.btnBackMap.setOnClickListener { requireActivity().onBackPressed() }
        binding.toolbarMap.inflateMenu(R.menu.menu_event)
        mapView = binding.mapViewEvent
        mapView.onCreate(savedInstanceState)
        eventAdapter.onItemClick = { }

        with(binding.rvEventMap) {
            layoutManager =
                LinearLayoutManager(requireContext(), LinearLayoutManager.HORIZONTAL, false)
            setHasFixedSize(true)
            adapter = eventAdapter
        }

        mapView.getMapAsync { mapboxMap ->
            this.mapboxMap = mapboxMap
            mapboxMap.setStyle(Style.MAPBOX_STREETS) { style ->
                symbolManager = SymbolManager(mapView, mapboxMap, style)
                symbolManager.iconAllowOverlap = true
                style.addImage(
                    ICON_ID,
                    BitmapFactory.decodeResource(resources, R.drawable.mapbox_marker_icon_default)
                )
                showMarkLocation(-0.4926438, 117.1553412, "Event 1")
                showMarkLocation(-0.4943658, 117.1553413, "Event 2")
                showMarkLocation(-0.4918694, 117.1553411, "Event 3")
                showMarkLocation(-0.4908439, 117.1560088, "Event 4")
            }
        }

    }

    private fun showMarkLocation(latitude: Double, longitude: Double, locationTitle: String) {
        val eventLocation = LatLng(latitude, longitude)
        symbolManager.create(
            SymbolOptions()
                .withLatLng(LatLng(eventLocation.latitude, eventLocation.longitude))
                .withIconImage(ICON_ID)
                .withIconSize(1.5f)
                .withIconOffset(arrayOf(0f, -1.5f))
                .withTextField(locationTitle)
                .withTextHaloColor("rgba(255, 255, 255, 100)")
                .withTextHaloWidth(5.0f)
                .withTextAnchor("top")
                .withTextOffset(arrayOf(0f, 1.5f))
                .withDraggable(true)
        )
        mapboxMap.moveCamera(CameraUpdateFactory.newLatLngZoom(eventLocation, 15.0))
    }

    override fun onStart() {
        super.onStart()
        mapView.onStart()
    }

    override fun onResume() {
        super.onResume()
        mapView.onResume()
    }

    override fun onPause() {
        super.onPause()
        mapView.onPause()
    }

    override fun onLowMemory() {
        super.onLowMemory()
        mapView.onLowMemory()
    }

    override fun onDestroy() {
        super.onDestroy()
        mapView.onDestroy()
    }

    companion object {
        private const val ICON_ID = "ICON_ID"
    }
}