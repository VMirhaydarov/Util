package com.example.util

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView

class EmployeeListAdapter : ListAdapter<EmployeeRoom, EmployeeListAdapter.EmployeeViewHolder>(EmployeesComparator()) {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): EmployeeViewHolder{
        return EmployeeViewHolder.create(parent)
    }

    override fun onBindViewHolder(holder: EmployeeViewHolder, position: Int){
        val current = getItem(position)
        holder.bind(current.name) // !!!!
    }


    class EmployeesComparator : DiffUtil.ItemCallback<EmployeeRoom>(){
        override fun areItemsTheSame(oldItem: EmployeeRoom, newItem: EmployeeRoom): Boolean {
            return oldItem === newItem
        }

        override fun areContentsTheSame(oldItem: EmployeeRoom, newItem: EmployeeRoom): Boolean {
            return oldItem.name == newItem.name
        }

    }

    class EmployeeViewHolder(itemView: View): RecyclerView.ViewHolder(itemView){
        private val employeeItemView: TextView = itemView.findViewById<TextView>(R.id.tvName) // !!!

        fun bind(text: String?){
            employeeItemView.text = text
        }

        companion object{
            fun create(parent: ViewGroup): EmployeeViewHolder{
                val view: View = LayoutInflater.from(parent.context).inflate(R.layout.employee_item, parent, false) //
                return EmployeeViewHolder(view)
            }
        }
    }

}