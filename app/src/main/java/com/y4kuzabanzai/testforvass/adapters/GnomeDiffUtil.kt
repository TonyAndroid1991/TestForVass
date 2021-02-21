package com.y4kuzabanzai.testforvass.adapters

import androidx.recyclerview.widget.DiffUtil
import com.y4kuzabanzai.testforvass.Models.Gnome

class GnomeDiffUtil: DiffUtil.ItemCallback<Gnome>() {

    override fun areItemsTheSame(oldItem: Gnome, newItem: Gnome): Boolean {
        return oldItem.id == newItem.id
    }

    override fun areContentsTheSame(oldItem: Gnome, newItem: Gnome): Boolean {
        return oldItem == newItem
    }
}