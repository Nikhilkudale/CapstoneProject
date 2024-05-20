package com.example.invoicegeneratorproject

import android.content.Intent
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.lifecycleScope
import com.example.invoicegeneratorproject.data.PreferenceHelper
import com.example.invoicegeneratorproject.data.login
import com.example.invoicegeneratorproject.services.UserService
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import retrofit2.HttpException
import retrofit2.Response
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

class LoginActivity : AppCompatActivity() {

    private lateinit var usernameEditText: EditText
    private lateinit var passwordEditText: EditText
    private lateinit var loginButton: Button

//    private val retrofit = Retrofit.Builder()
//        .baseUrl("http://10.0.2.2:8080/")
////        .baseUrl("http://localhost:8080/")
//
//        .addConverterFactory(GsonConverterFactory.create())
//        .build()
//
//    private val userService = retrofit.create(UserService::class.java)

    private val userService = RetrofitInstance.retrofit.create(UserService::class.java)


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_login)

        usernameEditText = findViewById(R.id.usernameEditText)
        passwordEditText = findViewById(R.id.passwordEditText)
        loginButton = findViewById(R.id.loginButton)

        loginButton.setOnClickListener {
            val username = usernameEditText.text.toString()
            val password = passwordEditText.text.toString()
            loginUser(username, password)
        }
    }

    private fun loginUser(username: String, password: String) {
        lifecycleScope.launch(Dispatchers.IO) {
            try {
                val response: Response<Map<String, Any>> = userService.validate(login(username, password))
                if (response.isSuccessful) {
                    val responseData = response.body()
                    responseData?.let {
                        val userId = (it["userId"] as Double).toLong()
                        val loggedInUsername = it["username"] as String

                        PreferenceHelper.saveUserId(this@LoginActivity, userId)
                        PreferenceHelper.saveUsername(this@LoginActivity, loggedInUsername)

                        val intent = Intent(this@LoginActivity, InvoiceListActivity::class.java).apply {
                            putExtra("userId", userId)
                            putExtra("username", loggedInUsername)
                        }
                        startActivity(intent)
                        finish()
                    }
                } else {
                    withContext(Dispatchers.Main) {
                        when (response.code()) {
                            401 -> showToast("Incorrect credentials, try again!")
                            else -> showToast("Login failed: ${response.message()}")
                        }
                    }
                }
            } catch (e: HttpException) {
                withContext(Dispatchers.Main) {
                    showToast("Network error: ${e.message}")
                }
            } catch (e: Exception) {
                withContext(Dispatchers.Main) {
                    showToast("An unexpected error occurred: ${e.message}")
                }
            }
        }
    }

    private fun showToast(message: String) {
        runOnUiThread {
            Toast.makeText(this@LoginActivity, message, Toast.LENGTH_SHORT).show()
        }
    }
}
























