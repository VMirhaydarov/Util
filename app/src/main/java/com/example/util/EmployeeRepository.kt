package com.example.util

import androidx.annotation.WorkerThread
import kotlinx.coroutines.flow.Flow


class EmployeeRepository(private val employeeDao: EmployeeDao) {

    val allEmployees: Flow<List<EmployeeRoom>> = employeeDao.getAlphabetizedEmployees()

    @Suppress("ReduntantSuspendModifier")
    @WorkerThread
    suspend fun insert(employeeRoom: EmployeeRoom){
        employeeDao.insert(employeeRoom)
    }

}