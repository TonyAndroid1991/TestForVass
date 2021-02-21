package com.y4kuzabanzai.testforvass.adapters

import android.graphics.drawable.Drawable
import android.util.Log
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.bumptech.glide.load.DataSource
import com.bumptech.glide.load.DecodeFormat
import com.bumptech.glide.load.engine.DiskCacheStrategy
import com.bumptech.glide.load.engine.GlideException
import com.bumptech.glide.request.RequestListener
import com.bumptech.glide.request.RequestOptions
import com.bumptech.glide.request.target.Target
import com.y4kuzabanzai.testforvass.Models.Gnome
import com.y4kuzabanzai.testforvass.R
import com.y4kuzabanzai.testforvass.databinding.GnomeElementBinding
import com.y4kuzabanzai.testforvass.fragments.HomeFragment
import com.y4kuzabanzai.testforvass.fragments.HomeFragmentDirections

class HomeRecyclerAdapter(var homeFragment: HomeFragment
): ListAdapter<Gnome, RecyclerView.ViewHolder>(GnomeDiffUtil()) {

    companion object {
        private const val TAG = "HomeRecyclerAdapter"
    }

    lateinit var gnomeElementBinding: GnomeElementBinding

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerView.ViewHolder {
        gnomeElementBinding = GnomeElementBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return GnomesViewHolder(gnomeElementBinding)
    }

    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) {
        when (holder) {
            is GnomesViewHolder -> {
                holder.bindElements(getItem(position))
                holder.binding.gnomeElement.setOnClickListener {
                    val action = HomeFragmentDirections.actionHomeFragmentToGnomeDetailsFragment(getItem(position))
                    homeFragment.findNavController().navigate(action)
                }
            }
        }
    }

    class GnomesViewHolder(var binding: GnomeElementBinding): RecyclerView.ViewHolder(binding.root) {

        fun bindElements(gnome: Gnome) {

            binding.name.text = gnome.name
            binding.age.text = gnome.age.toString()

           // Log.i(TAG, "bindElements: ${gnome.thumbnail}")

            val requestOptions = RequestOptions()
                .placeholder(R.drawable.ic_launcher_background)
                .error(R.drawable.ic_launcher_background)
                .diskCacheStrategy(DiskCacheStrategy.ALL)
                .format(DecodeFormat.PREFER_ARGB_8888)

            Glide.with(binding.root.context)
                .applyDefaultRequestOptions(requestOptions)
                .load(gnome.thumbnail)
                .listener(object : RequestListener<Drawable> {
                    override fun onLoadFailed(e: GlideException?, model: Any?, target: Target<Drawable>?, isFirstResource: Boolean
                    ): Boolean {
                        Log.e(TAG, "onLoadFailed: ${e}")
                        return false
                    }

                    override fun onResourceReady(resource: Drawable?, model: Any?, target: Target<Drawable>?, dataSource: DataSource?, isFirstResource: Boolean
                    ): Boolean {
                        return true
                    }
                })
                .into(binding.gnomeImage)
        }
    }
}