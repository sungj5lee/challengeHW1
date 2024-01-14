package com.example.challengehw1

import android.os.Bundle
import android.widget.ArrayAdapter
import android.widget.Spinner
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import androidx.databinding.DataBindingUtil
import com.example.challengehw1.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {
    private lateinit var binding: ActivityMainBinding
    private val viewModel: LoginViewModel by viewModels()
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = DataBindingUtil.setContentView(this, R.layout.activity_main)
        binding.viewModel = viewModel
        binding.lifecycleOwner = this

        val loname=binding.loName
        val loemail=binding.loEmail
        val lopwd=binding.loPwd
        val lopwdcheck=binding.loPwdcheck
        val name=binding.etName
        val email=binding.etEmail
        val pwd=binding.etPwd
        val pwdcheck=binding.etPwdcheck
        val btn=binding.button
        val spinner: Spinner = binding.spinner
        val items= resources.getStringArray(R.array.email_arr)
        val spinnerAdapter= ArrayAdapter.createFromResource(
            this,
            R.array.email_arr,
            android.R.layout.simple_spinner_item
        ).also { adapter ->
            adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item)
            spinner.adapter = adapter
        }

        loname.error=getString(R.string.name_err)
        loemail.error=getString(R.string.email_err)
        lopwd.error=getString(R.string.pwd_err)
        lopwdcheck.error=getString(R.string.pwd_check_err)
        
        name.setOnFocusChangeListener { v, hasFocus ->
            if(hasFocus.not()){
                viewModel.updateData("name", name.text.toString())
            }
        }

        email.setOnFocusChangeListener { v, hasFocus ->
            if (hasFocus.not()){
                viewModel.updateData("email", email.text.toString())
            }
        }

        pwd.setOnFocusChangeListener { v, hasFocus ->
            if (hasFocus.not()){
                viewModel.updateData("pwd", pwd.text.toString())
            }
        }

        pwdcheck.setOnFocusChangeListener { v, hasFocus ->
            if (hasFocus.not()){
                viewModel.updateData("check", pwdcheck.text.toString())
            }
        }

    }
}