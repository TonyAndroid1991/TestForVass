package com.y4kuzabanzai.testforvass.fragments

import android.annotation.SuppressLint
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.AdapterView
import android.widget.ArrayAdapter
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.y4kuzabanzai.testforvass.GnomeEnumInfo
import com.y4kuzabanzai.testforvass.Models.Gnome
import com.y4kuzabanzai.testforvass.R
import com.y4kuzabanzai.testforvass.adapters.HomeRecyclerAdapter
import com.y4kuzabanzai.testforvass.databinding.FragmentHomeBinding
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

        return binding.root
    }

    companion object {
        private const val TAG = "HomeFragment"

        @JvmStatic
        fun newInstance(param1: String, param2: String) =
            HomeFragment().apply {
            }
    }

    private fun assembleSearchBySpinner() {
        binding.searchBySpinner.adapter = ArrayAdapter(
            requireActivity(),
            android.R.layout.simple_list_item_1,
            viewModel?.searchByEnumArray()
        )
        binding.searchBySpinner.onItemSelectedListener =
            object : AdapterView.OnItemSelectedListener {
                @SuppressLint("FragmentLiveDataObserve")
                override fun onItemSelected(
                    parent: AdapterView<*>?,
                    view: View?,
                    position: Int,
                    id: Long
                ) {
                    when (viewModel.searchByEnumArray()[position]) {

                        GnomeEnumInfo.ALL -> {
                            viewModel.brastlewarkTownPopulation.observe(this@HomeFragment,
                                { populationList ->

                                    setCustomAdapter(populationList as ArrayList<Gnome>)
                                })
                        }

                        GnomeEnumInfo.AGE -> {
                            viewModel.brastlewarkTownPopulation.observe(this@HomeFragment,
                                { populationList ->

                                    var allAges = viewModel.getAllAges(populationList as ArrayList)
                                    assembleSearchBySelectionSpinner(GnomeEnumInfo.AGE, allAges)
                                })
                        }

                        GnomeEnumInfo.FRIENDS -> {
                            viewModel.brastlewarkTownPopulation.observe(this@HomeFragment,
                                { populationList ->

                                    var allNames =
                                        viewModel.getAllNames(populationList as ArrayList)
                                    assembleSearchBySelectionSpinner(
                                        GnomeEnumInfo.FRIENDS,
                                        allNames
                                    )
                                })
                        }

                        GnomeEnumInfo.HAIR_COLOR -> {
                            viewModel.brastlewarkTownPopulation.observe(this@HomeFragment,
                                { populationList ->

                                    var allHairColors =
                                        viewModel.getAllHairs(populationList as ArrayList)
                                    assembleSearchBySelectionSpinner(
                                        GnomeEnumInfo.HAIR_COLOR,
                                        allHairColors
                                    )
                                })
                        }

                        GnomeEnumInfo.HEIGHT -> {
                            viewModel.brastlewarkTownPopulation.observe(this@HomeFragment,
                                { populationList ->
                                    var allHeights =
                                        viewModel.getAllHeigths(populationList as ArrayList)
                                    assembleSearchBySelectionSpinner(
                                        GnomeEnumInfo.HEIGHT,
                                        allHeights
                                    )
                                })
                        }

                        GnomeEnumInfo.ID -> {
                            viewModel.brastlewarkTownPopulation.observe(this@HomeFragment,
                                { populationList ->
                                    var allIDs = viewModel.getAllIDs(populationList as ArrayList)
                                    assembleSearchBySelectionSpinner(GnomeEnumInfo.ID, allIDs)

                                })
                        }

                        GnomeEnumInfo.NAME -> {
                            viewModel.brastlewarkTownPopulation.observe(
                                this@HomeFragment,
                                { populationList ->
                                    var allNames =
                                        viewModel.getAllNames(populationList as ArrayList)
                                    assembleSearchBySelectionSpinner(GnomeEnumInfo.NAME, allNames)
                                })
                        }

                        GnomeEnumInfo.PROFESSIONS -> {
                            viewModel.brastlewarkTownPopulation.observe(this@HomeFragment,
                                { populationList ->
                                    var allProfessions =
                                        viewModel.getAllProfessions(populationList as ArrayList<Gnome>)
                                    assembleSearchBySelectionSpinner(
                                        GnomeEnumInfo.PROFESSIONS,
                                        allProfessions
                                    )
                                })
                        }

                        GnomeEnumInfo.WEIGHT -> {
                            viewModel.brastlewarkTownPopulation.observe(this@HomeFragment,
                                { populationList ->
                                    var allWeights =
                                        viewModel.getAllWeights(populationList as ArrayList)
                                    assembleSearchBySelectionSpinner(
                                        GnomeEnumInfo.WEIGHT,
                                        allWeights
                                    )
                                })
                        }

                    }
                }

                override fun onNothingSelected(parent: AdapterView<*>?) {
                    //binding.spinnerText.text = "Select an option"
                }
            }
    }


    fun <T> assembleSearchBySelectionSpinner(
        enumInfo: GnomeEnumInfo,
        polymorphArrayList: ArrayList<T>
    ) {
        var selectedGnomesList: ArrayList<Gnome> = arrayListOf()
        binding.searchBySelectionSpinner.adapter =
            ArrayAdapter(requireActivity(), android.R.layout.simple_list_item_1, polymorphArrayList)
        binding.searchBySelectionSpinner.onItemSelectedListener =
            object : AdapterView.OnItemSelectedListener {

                override fun onItemSelected(
                    parent: AdapterView<*>?,
                    view: View?,
                    position: Int,
                    id: Long
                ) {
                    viewModel.brastlewarkTownPopulation.observe(
                        this@HomeFragment,
                        { populationList ->

                            when (enumInfo) {
                                GnomeEnumInfo.AGE -> {
                                   setCustomAdapter(viewModel.addGnomeToAgeList(parent?.getItemAtPosition(position), populationList))
                                }

                                GnomeEnumInfo.FRIENDS -> {

                                    selectedGnomesList.clear()
                                    var gnomesByFriends = viewModel.checkCommonFriends(
                                        parent?.getItemAtPosition(position).toString(),
                                        populationList as ArrayList<Gnome>
                                    )
                                    setCustomAdapter(gnomesByFriends)
                                }

                                GnomeEnumInfo.HAIR_COLOR -> {
                                    selectedGnomesList.clear()
                                    for (gnome in populationList) {
                                        if (gnome.hairColor.equals(
                                                parent?.getItemAtPosition(
                                                    position
                                                )
                                            )
                                        ) {
                                            selectedGnomesList.add(gnome)
                                            setCustomAdapter(selectedGnomesList)
                                        }
                                    }
                                }

                                GnomeEnumInfo.HEIGHT -> {
                                    selectedGnomesList.clear()
                                    for (gnome in populationList) {
                                        if (gnome.height.equals(parent?.getItemAtPosition(position))) {
                                            selectedGnomesList.add(gnome)
                                            setCustomAdapter(selectedGnomesList)
                                        }
                                    }
                                }

                                GnomeEnumInfo.ID -> {
                                    selectedGnomesList.clear()
                                    for (gnome in populationList) {
                                        if (gnome.id.equals(parent?.getItemAtPosition(position))) {
                                            selectedGnomesList.add(gnome)
                                            setCustomAdapter(selectedGnomesList)
                                        }
                                    }
                                }

                                GnomeEnumInfo.NAME -> {
                                    selectedGnomesList.clear()
                                    for (gnome in populationList) {
                                        if (gnome.name.equals(parent?.getItemAtPosition(position))) {
                                            selectedGnomesList.add(gnome)
                                            setCustomAdapter(selectedGnomesList)
                                        }
                                    }
                                }

                                GnomeEnumInfo.PROFESSIONS -> {
                                    selectedGnomesList.clear()
                                    var gnomesByProfessions = viewModel.checkCommonProfessions(
                                        parent?.getItemAtPosition(position).toString(),
                                        populationList as ArrayList<Gnome>
                                    )
                                    setCustomAdapter(gnomesByProfessions)
                                }

                                GnomeEnumInfo.WEIGHT -> {
                                    selectedGnomesList.clear()
                                    for (gnome in populationList) {
                                        if (gnome.weight.equals(parent?.getItemAtPosition(position))) {
                                            selectedGnomesList.add(gnome)
                                            setCustomAdapter(selectedGnomesList)
                                        }
                                    }
                                }
                            }
                        })
                }

                override fun onNothingSelected(parent: AdapterView<*>?) {
                    //binding.spinnerText.text = "Select an option"
                }
            }
    }

    private fun setCustomAdapter(selectedGnomesList: ArrayList<Gnome>) {

        binding.homeRecyclerview.apply {
            layoutManager = LinearLayoutManager(activity)
            adapter = HomeRecyclerAdapter(this@HomeFragment) as HomeRecyclerAdapter
            val homeRecyclerAdapter = adapter as HomeRecyclerAdapter
            homeRecyclerAdapter.submitList(selectedGnomesList)

        }
    }
}