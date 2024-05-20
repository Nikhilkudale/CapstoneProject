//package com.example.invoicegeneratorproject
//
//import retrofit2.Response
//import retrofit2.http.Body
//import retrofit2.http.DELETE
//import retrofit2.http.GET
//import retrofit2.http.POST
//import retrofit2.http.PUT
//import retrofit2.http.Path
//import retrofit2.http.Query
//interface InvoiceService {
//
//    @GET("/invoices")
//    suspend fun getUserInvoices(@Query("username") username: Long): Response<List<Invoice>>
//
//    @POST("/invoices")
//    suspend fun addInvoice(@Body invoice: Invoice, @Query("userId") userId: Long): Response<String>
//
//    @PUT("/invoices/update/{clientId}")
//    suspend fun updateInvoice(@Body invoice: Invoice, @Path("clientId") clientId: Long): Response<String>
//
//    @DELETE("/invoices/delete/{clientId}")
//    suspend fun deleteInvoice(@Path("clientId") clientId: Long): Response<String>
//
////    @GET("/invoices/user/{userId}")
////    suspend fun getInvoicesByUserId(@Path("userId") userId: Long): Response<List<Invoice>>
//
//    @GET("users/{userId}/invoices")
//    suspend fun getUserInvoices(@Path("userId") userId: String): Response<List<Invoice>>
//
//
//    @GET("invoices/user/{userId}")
//    suspend fun getInvoicesByUserId(@Path("userId") userId: Long): Response<List<Invoice>>
//}
//
//
//
//
//
package com.example.invoicegeneratorproject.services

import com.example.invoicegeneratorproject.data.Invoice
import retrofit2.Response
import retrofit2.http.Body
import retrofit2.http.DELETE
import retrofit2.http.GET
import retrofit2.http.POST
import retrofit2.http.PUT
import retrofit2.http.Path
import retrofit2.http.Query

interface InvoiceService {


    @POST("invoices")
    suspend fun addInvoice(@Body invoice: Invoice, @Query("userId") userId: Long): Response<String>

    @PUT("invoices/update/{clientId}")
    suspend fun updateInvoice(@Body invoice: Invoice, @Path("clientId") clientId: Long): Response<String>

    @DELETE("invoices/delete/{clientId}")
    suspend fun deleteInvoice(@Path("clientId") clientId: Long): Response<String>

    @GET("invoices/user/{userId}")
    suspend fun getInvoicesByUserId(@Path("userId") userId: Long): Response<List<Invoice>>

}
