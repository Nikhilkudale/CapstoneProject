package com.example.invoicegeneratorproject.data


data class Invoice(
    val clientId: Long?,
    val userId: Long?,

    val clientName: String,
    val amount: Double,
    val description: String,
    val invoiceDate: String
)