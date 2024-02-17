package com.actual.coinmonitoring.view.intro

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.navigation.Navigation
import com.actual.coinmonitoring.R
import com.actual.coinmonitoring.databinding.FragmentIntro1Binding


class IntroFragment1 : Fragment() {

    private var _binding : FragmentIntro1Binding? = null
    private val binding get() = _binding!!

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        _binding = FragmentIntro1Binding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        binding.nextBtn.setOnClickListener{
            Navigation.findNavController(view).navigate(R.id.action_introFragment1_to_introFragment2)
        }

    }

    override fun onDestroy() {
        super.onDestroy()
        _binding = null
    }

}