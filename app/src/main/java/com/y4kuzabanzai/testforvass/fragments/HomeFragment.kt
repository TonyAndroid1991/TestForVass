package com.y4kuzabanzai.testforvass.fragments

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.AdapterView
import android.widget.ArrayAdapter
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import com.y4kuzabanzai.testforvass.GnomeEnumInfo
import com.y4kuzabanzai.testforvass.R
import com.y4kuzabanzai.testforvass.adapters.HomeRecyclerAdapter
import com.y4kuzabanzai.testforvass.databinding.FragmentHomeBinding
import com.y4kuzabanzai.testforvass.utils.Resource
import com.y4kuzabanzai.testforvass.viewmodels.HomeFragmentViewModel


class HomeFragment : Fragment() {


    lateinit var binding: FragmentHomeBinding
    lateinit var viewModel: HomeFragmentViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = DataBindingUtil.inflate(inflater, R.layout.fragment_home, container, false)
        viewModel = ViewModelProvider(this).get(HomeFragmentViewModel::class.java)
        binding.lifecycleOwner = this

        assembleSearchBySpinner()
        setRecycler()

        return binding.root
    }

    companion object {
        private const val TAG = "HomeFragment"

        @JvmStatic
        fun newInstance(param1: String, param2: String) =
            HomeFragment().apply {
            }
    }

    fun setRecycler() {
        binding.homeRecyclerview.apply {
            layoutManager = LinearLayoutManager(activity)

            viewModel?.brastlewarkTownData?.observe(
                viewLifecycleOwner,
                Observer { response ->
                    when (response) {
                        is Resource.Success -> {
                            response.data?.let { brastlewarkTownResponse ->

                                adapter = HomeRecyclerAdapter(
                                    brastlewarkTownResponse.brastlewarkPopulation,
                                    this@HomeFragment
                                )
                            }
                        }
                    }
                })
        }
    }


    fun assembleSearchBySpinner() {
        var mutableList: MutableList<Any>

        binding.searchBySpinner.adapter = ArrayAdapter(requireActivity(), android.R.layout.simple_list_item_1, viewModel?.searchByEnumArray())
        binding.searchBySpinner.onItemSelectedListener = object : AdapterView.OnItemSelectedListener {
                override fun onItemSelected(
                    parent: AdapterView<*>?,
                    view: View?,
                    position: Int,
                    id: Long
                ) {

                    when (viewModel.searchByEnumArray()[position]) {
                        GnomeEnumInfo.AGE -> {

                        }
                    }
                }

                override fun onNothingSelected(parent: AdapterView<*>?) {
                    //binding.spinnerText.text = "Select an option"
                }
            }
    }

    fun assembleSearchBySelectionSpinner() {
//        binding.searchBySelectionSpinner.adapter =
//            ArrayAdapter(requireActivity(), android.R.layout.simple_list_item_1, searchBy)
        binding.searchBySelectionSpinner.onItemSelectedListener =
            object : AdapterView.OnItemSelectedListener {

                override fun onItemSelected(
                    parent: AdapterView<*>?,
                    view: View?,
                    position: Int,
                    id: Long
                ) {
                    // binding.spinnerText.text = searchBy[position]
                }

                override fun onNothingSelected(parent: AdapterView<*>?) {
                    //binding.spinnerText.text = "Select an option"
                }
            }
    }
}