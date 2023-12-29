package com.example.challengehw1

import android.graphics.Color
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.text.Editable
import android.text.TextWatcher
import android.view.View
import android.view.ViewGroup
import android.widget.ArrayAdapter
import android.widget.Button
import android.widget.Spinner
import android.widget.TextView
import androidx.core.view.get
import androidx.core.widget.addTextChangedListener
import com.google.android.material.textfield.TextInputEditText
import com.google.android.material.textfield.TextInputLayout

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val loname=findViewById<TextInputLayout>(R.id.loName)
        val loemail=findViewById<TextInputLayout>(R.id.loEmail)
        val lopwd=findViewById<TextInputLayout>(R.id.loPwd)
        val lopwdcheck=findViewById<TextInputLayout>(R.id.loPwdcheck)
        val name=findViewById<TextInputEditText>(R.id.etName)
        val email=findViewById<TextInputEditText>(R.id.etEmail)
        val pwd=findViewById<TextInputEditText>(R.id.etPwd)
        val pwdcheck=findViewById<TextInputEditText>(R.id.etPwdcheck)
        val btn=findViewById<Button>(R.id.button)
        val spinner: Spinner = findViewById<Spinner>(R.id.spinner)
        val items= resources.getStringArray(R.array.email_arr)
        val spinnerAdapter= object : ArrayAdapter<String>(this,android.R.layout.simple_spinner_item, items) {
            override fun isEnabled(position: Int): Boolean {
                return position != 0
            }

            override fun getDropDownView(
                position: Int,
                convertView: View?,
                parent: ViewGroup
            ): View {
                val view: TextView = super.getDropDownView(position, convertView, parent) as TextView
                if(position == 0) {
                    view.setTextColor(Color.GRAY)
                }

                return view
            }
        }
        spinnerAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item)
        spinner.adapter = spinnerAdapter
        
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