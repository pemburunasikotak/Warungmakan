package com.example.penjualanmakanan.kasir
import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.penjualanmakanan.R

class AdapterPenjualan (private val context: Context, private val menu: List<ModelPenjualan>)
    : RecyclerView.Adapter<AdapterPenjualan.DaftarPesanHolder>() {
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): AdapterPenjualan.DaftarPesanHolder {
        return DaftarPesanHolder(
                LayoutInflater.from(parent.context).inflate(R.layout.list_tambahpesanankasir, parent, false)
        )
    }
    override fun onBindViewHolder(holder: AdapterPenjualan.DaftarPesanHolder, position: Int) {
        var list = menu[position]
        holder.tvid.text = list.id
        holder.tvhargalist.text= list.harga
        holder.tvname.text= list.nama

        holder.btn_pesankasir.setOnClickListener {
            val bundel = Bundle()
            bundel.putString("tvhargalist", list.harga.toString())
            bundel.putString("tvname", list.nama.toString())
            val intent = Intent(context, PesanKasir::class.java)
            intent.putExtras(bundel)
            context.startActivity(intent)
        }


    }

    override fun getItemCount(): Int = menu.size

    inner class DaftarPesanHolder(val view: View) : RecyclerView.ViewHolder(view) {

        var tvhargalist : TextView = view.findViewById(R.id.tvhargalistkasir)
        var tvid : TextView = view.findViewById(R.id.tvidkasir)
        var tvname : TextView = view.findViewById(R.id.tvnamekasir)
        var btn_pesankasir : Button = view.findViewById(R.id.btn_pesankasir)
    }
}