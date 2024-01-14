package com.example.challengehw1

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel

class LoginViewModel: ViewModel() {
    var nameErrorEnabled: Boolean = false
    var emailErrorEnabled: Boolean = false
    var pwdErrorEnabled: Boolean = false
    var pwdCheckErrorEnabled: Boolean = false
    var buttonEnabled: Boolean = false

    var userData: MutableLiveData<LoginData> = MutableLiveData<LoginData>(LoginData("", "", ""))

    fun updateData(type: String, data: String){
        when(type){
            "name" -> userData.value?.username = data
            "email" -> userData.value?.email = data
            "pwd" -> userData.value?.password = data
            "check" -> userData.value?.checkPasswordCheck(data)
        }
        nameErrorEnabled=userData.value?.isValidUsername() ?: false
        emailErrorEnabled=userData.value?.isValidEmail() ?: false
        pwdErrorEnabled=userData.value?.isValidPassword() ?: false
        pwdCheckErrorEnabled=userData.value?.passwordCheck ?: false
        buttonEnabled=userData.value?.isValid() ?: false
    }

}