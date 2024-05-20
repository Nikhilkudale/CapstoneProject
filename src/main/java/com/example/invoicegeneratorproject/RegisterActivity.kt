
package com.example.invoicegeneratorproject

import android.content.Intent
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.lifecycleScope
import com.example.invoicegeneratorproject.data.User
import com.example.invoicegeneratorproject.services.UserService
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import retrofit2.HttpException
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import java.text.SimpleDateFormat
import java.util.Calendar
import java.util.Locale

class RegisterActivity : AppCompatActivity() {

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
        setContentView(R.layout.register_activity)

        val usernameEditText = findViewById<EditText>(R.id.usernameEditText)
        val passwordEditText = findViewById<EditText>(R.id.passwordEditText)
        val emailEditText = findViewById<EditText>(R.id.emailEditText)
        val registerButton = findViewById<Button>(R.id.registerButton)
        val loginButton = findViewById<Button>(R.id.loginButton)

        registerButton.setOnClickListener {
            val username = usernameEditText.text.toString()
            val password = passwordEditText.text.toString()
            val email = emailEditText.text.toString()

            if (username.isBlank() || password.isBlank() || email.isBlank()) {
                showToast("Please fill in all the fields")
            } else {
                val registrationDate = SimpleDateFormat(
                    "yyyy-MM-dd'T'HH:mm:ss.SSS",
                    Locale.getDefault()
                ).format(Calendar.getInstance().time)
                registerUser(null, username, password, email, registrationDate)
            }
        }

        loginButton.setOnClickListener {
            startActivity(Intent(this@RegisterActivity, LoginActivity::class.java))
        }
    }

    private fun registerUser(userId: Long?, username: String, password: String, email: String, registrationDate: String) {
        lifecycleScope.launch(Dispatchers.IO) {
            try {
                userService.registerUser(User(userId, username, password, email, registrationDate))
                withContext(Dispatchers.Main) {
                    showToast("User registered successfully")
                }
            } catch (e: HttpException) {
                withContext(Dispatchers.Main) {
                    showToast("User Already Exists: ${e.message()}")
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
            Toast.makeText(this@RegisterActivity, message, Toast.LENGTH_SHORT).show()
        }
    }
}
