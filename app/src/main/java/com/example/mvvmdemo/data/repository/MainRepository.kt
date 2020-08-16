package com.example.mvvmdemo.data.repository

import com.example.mvvmdemo.data.api.ApiHelper

class MainRepository(private val apiHelper: ApiHelper) {

    suspend fun getWeather() =  apiHelper.getEmployeeData()
}