package com.example.penjualanmakanan.admin

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.example.penjualanmakanan.R
import kotlinx.android.synthetic.main.layout_homeadmin.*

class HomeAdmin : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.layout_homeadmin)

        btn_menumakan.setOnClickListener {
            val intent = Intent(this, MenuPesan::class.java)
            startActivity(intent)
        }
        btn_daftarpenjualan.setOnClickListener {
            val intent = Intent(this, LaporanAdmin::class.java)
            startActivity(intent)
        }

    }
}