package com.example.mvvmdemo.ui.main.adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.mvvmdemo.R
import com.example.mvvmdemo.data.model.Employee
import kotlinx.android.synthetic.main.row_layout_for_employee.view.*


class EmployeAdapter(private val employeData:ArrayList<Employee>) : RecyclerView.Adapter<EmployeAdapter.DataViewHolder>(){

    class DataViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView){

        fun bind(employee:Employee){
            itemView.apply {
                txtEmployeeNameValue.text = employee.employee_name
                txtEmployeeAgeValue.text = employee.employee_age
                txtSalaryValue.text = employee.employee_salary
            }
        }

    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): DataViewHolder {

       return DataViewHolder(LayoutInflater.from(parent.context).inflate(R.layout.row_layout_for_employee,parent,false))
    }

    override fun getItemCount(): Int {
        return  employeData.size
    }

    override fun onBindViewHolder(holder: DataViewHolder, position: Int) {
          holder.bind(employeData[position])
    }

    fun addEmployee(employee:ArrayList<Employee>){
        this.employeData.apply {
            clear()
            addAll(employee)
        }
    }


}