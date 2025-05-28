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
import com.cahayaaya.laundry.pilihlayanan_activity

class transaksiMainActivity : AppCompatActivity() {

    private lateinit var btPilihPelanggan: Button
    private lateinit var btPilihLayanan: Button

    private lateinit var tvNamaPelanggan: TextView
    private lateinit var tvNoHpPelanggan: TextView
    private lateinit var tvLayananNama: TextView
    private lateinit var tvLayananHarga: TextView

    private val pilihPelanggan = 1
    private val pilihLayanan = 2
    private val pilihanTambahLayanan = 3

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_transaksi_main)

        // Inisialisasi TextView
        tvNamaPelanggan = findViewById(R.id.tvnmpelanggan)
        tvNoHpPelanggan = findViewById(R.id.tvnnotransaksi)
        tvLayananNama = findViewById(R.id.tvtransaksilayanannama)
        tvLayananHarga = findViewById(R.id.tvtransaksilayananharga)

        // Inisialisasi Button
        btPilihPelanggan = findViewById(R.id.bttransaksipilihpelanggan)
        btPilihLayanan = findViewById(R.id.bttransaksipilihlayanan)

        btPilihPelanggan.setOnClickListener {
            val intent = Intent(this, pilih_pelanggan_MainActivity2::class.java)
            startActivityForResult(intent, pilihPelanggan)
        }

        btPilihLayanan.setOnClickListener {
            val intent = Intent(this, pilihlayanan_activity::class.java)
            startActivityForResult(intent, pilihLayanan)
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

                tvNamaPelanggan.text = "Nama: $nama"
                tvNoHpPelanggan.text = "No HP: $noHP"
            } else {
                Toast.makeText(this, "Batal Memilih Pelanggan", Toast.LENGTH_SHORT).show()
            }
        } else if (requestCode == pilihLayanan) {
            if (resultCode == RESULT_OK && data != null) {
                val namaLayanan = data.getStringExtra("nama")
                val hargaLayanan = data.getStringExtra("harga")

                tvLayananNama.text = "Nama Layanan: $namaLayanan"
                tvLayananHarga.text = "Harga: $hargaLayanan"
            } else {
                Toast.makeText(this, "Batal Memilih Layanan", Toast.LENGTH_SHORT).show()
            }
        } else if (requestCode == pilihanTambahLayanan) {
            if (resultCode == RESULT_OK && data != null) {
                // Handle layanan tambahan di sini jika ada
            } else {
                Toast.makeText(this, "Batal Memilih Layanan Tambahan", Toast.LENGTH_SHORT).show()
            }
        }
    }
}
