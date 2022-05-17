package com.example.pustaka

import retrofit2.Call
import retrofit2.http.*


interface ApiInterface {
    // Untuk endpoint http://localhost/pustaka/MyAPI.php?function=get_penerbit
    @GET("MyAPI.php?function=get_penerbit")
    fun getPenerbit(): Call<ArrayList<PenerbitModel>>

    // Untuk endpoint http://localhost/pustaka/MyAPI.php?function=insert_penerbit
    @FormUrlEncoded
    @POST("MyAPI.php?function=insert_penerbit")
    fun addPenerbit(
        @Field("nama_penerbit") nama: String,
        @Field("alamat_penerbit") alamat: String
    ): Call<ResponseModel>

}