package com.example.invoicegeneratorproject.data

data class login(
    val username: String,
    val password: String
)



data class User(
    val userId: Long?,
    val username: String,
    val password: String,
    val email: String,
    val registrationDate: String
)
