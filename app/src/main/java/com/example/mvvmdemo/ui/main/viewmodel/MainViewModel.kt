package com.example.mvvmdemo.ui.main.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.liveData
import com.example.mvvmdemo.data.repository.MainRepository
import com.example.mvvmdemo.utils.Resource
import kotlinx.coroutines.Dispatchers
import java.lang.Exception

class MainViewModel(private val mainRepository: MainRepository): ViewModel(){

    fun getEmployeeData() = liveData(Dispatchers.IO) {
         emit(Resource.loading(data = null))

        try{
            emit(Resource.success(data = mainRepository.getWeather()))
        }catch (exception:Exception){
            emit(Resource.error(data=null,message = exception.message?: "Error Occured"))
        }
    }
}