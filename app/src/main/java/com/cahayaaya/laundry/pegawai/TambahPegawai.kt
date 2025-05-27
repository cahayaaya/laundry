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
    lateinit var tvCard_Pegawai_Id: TextView
    lateinit var tvCard_Nama_Pegawai: EditText
    lateinit var tvCard_Pegawai_Alamat: EditText
    lateinit var tvCard_Pegawai_noHP: EditText
    lateinit var tvCard_Pegawai_Cabang: EditText
    lateinit var btsimpanPegawai: Button

    val  idPegawai:String=""
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_tambah_pegawai)
        init()
        getData()
        btsimpanPegawai.setOnClickListener {
            cekValidasi()
        }

        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }
    }

    fun  init(){
        tvCard_Pegawai_Id = findViewById(R.id.tvTitlePegawai)
        tvCard_Nama_Pegawai = findViewById(R.id.etgaris1pegawai)
        tvCard_Pegawai_Alamat = findViewById(R.id.etgaris2pegawai)
        tvCard_Pegawai_noHP = findViewById(R.id.etgaris3pegawai)
        tvCard_Pegawai_Cabang = findViewById(R.id.etgaris4pegawai)
        btsimpanPegawai = findViewById(R.id.btsimpanPegawai)
    }
    fun  cekValidasi() {
        val nama = tvCard_Nama_Pegawai.text.toString()
        val alamat = tvCard_Pegawai_Alamat.text.toString()
        val noHP = tvCard_Pegawai_noHP.text.toString()
        val namacabang = tvCard_Pegawai_Cabang.text.toString()

        //validasi data
        if (nama.isEmpty()) {
            tvCard_Nama_Pegawai.error = this.getString(R.string.validasi_Pelanggan_Nama)
            Toast.makeText(
                this,
                this.getString(R.string.validasi_Pelanggan_Nama),
                Toast.LENGTH_SHORT
            ).show()
            tvCard_Nama_Pegawai.requestFocus()
            return
        }
        if (alamat.isEmpty()) {
            tvCard_Pegawai_Alamat.error = this.getString(R.string.validasi_Pelanggan_Alamat)
            Toast.makeText(
                this,
                this.getString(R.string.validasi_Pelanggan_Alamat),
                Toast.LENGTH_SHORT
            ).show()
            tvCard_Pegawai_Alamat.requestFocus()
            return
        }
        if (noHP.isEmpty()) {
            tvCard_Pegawai_noHP.error = this.getString(R.string.validasi_Pelanggan_NoHP)
            Toast.makeText(
                this,
                this.getString(R.string.validasi_Pelanggan_NoHP),
                Toast.LENGTH_SHORT
            ).show()
            tvCard_Pegawai_noHP.requestFocus()
            return
        }
        if (namacabang.isEmpty()) {
            tvCard_Pegawai_Cabang.error = this.getString(R.string.validasi_Pelanggan_NoHP)
            Toast.makeText(
                this,
                this.getString(R.string.validasi_Pelanggan_NoHP),
                Toast.LENGTH_SHORT
            ).show()
            tvCard_Pegawai_Cabang.requestFocus()
            return
        }
        if (btsimpanPegawai.text.equals("Simpan")){
        simpan()
    }else if (btsimpanPegawai.text.equals("Sunting")) {
            hidup()
            tvCard_Nama_Pegawai.requestFocus()
            btsimpanPegawai.text = "Perbarui"
        }else if (btsimpanPegawai.text.equals("Perbarui")){
            update()
        }
        }

    fun simpan(){
        val  pegawaiBaru = myRef.push()
        val pegawaiId = pegawaiBaru.key
        val data = ModelPegawai(
            pegawaiId.toString(),
            tvCard_Nama_Pegawai.text.toString(),
            tvCard_Pegawai_Alamat.text.toString(),
            tvCard_Pegawai_noHP.text.toString(),
            tvCard_Pegawai_Cabang.text.toString(),

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
    fun  getData(){
        tvCard_Pegawai_Id.text = intent.getStringExtra("idPegawai") ?: ""
        val judul = intent.getStringExtra("judul")
        val nama = intent.getStringExtra("namaPegawai")
        val alamat = intent.getStringExtra("alamatPegawai")
        val hp = intent.getStringExtra("noHPPegawai")
        val cabang = intent.getStringExtra("idCAbang")
        tvCard_Pegawai_Id.text = judul
        tvCard_Nama_Pegawai.setText(nama)
        tvCard_Pegawai_Alamat.setText(alamat)
        tvCard_Pegawai_noHP.setText(hp)
        tvCard_Pegawai_Cabang.setText(cabang)
        if (!tvCard_Pegawai_Id.text.equals(this.getString(R.string.card_pegawai_tambah))){
            if(judul.equals("Edit Pegawai")){
                mati()
                btsimpanPegawai.text="Sunting"
            }
        }else{
            hidup()
            tvCard_Nama_Pegawai.requestFocus()
            btsimpanPegawai.text="Simpan"
        }
    }
    fun  mati(){
        tvCard_Nama_Pegawai.isEnabled=false
        tvCard_Pegawai_Alamat.isEnabled=false
        tvCard_Pegawai_noHP.isEnabled=false
        tvCard_Pegawai_Cabang.isEnabled=false
    }
    fun  hidup(){
        tvCard_Nama_Pegawai.isEnabled=true
        tvCard_Pegawai_Alamat.isEnabled=true
        tvCard_Pegawai_noHP.isEnabled=true
        tvCard_Pegawai_Cabang.isEnabled=true
    }
    fun update(){

        val timestamp = System.currentTimeMillis()

        val pegawaiRef = database.getReference("Pegawai").child(idPegawai)
        val data = ModelPegawai(
            idPegawai,
            tvCard_Nama_Pegawai.text.toString(),
            tvCard_Pegawai_Alamat.text.toString(),
            tvCard_Pegawai_noHP.text.toString(),
            tvCard_Pegawai_Cabang.text.toString(),
            timestamp.toString()
        )
        // Buat Map untuk update data
        val updateData = mutableMapOf<String, Any>()
        updateData["namaPegawai"] = data.tvCard_Nama_Pegawai.toString()
        updateData["alamatPegawai"] = data.tvCard_Pegawai_Alamat.toString()
        updateData["noHPPegawai"] = data.tvCard_Pegawai_noHP.toString()
        updateData["idPegawai"] = data.tvCard_Pegawai_Id.toString()
        updateData["timestamp"] = data.timestamp.toString()

        pegawaiRef.updateChildren(updateData).addOnSuccessListener {
            Toast.makeText(this@TambahPegawai,"Data Pegawai Berhasil Diperbarui",Toast.LENGTH_SHORT)
            finish()
        }.addOnFailureListener {
            Toast.makeText(this@TambahPegawai, "Data Pegawai Gagal Diperbarui", Toast.LENGTH_SHORT)
            finish()
        }

    }
}