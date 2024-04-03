package com.example.koleo.presentation.feature.distance

import android.content.Intent
import android.net.Uri
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import com.example.koleo.R
import com.example.koleo.databinding.FragmentDistanceBinding
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class DistanceFragment : Fragment(R.layout.fragment_distance) {
    private val viewModel by viewModels<DistanceViewModel>()
    private var _binding: FragmentDistanceBinding? = null
    private val binding get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?,
    ): View {
        _binding = FragmentDistanceBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        initViews()
        initListeners()
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }

    private fun initListeners() {
        binding.btnShowOnMap.setOnClickListener {
            directionBetweenTwoMap(
                viewModel.departureLatitude,
                viewModel.departureLongitude,
                viewModel.arrivalLatitude,
                viewModel.arrivalLongitude,
            )
        }
    }

    private fun initViews() {
        binding.tvDepartureStation.text = viewModel.departureName
        binding.tvArrivalStation.text = viewModel.arrivalName
        binding.tvDistanceValue.text = viewModel.distance.toString()
    }

    private fun directionBetweenTwoMap(
        sourceLatitude: Double,
        sourceLongitude: Double,
        destinationLatitude: Double,
        destinationLongitude: Double,
    ) {
        val mapUri = Uri.parse("https://maps.google.com/maps?saddr=$sourceLatitude," +
                "$sourceLongitude&daddr=$destinationLatitude,$destinationLongitude")
        val intent = Intent(Intent.ACTION_VIEW, mapUri)
        startActivity(intent)
    }
}

