package com.cahayaaya.laundry

import android.os.Bundle
import android.view.View
import android.widget.TextView
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.appcompat.widget.SearchView
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.cahayaaya.laundry.adapter.PilihPelangganAdapter
import com.cahayaaya.laundry.modeldata.ModelPelanggan
import com.google.firebase.database.DataSnapshot
import com.google.firebase.database.DatabaseError
import com.google.firebase.database.FirebaseDatabase
import com.google.firebase.database.ValueEventListener

class pilih_pelanggan_MainActivity2 : AppCompatActivity() {

    private lateinit var rvpilihPelanggan: RecyclerView
    private lateinit var tvKosong: TextView
    private lateinit var searchView: SearchView
    private lateinit var adapter: PilihPelangganAdapter
    private val pelangganList = mutableListOf<ModelPelanggan>()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_pilih_pelanggan_main2)

        tvKosong = findViewById(R.id.tvPilihPelangganKosong)
        rvpilihPelanggan = findViewById(R.id.rv_pilih_data_pelanggan)
        searchView = findViewById(R.id.searchViewPelanggan)

        rvpilihPelanggan.layoutManager = LinearLayoutManager(this)
        adapter = PilihPelangganAdapter(this, pelangganList, tvKosong)
        rvpilihPelanggan.adapter = adapter

        searchView.setOnQueryTextListener(object : SearchView.OnQueryTextListener {
            override fun onQueryTextSubmit(query: String?) = false
            override fun onQueryTextChange(newText: String?): Boolean {
                adapter.filter.filter(newText)
                return true
            }
        })

        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }

        getData()
    }

    private fun getData() {
        val db = FirebaseDatabase.getInstance()
        val myRef = db.getReference("pelanggan")

        myRef.limitToLast(100).addValueEventListener(object : ValueEventListener {
            override fun onDataChange(snapshot: DataSnapshot) {
                pelangganList.clear()
                if (snapshot.exists()) {
                    for (data in snapshot.children) {
                        val pelanggan = data.getValue(ModelPelanggan::class.java)
                        pelanggan?.let { pelangganList.add(it) }
                    }
                    val reversedList = pelangganList.reversed()
                    adapter.updateData(reversedList)
                    tvKosong.visibility = View.GONE
                } else {
                    tvKosong.visibility = View.VISIBLE
                }
            }

            override fun onCancelled(error: DatabaseError) {
                // Log error if needed
            }
        })
    }
}
