package com.example.mvvmdemo.ui.main.view

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.View
import android.widget.Adapter
import android.widget.Toast
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.ViewModelProviders
import androidx.recyclerview.widget.DividerItemDecoration
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.mvvmdemo.R
import com.example.mvvmdemo.data.api.ApiHelper
import com.example.mvvmdemo.data.api.RetrofitBuilder
import com.example.mvvmdemo.data.model.Employee
import com.example.mvvmdemo.data.model.EmployeeResponse
import com.example.mvvmdemo.ui.base.ViewModelFactory
import com.example.mvvmdemo.ui.main.adapter.EmployeAdapter
import com.example.mvvmdemo.ui.main.viewmodel.MainViewModel
import com.example.mvvmdemo.utils.Status
import kotlinx.android.synthetic.main.activity_main.*
import java.io.LineNumberInputStream

class MainActivity : AppCompatActivity() {

    private lateinit var viewModel: MainViewModel
    private lateinit var adapter: EmployeAdapter
    private var employeeList: ArrayList<Employee>?=null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        setUpViewModel()
        setUpUI()
        setUpObservers()
    }

    private fun setUpObservers() {

        viewModel.getEmployeeData().observe(this, Observer {
            it?.let {resource ->
                when(resource.status){
                    Status.SUCCESS -> {
                        employeeRecyclerView.visibility = View.VISIBLE
                        progressBar.visibility = View.GONE

                        var employeList  = resource.data?.data
                        Log.e("resonse","Data"+employeList)
                        employeList.let { list -> list?.let { it1 -> retriveList(it1) } }


                    }

                    Status.ERROR -> {
                        employeeRecyclerView.visibility = View.VISIBLE
                        progressBar.visibility = View.GONE
                        Toast.makeText(this, it.message, Toast.LENGTH_LONG).show()
                    }
                    Status.LOADING -> {
                        employeeRecyclerView.visibility = View.GONE
                        progressBar.visibility = View.VISIBLE
                    }
                }
            }
        })


    }

    private fun retriveList(employee: List<Employee>) {
       //employeeList = employee.data
       adapter.apply {
           addEmployee(employee as ArrayList<Employee>)
           notifyDataSetChanged()
       }

    }


    private fun setUpUI() {

        employeeRecyclerView.layoutManager = LinearLayoutManager(this)
        adapter = EmployeAdapter(arrayListOf())
        employeeRecyclerView.addItemDecoration(
            DividerItemDecoration(employeeRecyclerView.context,(employeeRecyclerView.layoutManager as LinearLayoutManager).orientation)
        )

        employeeRecyclerView.adapter = adapter

    }

    private fun setUpViewModel() {
        viewModel = ViewModelProviders.of(
            this,ViewModelFactory(ApiHelper(RetrofitBuilder.apiAService))
        ).get(MainViewModel::class.java)
    }
}