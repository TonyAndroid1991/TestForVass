package com.y4kuzabanzai.testforvass.viewmodels

import androidx.lifecycle.*
import com.y4kuzabanzai.testforvass.GnomeEnumInfo
import com.y4kuzabanzai.testforvass.Models.BrastlewarkTown
import com.y4kuzabanzai.testforvass.Models.Gnome
import com.y4kuzabanzai.testforvass.repository.AppRepository
import com.y4kuzabanzai.testforvass.utils.Resource
import kotlinx.coroutines.launch
import retrofit2.Response

class HomeFragmentViewModel : ViewModel() {

    val brastlewarkTownData: MutableLiveData<Resource<BrastlewarkTown>> = MutableLiveData()

    val brastlewarkTownPopulation: LiveData<List<Gnome>> = liveData {
        AppRepository().getBrastlewarkPopulation().body()?.let { emit(it.brastlewarkPopulation) }
    }


    init {
        getBrastlewarkData()
    }

    fun getBrastlewarkData() = viewModelScope.launch {
        brastlewarkTownData.postValue(Resource.Loading())
        val response = AppRepository().getBrastlewarkPopulation()
        brastlewarkTownData.postValue(handleResponse(response))
    }

    private fun handleResponse(response: Response<BrastlewarkTown>): Resource<BrastlewarkTown> {
        if (response.isSuccessful) {
            response.body()?.let { resultResponse ->
                return Resource.Success(resultResponse)
            }
        }
        return Resource.Error(response.message())
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

    fun checkCommonFriends(friendName: String, totalGnomesPopulation: ArrayList<Gnome>): ArrayList<Gnome> {

        var gnomesByFriends: ArrayList<Gnome> = arrayListOf()

        for (gnome in totalGnomesPopulation) {
            if (gnome.friends.contains(friendName)) {
                gnomesByFriends.add(gnome)
            }
        }
        return gnomesByFriends
    }

    fun checkCommonProfessions(profession: String, totalGnomesPopulation: ArrayList<Gnome>): ArrayList<Gnome> {
        var gnomesByProfessions: ArrayList<Gnome> = arrayListOf()

        for (gnome in totalGnomesPopulation) {
            if (gnome.professions.contains(profession)) {
                gnomesByProfessions.add(gnome)
            }
        }
        return gnomesByProfessions
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
}