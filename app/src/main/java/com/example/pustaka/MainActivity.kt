package com.example.pustaka

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.Button
import android.widget.EditText
import android.widget.Toast
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response


class MainActivity : AppCompatActivity() {

    lateinit var apiInterface: ApiInterface

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        apiInterface = ApiClient().getApiClient()!!.create(ApiInterface::class.java)
        viewPenerbit()
        val btnSimpan = findViewById<Button>(R.id.btnSimpan)
        btnSimpan.setOnClickListener {
            addPenerbit()
        }

    }

    // Method untuk menambahkan data penerbit data ke dalam database
    private fun addPenerbit() {
        val namaPenerbit = findViewById<EditText>(R.id.etNama).text
        val alamatPenerbit = findViewById<EditText>(R.id.etAlamat).text

        // Cek jika masih ada field yang kosong
        if (namaPenerbit.toString().trim() == "" ||
            alamatPenerbit.toString().trim() == "") {
            Toast.makeText(this, "Tolong lengkapi field yang kosong", Toast.LENGTH_SHORT).show()
        } else {
            var requestCall: Call<ResponseModel> = apiInterface
                .addPenerbit(namaPenerbit.toString(), alamatPenerbit.toString())

            requestCall.enqueue(object : Callback<ResponseModel> {
                override fun onResponse(
                    call: Call<ResponseModel>,
                    response: Response<ResponseModel>
                ) {
                    Log.d("response", response.body()!!.message.toString())
                    if (response.isSuccessful) {
                        Toast.makeText(
                            this@MainActivity,
                            "Data Berhasil Tersimpan",
                            Toast.LENGTH_SHORT
                        ).show()
                        viewPenerbit()
                        namaPenerbit.clear()
                        alamatPenerbit.clear()
                    } else {
                        Toast.makeText(
                            this@MainActivity,
                            "Data Gagal Tersimpan",
                            Toast.LENGTH_SHORT
                        ).show()
                    }
                }

                override fun onFailure(call: Call<ResponseModel>, t: Throwable) {
                    Toast.makeText(baseContext, "Data Gagal Tersimpan", Toast.LENGTH_SHORT).show()
                }
            })
        }
    }


    // Untuk menampilkan data penerbit pada Recylerview
    private fun viewPenerbit() {
        val rvBuku = findViewById<RecyclerView>(R.id.rvPenerbit)
        rvBuku.layoutManager = LinearLayoutManager(this)
        apiInterface.getPenerbit().enqueue(object : retrofit2.Callback<ArrayList<PenerbitModel>> {
            override fun onResponse(
                call: Call<ArrayList<PenerbitModel>>,
                response: Response<ArrayList<PenerbitModel>>
            ) {
                var penerbitData = response?.body()!!
                if (penerbitData.size > 0) {
                    rvBuku.adapter = PenerbitAdapter(penerbitData)
                }
            }

            override fun onFailure(call: Call<ArrayList<PenerbitModel>>, t: Throwable) {
                Log.e("error", "error ${t.message}")
            }
        })
    }
}