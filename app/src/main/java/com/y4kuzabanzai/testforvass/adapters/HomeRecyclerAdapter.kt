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
import com.bumptech.glide.load.engine.GlideException
import com.bumptech.glide.load.model.GlideUrl
import com.bumptech.glide.load.model.LazyHeaders
import com.bumptech.glide.request.RequestListener
import com.bumptech.glide.request.RequestOptions
import com.bumptech.glide.request.target.Target
import com.y4kuzabanzai.testforvass.Models.Gnome
import com.y4kuzabanzai.testforvass.R
import com.y4kuzabanzai.testforvass.databinding.GnomeElementBinding
import com.y4kuzabanzai.testforvass.fragments.HomeFragment
import com.y4kuzabanzai.testforvass.fragments.HomeFragmentDirections

class HomeRecyclerAdapter(var homeFragment: HomeFragment): ListAdapter<Gnome, GnomesViewHolder>(GnomeDiffUtil()) {

    lateinit var gnomeElementBinding: GnomeElementBinding

    companion object {

    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): GnomesViewHolder {
        gnomeElementBinding = GnomeElementBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return GnomesViewHolder(gnomeElementBinding)
    }

    override fun onBindViewHolder(holder: GnomesViewHolder, position: Int) {
        when (holder) {
            is GnomesViewHolder -> {
                holder.bindElements(getItem(position))
                holder.binding.gnomeElement.setOnClickListener {
                    val action = HomeFragmentDirections.actionHomeFragmentToGnomeDetailsFragment(
                        getItem(position)
                    )
                    homeFragment.findNavController().navigate(action)
                }
            }
        }
    }
}

class GnomesViewHolder(var binding: GnomeElementBinding): RecyclerView.ViewHolder(binding.root) {

    private val USER_AGENT = "Mozilla/5.0 (Linux; Android 11) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/88.0.4324.181 Mobile Safari/537.36"

    fun bindElements(gnome: Gnome) {

        binding.name.text = itemView.context.getString(R.string.name).plus(" " + gnome.name)
        binding.age.text = itemView.context.getString(R.string.age).plus(" " + gnome.age.toString())
        binding.gnomeElementId.text = itemView.context.getString(R.string.id).plus(" " + gnome.id.toString())
        binding.gnomeElementHairColor.text = itemView.context.getString(R.string.hair_color).plus(" " + gnome.hairColor)


        val glideUrl = GlideUrl(
            gnome.thumbnail,
            LazyHeaders.Builder().addHeader("User-Agent", USER_AGENT).build())

        val requestOptions = RequestOptions()
            .placeholder(R.drawable.ic_launcher_background)
            .error(R.drawable.ic_launcher_background)


       Glide.with(itemView.context)
            .applyDefaultRequestOptions(requestOptions)
            .load(glideUrl)
            .timeout(60000)
            .override(320, 480)
            .listener(object : RequestListener<Drawable> {
                override fun onLoadFailed(
                    e: GlideException?,
                    model: Any?,
                    target: Target<Drawable>?,
                    isFirstResource: Boolean
                ): Boolean {
                    Log.e("Glide", "onLoadFailed: $e")
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
            .into(binding.gnomeImage)
    }
}
