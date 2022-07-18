package com.example.util

import androidx.lifecycle.*
import kotlinx.coroutines.launch

class EmployeeViewModel(private val repository: EmployeeRepository): ViewModel() {

    val allEmployees: LiveData<List<EmployeeRoom>> = repository.allEmployees.asLiveData()

    fun insert(employeeRoom: EmployeeRoom) = viewModelScope.launch {
        repository.insert(employeeRoom)
    }

}

class EmployeeViewModelFactory(private val repository: EmployeeRepository) : ViewModelProvider.Factory{
    override fun <T: ViewModel> create(modelClass: Class<T>): T{
        if (modelClass.isAssignableFrom(EmployeeViewModel::class.java)){
            @Suppress("UNCHECKED_CAST")
                    return EmployeeViewModel(repository) as T
        }
        throw java.lang.IllegalArgumentException("Unknown ViewModel class")
    }
}