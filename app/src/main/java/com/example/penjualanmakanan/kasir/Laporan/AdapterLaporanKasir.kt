package com.example.penjualanmakanan.kasir
import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.penjualanmakanan.R

class AdapterLaporanKasir (private val context: Context, private val menu: List<ModelLaporanKasir>)
    : RecyclerView.Adapter<AdapterLaporanKasir.DaftarPesanHolder>() {
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): AdapterLaporanKasir.DaftarPesanHolder {
        return DaftarPesanHolder(
                LayoutInflater.from(parent.context).inflate(R.layout.list_laporankasir, parent, false)
        )
    }
    override fun onBindViewHolder(holder: AdapterLaporanKasir.DaftarPesanHolder, position: Int) {
        var list = menu[position]
        holder.tvhargalaporankasir.text = list.harga
        holder.tvtotallaporankasir.text= list.total
        holder.tvwaktulaporankasir.text= list.waktu
        holder.tvjumlahlaporankasir.text= list.jumlah
        holder.tvnamalaporankasir.text = list.nama
    }

    override fun getItemCount(): Int = menu.size

    inner class DaftarPesanHolder(val view: View) : RecyclerView.ViewHolder(view) {

        var tvhargalaporankasir : TextView = view.findViewById(R.id.tvhargalaporankasir)
        var tvtotallaporankasir : TextView = view.findViewById(R.id.tvtotallaporankasir)
        var tvwaktulaporankasir : TextView = view.findViewById(R.id.tvwaktulaporankasir)
        var tvjumlahlaporankasir : TextView = view.findViewById(R.id.tvtotallaporankasir)
        var tvnamalaporankasir : TextView = view.findViewById(R.id.tvnamalaporankasir)
    }
}