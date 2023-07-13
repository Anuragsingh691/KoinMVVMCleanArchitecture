package com.example.swipemvvmkoin.ui

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.lifecycle.Observer
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.swipemvvmkoin.R
import com.example.swipemvvmkoin.adapter.ProductAdapter
import com.example.swipemvvmkoin.databinding.FragmentProductListBinding
import com.example.swipemvvmkoin.viewModel.ProductListViewModel
import org.koin.androidx.viewmodel.ext.android.viewModel

class ProductListFragment : Fragment() {
    private val productListViewModel by viewModel<ProductListViewModel>()
    private val adapter = ProductAdapter()
    lateinit var binding: FragmentProductListBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?
    ): View? {
        binding = FragmentProductListBinding.inflate(layoutInflater)
        return binding.root
    }

    override fun onResume() {
        super.onResume()
        initializeView()
    }

    private fun initializeView() {
        binding.recyclerView.layoutManager = LinearLayoutManager(activity)
        binding.recyclerView.adapter = adapter

        productListViewModel.getAllProducts()

        productListViewModel.productList.observe(this) {
            if (it != null) {
                adapter.updateData(it)
            }
        }

        productListViewModel.showError.observe(this) { string ->
            string?.let {
                Toast.makeText(activity, it, Toast.LENGTH_SHORT).show()
            }
        }

        productListViewModel.showLoading.observe(this, Observer {
            if (it) {
                binding.progressBarCyclic.visibility = View.VISIBLE
            } else {
                binding.progressBarCyclic.visibility = View.GONE
            }
        })
        binding.proceedBtn.setOnClickListener {
            findNavController().navigate(R.id.action_productListFragment_to_addProductFragment)
        }
    }

}