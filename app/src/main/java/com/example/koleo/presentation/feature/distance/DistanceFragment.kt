package com.example.koleo.presentation.feature.distance

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.lifecycle.Lifecycle
import androidx.lifecycle.lifecycleScope
import androidx.lifecycle.repeatOnLifecycle
import com.example.koleo.R
import com.example.koleo.databinding.FragmentDistanceBinding
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.launch

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
        initObservers()
        initDistance()
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }

    private fun initObservers() {
        lifecycleScope.launch {
            viewLifecycleOwner.repeatOnLifecycle(Lifecycle.State.STARTED) {
                launch {
                    viewModel.departure.collect {
                        binding.tvDepartureStation.text = it.name
                    }
                }
                launch {
                    viewModel.arrival.collect {
                        binding.tvArrivalStation.text = it.name
                    }
                }
            }
        }
    }

    private fun initDistance() {
        binding.tvDistanceValue.text = viewModel.distance.toString()
    }
}

