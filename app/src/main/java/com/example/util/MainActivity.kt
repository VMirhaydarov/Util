package com.example.util

import android.annotation.SuppressLint
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.Button
import android.widget.EditText
import android.widget.Toast
import androidx.activity.viewModels
import androidx.viewpager2.widget.ViewPager2
import com.example.util.databinding.ActivityMainBinding
import com.google.android.material.snackbar.Snackbar
import com.google.android.material.tabs.TabLayout
import com.google.android.material.tabs.TabLayoutMediator
import io.reactivex.Observer
import java.util.regex.Pattern

class MainActivity : AppCompatActivity() {

    var tabTitle = arrayOf("Сотрудники", "Удаление", "Статистика")
    var isFabEnabled = false

    private val employeeViewModel: EmployeeViewModel by viewModels {
        EmployeeViewModelFactory((application as EmployeesApplication).repository)
    }
    var num : Int = 2


    @SuppressLint("MissingInflatedId")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        //employeeViewModel.allEmployees.observe(this, Observer{ employees ->
        //    employees?.let {adapter.submitList(it)}
        //})

        var pager = findViewById<ViewPager2>(R.id.viewPager2)
        var tl = findViewById<TabLayout>(R.id.tableLayout)
        pager.adapter = PagerAdapter(supportFragmentManager,lifecycle)

        TabLayoutMediator(tl,pager){
                tab, position ->
            tab.text = tabTitle[position]
        }.attach()

        //var fr4 = Fragment4()
        //var fr1 = Fragment1()
        var btnOk = findViewById<Button>(R.id.btnOK)
        var btnCancel = findViewById<Button>(R.id.btnCancel)
        var etName = findViewById<EditText>(R.id.etName)
        var etEmail = findViewById<EditText>(R.id.etEmail)
        var etPhone = findViewById<EditText>(R.id.etPhone)
        val fab: View = findViewById(R.id.fab)
        fab.setOnClickListener(View.OnClickListener { view ->
            //Snackbar.make(view, "Add", Snackbar.LENGTH_LONG)
            //    .setAction("Action", null)
            //    .show()
            if(isFabEnabled == false) {
                isFabEnabled=true
                btnOk.visibility = View.VISIBLE
                btnCancel.visibility = View.VISIBLE
                etName.visibility = View.VISIBLE
                etEmail.visibility = View.VISIBLE
                etPhone.visibility = View.VISIBLE
                /*supportFragmentManager.beginTransaction()
                    .add(R.id.bottom_view, fr4)
                    //.remove()
                    //.addToBackStack(null)
                    .commit()*/
                }
            }
        )

        btnOk.setOnClickListener {
            //supportFragmentManager.beginTransaction().remove(fr4).commit()

            val name: String = etName.text.toString()
            val email: String = etEmail.text.toString()
            val phone: String = etPhone.text.toString()
            if ((name.isNotBlank())&&(email.isNotBlank())&&phone.isNotBlank()&&(isEmailValid(email)))
            {
                num++
                val employeeRoom = EmployeeRoom(num,name,email,phone)
                //employeeViewModel.insert(employeeRoom)
            }
            else {
                Toast.makeText(this,"Введите все данные правильно!",Toast.LENGTH_LONG).show()
            }
            btnOk.visibility = View.INVISIBLE
            btnCancel.visibility = View.INVISIBLE
            etName.visibility = View.INVISIBLE
            etEmail.visibility = View.INVISIBLE
            etPhone.visibility = View.INVISIBLE
            isFabEnabled=false
        }

        btnCancel.setOnClickListener {
            //supportFragmentManager.beginTransaction().remove(fr4).commit()
            btnOk.visibility = View.INVISIBLE
            btnCancel.visibility = View.INVISIBLE
            etName.visibility = View.INVISIBLE
            etEmail.visibility = View.INVISIBLE
            etPhone.visibility = View.INVISIBLE
            isFabEnabled=false
        }

    }

    fun isEmailValid(email: String): Boolean {
        return Pattern.compile(
            "^(([\\w-]+\\.)+[\\w-]+|([a-zA-Z]|[\\w-]{2,}))@"
                    + "((([0-1]?[0-9]{1,2}|25[0-5]|2[0-4][0-9])\\.([0-1]?"
                    + "[0-9]{1,2}|25[0-5]|2[0-4][0-9])\\."
                    + "([0-1]?[0-9]{1,2}|25[0-5]|2[0-4][0-9])\\.([0-1]?"
                    + "[0-9]{1,2}|25[0-5]|2[0-4][0-9]))|"
                    + "([a-zA-Z]+[\\w-]+\\.)+[a-zA-Z]{2,4})$"
        ).matcher(email).matches()
    }

}