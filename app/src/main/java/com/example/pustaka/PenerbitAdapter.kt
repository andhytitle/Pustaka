package com.example.pustaka

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView

class PenerbitAdapter(var dataList: ArrayList<PenerbitModel>?) :
    RecyclerView.Adapter<PenerbitAdapter.MyViewHolder>() {
    class MyViewHolder(view: View) : RecyclerView.ViewHolder(view) {
        val tvNo = view.findViewById<TextView>(R.id.tvNo)
        val tvNama = view.findViewById<TextView>(R.id.tvNama)
        val tvAlamat = view.findViewById<TextView>(R.id.tvAlamat)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MyViewHolder {
        return MyViewHolder(
            LayoutInflater.from(parent.context)
                .inflate(R.layout.penerbit_item, parent, false)
        )
    }

    override fun onBindViewHolder(holder: MyViewHolder, position: Int) {
        val item = dataList?.get(position)

        holder.tvNo.text = "${(position + 1)}."
        holder.tvNama.text = item?.namaPenenrbit.toString()
        holder.tvAlamat.text = item?.alamatPenenrbit.toString()
    }

    override fun getItemCount(): Int {
        return dataList!!.size
    }
}