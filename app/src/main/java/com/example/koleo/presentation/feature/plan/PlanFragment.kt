package com.example.koleo.presentation.feature.plan

import android.annotation.SuppressLint
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.lifecycle.Lifecycle
import androidx.lifecycle.lifecycleScope
import androidx.lifecycle.repeatOnLifecycle
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.koleo.data.entities.Station
import com.example.koleo.databinding.FragmentPlanBinding
import com.example.koleo.presentation.extensions.afterTextChanged
import kotlinx.coroutines.launch
import timber.log.Timber

class PlanFragment : Fragment() {
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
                        tvDeparture.text = it.name
                    }
                }
                launch {
                    viewModel.arrival.collect {
                        etArrival.setText("")
                        tvArrival.text = it.name
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

                            is PlanEvent.ShowDistanceScreen ->{
                                Timber.tag("TEST03").d(it.departureStation.toString(), it.arrivalStation.toString())
                                val action = PlanFragmentDirections
                                    .actionPlanFragmentToDistanceFragment(
                                        it.departureStation,
                                        it.arrivalStation
                                    )
                                findNavController().navigate(
//                                    R.id.action_PlanFragment_to_DistanceFragment
                                    action
                                )
                            }
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