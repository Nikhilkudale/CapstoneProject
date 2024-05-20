
package com.example.invoicegeneratorproject
import android.content.Intent
import kotlinx.coroutines.withContext

import android.os.Bundle
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.invoicegeneratorproject.Adapters.InvoiceAdapter
import com.example.invoicegeneratorproject.data.PreferenceHelper
import com.example.invoicegeneratorproject.services.InvoiceService
import com.example.invoicegeneratorproject.services.UserService
import com.google.android.material.floatingactionbutton.FloatingActionButton
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory


class InvoiceListActivity : AppCompatActivity() {

    // Declare InvoiceService instance
//    private val retrofit = Retrofit.Builder()
//        .baseUrl("http://10.0.2.2:8080/")
//        .addConverterFactory(GsonConverterFactory.create())
//        .build()
//
//    private val userService = retrofit.create(UserService::class.java)
    private val invoiceService = RetrofitInstance.retrofit.create(InvoiceService::class.java)



    private lateinit var recyclerView: RecyclerView
    private lateinit var adapter: InvoiceAdapter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_list_invo)

        recyclerView = findViewById(R.id.invoiceList_recyclerView)
        recyclerView.layoutManager = LinearLayoutManager(this)

        fetchInvoices()

        findViewById<FloatingActionButton>(R.id.ActionButton).setOnClickListener {
            val intent = Intent(this, CreateInvoiceActivity::class.java)
            startActivity(intent)
        }
    }

    private fun fetchInvoices() {
        val userId = PreferenceHelper.getUserId(this)
        userId.takeIf { it != -1L }?.let { loggedInUserId ->
            GlobalScope.launch(Dispatchers.IO) {
                try {
                    val response = invoiceService.getInvoicesByUserId(loggedInUserId)
                    if (response.isSuccessful) {
                        val invoices = response.body()
                        invoices?.let {
                            withContext(Dispatchers.Main) {
                                adapter = InvoiceAdapter(it)
                                recyclerView.adapter = adapter
                            }
                        }
                    } else {
                        withContext(Dispatchers.Main) {
                            // Handle the error response
                            showToast("NO Invoices Found: ${response.message()}")
                        }
                    }
                } catch (e: Exception) {
                    // Handle exception, e.g., display error message
                    e.printStackTrace()
                }
            }
        } ?: showToast("User not logged in") // Handle case where user is not logged in
    }

    private fun showToast(message: String) {
        runOnUiThread {
            Toast.makeText(this@InvoiceListActivity, message, Toast.LENGTH_SHORT).show()
        }
    }
}
