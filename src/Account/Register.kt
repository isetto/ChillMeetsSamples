package com.example.ad.retrofittest.Account

import android.annotation.SuppressLint
import android.content.Intent
import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import android.text.TextUtils
import android.util.Log
import android.view.View
import android.widget.EditText
import android.widget.LinearLayout
import android.widget.TextView
import android.widget.Toast
import com.example.ad.retrofittest.Common_Clases.ErrorHandler.CallbackWrapper
import com.example.ad.retrofittest.Common_Clases.HideKeyBoard

import com.example.ad.retrofittest.Model.StatusResponse
import com.example.ad.retrofittest.Profile.EditProfile
import com.example.ad.retrofittest.R
import com.example.ad.retrofittest.Retrofit.ApiUtils
import com.example.ad.retrofittest.Retrofit.Services.LoginService

import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.schedulers.Schedulers

class Register : AppCompatActivity() {

    private var loginEt: EditText? = null
    private var passwordEt: EditText? = null
    private var rePasswordEt: EditText? = null
    private var emailEt: EditText? = null
    private var apiServiceRegister: LoginService? = null
    private var linearLayout: LinearLayout? = null


    @SuppressLint("ClickableViewAccessibility")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_register)

        loginEt = findViewById(R.id.loginEtIdReg)
        passwordEt = findViewById(R.id.passwordEtIdReg)
        rePasswordEt = findViewById(R.id.repasswordEtIdReg)
        emailEt = findViewById(R.id.emailEtIdReg)
        apiServiceRegister = ApiUtils.getLoginService(baseContext)

        linearLayout = findViewById<View>(R.id.linearLayoutIdRegister) as LinearLayout

        linearLayout!!.setOnTouchListener { view, motionEvent ->
            HideKeyBoard.hideKeyboardFrom(baseContext, view)
            false
        }
    }

    fun registerBt(v: View) {
        val loginString = loginEt!!.text.toString().trim { it <= ' ' }
        val passwordString = passwordEt!!.text.toString().trim { it <= ' ' }
        val rePasswordString = rePasswordEt!!.text.toString().trim { it <= ' ' }
        val emailString = emailEt!!.text.toString().trim { it <= ' ' }
        if (!TextUtils.isEmpty(loginString) && !TextUtils.isEmpty(passwordString)
                && !TextUtils.isEmpty(rePasswordString) && !TextUtils.isEmpty(emailString)) {
            if (passwordString == rePasswordString)
                registerCall(loginString, passwordString, emailString)
            else
                Toast.makeText(baseContext, "Podane hasła są różne", Toast.LENGTH_SHORT).show()
        } else
            Toast.makeText(baseContext, "Wypełnij wszystkie pola", Toast.LENGTH_SHORT).show()
    }


    fun registerCall(login: String, password: String, email: String) {
        Login.compositeDisposable.add(apiServiceRegister!!.registerInterface(login, password, email)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribeWith(object : CallbackWrapper<StatusResponse>(baseContext) {
                    override fun onNext(response: StatusResponse) {
                        Log.i("stautsLogin", "message: " + response.message + " status " + response.status)
                        val i = Intent(this@Register, EditProfile::class.java)
                        startActivity(i)
                    }}))
    }

}
