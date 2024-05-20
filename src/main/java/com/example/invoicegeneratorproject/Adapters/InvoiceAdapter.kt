package com.example.invoicegeneratorproject.Adapters

import android.content.Intent
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.invoicegeneratorproject.data.Invoice
import com.example.invoicegeneratorproject.R
import com.example.invoicegeneratorproject.DeleteAnvoiceActivity
import com.example.invoicegeneratorproject.UpdateInvoiceActivity

class InvoiceAdapter(private val invoices: List<Invoice>) : RecyclerView.Adapter<InvoiceAdapter.ViewHolder>() {

    inner class ViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        val clientNameTextView: TextView = itemView.findViewById(R.id.tv_client_name)
        val amountTextView: TextView = itemView.findViewById(R.id.tv_amount)
        val invoiceDateTextView: TextView = itemView.findViewById(R.id.tv_date)
        val editImageView: ImageView = itemView.findViewById(R.id.imageView2)
        val descriptionTextView: TextView = itemView.findViewById(R.id.descriptionTextView)

    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val itemView = LayoutInflater.from(parent.context).inflate(R.layout.list_invoice_item, parent, false)
        return ViewHolder(itemView)
    }

    override fun getItemCount() = invoices.size

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val currentInvoice = invoices[position]
        holder.clientNameTextView.text = currentInvoice.clientName
        holder.amountTextView.text = "Amount: $${currentInvoice.amount.toString()}"
        holder.invoiceDateTextView.text = currentInvoice.invoiceDate
        holder.descriptionTextView.text = "Description: ${currentInvoice.description}"


        holder.editImageView.setOnClickListener {
            val context = holder.itemView.context
            val intent = Intent(context, UpdateInvoiceActivity::class.java).apply {
                putExtra("INVOICE_ID", currentInvoice.userId)
                putExtra("CLIENT_ID", currentInvoice.clientId)

                putExtra("CLIENT_NAME", currentInvoice.clientName)
                putExtra("AMOUNT", currentInvoice.amount)
                putExtra("INVOICE_DATE", currentInvoice.invoiceDate)
                putExtra("DESCRIPTION", currentInvoice.description)
            }
            context.startActivity(intent)
        }

        holder.clientNameTextView.setOnClickListener {
            val context = holder.itemView.context
            val intent = Intent(context, DeleteAnvoiceActivity::class.java).apply {
                putExtra("INVOICE_ID", currentInvoice.userId)
                putExtra("CLIENT_ID", currentInvoice.clientId)
                putExtra("CLIENT_NAME", currentInvoice.clientName)
                putExtra("AMOUNT", currentInvoice.amount)
                putExtra("INVOICE_DATE", currentInvoice.invoiceDate)
                putExtra("DESCRIPTION", currentInvoice.description)
            }
            context.startActivity(intent)
        }
    }
}
