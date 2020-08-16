package com.example.mvvmdemo.data.api

import com.example.mvvmdemo.data.model.Employee
import com.example.mvvmdemo.data.model.EmployeeResponse
import com.example.recyclerviewdemo.model.WeatherData
import retrofit2.http.GET

interface ApiService {

    @GET("api/v1/employees")
    suspend fun getEmployeeData(): EmployeeResponse

}