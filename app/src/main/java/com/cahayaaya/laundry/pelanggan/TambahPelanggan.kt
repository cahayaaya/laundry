package com.cahayaaya.laundry.pelanggan

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
import com.cahayaaya.laundry.modeldata.ModelPelanggan
import com.google.firebase.database.FirebaseDatabase

class TambahPelanggan : AppCompatActivity() {
    val  database = FirebaseDatabase.getInstance()
    val myRef = database.getReference("pelanggan")
    lateinit var tvJudul: TextView
    lateinit var etNama:EditText
    lateinit var etAlamat: EditText
    lateinit var etNoHP: EditText
    lateinit var etCabang: EditText
    lateinit var btSimpan:Button
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_tambah_pelanggan)
        init()
        btSimpan.setOnClickListener {
            cekValidasi()
        }
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }
    }

    fun  init(){
        tvJudul = findViewById(R.id.tvTitlePelanggan)
        etNama = findViewById(R.id.etgaris1pelanggan)
        etAlamat = findViewById(R.id.etgaris2pelanggan)
        etNoHP = findViewById(R.id.etgaris3pelanggan)
        btSimpan = findViewById(R.id.btsimpanpelanggan)
        etCabang = findViewById(R.id.etgaris4pelanggan)
    }
    fun  cekValidasi() {
        val nama = etNama.text.toString()
        val alamat = etAlamat.text.toString()
        val noHP = etNoHP.text.toString()
        val cabang = etCabang.text.toString()

        //validasi data
        if (nama.isEmpty()) {
            etNama.error = this.getString(R.string.validasi_Pelanggan_Nama)
            Toast.makeText(
                this,
                this.getString(R.string.validasi_Pelanggan_Nama),
                Toast.LENGTH_SHORT
            ).show()
            etNama.requestFocus()
            return
        }
        if (alamat.isEmpty()) {
            etAlamat.error = this.getString(R.string.validasi_Pelanggan_Alamat)
            Toast.makeText(
                this,
                this.getString(R.string.validasi_Pelanggan_Alamat),
                Toast.LENGTH_SHORT
            ).show()
            etAlamat.requestFocus()
            return
        }
        if (noHP.isEmpty()) {
            etNoHP.error = this.getString(R.string.validasi_Pelanggan_NoHP)
            Toast.makeText(
                this,
                this.getString(R.string.validasi_Pelanggan_NoHP),
                Toast.LENGTH_SHORT
            ).show()
            etNoHP.requestFocus()
            return
        }

        if (cabang.isEmpty()) {
            etCabang.error = this.getString(R.string.validasi_Pelanggan_Cabang)
            Toast.makeText(
                this,
                this.getString(R.string.validasi_Pelanggan_Cabang),
                Toast.LENGTH_SHORT
            ).show()
            etCabang.requestFocus()
            return
        }
        simpan()
    }

    fun simpan(){
        val  pelangganBaru = myRef.push()
        val pelangganId = pelangganBaru.key
        val  data = ModelPelanggan(
            pelangganId.toString(),
            etNama.text.toString(),
            etAlamat.text.toString(),
            etNoHP.text.toString(),
        )
        pelangganBaru.setValue(data)
            .addOnSuccessListener {
                Toast.makeText(this,this.getString(R.string.sukses_simpan_pelanggan), Toast.LENGTH_SHORT).show()
                finish()
            }
            .addOnFailureListener {
                Toast.makeText(this,this.getString(R.string.gagal_simpan_pelanggan), Toast.LENGTH_SHORT).show()
                finish()
            }
    }
    }
