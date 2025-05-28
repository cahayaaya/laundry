package com.cahayaaya.laundry.tambahan

import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.TextView
import android.widget.Toast
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import com.cahayaaya.laundry.R

import com.cahayaaya.laundry.modeldata.ModelTambahan
import com.google.firebase.database.FirebaseDatabase

class TambahTambahan : AppCompatActivity() { // ✅ WARISI AppCompatActivity

    private val database = FirebaseDatabase.getInstance()
    private val myRef = database.getReference("tambahan")

    private lateinit var tvTitle: TextView
    private lateinit var etgaris1: EditText
    private lateinit var etgaris2: EditText
    private lateinit var etgaris3: EditText
    private lateinit var btsimpan: Button

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_tambah_tambahan) // GANTI layout yg benar

        init() // pastikan ID-nya cocok dengan isi XML

        btsimpan.setOnClickListener {
            cekValidasi()
        }
    }


    fun init(){
        tvTitle = findViewById(R.id.tvTitleTambahan)
        etgaris1 = findViewById(R.id.etgaris1tambahan)
        etgaris2 = findViewById(R.id.etgaris2tambahan)
        etgaris3 = findViewById(R.id.etgaris3tambahan)
        btsimpan = findViewById(R.id.btsimpantambahan)
    }

    private fun cekValidasi() {
        val nama = etgaris1.text.toString()
        val harga = etgaris2.text.toString()
        val cabang = etgaris3.text.toString()

        if (nama.isEmpty()) {
            etgaris1.error = getString(R.string.validasi_Layanan_Nama)
            etgaris1.requestFocus()
            return
        }
        if (harga.isEmpty()) {
            etgaris2.error = getString(R.string.validasi_Layanan_Harga)
            etgaris2.requestFocus()
            return
        }
        if (cabang.isEmpty()) {
            etgaris3.error = getString(R.string.validasi_Layanan_namacabang)
            etgaris3.requestFocus()
            return
        }

        simpan(nama, harga, cabang)
    }

    private fun simpan(nama: String, harga: String, cabang: String) {
        val tambahanBaru = myRef.push()
        val id = tambahanBaru.key ?: ""

        val data = ModelTambahan(
            idtambahan = id,
            namatambahan = nama,
            hargatambahan = harga,
            cabangtambahan = cabang // ← Tambahkan properti ini di model
        )

        tambahanBaru.setValue(data)
            .addOnSuccessListener {
                Toast.makeText(this, getString(R.string.sukses_simpan_pelanggan), Toast.LENGTH_SHORT).show()
                finish()
            }
            .addOnFailureListener {
                Toast.makeText(this, getString(R.string.gagal_simpan_pelanggan), Toast.LENGTH_SHORT).show()
            }
    }

}
