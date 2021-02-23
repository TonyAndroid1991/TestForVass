package com.y4kuzabanzai.testforvass.fragments

import android.graphics.drawable.Drawable
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ArrayAdapter
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.navArgs
import com.bumptech.glide.load.DataSource
import com.bumptech.glide.load.engine.GlideException
import com.bumptech.glide.request.RequestListener
import com.bumptech.glide.request.RequestOptions
import com.bumptech.glide.request.target.Target
import com.y4kuzabanzai.testforvass.Models.Gnome
import com.y4kuzabanzai.testforvass.R
import com.y4kuzabanzai.testforvass.databinding.FragmentGnomeDetailsBinding
import com.y4kuzabanzai.testforvass.viewmodels.GlideApp

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
            detailsAge.text = getString(R.string.age).plus(" " + currentGnome.age.toString())
            detailsWeight.text = getString(R.string.weight).plus(" " + currentGnome.weight.toString())
            detailsHeight.text = getString(R.string.height).plus(" " + currentGnome.height.toString())
            detailsHairColor.text = getString(R.string.hair_color).plus(" " + currentGnome.hairColor)
            detailsId.text = getString(R.string.id).plus(" " + currentGnome.id.toString())


            val requestOptions = RequestOptions()
                .placeholder(R.drawable.ic_launcher_background)
                .error(R.drawable.ic_launcher_background)


            GlideApp.with(requireActivity())
                .applyDefaultRequestOptions(requestOptions)
                .load(currentGnome.thumbnail)
                .timeout(60000)
                .override(480, 800)
                .listener(object : RequestListener<Drawable> {
                    override fun onLoadFailed(
                        e: GlideException?,
                        model: Any?,
                        target: Target<Drawable>?,
                        isFirstResource: Boolean
                    ): Boolean {
                        Log.e("Glide", "onLoadFailed: $e  =========")
                        return true
                    }

                    override fun onResourceReady(
                        resource: Drawable?,
                        model: Any?,
                        target: Target<Drawable>?,
                        dataSource: DataSource?,
                        isFirstResource: Boolean
                    ): Boolean {
                        return false
                    }

                })
                .into(binding.currentGnomeImage)

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