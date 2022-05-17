package com.example.pustaka

import com.google.gson.annotations.SerializedName

class PenerbitModel(
    @SerializedName("kode_penerbit")    //atribut sama dengan yang ada di tabel penerbit
    val kodePenenrbit: String?,         //atribut digunakan di adapter
    @SerializedName("nama_penerbit")
    val namaPenenrbit: String?,
    @SerializedName("alamat_penerbit")
    val alamatPenenrbit: String?

)

