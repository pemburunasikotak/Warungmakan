package com.example.penjualanmakanan.kasir

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.example.penjualanmakanan.R
import kotlinx.android.synthetic.main.layout_homekasir.*

class HomeKasir : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.layout_homekasir)

        btn_laporanKasir.setOnClickListener {
            val intent = Intent(this, LaporanKasir::class.java)
            startActivity(intent)
        }
        btn_daftarkasir.setOnClickListener {
            val intent = Intent(this, Penjualan::class.java)
            startActivity(intent)
        }
    }
}