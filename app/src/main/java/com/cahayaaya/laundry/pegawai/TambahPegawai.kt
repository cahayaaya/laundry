package com.cahayaaya.laundry.pegawai

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
import com.cahayaaya.laundry.modeldata.ModelPegawai
import com.cahayaaya.laundry.modeldata.ModelPelanggan
import com.google.firebase.database.FirebaseDatabase

class TambahPegawai : AppCompatActivity() {
    val  database = FirebaseDatabase.getInstance()
    val myRef = database.getReference("pegawai")
    lateinit var tvTitle: TextView
    lateinit var etgaris1: EditText
    lateinit var etgaris2: EditText
    lateinit var etgaris3: EditText
    lateinit var etgaris4: EditText
    lateinit var btsimpan: Button
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_tambah_pegawai)
        init()
        btsimpan.setOnClickListener {
            cekValidasi()
        }
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }
    }

    fun  init(){
        tvTitle = findViewById(R.id.tvTitle)
        etgaris1 = findViewById(R.id.etgaris1)
        etgaris2 = findViewById(R.id.etgaris2)
        etgaris3 = findViewById(R.id.etgaris3)
        etgaris4 = findViewById(R.id.etgaris4)
        btsimpan = findViewById(R.id.btsimpan)
    }
    fun  cekValidasi() {
        val nama = etgaris1.text.toString()
        val alamat = etgaris2.text.toString()
        val noHP = etgaris3.text.toString()
        val namacabang = etgaris4.text.toString()

        //validasi data
        if (nama.isEmpty()) {
            etgaris1.error = this.getString(R.string.validasi_Pelanggan_Nama)
            Toast.makeText(
                this,
                this.getString(R.string.validasi_Pelanggan_Nama),
                Toast.LENGTH_SHORT
            ).show()
            etgaris1.requestFocus()
            return
        }
        if (alamat.isEmpty()) {
            etgaris2.error = this.getString(R.string.validasi_Pelanggan_Alamat)
            Toast.makeText(
                this,
                this.getString(R.string.validasi_Pelanggan_Alamat),
                Toast.LENGTH_SHORT
            ).show()
            etgaris2.requestFocus()
            return
        }
        if (noHP.isEmpty()) {
            etgaris3.error = this.getString(R.string.validasi_Pelanggan_NoHP)
            Toast.makeText(
                this,
                this.getString(R.string.validasi_Pelanggan_NoHP),
                Toast.LENGTH_SHORT
            ).show()
            etgaris3.requestFocus()
            return
        }
        if (namacabang.isEmpty()) {
            etgaris4.error = this.getString(R.string.validasi_Pelanggan_NoHP)
            Toast.makeText(
                this,
                this.getString(R.string.validasi_Pelanggan_NoHP),
                Toast.LENGTH_SHORT
            ).show()
            etgaris4.requestFocus()
            return
        }
        simpan()
    }

    fun simpan(){
        val  pegawaiBaru = myRef.push()
        val pegawaiId = pegawaiBaru.key
        val data = ModelPegawai(
            pegawaiId.toString(),
            etgaris1.text.toString(),
            etgaris2.text.toString(),
            etgaris3.text.toString(),
            etgaris4.text.toString(),

        )
        pegawaiBaru.setValue(data)
            .addOnSuccessListener {
                Toast.makeText(this,this.getString(R.string.sukses_simpan_pelanggan), Toast.LENGTH_SHORT).show()
                finish()
            }
            .addOnFailureListener {
                Toast.makeText(this,this.getString(R.string.gagal_simpan_pelanggan), Toast.LENGTH_SHORT).show()
            }
    }
}