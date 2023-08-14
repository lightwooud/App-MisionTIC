package com.example.sitiosmisiontic.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.sitiosmisiontic.model.SiteModel
import com.example.sitiosmisiontic.network.RetrofitInstance
import com.example.sitiosmisiontic.network.RetrofitService
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class MainViewModel : ViewModel() {

    private var siteModelLiveData : MutableLiveData<SiteModel> = MutableLiveData()

    fun getSiteModelObserver(): LiveData<SiteModel> {
        return siteModelLiveData
    }

    fun makeApiCall(){
        viewModelScope.launch(Dispatchers.IO) {
            val retroInstance = RetrofitInstance.getRetroInstance()
            val retroService = retroInstance.create(RetrofitService::class.java)
            val response = retroService.getDataFromApi()
            siteModelLiveData.postValue(response)
        }
    }
}