package com.cahayaaya.laundry.pelanggan

import android.content.Intent
import android.os.Bundle
import android.service.controls.actions.FloatAction
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import com.cahayaaya.laundry.R
import com.google.android.material.floatingactionbutton.FloatingActionButton

class DataPelanggan : AppCompatActivity() {
    lateinit var bt_data_pengguna_tambah : FloatingActionButton
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_data_pelanggan)
        init()
        tekan()
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }
    }
    fun init() {
        bt_data_pengguna_tambah = findViewById(R.id.bt_data_pengguna_tambah)
    }
    fun tekan() {
        bt_data_pengguna_tambah.setOnClickListener {
            val intent = Intent(this,TambahPelanggan::class.java)
            startActivity(intent)
        }
    }
}