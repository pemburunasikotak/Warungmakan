package com.example.penjualanmakanan.kasir

import android.app.DatePickerDialog
import android.app.TimePickerDialog
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.text.format.DateFormat
import android.widget.Toast
import com.example.penjualanmakanan.R
import com.google.firebase.database.DatabaseReference
import com.google.firebase.database.FirebaseDatabase
import kotlinx.android.synthetic.main.layout_homekasir.*
import kotlinx.android.synthetic.main.layout_pesankasir.*
import kotlinx.android.synthetic.main.layout_pesankasir.idtambah
import kotlinx.android.synthetic.main.layout_tambahmenu.*
import java.math.BigInteger
import java.util.*

class PesanKasir : AppCompatActivity() {
    lateinit var ref : DatabaseReference
    private var timePickerDialog: TimePickerDialog? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.layout_pesankasir)
        ref = FirebaseDatabase.getInstance().getReference("Pesanan")

        val bundle = intent.extras
        et_namapesankasir.setText(bundle?.getString("tvname"))
        et_hargapesankasir.setText(bundle?.getString("tvhargalist"))


        val c = Calendar.getInstance()
        val year = c.get(Calendar.YEAR)
        val month = c.get(Calendar.MONTH)
        val day = c.get(Calendar.DAY_OF_MONTH)
        btn_tangal.setOnClickListener {
            val dpd = DatePickerDialog(this, DatePickerDialog.OnDateSetListener { view, year, monthOfYear, dayOfMonth ->
                // Display Selected date in TextView
                et_tanggalkasir.setText("" +   dayOfMonth + " " + month + ", " + year)
            }, year, month, day)
            dpd.show()
        }
        btn_tambahpesanan.setOnClickListener {
            validasi()
        }
        btn_total.setOnClickListener {
            if (et_jumlahpesan.text.toString().isEmpty()){
                et_jumlahpesan.setError("harus di isi")
            }else{
                val jml = et_jumlahpesan.text.toString()
                val harga =et_hargapesankasir.text.toString()
                et_totalpesanan.setText(kali(jml,harga).toString())
            }
        }
    }

    private fun kali(jml: String, harga:String): BigInteger {
        return jml.toBigInteger()*harga.toBigInteger()
    }

    private fun validasi() {
        if (et_tanggalkasir.text.toString().isEmpty()) {
            et_tanggalkasir.error = "Masukkan tangal"
            et_tanggalkasir.requestFocus()
        }
        if (et_totalpesanan.text.toString().isEmpty()) {
            et_totalpesanan.error = "Cek dulu "
            et_totalpesanan.requestFocus()
        }
        if(et_tanggalkasir.text.toString().isNotEmpty()&&et_totalpesanan.text.toString().isNotEmpty()){
            simpankedatabase()
        }
    }

    private fun simpankedatabase() {
        val id = ref.push().key.toString()
        val nama = et_namapesankasir.text.toString()
        val harga = et_hargapesankasir.text.toString()
        val waktu = et_tanggalkasir.text.toString()
        val total = et_totalpesanan.text.toString()
        val jumlah =et_jumlahpesan.text.toString()

        val pesan = Pesan(id,nama,harga,jumlah,waktu,total)
        ref.child(id).setValue(pesan).addOnCompleteListener {
            Toast.makeText(this, "Sukses Pesan ", Toast.LENGTH_LONG).show()
            val intent = Intent(this, HomeKasir::class.java)
            startActivity(intent)
        }
    }
}