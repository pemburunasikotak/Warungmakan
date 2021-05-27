package com.example.penjualanmakanan.admin
import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.penjualanmakanan.R

class AdapterLaporanAdmin (private val context: Context, private val menu: List<ModelLaporanAdmin>)
    : RecyclerView.Adapter<AdapterLaporanAdmin.DaftarPesanHolder>() {
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): AdapterLaporanAdmin.DaftarPesanHolder {
        return DaftarPesanHolder(
                LayoutInflater.from(parent.context).inflate(R.layout.list_laporanadmin, parent, false)
        )
    }
    override fun onBindViewHolder(holder: AdapterLaporanAdmin.DaftarPesanHolder, position: Int) {
        var list = menu[position]
        holder.tvhargalaporankasir.text = list.harga
        holder.tvtotallaporankasir.text= list.total
        holder.tvwaktulaporankasir.text= list.waktu
        holder.tvjumlahlaporankasir.text= list.jumlah
        holder.tvnamalaporankasir.text = list.nama
    }

    override fun getItemCount(): Int = menu.size

    inner class DaftarPesanHolder(val view: View) : RecyclerView.ViewHolder(view) {

        var tvhargalaporankasir : TextView = view.findViewById(R.id.tvhargalaporanadmin)
        var tvtotallaporankasir : TextView = view.findViewById(R.id.tvtotallaporanadmin)
        var tvwaktulaporankasir : TextView = view.findViewById(R.id.tvwaktulaporanadmin)
        var tvjumlahlaporankasir : TextView = view.findViewById(R.id.tvtotallaporanadmin )
        var tvnamalaporankasir : TextView = view.findViewById(R.id.tvnamalaporanadmin)
    }
}