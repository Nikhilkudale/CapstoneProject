//package com.example.invoicegeneratorproject
//
//import android.os.Bundle
//import android.widget.Button
//import android.widget.EditText
//import android.widget.Toast
//import androidx.appcompat.app.AppCompatActivity
//import com.example.invoicegeneratorproject.data.Invoice
//import com.example.invoicegeneratorproject.services.InvoiceService
//import kotlinx.coroutines.Dispatchers
//import kotlinx.coroutines.GlobalScope
//import kotlinx.coroutines.launch
//import kotlinx.coroutines.withContext
//import retrofit2.Retrofit
//import retrofit2.converter.gson.GsonConverterFactory
//
//class UpdateInvoiceActivity : AppCompatActivity() {
//
////    private val retrofit = Retrofit.Builder()
////        .baseUrl("http://10.0.2.2:8080/")
////        .addConverterFactory(GsonConverterFactory.create())
////        .build()
//
//    private val invoiceService = RetrofitInstance.retrofit.create(InvoiceService::class.java)
//
//    override fun onCreate(savedInstanceState: Bundle?) {
//        super.onCreate(savedInstanceState)
//        setContentView(R.layout.activity_update_invoice)
//
//        val clientId = intent.getLongExtra("CLIENT_ID", -1)
//        val clientName = intent.getStringExtra("CLIENT_NAME")
//        val amount = intent.getDoubleExtra("AMOUNT", 0.0)
//        val invoiceDate = intent.getStringExtra("INVOICE_DATE")
//        val description = intent.getStringExtra("DESCRIPTION")
//
//        val clientNameEditText: EditText = findViewById(R.id.et_client_name)
//        val amountEditText: EditText = findViewById(R.id.et_amount)
//        val invoiceDateEditText: EditText = findViewById(R.id.et_date)
//        val descriptionEditText: EditText = findViewById(R.id.et_description)
//
//        clientNameEditText.setText(clientName)
//        amountEditText.setText(amount.toString())
//        invoiceDateEditText.setText(invoiceDate)
//        descriptionEditText.setText(description)
//
//        findViewById<Button>(R.id.updateButton).setOnClickListener {
//            val updatedClientName = clientNameEditText.text.toString()
//            val updatedAmount = amountEditText.text.toString().toDouble()
//            val updatedInvoiceDate = invoiceDateEditText.text.toString()
//            val updatedDescription = descriptionEditText.text.toString()
//
//            val updatedInvoice = Invoice(clientId, null, updatedClientName, updatedAmount, updatedInvoiceDate, updatedDescription)
//
//            updateInvoice(updatedInvoice)
//        }
//    }
//
//    private fun updateInvoice(invoice: Invoice) {
//        GlobalScope.launch(Dispatchers.IO) {
//            try {
//                val response = invoiceService.updateInvoice(invoice, invoice.clientId!!)
//                withContext(Dispatchers.Main) {
//                    if (response.isSuccessful) {
//                        Toast.makeText(this@UpdateInvoiceActivity, "Invoice updated successfully", Toast.LENGTH_SHORT).show()
//                        finish() // Close the activity
//                    } else {
//                        Toast.makeText(this@UpdateInvoiceActivity, "Failed to update invoice: ${response.message()}", Toast.LENGTH_SHORT).show()
//                    }
//                }
//            } catch (e: Exception) {
//                withContext(Dispatchers.Main) {
//                    Toast.makeText(this@UpdateInvoiceActivity, "", Toast.LENGTH_SHORT)
//                }
//            }
//        }
//    }
//}









package com.example.invoicegeneratorproject

import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.example.invoicegeneratorproject.data.Invoice
import com.example.invoicegeneratorproject.services.InvoiceService
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext

class UpdateInvoiceActivity : AppCompatActivity() {
    private val invoiceService = RetrofitInstance.retrofit.create(InvoiceService::class.java)

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_update_invoice)



        val clientId = intent.getLongExtra("CLIENT_ID", -1)
        val clientName = intent.getStringExtra("CLIENT_NAME")
        val amount = intent.getDoubleExtra("AMOUNT", 0.0)
        val invoiceDate = intent.getStringExtra("INVOICE_DATE")
        val description = intent.getStringExtra("DESCRIPTION")

        val clientNameEditText: EditText = findViewById(R.id.clientNameEditText)
        val amountEditText: EditText = findViewById(R.id.amountEditText)
        val descriptionEditText: EditText = findViewById(R.id.descriptionEditText)
        val invoiceDateEditText: EditText = findViewById(R.id.dateEditText)


        clientNameEditText.setText(clientName)
        amountEditText.setText(amount.toString())
        invoiceDateEditText.setText(invoiceDate)
        descriptionEditText.setText(description)

        findViewById<Button>(R.id.updateButton).setOnClickListener {
            val updatedClientName = clientNameEditText.text.toString()
            val updatedAmount = amountEditText.text.toString().toDouble()
            val updatedInvoiceDate = invoiceDateEditText.text.toString()
            val updatedDescription = descriptionEditText.text.toString()

            val updatedInvoice = Invoice(clientId, null, updatedClientName, updatedAmount, updatedInvoiceDate, updatedDescription)

            updateInvoice(updatedInvoice)
        }
    }

    private fun updateInvoice(invoice: Invoice) {
        GlobalScope.launch(Dispatchers.IO) {
            try {
                val response = invoiceService.updateInvoice(invoice, invoice.clientId!!)
                withContext(Dispatchers.Main) {
                    if (response.isSuccessful) {
                        Toast.makeText(
                            this@UpdateInvoiceActivity,
                            "Invoice updated successfully",
                            Toast.LENGTH_SHORT
                        ).show()
                        finish() // Close the activity
                    } else {
                        Toast.makeText(
                            this@UpdateInvoiceActivity,
                            "Failed to update invoice: ${response.message()}",
                            Toast.LENGTH_SHORT
                        ).show()
                    }
                }
            } catch (e: Exception) {
                withContext(Dispatchers.Main) {
                    Toast.makeText(this@UpdateInvoiceActivity, "", Toast.LENGTH_SHORT)
                }
            }
        }}}

