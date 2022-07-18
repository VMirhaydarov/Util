package com.example.util

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import androidx.sqlite.db.SupportSQLiteDatabase
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.launch

@Database(entities = arrayOf(EmployeeRoom::class), version = 1, exportSchema = false)
public abstract class EmployeeRoomDatabase : RoomDatabase() {

    abstract fun employeeDao(): EmployeeDao

    private class EmployeeDataBaseCallBack(
        private val scope: CoroutineScope
    ) : RoomDatabase.Callback() {

        override fun onCreate(db: SupportSQLiteDatabase){
            super.onCreate(db)
            INSTANCE?.let { database ->
                scope.launch {
                    var employeeDao = database.employeeDao()

                    employeeDao.deleteAll()

                    var employeeRoom = EmployeeRoom(1, "Василий", "vasya@mail.com", "48-1-2-3")
                    employeeDao.insert(employeeRoom)

                    employeeRoom = EmployeeRoom(2, "Ваcилиса", "vasilisa@mail.com", "48-1-2-4")
                    employeeDao.insert(employeeRoom)

                }
            }
        }

    }

    companion object{

        @Volatile
        private var INSTANCE: EmployeeRoomDatabase? = null

        fun getDatabase(context: Context, scope: CoroutineScope): EmployeeRoomDatabase{

            return INSTANCE ?: synchronized(this){
                val instance = Room.databaseBuilder(
                    context.applicationContext,
                    EmployeeRoomDatabase::class.java,
                    "employees_database"
                ).build()
                INSTANCE = instance
                instance
            }
        }

    }


}