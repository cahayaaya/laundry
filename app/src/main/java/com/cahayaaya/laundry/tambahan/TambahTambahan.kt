package com.cahayaaya.laundry.tambahan

import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.TextView
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.cahayaaya.laundry.R
import com.cahayaaya.laundry.modeldata.ModelTambahan
import com.google.firebase.database.FirebaseDatabase

class TambahTambahan : AppCompatActivity() {

    // Referensi Firebase
    private val database = FirebaseDatabase.getInstance()
    private val myRef = database.getReference("tambahan")

    // View
    private lateinit var tvTitle: TextView
    private lateinit var etNama: EditText
    private lateinit var etHarga: EditText
    private lateinit var etCabang: EditText
    private lateinit var btnSimpan: Button

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_tambah_tambahan)

        initViews()

        btnSimpan.setOnClickListener {
            validateAndSave()
        }
    }

    private fun initViews() {
        tvTitle = findViewById(R.id.tvTitleTambahan)
        etNama = findViewById(R.id.etgaris1tambahan)
        etHarga = findViewById(R.id.etgaris2tambahan)
        etCabang = findViewById(R.id.etgaris3tambahan)
        btnSimpan = findViewById(R.id.btsimpantambahan)
    }

    private fun validateAndSave() {
        val nama = etNama.text.toString().trim()
        val harga = etHarga.text.toString().trim()
        val cabang = etCabang.text.toString().trim()

        if (nama.isEmpty()) {
            etNama.error = getString(R.string.validasi_Layanan_Nama)
            etNama.requestFocus()
            return
        }

        if (harga.isEmpty()) {
            etHarga.error = getString(R.string.validasi_Layanan_Harga)
            etHarga.requestFocus()
            return
        }

        if (cabang.isEmpty()) {
            etCabang.error = getString(R.string.validasi_Layanan_namacabang)
            etCabang.requestFocus()
            return
        }

        saveToFirebase(nama, harga, cabang)
    }

    private fun saveToFirebase(nama: String, harga: String, cabang: String) {
        val newEntry = myRef.push()
        val id = newEntry.key ?: ""

        val data = ModelTambahan(
            idtambahan = id,
            namatambahan = nama,
            hargatambahan = harga,
            cabangtambahan = cabang
        )

        newEntry.setValue(data)
            .addOnSuccessListener {
                Toast.makeText(this, getString(R.string.sukses_simpan_pelanggan), Toast.LENGTH_SHORT).show()
                finish()
            }
            .addOnFailureListener {
                Toast.makeText(this, getString(R.string.gagal_simpan_pelanggan), Toast.LENGTH_SHORT).show()
            }
    }
}
