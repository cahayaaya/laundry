package com.cahayaaya.laundry.transaksi

import android.content.Intent
import android.os.Bundle
import android.widget.Button
import android.widget.TextView
import android.widget.Toast
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import com.cahayaaya.laundry.R
import com.cahayaaya.laundry.pilih_pelanggan_MainActivity2

class transaksiMainActivity : AppCompatActivity() {

    private lateinit var  btpilihpelanggan: Button

    private val pilihPelanggan = 1
    private val pilihLayanan = 2
    private val pilihanTambahLayanan = 3


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_transaksi_main)

        btpilihpelanggan = findViewById(R.id.bttransaksipilihpelanggan)

        btpilihpelanggan.setOnClickListener {
            val intent = Intent(this, pilih_pelanggan_MainActivity2::class.java)
            startActivityForResult(intent, pilihPelanggan)
        }

        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }
    }

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)
        if (requestCode == pilihPelanggan) {
            if (resultCode == RESULT_OK && data != null) {
                val idPelanggan = data.getStringExtra("idPelanggan")
                val nama = data.getStringExtra("nama")
                val noHP = data.getStringExtra("noHP")

                
            } else if (resultCode == RESULT_CANCELED) {
                Toast.makeText(this, "Batal Memilih Pelanggan", Toast.LENGTH_SHORT).show()
            }
        }
    }
}
