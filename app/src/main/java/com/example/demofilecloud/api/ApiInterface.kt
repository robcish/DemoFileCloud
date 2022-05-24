package com.example.demofilecloud.api

import com.example.demofilecloud.data.GifDetailResponse
import com.example.demofilecloud.data.Response
import io.reactivex.Observable
import retrofit2.http.GET
import retrofit2.http.Path
import retrofit2.http.Query

interface ApiInterface {

    @GET("gifs/trending")
    fun getTrendList(@Query(value = "api_key") api_key: String, @Query(value = "limit") limit: Int): Observable<Response>

    @GET("gifs/search")
    fun search(
        @Query(value = "api_key") api_key: String,
        @Query(value = "q") tag: String,
        @Query(value = "limit") limit: Int,
        @Query(value = "offset") offset: Int,
        @Query(value = "rating") rating: String,
        @Query(value = "lang") language: String
    ): Observable<Response>

    @GET("gifs/{gif_id}")
    fun getDetail(@Path(value = "gif_id") id: String, @Query(value = "api_key") api_key: String): Observable<GifDetailResponse>
}
