package com.example.challengehw1

import android.os.Bundle
import android.widget.ArrayAdapter
import android.widget.Spinner
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import androidx.core.widget.addTextChangedListener
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
        
        name.setOnFocusChangeListener { v, hasFocus ->
            if (!hasFocus && name.text.toString()==""){
                loname.error = "이름을 입력해주세요."
            }
        }

        email.setOnFocusChangeListener { v, hasFocus ->
            if (spinner.selectedItemPosition==0){
                loemail.error = "도메인을 입력해주세요."
            }
            if (!hasFocus && name.text.toString()==""){
                loemail.error = "이메일을 입력해주세요."
            }
        }

        pwd.setOnFocusChangeListener { v, hasFocus ->
            if (!hasFocus && name.text.toString()==""){
                lopwd.error = "비밀번호를 입력해주세요."
            }
            if (hasFocus && name.text.toString()==""){
                lopwd.error = null
                lopwd.helperText="10자리 이상, 특수문자, 대문자 포함"
            }
        }

        pwdcheck.setOnFocusChangeListener { v, hasFocus ->
            if (!hasFocus && name.text.toString()==""){
                lopwdcheck.error = "비밀번호가 다시 입력해주세요."
            }
        }

        name.addTextChangedListener { text ->
            if(text.toString().isNotEmpty()){
                loname.error=null
            }
            btn.isEnabled=!(loname.error.isNullOrEmpty() || loemail.error.isNullOrEmpty() || lopwd.error.isNullOrEmpty() || lopwdcheck.error.isNullOrEmpty())
        }

        email.addTextChangedListener { text ->
            if(text.toString().isNotEmpty()){
                loemail.error=null
            }
            btn.isEnabled=!(loname.error.isNullOrEmpty() || loemail.error.isNullOrEmpty() || lopwd.error.isNullOrEmpty() || lopwdcheck.error.isNullOrEmpty())
        }

        pwd.addTextChangedListener { text ->
            if(text.toString().isNotEmpty()){
                lopwd.error=null
            }
            if(text.isNullOrEmpty()){
                lopwd.helperText="10자리 이상, 특수문자, 대문자 포함"
            } else{
                lopwd.helperText=null
            }
            if(text?.length ?: 0<10){
                lopwd.error="10자리 이상 입력하세요."
            } else if(!(text?.contains("[!\"#$%&'()*+,-./:;\\\\<=>?@\\[\\]^_`{|}~]".toRegex()) ?: false)){
                lopwd.error="특수문자를 포함하세요."
            } else if(!(text?.contains("[A-Z]".toRegex()) ?: false)){
                lopwd.error="대문자를 포함하세요."
            }
            btn.isEnabled=!(loname.error.isNullOrEmpty() || loemail.error.isNullOrEmpty() || lopwd.error.isNullOrEmpty() || lopwdcheck.error.isNullOrEmpty())
        }

        pwdcheck.addTextChangedListener { text ->
            if(text.toString().isNotEmpty()){
                lopwdcheck.error=null
            }
            if(text.toString()!=pwd.text.toString()){
                lopwdcheck.error="동일하지 않습니다."
            }
            btn.isEnabled=!(loname.error.isNullOrEmpty() || loemail.error.isNullOrEmpty() || lopwd.error.isNullOrEmpty() || lopwdcheck.error.isNullOrEmpty())
        }

    }
}