package com.y4kuzabanzai.testforvass.viewmodels

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.y4kuzabanzai.testforvass.Models.BrastlewarkTown
import com.y4kuzabanzai.testforvass.repository.AppRepository
import com.y4kuzabanzai.testforvass.utils.Resource
import kotlinx.coroutines.launch
import retrofit2.Response

class HomeFragmentViewModel : ViewModel() {

    val brastlewarkTownData: MutableLiveData<Resource<BrastlewarkTown>> = MutableLiveData()

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
}