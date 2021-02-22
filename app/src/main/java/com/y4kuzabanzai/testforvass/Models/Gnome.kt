package com.y4kuzabanzai.testforvass.Models

import androidx.room.PrimaryKey
import com.google.gson.annotations.SerializedName
import java.io.Serializable


data class Gnome(
    @SerializedName("age")
    val age: Int,
    @SerializedName("friends")
    val friends: List<String>,
    @SerializedName("hair_color")
    val hairColor: String,
    @SerializedName("height")
    val height: Double,
    @PrimaryKey
    @SerializedName("id")
    val id: Int,
    @SerializedName("name")
    val name: String,
    @SerializedName("professions")
    val professions: List<String>,
    @SerializedName("thumbnail")
    val thumbnail: String,
    @SerializedName("weight")
    val weight: Double
): Serializable