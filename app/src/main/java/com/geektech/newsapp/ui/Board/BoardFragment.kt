package com.geektech.newsapp.ui.Board

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.activity.OnBackPressedCallback
import androidx.navigation.fragment.findNavController
import androidx.viewpager2.widget.ViewPager2
import com.geektech.newsapp.R
import com.geektech.newsapp.databinding.FragmentBoardBinding
import com.geektech.newsapp.databinding.FragmentHomeBinding
import com.geektech.newsapp.ui.notifications.Prefs
import com.tbuonomo.viewpagerdotsindicator.SpringDotsIndicator

class BoardFragment : Fragment() {
    private lateinit var binding: FragmentBoardBinding

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentBoardBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        val adapter = BoardAdapter {
            Prefs(requireContext()).saveState()
            findNavController().navigateUp()


        }
        binding.viewPager.adapter = adapter
        val dotsIndicator = binding.springDotsIndicator
        val viewPager = binding.viewPager
        dotsIndicator.setViewPager2(viewPager)

        binding.viewPager.registerOnPageChangeCallback(object : ViewPager2.OnPageChangeCallback() {
            override fun onPageScrolled(
                position: Int,
                positionOffset: Float,
                positionOffsetPixels: Int
            ) {
                super.onPageScrolled(position, positionOffset, positionOffsetPixels)
                if (position == 2) {
                    binding.btnSkip.visibility = View.INVISIBLE
                } else {
                    binding.btnSkip.visibility = View.VISIBLE
                }
            }
        })

        binding.viewPager.adapter = adapter

        binding.btnSkip.setOnClickListener {
            findNavController().navigateUp()
        }

        requireActivity().onBackPressedDispatcher.addCallback(
            viewLifecycleOwner,
            object : OnBackPressedCallback(true) {
                override fun handleOnBackPressed() {
                    requireActivity().finish()
                }
            })
    }
}