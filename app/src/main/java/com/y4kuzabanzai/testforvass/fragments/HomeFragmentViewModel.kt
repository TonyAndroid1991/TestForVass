package com.y4kuzabanzai.testforvass.fragments

import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.liveData
import com.y4kuzabanzai.testforvass.GnomeEnumInfo
import com.y4kuzabanzai.testforvass.Models.Gnome
import com.y4kuzabanzai.testforvass.repository.AppRepository

class HomeFragmentViewModel : ViewModel() {

    val brastlewarkTownPopulation: LiveData<List<Gnome>> = liveData {
        AppRepository().getBrastlewarkPopulation().body()?.let { emit(it.brastlewarkPopulation) }
    }

    fun searchByEnumArray(): Array<GnomeEnumInfo> {
        val enumArray = arrayOf(
            GnomeEnumInfo.NAME,
            GnomeEnumInfo.AGE,
            GnomeEnumInfo.ID,
            GnomeEnumInfo.WEIGHT,
            GnomeEnumInfo.HEIGHT,
            GnomeEnumInfo.HAIR_COLOR,
            GnomeEnumInfo.PROFESSIONS,
            GnomeEnumInfo.FRIENDS,
            GnomeEnumInfo.ALL
        )
        enumArray.sortedBy { it.name }
        return enumArray
    }

    fun groupGnomesByCommonFriends(
        friendName: String,
        totalGnomesPopulation: ArrayList<Gnome>
    ): ArrayList<Gnome> {
        var gnomesByFriends: ArrayList<Gnome> = arrayListOf()

        for (gnome in totalGnomesPopulation) {
            if (gnome.friends.contains(friendName)) {
                gnomesByFriends.add(gnome)
            }
        }
        return gnomesByFriends
    }

    fun groupGnomesByProfessions(
        profession: String,
        totalGnomesPopulation: ArrayList<Gnome>
    ): ArrayList<Gnome> {
        var gnomesByProfessions: ArrayList<Gnome> = arrayListOf()

        for (gnome in totalGnomesPopulation) {
            if (gnome.professions.contains(profession)) {
                gnomesByProfessions.add(gnome)
            }
        }
        return gnomesByProfessions
    }

    fun getAllAges(totalGnomesPopulation: ArrayList<Gnome>): ArrayList<Int> {
        var gnomesListByAge: ArrayList<Int> = arrayListOf()
        for (gnome in totalGnomesPopulation) {
            if (!gnomesListByAge.contains(gnome.age)) {
                gnomesListByAge.add(gnome.age)
                gnomesListByAge.sort()
            }
        }
        return gnomesListByAge
    }

    fun getAllProfessions(totalGnomesPopulation: ArrayList<Gnome>): ArrayList<String> {
        var allProfessions: ArrayList<String> = arrayListOf()

        for (gnome in totalGnomesPopulation) {
            for (profession in gnome.professions) {
                var professionWithoutSpace = profession.replace(" ", "")
                if (!allProfessions.contains(professionWithoutSpace)) {
                    allProfessions.add(professionWithoutSpace)
                    allProfessions.sort()
                }
            }
        }
        return allProfessions
    }

    fun getAllHairs(totalGnomesPopulation: java.util.ArrayList<Gnome>): ArrayList<String> {
        var allHairColors: ArrayList<String> = arrayListOf()
        for (gnome in totalGnomesPopulation) {
            if (!allHairColors.contains(gnome.hairColor)) {
                allHairColors.add(gnome.hairColor)
                allHairColors.sort()
            }
        }
        return allHairColors
    }

    fun getAllNames(totalGnomesPopulation: ArrayList<Gnome>): ArrayList<String> {
        var allNames: ArrayList<String> = arrayListOf()
        for (gnome in totalGnomesPopulation) {
            if (!allNames.contains(gnome.name)) {
                allNames.add(gnome.name)
                allNames.sort()
            }
        }
        return allNames
    }

    fun getAllHeigths(totalGnomesPopulation: java.util.ArrayList<Gnome>): ArrayList<Double> {
        var allHeigths: ArrayList<Double> = arrayListOf()
        for (gnome in totalGnomesPopulation) {
            if (!allHeigths.contains(gnome.height)) {
                allHeigths.add(gnome.height)
                allHeigths.sort()
            }
        }
        return allHeigths
    }

    fun getAllIDs(totalGnomesPopulation: ArrayList<Gnome>): ArrayList<Int> {
        var allIDs: ArrayList<Int> = arrayListOf()
        for (gnome in totalGnomesPopulation) {
            if (!allIDs.contains(gnome.id)) {
                allIDs.add(gnome.id)
                allIDs.sort()
            }
        }
        return allIDs
    }

    fun getAllWeights(totalGnomesPopulation: java.util.ArrayList<Gnome>): ArrayList<Double> {
        var allWeights: ArrayList<Double> = arrayListOf()
        for (gnome in totalGnomesPopulation) {
            if (!allWeights.contains(gnome.weight)) {
                allWeights.add(gnome.weight)
                allWeights.sort()
            }
        }
        return allWeights
    }

    fun groupGnomesByAge(itemAtPosition: Any?, populationList: List<Gnome>): ArrayList<Gnome> {
        var selectedGnomesByAgeList: ArrayList<Gnome> = arrayListOf()

        for (gnome in populationList) {
            if (gnome.age.equals(itemAtPosition)) {
                selectedGnomesByAgeList.add(gnome)
            }
        }
        return selectedGnomesByAgeList
   }

    fun groupGnomesByHairColor(itemAtPosition: Any?, populationList: List<Gnome>): ArrayList<Gnome> {
        var selectedGnomesByHairColorList: ArrayList<Gnome> = arrayListOf()

        for (gnome in populationList) {
            if (gnome.hairColor.equals(itemAtPosition)) {
                selectedGnomesByHairColorList.add(gnome)
            }
        }
        return selectedGnomesByHairColorList
    }

    fun groupGnomesByHeight(itemAtPosition: Any?, populationList: List<Gnome>): java.util.ArrayList<Gnome> {
        var selectedGnomesByHeightList: ArrayList<Gnome> = arrayListOf()

        for (gnome in populationList) {
            if (gnome.height.equals(itemAtPosition)) {
                selectedGnomesByHeightList.add(gnome)
            }
        }
        return selectedGnomesByHeightList
    }

    fun groupGnomesById(itemAtPosition: Any?, populationList: List<Gnome>): ArrayList<Gnome> {
        var selectedGnomesByIdList: ArrayList<Gnome> = arrayListOf()

        for (gnome in populationList) {
            if (gnome.id.equals(itemAtPosition)) {
                selectedGnomesByIdList.add(gnome)
            }
        }
        return selectedGnomesByIdList
    }

    fun groupGnomesByName(itemAtPosition: Any?, populationList: List<Gnome>): java.util.ArrayList<Gnome> {
        var selectedGnomesByName: ArrayList<Gnome> = arrayListOf()

        for (gnome in populationList) {
            if (gnome.name.equals(itemAtPosition)) {
                selectedGnomesByName.add(gnome)
            }
        }
        return selectedGnomesByName
    }

    fun groupGnomesByWeight(itemAtPosition: Any?, populationList: List<Gnome>): java.util.ArrayList<Gnome> {
        var selectedGnomesByWeight: ArrayList<Gnome> = arrayListOf()

        for (gnome in populationList) {
            if (gnome.weight.equals(itemAtPosition)) {
                selectedGnomesByWeight.add(gnome)
            }
        }
        return selectedGnomesByWeight
    }


}