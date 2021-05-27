package com.example.penjualanmakanan.admin

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.Menu
import android.widget.Toast
import com.example.penjualanmakanan.R
import com.google.firebase.database.DatabaseReference
import com.google.firebase.database.FirebaseDatabase
import kotlinx.android.synthetic.main.layout_tambahmenu.*

class TambahMenu : AppCompatActivity() {

    lateinit var ref : DatabaseReference

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.layout_tambahmenu)


        //Intent Data
        val bundle = intent.extras
        idtambah.setText(bundle?.getString("tvid"))
        et_namatambahmenu.setText(bundle?.getString("tvname"))
        et_hargatambahmenu.setText(bundle?.getString("tvhargalist"))
        et_modaltambahmenu.setText(bundle?.getString("modal"))
        ref = FirebaseDatabase.getInstance().getReference("Menu")
        btn_tambahmenu.setOnClickListener {
            tambah()
        }

        if(idtambah.text.toString().isNotEmpty()){
            btn_tambahmenu.setText("Edit")
        }
    }
    private fun tambah() {
        if (et_namatambahmenu.toString().isEmpty()) {
            et_namatambahmenu.error = "Masukkan nama"
            et_namatambahmenu.requestFocus()
        }
        if (et_hargatambahmenu.toString().isEmpty()) {
            et_hargatambahmenu.error = "Masukan harga"
            et_hargatambahmenu.requestFocus()
            return
        }
        if (et_modaltambahmenu.toString().isEmpty()) {
            et_modaltambahmenu.error = "Masukkan modal"
            et_modaltambahmenu.requestFocus()
            return
        }


        val id = ref.push().key.toString()
        val nama = et_namatambahmenu.text.toString().trim()
        val harga = et_hargatambahmenu.text.toString().trim()
        val modal = et_modaltambahmenu.text.toString().trim()
        val event = Menu(id, nama,harga, modal)
        ref.child(nama).setValue(event).addOnCompleteListener {
            Toast.makeText(this, "Sukses Tambah data ", Toast.LENGTH_LONG).show()
            val intent = Intent(this, HomeAdmin::class.java)
            startActivity(intent)
        }
    }

}