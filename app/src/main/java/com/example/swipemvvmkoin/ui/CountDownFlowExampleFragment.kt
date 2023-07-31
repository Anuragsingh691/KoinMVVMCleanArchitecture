package com.example.swipemvvmkoin.ui

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.example.swipemvvmkoin.databinding.FragmentCountDownFlowExampleBinding
import com.example.swipemvvmkoin.viewModel.CountDownViewModel
import org.koin.androidx.viewmodel.ext.android.viewModel

class CountDownFlowExampleFragment : Fragment() {
    private val countDownViewModel by viewModel<CountDownViewModel>()
    private lateinit var binding: FragmentCountDownFlowExampleBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        binding = FragmentCountDownFlowExampleBinding.inflate(layoutInflater)
        return binding.root
    }

    override fun onResume() {
        super.onResume()
        countDownViewModel.collectFlow()
        countDownViewModel.collectStateFlow()
        binding.incrementBtn.setOnClickListener {
            countDownViewModel.incrementCounter()
        }
        initialiseCountDown()
    }

    private fun initialiseCountDown() {
        countDownViewModel.state.observe(this){
            binding.countDown.text = it.toString()
        }
//        countDownViewModel.time.observe(this){
//            binding.countDown.text = it.toString()
//        }
    }
}