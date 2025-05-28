package com.cahayaaya.laundry

import ModelLayanan
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
import com.cahayaaya.laundry.adapter.PilihLayananAdapter
import com.google.firebase.database.*

class pilihlayanan_activity : AppCompatActivity() {

    private lateinit var rvpilihLayanan: RecyclerView
    private lateinit var tvKosong: TextView
    private lateinit var searchView: SearchView
    private lateinit var adapter: PilihLayananAdapter
    private val layananList = mutableListOf<ModelLayanan>()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_pilihlayanan_main2)

        tvKosong = findViewById(R.id.tvPilihPelangganKosong)
        rvpilihLayanan = findViewById(R.id.rv_pilih_data_layanan)
        searchView = findViewById(R.id.searchViewLayananPilih)

        rvpilihLayanan.layoutManager = LinearLayoutManager(this)
        adapter = PilihLayananAdapter(this, layananList, tvKosong)
        rvpilihLayanan.adapter = adapter

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
        val myRef = db.getReference("layanan")

        myRef.limitToLast(100).addValueEventListener(object : ValueEventListener {
            override fun onDataChange(snapshot: DataSnapshot) {
                layananList.clear()
                if (snapshot.exists()) {
                    for (data in snapshot.children) {
                        val layanan = data.getValue(ModelLayanan::class.java)
                        layanan?.let { layananList.add(it) }
                    }
                    val reversedList = layananList.reversed()
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
