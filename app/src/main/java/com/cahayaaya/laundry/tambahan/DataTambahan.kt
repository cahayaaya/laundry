package com.cahayaaya.laundry.tambahan

import android.content.Intent
import android.os.Bundle
import android.widget.Toast
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.cahayaaya.laundry.R
import com.cahayaaya.laundry.adapter.DataTambahanAdapter // ✅ PENTING: ganti ke adapter yang sesuai
import com.cahayaaya.laundry.modeldata.ModelTambahan
import com.google.android.material.floatingactionbutton.FloatingActionButton
import com.google.firebase.database.*

class DataTambahan : AppCompatActivity() {

    private lateinit var rv_data_tambahan: RecyclerView
    private lateinit var bt_data_layanan_tambah: FloatingActionButton
    private val tambahanList = ArrayList<ModelTambahan>()

    private val myRef = FirebaseDatabase.getInstance().getReference("tambahan")

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_data_layanan) // Pastikan layout ini sesuai

        init()

        rv_data_tambahan.layoutManager = LinearLayoutManager(this).apply {
            reverseLayout = true
            stackFromEnd = true
        }
        rv_data_tambahan.setHasFixedSize(true)

        getdata()

        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }
    }

    private fun init() {
        rv_data_tambahan = findViewById(R.id.rv_data_layanan)
        bt_data_layanan_tambah = findViewById(R.id.bt_data_layanan_tambah)
        bt_data_layanan_tambah.setOnClickListener {
            val intent = Intent(this, TambahTambahan::class.java) // ✅ Ganti ke TambahTambahan
            startActivity(intent)
        }
    }

    private fun getdata() {
        myRef.orderByChild("idtambahan").limitToLast(100)
            .addValueEventListener(object : ValueEventListener {
                override fun onDataChange(snapshot: DataSnapshot) {
                    tambahanList.clear()
                    if (snapshot.exists()) {
                        for (dataSnapshot in snapshot.children) {
                            val layanan = dataSnapshot.getValue(ModelTambahan::class.java)
                            layanan?.let { tambahanList.add(it) }
                        }
                        val adapter = DataTambahanAdapter(tambahanList)
                        rv_data_tambahan.adapter = adapter
                    } else {
                        Toast.makeText(this@DataTambahan, "Data kosong", Toast.LENGTH_SHORT).show()
                    }
                }

                override fun onCancelled(error: DatabaseError) {
                    Toast.makeText(this@DataTambahan, error.message, Toast.LENGTH_SHORT).show()
                }
            })
    }
}
