package com.example.util

import android.app.Application
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.SupervisorJob

class EmployeesApplication : Application() {

    val applicationScope = CoroutineScope(SupervisorJob())

    val database by lazy { EmployeeRoomDatabase.getDatabase(this, applicationScope)}
    val repository by lazy {EmployeeRepository(database.employeeDao())}

}