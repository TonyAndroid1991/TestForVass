package com.y4kuzabanzai.testforvass.fragments

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ArrayAdapter
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.navArgs
import com.y4kuzabanzai.testforvass.Models.Gnome
import com.y4kuzabanzai.testforvass.R
import com.y4kuzabanzai.testforvass.databinding.FragmentGnomeDetailsBinding

class GnomeDetailsFragment : Fragment() {

    private val args by navArgs<GnomeDetailsFragmentArgs>()
    lateinit var binding: FragmentGnomeDetailsBinding
    lateinit var currentGnome: Gnome

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = DataBindingUtil.inflate(inflater, R.layout.fragment_gnome_details, container, false)

        currentGnome = args.currentGnome

        setCurrentGnomeInfo()

        return binding.root
    }


    private fun setCurrentGnomeInfo() {
        binding.apply {
            detailsName.text = currentGnome.name
            detailsAge.text = currentGnome.age.toString()
            detailsWeight.text = currentGnome.weight.toString()
            detailsHeight.text = currentGnome.height.toString()
            detailsHairColor.text = currentGnome.hairColor
            detailsId.text = currentGnome.id.toString()

            setFriendsList()
            setProfessionsList()
        }
    }

    private fun setProfessionsList() {
        val professionsAdapter = ArrayAdapter(requireContext(), android.R.layout.simple_list_item_1, currentGnome.professions)
        binding.detailsListProfessions.adapter = professionsAdapter
    }

    private fun setFriendsList() {
        val friendsAdapter = ArrayAdapter(requireContext(), android.R.layout.simple_list_item_1, currentGnome.friends)
        binding.detailsListFriends.adapter = friendsAdapter
    }


    companion object {

        @JvmStatic
        fun newInstance(param1: String, param2: String) =
            GnomeDetailsFragment().apply {
                arguments = Bundle().apply {

                }
            }
    }
}