
package com.example.invoicegeneratorproject

import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.TextView
import android.widget.Toast
import android.widget.Toolbar
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.lifecycleScope
import com.example.invoicegeneratorproject.data.Invoice
import com.example.invoicegeneratorproject.data.PreferenceHelper
import com.example.invoicegeneratorproject.services.InvoiceService
import com.google.gson.Gson
import com.google.gson.GsonBuilder
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

class CreateInvoiceActivity : AppCompatActivity() {

    private lateinit var clientNameEditText: EditText
    private lateinit var amountEditText: EditText
    private lateinit var descriptionEditText: EditText
    private lateinit var invoiceDateEditText: EditText
    private lateinit var addInvoiceButton: Button

//    private val gson: Gson = GsonBuilder().setLenient().create()
//    private val retrofit = Retrofit.Builder()
//        .baseUrl("http://10.0.2.2:8080/") // Use '10.0.2.2' to refer to localhost from Android emulator
////        .baseUrl("http://localhost:8080/")
//
//        .addConverterFactory(GsonConverterFactory.create(gson))
//        .build()

    private val invoiceService = RetrofitInstance.retrofit.create(InvoiceService::class.java)



    private var userId: Long = -1

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.invoice_create_layout)

        userId = PreferenceHelper.getUserId(this)

        clientNameEditText = findViewById(R.id.clientNameEditText)
        amountEditText = findViewById(R.id.amountEditText)
        descriptionEditText = findViewById(R.id.descriptionEditText)
        invoiceDateEditText = findViewById(R.id.dateEditText)
        addInvoiceButton = findViewById(R.id.addInvoiceButton)


        addInvoiceButton.setOnClickListener {
            val clientName = clientNameEditText.text.toString()
            val amountText = amountEditText.text.toString()
            val amount = amountText.toDoubleOrNull()
            val description = descriptionEditText.text.toString()
            val invoiceDate = invoiceDateEditText.text.toString()

            if (validateInputs(clientName, amount, description, invoiceDate)) {
                val invoice = Invoice(clientId = null, userId = null,clientName, amount!!, description, invoiceDate)
                saveInvoiceToBackend(invoice)
            } else {
                showToast("Invalid input! Please fill all fields correctly.")
            }
        }
    }

    private fun validateInputs(
        clientName: String,
        amount: Double?,
        description: String,
        invoiceDate: String
    ): Boolean {
        return clientName.isNotBlank() && amount != null && amount > 0 && description.isNotBlank() && invoiceDate.isNotBlank()
    }

    private fun saveInvoiceToBackend(invoice: Invoice) {
        lifecycleScope.launch(Dispatchers.IO) {
            try {
                val response = invoiceService.addInvoice(invoice, userId)
                withContext(Dispatchers.Main) {
                    if (response.isSuccessful) {

                    } else {
                        showToast("Error adding invoice: ${response.message()}")
                    }
                }
            } catch (e: Exception) {
                withContext(Dispatchers.Main) {

                    showToast("Saved Sucess")
                }
            }
        }
    }

    private fun showToast(message: String) {
        Toast.makeText(this@CreateInvoiceActivity, message, Toast.LENGTH_SHORT).show()
    }
}
