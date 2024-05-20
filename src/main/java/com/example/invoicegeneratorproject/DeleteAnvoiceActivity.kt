//package com.example.invoicegeneratorproject
//
//
//import android.os.Bundle
//import android.widget.Button
//import android.widget.TextView
//import android.widget.Toast
//import androidx.appcompat.app.AppCompatActivity
//import com.example.invoicegeneratorproject.services.InvoiceService
//import kotlinx.coroutines.Dispatchers
//import kotlinx.coroutines.GlobalScope
//import kotlinx.coroutines.launch
//import kotlinx.coroutines.withContext
//import retrofit2.Retrofit
//import retrofit2.converter.gson.GsonConverterFactory
//
//class DeleteAnvoiceActivity : AppCompatActivity() {
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
//        setContentView(R.layout.activity_delete_invoice)
//
//        val clientId=intent.getLongExtra("CLIENT_ID",-1)
//        val clientName = intent.getStringExtra("CLIENT_NAME")
//        val amount = intent.getDoubleExtra("AMOUNT", 0.0)
//        val invoiceDate = intent.getStringExtra("INVOICE_DATE")
//        val description = intent.getStringExtra("DESCRIPTION")
//
//        findViewById<TextView>(R.id.tv_clientName).text = clientName
//        findViewById<TextView>(R.id.tvSingle_amount).text = "Amount: $amount"
//        findViewById<TextView>(R.id.tvSingle_date).text = "Date: $invoiceDate"
//        findViewById<TextView>(R.id.tv_scrollable_text).text = description
//
//        findViewById<Button>(R.id.deleteButton).setOnClickListener {
//            deleteInvoice(clientId)
//        }
//    }
//
//    private fun deleteInvoice(clientId: Long) {
//        GlobalScope.launch(Dispatchers.IO) {
//            try {
//                val response = invoiceService.deleteInvoice(clientId)
//                withContext(Dispatchers.Main) {
//                    if (response.isSuccessful) {
//                        finish() // Close the activity
//                    } else {
//                        Toast.makeText(this@DeleteAnvoiceActivity, "Failed to delete invoice: ${response.message()}", Toast.LENGTH_SHORT).show()
//                    }
//                }
//            } catch (e: Exception) {
//                withContext(Dispatchers.Main) {
//                    Toast.makeText(this@DeleteAnvoiceActivity, "", Toast.LENGTH_SHORT).show()
//                }
//            }
//        }
//    }
//}









package com.example.invoicegeneratorproject

import android.content.Intent
import android.os.Bundle
import android.widget.Button
import android.widget.TextView
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.example.invoicegeneratorproject.services.InvoiceService
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import android.app.AlertDialog
import android.view.LayoutInflater

class DeleteAnvoiceActivity : AppCompatActivity() {

    private val invoiceService = RetrofitInstance.retrofit.create(InvoiceService::class.java)
    private val mainScope = CoroutineScope(Dispatchers.Main)

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_delete_invoice)

        val clientId = intent.getLongExtra("CLIENT_ID", -1)
        val clientName = intent.getStringExtra("CLIENT_NAME")
        val amount = intent.getDoubleExtra("AMOUNT", 0.0)
        val invoiceDate = intent.getStringExtra("INVOICE_DATE")
        val description = intent.getStringExtra("DESCRIPTION")

        findViewById<TextView>(R.id.tv_clientName).text = clientName
        findViewById<TextView>(R.id.tvSingle_amount).text = "Amount: $amount"
        findViewById<TextView>(R.id.tvSingle_date).text = "Date: $invoiceDate"
        findViewById<TextView>(R.id.tv_scrollable_text).text = description

        findViewById<Button>(R.id.deleteButton).setOnClickListener {
            showDeleteConfirmationDialog(clientId)
        }
    }

    private fun showDeleteConfirmationDialog(clientId: Long) {
        val dialogView = LayoutInflater.from(this).inflate(R.layout.dialog_delete_confirmation, null)
        val builder = AlertDialog.Builder(this)
            .setView(dialogView)
            .setCancelable(false)

        val alertDialog = builder.create()

        dialogView.findViewById<Button>(R.id.btn_yes).setOnClickListener {
            alertDialog.dismiss()
            mainScope.launch {
                try {
                    val response = withContext(Dispatchers.IO) {
                        invoiceService.deleteInvoice(clientId)
                    }
                    if (response.isSuccessful) {
                        Toast.makeText(this@DeleteAnvoiceActivity, "Deleted", Toast.LENGTH_SHORT).show()
                        val intent = Intent(this@DeleteAnvoiceActivity, InvoiceListActivity::class.java)
                        startActivity(intent)
                        finish()
                    } else {
                        Toast.makeText(this@DeleteAnvoiceActivity, "Deletion Error!", Toast.LENGTH_SHORT).show()
                    }
                } catch (e: Exception) {
                    Toast.makeText(this@DeleteAnvoiceActivity, "Success", Toast.LENGTH_SHORT).show()
                }
            }
        }

        dialogView.findViewById<Button>(R.id.btn_no).setOnClickListener {
            alertDialog.dismiss()
        }

        alertDialog.show()
    }
}
