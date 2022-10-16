package com.gallardo.sportsoracle.network

import com.squareup.moshi.Moshi
import com.squareup.moshi.kotlin.reflect.KotlinJsonAdapterFactory
import retrofit2.Retrofit
import retrofit2.converter.moshi.MoshiConverterFactory
import retrofit2.http.Headers
import retrofit2.http.POST

private const val BASE_URL = "https://database.deta.sh/v1/a07grhue/"

private val moshi = Moshi.Builder()
    .add(KotlinJsonAdapterFactory())
    .build()

private val retrofit = Retrofit.Builder()
    .addConverterFactory(MoshiConverterFactory.create(moshi))
    .baseUrl(BASE_URL)
    .build()

interface FootballApiService {
    @Headers(
        "X-API-Key: a07grhue_tQujP5CixjTwDQc3jYczBeYF3gDz3WSw"
    )

    @POST("Groups/query")
    suspend fun getGroups() : Response
}

object FootballApi {
    val retrofitService : FootballApiService by lazy {
        retrofit.create(FootballApiService::class.java)
    }
}