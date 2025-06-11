package com.cahayaaya.laundry.transaksi

import android.app.Activity
import android.content.Intent
import android.os.Bundle
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import androidx.appcompat.widget.SearchView
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.cahayaaya.laundry.R
import com.cahayaaya.laundry.adapter.PilihTambahan1Adapter
import com.cahayaaya.laundry.modeldata.ModelTambahan
import com.google.firebase.database.*

class activity_pilih_tambahan : AppCompatActivity() {

    private lateinit var searchView: SearchView
    private lateinit var recyclerView: RecyclerView
    private lateinit var tvKosong: TextView
    private lateinit var dbRef: DatabaseReference
    private lateinit var tambahList: ArrayList<ModelTambahan>
    private lateinit var adapterTambah: PilihTambahan1Adapter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_pilih_tambahan)

        searchView = findViewById(R.id.searchViewPilih)
        recyclerView = findViewById(R.id.rv_pilih_data_pelanggan)
        tvKosong = findViewById(R.id.tvPilihTambahanKosong)

        recyclerView.layoutManager = LinearLayoutManager(this)
        recyclerView.setHasFixedSize(true)

        tambahList = arrayListOf()
        adapterTambah = PilihTambahan1Adapter(tambahList, tvKosong)
        recyclerView.adapter = adapterTambah

        adapterTambah.setOnItemClickListener(object : PilihTambahan1Adapter.OnItemClickListener {
            override fun onItemClick(item: ModelTambahan) {
                val intent = Intent()
                intent.putExtra("idLayanan", item.idtambahan)
                intent.putExtra("nama", item.namatambahan)
                intent.putExtra("harga", item.hargatambahan)
                setResult(Activity.RESULT_OK, intent)
                finish()
            }
        })

        getDataTambah()

        searchView.setOnQueryTextListener(object : SearchView.OnQueryTextListener {
            override fun onQueryTextSubmit(query: String?): Boolean = true

            override fun onQueryTextChange(newText: String?): Boolean {
                filterData(newText ?: "")
                return true
            }
        })
    }

    private fun getDataTambah() {
        dbRef = FirebaseDatabase.getInstance().getReference("tambahan")

        dbRef.addValueEventListener(object : ValueEventListener {
            override fun onDataChange(snapshot: DataSnapshot) {
                tambahList.clear()
                if (snapshot.exists()) {
                    for (data in snapshot.children) {
                        val item = data.getValue(ModelTambahan::class.java)
                        item?.let { tambahList.add(it) }
                    }
                    adapterTambah.updateList(tambahList)
                    tvKosong.visibility = if (tambahList.isEmpty()) TextView.VISIBLE else TextView.GONE
                } else {
                    tvKosong.visibility = TextView.VISIBLE
                }
            }

            override fun onCancelled(error: DatabaseError) {
                // Bisa tambahkan logging atau toast error jika perlu
            }
        })
    }

    private fun filterData(query: String) {
        val filteredList = tambahList.filter {
            it.namatambahan?.contains(query, ignoreCase = true) == true ||
                    it.hargatambahan?.contains(query, ignoreCase = true) == true ||
                    it.cabangtambahan?.contains(query, ignoreCase = true) == true
        }
        adapterTambah.updateList(filteredList)
    }
}
