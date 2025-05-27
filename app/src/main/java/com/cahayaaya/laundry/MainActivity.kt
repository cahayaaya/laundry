package com.cahayaaya.laundry

import android.content.Intent
import android.os.Bundle
import android.widget.ImageView
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.cardview.widget.CardView
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import com.cahayaaya.laundry.cabang.DataCabang
import com.cahayaaya.laundry.layanan.DataLayanan
import com.cahayaaya.laundry.pegawai.DataPegawai
import com.cahayaaya.laundry.pelanggan.DataPelanggan
import com.cahayaaya.laundry.transaksi.transaksiMainActivity


class MainActivity : AppCompatActivity() {
    lateinit var pegawai : CardView
    lateinit var pelanggan: ImageView
    lateinit var layanan: ImageView
    lateinit var cabang: ImageView
    lateinit var transaksi: ImageView

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_main)

        pegawai=findViewById(R.id.cvlima)
        pelanggan=findViewById(R.id.customer)
        layanan=findViewById(R.id.mesincuci)
        cabang=findViewById(R.id.cabang)
        transaksi=findViewById(R.id.transaksi)

        pegawai.setOnClickListener{
            val intent = Intent(this,DataPegawai::class.java)
            startActivity(intent)
        }
        pelanggan.setOnClickListener{
            val intent = Intent(this,DataPelanggan::class.java)
            startActivity(intent)
        }
        layanan.setOnClickListener{
            val intent = Intent(this,DataLayanan::class.java)
            startActivity(intent)
        }
        cabang.setOnClickListener{
            val intent = Intent(this,DataCabang::class.java)
            startActivity(intent)
        }
        transaksi.setOnClickListener{
            val intent = Intent(this,transaksiMainActivity::class.java)
            startActivity(intent)
        }

        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }
    }
}