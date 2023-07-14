package com.example.swipemvvmkoin.ui

import android.os.Bundle
import android.text.Editable
import android.text.TextWatcher
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ProgressBar
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.swipemvvmkoin.R
import com.example.swipemvvmkoin.adapter.ProductAdapter
import com.example.swipemvvmkoin.databinding.FragmentProductListBinding
import com.example.swipemvvmkoin.util.ToastUtils
import com.example.swipemvvmkoin.viewModel.ProductListViewModel
import com.github.ybq.android.spinkit.sprite.Sprite
import com.github.ybq.android.spinkit.style.Circle
import com.github.ybq.android.spinkit.style.DoubleBounce
import com.github.ybq.android.spinkit.style.WanderingCubes
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
//        binding.recyclerView.addItemDecoration(ItemDecoration())

        productListViewModel.productList.observe(this) { list->
            if (list != null) {
                list.sortedByDescending { it.image!=null  || it.image!=" "}
                adapter.updateData(list)
            }
        }
        binding.searchEditText.addTextChangedListener(object : TextWatcher {
            override fun beforeTextChanged(
                s: CharSequence?,
                start: Int,
                count: Int,
                after: Int
            ) {
            }

            override fun onTextChanged(
                s: CharSequence?,
                start: Int,
                before: Int,
                count: Int
            ) {
            }

            override fun afterTextChanged(s: Editable?) {
                val filteredList = productListViewModel.productList.value?.filter { it.productName?.contains(s.toString(), true) ?: false }
                filteredList?.let {
                    adapter.updateData(it)
                }
            }

        })

        productListViewModel.showError.observe(this) { string ->
            string?.let {
                ToastUtils.showToastError(activity, it);
            }
        }

        productListViewModel.showLoading.observe(this, Observer {
            if (it==false) {
                binding.progressBarCyclic.visibility = View.GONE
                binding.recyclerView.visibility = View.VISIBLE
            } else {
                binding.progressBarCyclic.visibility = View.VISIBLE
                binding.recyclerView.visibility = View.GONE
            }
        })
        binding.addFab.setOnClickListener {
            findNavController().navigate(R.id.action_productListFragment_to_addProductFragment)
        }
    }

}