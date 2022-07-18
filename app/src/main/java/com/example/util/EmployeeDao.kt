package com.example.util

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import kotlinx.coroutines.flow.Flow

@Dao
interface EmployeeDao {
    @Query("SELECT * FROM employees_database")
    fun getAlphabetizedEmployees(): Flow<List<EmployeeRoom>>

    @Insert(onConflict = OnConflictStrategy.IGNORE)
    fun insert(employee: EmployeeRoom)

    @Query("DELETE FROM employees_database")
    fun deleteAll()

}