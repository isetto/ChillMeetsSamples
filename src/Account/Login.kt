package com.example.ad.retrofittest.Account

import android.Manifest
import android.annotation.SuppressLint
import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.support.v4.app.ActivityCompat
import android.support.v7.app.AppCompatActivity
import android.text.TextUtils
import android.util.Log
import android.view.View
import android.widget.EditText
import android.widget.LinearLayout
import android.widget.TextView
import android.widget.Toast

import com.example.ad.retrofittest.Common_Clases.HideKeyBoard
import com.example.ad.retrofittest.Common_Clases.ErrorHandler.CallbackWrapper
import com.example.ad.retrofittest.Common_Clases.SharedPreferencesRestore
import com.example.ad.retrofittest.Common_Clases.SharedPreferencesRestore.loginSave
import com.example.ad.retrofittest.Common_Clases.SharedPreferencesRestore.tokenSave
import com.example.ad.retrofittest.Maps.StartActivity
import com.example.ad.retrofittest.Model.Auth.AuthUser
import com.example.ad.retrofittest.Model.Auth.Token
import com.example.ad.retrofittest.R
import com.example.ad.retrofittest.Retrofit.ApiUtils
import com.example.ad.retrofittest.Retrofit.Services.LoginService

import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.disposables.CompositeDisposable
import io.reactivex.schedulers.Schedulers

//test
class Login : AppCompatActivity() {
    private var loginEt: EditText? = null
    private var passwordEt: EditText? = null
    private var apiServiceLogin: LoginService? = null
    private var linearLayout: LinearLayout? = null


    @SuppressLint("ClickableViewAccessibility")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        linearLayout = findViewById<View>(R.id.loginBackgroudId) as LinearLayout
        apiServiceLogin = ApiUtils.getLoginService(baseContext)
        loginEt = findViewById(R.id.loginEtIdLog)
        passwordEt = findViewById(R.id.passwordEtIdLog)

        linearLayout!!.setOnTouchListener { view, motionEvent ->
            HideKeyBoard.hideKeyboardFrom(baseContext, view)
            false
        }

        requestPermissions()
    }

    override fun onResume() {
        super.onResume()
        val token = SharedPreferencesRestore.token_restore(baseContext)
        val login = SharedPreferencesRestore.login_restore(baseContext)
        apiServiceLogin = ApiUtils.getLoginService(baseContext)

        if (token != "" && login != "") {
            tokenGlobal = token
            loginGlobal = login
        }

        isTokenValidLogin(tokenGlobal, baseContext)
    }

    fun isTokenValidLogin(token: String, context: Context) {
        val apiServiceLoginbla: LoginService
        apiServiceLoginbla = ApiUtils.getLoginService(context)
        val check = booleanArrayOf(true)
        Login.compositeDisposable.add(apiServiceLoginbla.aboutInterface(token)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribeWith(object : CallbackWrapper<AuthUser>(context) {
                    override fun onNext(response: AuthUser) {
                        val i = Intent(this@Login, StartActivity::class.java)
                        startActivity(i)
                    } }))
    }

    fun passwordForgot(v: View) {
        Toast.makeText(applicationContext, "My też nie ;)", Toast.LENGTH_LONG).show()
    }

    fun requestPermissions() {
        ActivityCompat.requestPermissions(this,
                arrayOf(ACCESS_COARSE_LOCATION, ACCESS_FINE_LOCATION),
                PERMISSION_REQUEST_CODE)
    }

    fun registerActivityBt(v: View) {
        val i = Intent(this@Login, Register::class.java)
        startActivity(i)
    }

    fun loginBt(v2: View) {
        val login = loginEt!!.text.toString().trim { it <= ' ' }
        val password = passwordEt!!.text.toString().trim { it <= ' ' }
        if (!TextUtils.isEmpty(login) && !TextUtils.isEmpty(password)) {
            loginCall(login, password)
        } else {
            Toast.makeText(baseContext, "Wypełnij wszystkie pola", Toast.LENGTH_SHORT).show()
        }

    }

    fun loginCall(login: String, password: String) {
        compositeDisposable.add(apiServiceLogin!!.loginInterface(login, password)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribeWith(object : CallbackWrapper<Token>(baseContext) {
                    override fun onNext(response: Token) {
                        loginGlobal = response.login
                        tokenGlobal = response.token

                        tokenSave(tokenGlobal, baseContext)
                        loginSave(loginGlobal, baseContext)
                        isTokenValidLogin(tokenGlobal, baseContext)
                    }}))
    }


    override fun onStop() {
        compositeDisposable.clear()
        super.onStop()
    }

    companion object {
        @JvmStatic val ACCESS_COARSE_LOCATION = Manifest.permission.ACCESS_COARSE_LOCATION
        @JvmStatic val ACCESS_FINE_LOCATION = Manifest.permission.ACCESS_FINE_LOCATION
        private val PERMISSION_REQUEST_CODE = 1234
        @JvmStatic var currentUserId: Int = 0
        @JvmStatic var tokenGlobal: String = "initialToken"
        @JvmStatic var loginGlobal: String = "initialLogin"
        @JvmStatic var compositeDisposable = CompositeDisposable()
    }
}
