package com.cahayaaya.laundry.transaksi

import android.content.Intent
import android.os.Bundle
import android.view.View
import android.widget.Button
import android.widget.TextView
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.cahayaaya.laundry.R
import com.cahayaaya.laundry.adapter.PilihTambahanAdapter
import com.cahayaaya.laundry.modeldata.ModelTambahan
import com.cahayaaya.laundry.pilih_pelanggan_MainActivity2
import com.cahayaaya.laundry.pilihlayanan_activity

class transaksiMainActivity : AppCompatActivity() {

    private lateinit var btPilihPelanggan: Button
    private lateinit var btPilihLayanan: Button
    private lateinit var btnProses: Button
    private lateinit var btTambahan: Button

    private lateinit var tvNamaPelanggan: TextView
    private lateinit var tvNoHpPelanggan: TextView
    private lateinit var tvLayananNama: TextView
    private lateinit var tvLayananHarga: TextView
    private lateinit var tvKosong: TextView

    private lateinit var recyclerView: RecyclerView
    private lateinit var adapter: PilihTambahanAdapter

    private var namaPelanggan: String? = null
    private var noHpPelanggan: String? = null
    private var namaLayanan: String? = null
    private var hargaLayanan: String? = null

    private val pilihPelanggan = 1
    private val pilihLayanan = 2
    private val pilihTambahan = 3

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_transaksi_main)

        tvNamaPelanggan = findViewById(R.id.tvnmpelanggan)
        tvNoHpPelanggan = findViewById(R.id.tvnnotransaksi)
        tvLayananNama = findViewById(R.id.tvtransaksilayanannama)
        tvLayananHarga = findViewById(R.id.tvtransaksilayananharga)
        tvKosong = findViewById(R.id.tvKosongTambahan)

        btPilihPelanggan = findViewById(R.id.bttransaksipilihpelanggan)
        btPilihLayanan = findViewById(R.id.bttransaksipilihlayanan)
        btnProses = findViewById(R.id.btnprosestransaksi)
        btTambahan = findViewById(R.id.bttambahantransaksi)

        recyclerView = findViewById(R.id.rvtransaksi)
        recyclerView.layoutManager = LinearLayoutManager(this)
        recyclerView.setHasFixedSize(true)

        adapter = PilihTambahanAdapter(this, mutableListOf(), tvKosong)
        recyclerView.adapter = adapter

        tvKosong.visibility = View.VISIBLE

        btPilihPelanggan.setOnClickListener {
            startActivityForResult(Intent(this, pilih_pelanggan_MainActivity2::class.java), pilihPelanggan)
        }

        btPilihLayanan.setOnClickListener {
            startActivityForResult(Intent(this, pilihlayanan_activity::class.java), pilihLayanan)
        }

        btTambahan.setOnClickListener {
            startActivityForResult(Intent(this, activity_pilih_tambahan::class.java), pilihTambahan)
        }

        btnProses.setOnClickListener {
            if (namaPelanggan == null || namaLayanan == null) {
                Toast.makeText(this, "Lengkapi data pelanggan dan layanan!", Toast.LENGTH_SHORT).show()
                return@setOnClickListener
            }

            val intent = Intent(this, konfirmasiTransaksiActivity::class.java)
            intent.putExtra("namaPelanggan", namaPelanggan)
            intent.putExtra("noHP", noHpPelanggan)
            intent.putExtra("namaLayanan", namaLayanan)
            intent.putExtra("hargaLayanan", hargaLayanan)
            intent.putParcelableArrayListExtra("dataTambahan", ArrayList(adapter.getSelectedItems()))
            startActivity(intent)
        }
    }

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)

        if (resultCode == RESULT_OK && data != null) {
            when (requestCode) {
                pilihPelanggan -> {
                    namaPelanggan = data.getStringExtra("nama")
                    noHpPelanggan = data.getStringExtra("noHP")
                    tvNamaPelanggan.text = "Nama: $namaPelanggan"
                    tvNoHpPelanggan.text = "No HP: $noHpPelanggan"
                }
                pilihLayanan -> {
                    namaLayanan = data.getStringExtra("nama")
                    hargaLayanan = data.getStringExtra("harga")
                    tvLayananNama.text = "Nama Layanan: $namaLayanan"
                    tvLayananHarga.text = "Harga: $hargaLayanan"
                }
                pilihTambahan -> {
                    val id = data.getStringExtra("idLayanan")
                    val nama = data.getStringExtra("nama")
                    val harga = data.getStringExtra("harga")

                    if (!id.isNullOrEmpty() && !nama.isNullOrEmpty() && !harga.isNullOrEmpty()) {
                        val tambahan = ModelTambahan(id, nama, harga)
                        adapter.addSelectedItem(tambahan)
                        recyclerView.scrollToPosition(adapter.itemCount - 1)
                    } else {
                        Toast.makeText(this, "Data tambahan tidak valid", Toast.LENGTH_SHORT).show()
                    }
                }
            }
        }
    }
}
