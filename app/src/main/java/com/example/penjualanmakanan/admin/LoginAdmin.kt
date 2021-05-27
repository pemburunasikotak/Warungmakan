package com.example.penjualanmakanan.admin

import android.content.Intent
import android.os.Bundle
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.example.penjualanmakanan.R
import com.example.penjualanmakanan.Users
import com.google.firebase.database.DataSnapshot
import com.google.firebase.database.DatabaseError
import com.google.firebase.database.FirebaseDatabase
import com.google.firebase.database.ValueEventListener
import kotlinx.android.synthetic.main.login_admin.*
import kotlinx.android.synthetic.main.login_kasir.*

class LoginAdmin : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.login_admin)
        btn_loginadmin.setOnClickListener {
            Login()
        }
    }
    private fun Login() {
        if (et_email_admin.text.toString().isEmpty()){
            et_email_admin.error = "masukkan Email"
            et_email_admin.requestFocus()
        }

        if (et_passwd_admin.text.toString().isEmpty()){
            et_passwd_admin.error ="masukkan Paswd yang benar"
            et_passwd_admin.requestFocus()
            return
        }
        val user =et_email_admin.text.toString().trim()
        val pasword= et_passwd_admin.text.toString()
        var query = FirebaseDatabase.getInstance().getReference("admin").orderByChild("user").equalTo(
            user
        )
        query.addValueEventListener(object : ValueEventListener {
            override fun onDataChange(snapshot: DataSnapshot) {
                if (snapshot.exists()) {
                    for (snap in snapshot.children) {
                        val x = snap.getValue(Users::class.java)
                        //Log.e("test", Gson().toJson(x))
                        if (x!!.password.equals(pasword.trim())) {
                            val intent = Intent(this@LoginAdmin, HomeAdmin::class.java)
                            startActivity(intent)
                        } else {
                            Toast.makeText(
                                this@LoginAdmin,
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