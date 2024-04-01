package com.example.koleo

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.findNavController
import com.example.koleo.databinding.FragmentPlanBinding

class PlanFragment : Fragment() {

    private var _binding: FragmentPlanBinding? = null

    // This property is only valid between onCreateView and
    // onDestroyView.
    private val binding get() = _binding!!

    var isEnd = false // FIXME: read current state

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {

        _binding = FragmentPlanBinding.inflate(inflater, container, false)
        return binding.root

    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        binding.btnTest.setOnClickListener {
            if (!isEnd) binding.mlRoot.transitionToEnd()
            else binding.mlRoot.transitionToStart()
            isEnd = !isEnd
        }
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}