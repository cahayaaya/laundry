package com.cahayaaya.laundry.cabang

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
import com.cahayaaya.laundry.modeldata.ModelCabang
import com.cahayaaya.laundry.modeldata.ModelPegawai
import com.google.firebase.database.FirebaseDatabase

class TambahCabang : AppCompatActivity() {
    val  database = FirebaseDatabase.getInstance()
    val myRef = database.getReference("cabang")
    lateinit var tvTitle: TextView
    lateinit var etgaris1cabang: EditText
    lateinit var etgaris2cabang: EditText
    lateinit var etgaris3cabang: EditText
    lateinit var etgaris4cabang: EditText
    lateinit var btsimpancabang: Button
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_tambah_cabang)
        init()
        btsimpancabang.setOnClickListener {
            cekValidasi()
        }
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }
    }

    fun  init(){
        tvTitle = findViewById(R.id.tvTitleCabang)
        etgaris1cabang = findViewById(R.id.etgaris1cabang)
        etgaris2cabang = findViewById(R.id.etgaris2cabang)
        etgaris3cabang = findViewById(R.id.etgaris3cabang)
        etgaris4cabang = findViewById(R.id.etgaris4cabang)
        btsimpancabang = findViewById(R.id.btsimpancabang)
    }
    fun  cekValidasi() {
        val nama = etgaris1cabang.text.toString()
        val alamat = etgaris2cabang.text.toString()
        val telepon = etgaris3cabang.text.toString()
        val layanan = etgaris3cabang.text.toString()

        //validasi data
        if (nama.isEmpty()) {
            etgaris1cabang.error = this.getString(R.string.validasi_Cabang_Nama)
            Toast.makeText(
                this,
                this.getString(R.string.validasi_Cabang_Nama),
                Toast.LENGTH_SHORT
            ).show()
            etgaris1cabang.requestFocus()
            return
        }
        if (alamat.isEmpty()) {
            etgaris2cabang.error = this.getString(R.string.validasi_Cabang_Alamat)
            Toast.makeText(
                this,
                this.getString(R.string.validasi_Cabang_Alamat),
                Toast.LENGTH_SHORT
            ).show()
            etgaris2cabang.requestFocus()
            return
        }
        if (telepon.isEmpty()) {
            etgaris3cabang.error = this.getString(R.string.validasi_Cabang_Telepon)
            Toast.makeText(
                this,
                this.getString(R.string.validasi_Cabang_Telepon),
                Toast.LENGTH_SHORT
            ).show()
            etgaris3cabang.requestFocus()
            return
        }
        if (layanan.isEmpty()) {
            etgaris4cabang.error = this.getString(R.string.validasi_Cabang_Layanan)
            Toast.makeText(
                this,
                this.getString(R.string.validasi_Cabang_Layanan),
                Toast.LENGTH_SHORT
            ).show()
            etgaris4cabang.requestFocus()
            return
        }
        simpan()
    }

    fun simpan(){
        val  cabangBaru = myRef.push()
        val cabangId = cabangBaru.key
        val data = ModelCabang(
            cabangId.toString(),
            etgaris1cabang.text.toString(),
            etgaris2cabang.text.toString(),
            etgaris3cabang.text.toString(),
            etgaris4cabang.text.toString(),

            )
        cabangBaru.setValue(data)
            .addOnSuccessListener {
                Toast.makeText(this,this.getString(R.string.sukses_simpan_cabang), Toast.LENGTH_SHORT).show()
                finish()
            }
            .addOnFailureListener {
                Toast.makeText(this,this.getString(R.string.gagal_simpan_cabang), Toast.LENGTH_SHORT).show()
            }
    }
}