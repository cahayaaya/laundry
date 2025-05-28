package com.cahayaaya.laundry.pegawai

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
import com.cahayaaya.laundry.adapter.DataPegawaiAdapter
import com.cahayaaya.laundry.adapter.DataPelangganAdapter
import com.cahayaaya.laundry.modeldata.ModelPegawai
import com.cahayaaya.laundry.modeldata.ModelPelanggan
import com.cahayaaya.laundry.pelanggan.TambahPelanggan
import com.google.android.material.floatingactionbutton.FloatingActionButton
import com.google.firebase.database.DataSnapshot
import com.google.firebase.database.DatabaseError
import com.google.firebase.database.FirebaseDatabase
import com.google.firebase.database.ValueEventListener

class DataPegawai : AppCompatActivity() {
    val database = FirebaseDatabase.getInstance()
    val myRef = database.getReference("pegawai")
    lateinit var rv_data_pegawai: RecyclerView
    lateinit var bt_data_pegawai_tambah: FloatingActionButton
    lateinit var pegawaiList: ArrayList<ModelPegawai>


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_data_pegawai)
        init()
        val layoutManager = LinearLayoutManager(this)
        layoutManager.reverseLayout = true
        layoutManager.stackFromEnd = true
        rv_data_pegawai.layoutManager=layoutManager
        rv_data_pegawai.setHasFixedSize(true)
        pegawaiList = arrayListOf<ModelPegawai>()
        getdata()

        val bt_data_pegawai_tambah : FloatingActionButton = findViewById(R.id.bt_data_pegawai_tambah)
        bt_data_pegawai_tambah.setOnClickListener {
            val intent = Intent(this, TambahPegawai::class.java)
            intent.putExtra("tvTitlePegawai", (this.getString(R.string.tvTitlePegawai)))
            intent.putExtra("tvCard_Pegawai_Id", "")
            intent.putExtra("tvCard_Nama_Pegawai", "")
            intent.putExtra("tvCard_Pegawai_Alamat", "")
            intent.putExtra("ttvCard_Pegawai_Cabang", "")
            intent.putExtra("tvCard_Pegawai_noHP", "")
            startActivity(intent)
        }


        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }
    }



    fun getdata() {
        val query = myRef.orderByChild("idPegawai").limitToLast(100)
        query.addValueEventListener(object : ValueEventListener {
            override fun  onDataChange(snapshot: DataSnapshot) {
                if (snapshot.exists()){
                    pegawaiList.clear()
                    for (dataSnapshot in snapshot.children){
                        val pegawai = dataSnapshot.getValue(ModelPegawai::class.java)
                        pegawaiList.add(pegawai!!)
                    }
                    val adapter = DataPegawaiAdapter(pegawaiList)
                    rv_data_pegawai.adapter = adapter
                    adapter.notifyDataSetChanged()
                }
            }
            override fun  onCancelled(error : DatabaseError) {
                Toast.makeText(this@DataPegawai, error.message, Toast.LENGTH_SHORT).show()
            }
        })
    }

    fun init() {
        rv_data_pegawai = findViewById(R.id.rv_data_pegawai)
        bt_data_pegawai_tambah = findViewById(R.id.bt_data_pegawai_tambah)
    }



}