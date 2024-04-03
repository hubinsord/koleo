package com.example.koleo.presentation.feature.plan

import android.annotation.SuppressLint
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.lifecycle.Lifecycle
import androidx.lifecycle.lifecycleScope
import androidx.lifecycle.repeatOnLifecycle
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.koleo.R
import com.example.koleo.data.entities.Station
import com.example.koleo.databinding.FragmentPlanBinding
import com.example.koleo.presentation.extensions.afterTextChanged
import com.example.koleo.presentation.extensions.hideKeyboard
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.launch

@AndroidEntryPoint
class PlanFragment : Fragment(R.layout.fragment_plan) {
    private val viewModel by viewModels<PlanViewModel>()
    private var _binding: FragmentPlanBinding? = null
    private val binding get() = _binding!!
    private lateinit var adapter: PlanAdapter

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?,
    ): View {
        _binding = FragmentPlanBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        initListeners()
        initObservers()
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }

    private fun initListeners() {
        binding.etDeparture.afterTextChanged {
            viewModel.departureInputChanged(it)
        }
        binding.etArrival.afterTextChanged {
            viewModel.arrivalInputChanged(it)
        }
    }

    private fun initObservers() = with(binding) {
        lifecycleScope.launch {
            viewLifecycleOwner.repeatOnLifecycle(Lifecycle.State.STARTED) {
                launch {
                    viewModel.state.collect {
                        renderItems(it.items)
                    }
                }
                launch {
                    viewModel.departure.collect {
                        etDeparture.setText("")
                    }
                }
                launch {
                    viewModel.arrival.collect {
                        etArrival.setText("")
                    }
                }
                launch {
                    viewModel.event.collect {
                        when (it) {
                            is PlanEvent.ShowDeparture -> {
                                root.transitionToStart()
                                etDeparture.requestFocus()
                            }

                            is PlanEvent.ShowArrival -> {
                                root.transitionToEnd()
                                etArrival.requestFocus()
                            }

                            is PlanEvent.ShowDistanceScreen -> {
                                etArrival.hideKeyboard()
                                root.post {
                                    findNavController().navigate(
                                        PlanFragmentDirections
                                            .actionPlanFragmentToDistanceFragment(
                                                it.departureStation,
                                                it.arrivalStation,
                                            )
                                    )
                                }
                            }

                            is PlanEvent.ShowError ->
                                Toast.makeText(
                                    requireContext(),
                                    getString(R.string.fetching_error),
                                    Toast.LENGTH_LONG
                                )
                                    .show()
                        }
                    }
                }
            }
        }
    }

    @SuppressLint("NotifyDataSetChanged")
    private fun renderItems(items: List<Station>) {
        if (!::adapter.isInitialized || binding.rvItems.adapter == null) {
            adapter = PlanAdapter(items) {
                viewModel.stationSelected(it)
            }
            binding.rvItems.adapter = adapter
            binding.rvItems.layoutManager =
                LinearLayoutManager(requireContext(), RecyclerView.VERTICAL, false)
        } else {
            adapter.items = items
            adapter.notifyDataSetChanged()
        }
    }
}