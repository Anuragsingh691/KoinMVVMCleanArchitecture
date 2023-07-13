package com.example.swipemvvmkoin.ui

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.navigation.fragment.findNavController
import com.example.swipemvvmkoin.R
import com.example.swipemvvmkoin.adapter.ProductAdapter
import com.example.swipemvvmkoin.databinding.FragmentProductListBinding
import com.example.swipemvvmkoin.viewModel.ProductListViewModel

class ProductListFragment : Fragment() {
//    lateinit var viewModel: ProductListViewModel
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
//        val retrofitService = SwipeApiService.getInstance()
//        val mainRepository = SwipeApiRepository(retrofitService)
        Toast.makeText(activity, "inside list fragment", Toast.LENGTH_SHORT).show()
//        viewModel =
//            ViewModelProvider(this, MyViewModelFactory(mainRepository))[BaseViewModel::class.java]
//        binding.recyclerView.layoutManager = LinearLayoutManager(activity)
//        binding.recyclerView.adapter = adapter
//
//        viewModel.movieList.observe(this) {
//            adapter.updateData(it)
//        }
//
//        viewModel.errorMessage.observe(this) {
//            Toast.makeText(activity, it, Toast.LENGTH_SHORT).show()
//        }
//
//        viewModel.loading.observe(this, Observer {
//            if (it) {
//                binding.progressBarCyclic.visibility = View.VISIBLE
//            } else {
//                binding.progressBarCyclic.visibility = View.GONE
//            }
//        })
//        viewModel.getAllUsers()
        binding.proceedBtn.setOnClickListener {
            findNavController().navigate(R.id.action_productListFragment_to_addProductFragment)
        }
    }

    companion object {
        @JvmStatic
        fun newInstance(param1: String, param2: String) = ProductListFragment().apply {
        }
    }
}