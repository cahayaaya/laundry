package com.cahayaaya.laundry

import android.content.Intent
import android.os.Bundle
import android.view.View
import android.widget.TextView
import android.widget.Toast
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.cahayaaya.laundry.adapter.PilihPelangganAdapter
import com.cahayaaya.laundry.modeldata.ModelPelanggan
import com.google.firebase.database.*

class pilih_pelanggan_MainActivity2 : AppCompatActivity() {

    private lateinit var sharedPref: android.content.SharedPreferences
    private lateinit var rv_pilih_data_layanan :RecyclerView
    private lateinit var tvKosong: TextView
    private lateinit var myRef: DatabaseReference

    private var pelangganList = arrayListOf<ModelPelanggan>()
    private var idcabang: String =""

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_pilih_pelanggan_main2)
        myRef = FirebaseDatabase.getInstance().getReference("pelanggan")
        tvKosong = findViewById(R.id.tvPilihPelangganKosong)


        sharedPref = getSharedPreferences("user_data", MODE_PRIVATE)
        idcabang = sharedPref.getString("idcabang", null).toString()

        rv_pilih_data_layanan = findViewById(R.id.rv_pilih_data_layanan)

        val layoutManager = LinearLayoutManager(this)
        layoutManager.reverseLayout = true
        layoutManager.stackFromEnd = true
        rv_pilih_data_layanan.layoutManager = layoutManager
        rv_pilih_data_layanan.setHasFixedSize(true)

        getData()

        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }
    }


    private fun getData() {
        val myRef = FirebaseDatabase.getInstance().getReference("pelanggan")
        val query = myRef.orderByChild("idCabang").equalTo(idcabang).limitToLast(100)

        query.addValueEventListener(object : ValueEventListener {
            override fun onDataChange(snapshot: DataSnapshot) {
                if (snapshot.exists()) {
                    pelangganList.clear()
                    for (dataSnapshot in snapshot.children) {
                        val pelanggan = dataSnapshot.getValue(ModelPelanggan::class.java)
                        if (pelanggan != null) {
                            pelangganList.add(pelanggan)
                        }
                    }
                    rv_pilih_data_layanan.adapter = PilihPelangganAdapter(pelangganList)
                    tvKosong.visibility = View.GONE
                } else {
                    tvKosong.visibility = View.VISIBLE
                }
            }

            override fun onCancelled(error: DatabaseError) {
                Toast.makeText(this@pilih_pelanggan_MainActivity2, "Gagal ambil data: ${error.message}", Toast.LENGTH_SHORT).show()
            }
        })
    }
}
