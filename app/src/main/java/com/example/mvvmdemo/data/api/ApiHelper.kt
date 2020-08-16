package com.example.mvvmdemo.data.api

class ApiHelper(private val apiService: ApiService) {

    suspend fun getEmployeeData() = apiService.getEmployeeData()

}