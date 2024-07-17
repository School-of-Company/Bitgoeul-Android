package com.msg.network.api

import com.msg.network.request.company.PostCompanyRequest
import com.msg.network.response.company.GetCompanyListResponse
import retrofit2.http.Body
import retrofit2.http.DELETE
import retrofit2.http.GET
import retrofit2.http.POST
import retrofit2.http.Path

interface CompanyAPI {

    @GET("company")
    suspend fun getCompanyList(): GetCompanyListResponse

    @POST("company")
    suspend fun postCompany(
        @Body body: PostCompanyRequest
    )

    @DELETE("company/{id}")
    suspend fun deleteCompany(
        @Path("id") id: Long
    )
}