package com.example.penjualanmakanan

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import com.example.penjualanmakanan.admin.LoginAdmin
import com.example.penjualanmakanan.kasir.HomeKasir
import com.google.firebase.database.DataSnapshot
import com.google.firebase.database.DatabaseError
import com.google.firebase.database.FirebaseDatabase
import com.google.firebase.database.ValueEventListener
import kotlinx.android.synthetic.main.login_kasir.*

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.login_kasir)
        btn_loginkasir.setOnClickListener {
            Login()
        }

        et_loginadmin.setOnClickListener {
            val intent =Intent(this, LoginAdmin::class.java)
            startActivity(intent)
        }
    }

    private fun Login() {
        if (et_email_kasir.text.toString().isEmpty()){
            et_email_kasir.error = "masukkan Email"
            et_email_kasir.requestFocus()
        }

        if (et_passwd_kasir.text.toString().isEmpty()){
            et_passwd_kasir.error ="masukkan Paswd yang benar"
            et_passwd_kasir.requestFocus()
            return
        }
        val user =et_email_kasir.text.toString().trim()
        val pasword= et_passwd_kasir.text.toString()


        var query = FirebaseDatabase.getInstance().getReference("kasir").orderByChild("user").equalTo(
            user
        )
        query.addValueEventListener(object : ValueEventListener {
            override fun onDataChange(snapshot: DataSnapshot) {
                if (snapshot.exists()) {
                    for (snap in snapshot.children) {
                        val x = snap.getValue(Users::class.java)
                        //Log.e("test", Gson().toJson(x))
                        if (x!!.password.equals(pasword.trim())) {
                            val intent = Intent(this@MainActivity, HomeKasir::class.java)
                            startActivity(intent)
                        } else {
                            Toast.makeText(
                                this@MainActivity,
                                "User Tidak Ditemukan",
                                Toast.LENGTH_LONG
                            ).show()
                        }
                    }
                }

            }

            override fun onCancelled(error: DatabaseError) {
                TODO("Not yet implemented")
            }
        })
    }
}