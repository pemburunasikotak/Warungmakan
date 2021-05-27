package com.example.penjualanmakanan.admin

import android.content.ContentValues
import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.penjualanmakanan.R
import com.google.firebase.database.*

class AdapterMenu(private val context: Context, private val menu: MutableList<ModelMenu>)
    : RecyclerView.Adapter<AdapterMenu.DaftarPesanHolder>() {
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): AdapterMenu.DaftarPesanHolder {
        return DaftarPesanHolder(
                LayoutInflater.from(parent.context).inflate(R.layout.list_menuadmin, parent, false)
        )
    }

    override fun onBindViewHolder(holder:AdapterMenu.DaftarPesanHolder, position: Int) {
        var list = menu[position]
        holder.tvid.text = list.id
        holder.tvhargalist.text= list.harga
        holder.tvname.text= list.nama
        holder.modal.text= list.modal
        holder.btn_editListadmin.setOnClickListener {
            val bundel = Bundle()
            bundel.putString("tvid", list.id.toString())
            bundel.putString("tvhargalist", list.harga.toString())
            bundel.putString("tvname", list.nama.toString())
            bundel.putString("modal",list.modal.toString())

            val intent = Intent(context, TambahMenu::class.java)
            intent.putExtras(bundel)
            context.startActivity(intent)
        }
        holder.btn_hapuslistadmin.setOnClickListener {
            val ref = FirebaseDatabase.getInstance().getReference("Menu").child(list.nama)
            val Query: Query = ref
            Query.addListenerForSingleValueEvent(object : ValueEventListener {
                override fun onDataChange(dataSnapshot: DataSnapshot) {
                    for (appleSnapshot in dataSnapshot.children) {
                        appleSnapshot.ref.removeValue()
                        val intent = Intent(context, HomeAdmin::class.java)
                        context.startActivity(intent)
                    }
                }

                override fun onCancelled(databaseError: DatabaseError) {
                    Log.e(ContentValues.TAG, "onCancelled", databaseError.toException())
                }
            })
        }


    }

    override fun getItemCount(): Int = menu.size

    inner class DaftarPesanHolder(val view: View) : RecyclerView.ViewHolder(view) {


        var tvhargalist : TextView = view.findViewById(R.id.tvhargalist)
        var tvid : TextView = view.findViewById(R.id.tvid)
        var tvname : TextView = view.findViewById(R.id.tvnameadmin)
        val modal: TextView= view.findViewById(R.id.tvmodallist)
        var btn_hapuslistadmin : Button = view.findViewById(R.id.btn_hapuslistadmin)
        var btn_editListadmin : Button = view.findViewById(R.id.btn_editListadmin)
    }

}