package com.demox.countries.presentation.mainscreen

import android.os.Bundle
import android.view.Gravity
import android.view.View
import android.widget.Toast
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.lifecycleScope
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import com.demox.countries.R
import com.demox.countries.databinding.FragmentMainBinding
import com.demox.countries.domain.mainscreen.model.Country
import com.demox.countries.plugins.ViewBindingFragment
import com.demox.countries.presentation.mainscreen.adapter.CountryAdapter
import com.demox.countries.presentation.mainscreen.vm.MainVMFactory
import com.demox.countries.presentation.mainscreen.vm.MainViewModel
import kotlinx.coroutines.flow.collectLatest

class MainFragment : ViewBindingFragment<FragmentMainBinding>(FragmentMainBinding::inflate) {

    lateinit var viewModel: MainViewModel
    private var countryAdapter: CountryAdapter? = null

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        viewModel = ViewModelProvider(this, MainVMFactory())[MainViewModel::class.java]
        initList()
        bindViewModel()

        binding.flabUpdate.setOnClickListener {
            viewModel.update()
        }
    }

    private fun bindViewModel() {
        lifecycleScope.launchWhenResumed {
            viewModel.countryList.collectLatest {
                countryAdapter?.items = it
            }
        }

        lifecycleScope.launchWhenResumed {
            viewModel.errorMessage.collectLatest {
                showToast(it)
            }
        }

        lifecycleScope.launchWhenResumed {
            viewModel.loading.collectLatest {
                if (it) {
                    binding.pbLoading.visibility = View.VISIBLE
                } else {
                    binding.pbLoading.visibility = View.GONE
                }
            }
        }
    }

    private fun initList() {
        countryAdapter = CountryAdapter { item: Country -> showDetails(item) }

        with(binding.rvCountry) {
            adapter = countryAdapter
            layoutManager = LinearLayoutManager(requireContext())
            setHasFixedSize(true)
        }
    }

    private fun showDetails(country: Country) {
        val bundle = Bundle()
        bundle.putParcelable("country", country)
        findNavController().navigate(R.id.action_mainFragment_to_detailFragment, bundle)
    }

    private fun showToast(message: String) {
        if (message.isNotEmpty()) {
            val toast = Toast.makeText(requireActivity().applicationContext, message, Toast.LENGTH_LONG)
            toast.setGravity(Gravity.TOP, 0, 0)
            toast.show()
        }
    }

    override fun onDestroyView() {
        super.onDestroyView()
        viewModel.cleanData()
    }
}
