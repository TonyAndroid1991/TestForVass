package com.y4kuzabanzai.testforvass.Models


import com.google.gson.annotations.SerializedName

data class BrastlewarkTown(
    @SerializedName("Brastlewark")
    val brastlewarkPopulation: List<Gnome>
)