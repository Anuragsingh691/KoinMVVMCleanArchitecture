package com.example.swipemvvmkoin.ui

import android.net.Uri
import android.os.Bundle
import android.text.TextUtils
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import com.example.swipemvvmkoin.R
import com.example.swipemvvmkoin.databinding.FragmentAddProductBinding
import com.example.swipemvvmkoin.model.ProductRequest
import com.example.swipemvvmkoin.viewModel.AddProductViewModel
import org.koin.androidx.viewmodel.ext.android.viewModel


class AddProductFragment : Fragment() {

    private val addProductViewModel by viewModel<AddProductViewModel>()
    lateinit var binding: FragmentAddProductBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentAddProductBinding.inflate(layoutInflater)
        return binding.root
    }

    override fun onResume() {
        super.onResume()
        initViews()
    }

    private fun initViews() {
        addProductViewModel.showLoading.observe(this, Observer {
            if (it == false) {
                binding.progressBarCyclic.visibility = View.GONE
            } else {
                binding.progressBarCyclic.visibility = View.VISIBLE
            }
        })

        addProductViewModel.showError.observe(viewLifecycleOwner) { string ->
            string?.let {
                Toast.makeText(activity, it, Toast.LENGTH_SHORT).show()
            }
        }

        addProductViewModel.showSuccessToast.observe(viewLifecycleOwner) {
            if(it==true){
                Toast.makeText(activity, "success", Toast.LENGTH_SHORT).show()
            }
        }
        binding.submitBtn.setOnClickListener {
            if (TextUtils.isEmpty(binding.productNameTextInputEditText.text.toString())) {
                binding.productNameTextInputLayout.error = getString(R.string.enter_name)
            } else if (TextUtils.isEmpty(binding.productTypeTextInputEditText.text.toString())) {
                binding.productTypeTextInputLayout.error = getString(R.string.enter_type)
            } else if (TextUtils.isEmpty(binding.productPriceTextInputEditText.text.toString())) {
                binding.productPriceTextInputLayout.error = getString(R.string.enter_price)
            } else if (TextUtils.isEmpty(binding.productTaxTextInputEditText.text.toString())) {
                binding.productTaxTextInputLayout.error = getString(R.string.enter_tax)
            } else {
                val name = binding.productNameTextInputEditText.text.toString()
                val type = binding.productTypeTextInputEditText.text.toString()
                val price = binding.productPriceTextInputEditText.text.toString()
                val tax = binding.productTaxTextInputEditText.text.toString()
                val path = Uri.parse("android.resource://com.example.swipemvvmkoin.ui" + R.drawable.profile)
                addProductViewModel.addProducts(ProductRequest(productName = name, product_type = type, price = price.toDouble(), tax = tax.toDouble(), image = path.toString()))
            }
        }
    }
}