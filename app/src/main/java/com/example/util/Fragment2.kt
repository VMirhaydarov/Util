package com.example.util

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import androidx.fragment.app.viewModels
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView

class Fragment2 : Fragment() {

    private val employeeViewModel: EmployeeViewModel by viewModels {
        EmployeeViewModelFactory((activity as EmployeesApplication).repository)
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_2, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val recyclerView2 = view.findViewById<RecyclerView>(R.id.rcView2)
        val adapter = EmployeeListAdapter()
        recyclerView2.adapter = adapter
        recyclerView2.layoutManager = LinearLayoutManager(activity)

        /*employeeViewModel.allEmployees.observe(getViewLifecycleOwner(), Observer{ employees ->
                employees?.let {adapter.submitList(it)}
            })*/

        //val btnOK = view.findViewById<Button>(R.id.btnOK)
        //btnOK.visibility = View.INVISIBLE
    }

}