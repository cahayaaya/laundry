package com.cahayaaya.laundry

import android.content.Intent
import android.os.Bundle
import android.widget.ImageView
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.cardview.widget.CardView
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import com.cahayaaya.laundry.pegawai.DataPegawai
import com.cahayaaya.laundry.pelanggan.DataPelanggan



class MainActivity : AppCompatActivity() {
    lateinit var pegawai : CardView
    lateinit var pelanggan: ImageView

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_main)

        pegawai=findViewById(R.id.cvlima)
        pelanggan=findViewById(R.id.customer)

        pegawai.setOnClickListener{
            val intent = Intent(this,DataPegawai::class.java)
            startActivity(intent)
        }
        pelanggan.setOnClickListener{
            val intent = Intent(this,DataPelanggan::class.java)
            startActivity(intent)
        }

        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }
    }
}