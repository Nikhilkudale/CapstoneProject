package com.example.invoicegeneratorproject.services

import com.example.invoicegeneratorproject.data.User
import com.example.invoicegeneratorproject.data.login
import retrofit2.Response
import retrofit2.http.Body
import retrofit2.http.POST
interface UserService {

    @POST("addUser")
    suspend fun registerUser(@Body user: User)

    @POST("validate")
    suspend fun validate(@Body user: login): Response<Map<String, Any>>
}