package com.cahayaaya.laundry.adapter

import android.content.Intent
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.cardview.widget.CardView
import androidx.recyclerview.widget.RecyclerView
import com.cahayaaya.laundry.modeldata.ModelPegawai
import com.cahayaaya.laundry.R
import com.cahayaaya.laundry.pegawai.TambahPegawai
import com.google.firebase.database.DatabaseReference
import android.content.Context

class DataPegawaiAdapter(
    private val listpegawai: ArrayList<ModelPegawai>
) : RecyclerView.Adapter<DataPegawaiAdapter.ViewHolder>() {
    lateinit var appContext: Context
    lateinit var databaseReference: DatabaseReference
    override fun onCreateViewHolder(
        parent: ViewGroup,
        viewType: Int
    ): ViewHolder {
        val view = LayoutInflater.from(parent.context)
            .inflate(R.layout.card_data_pegawai, parent, false)
        appContext = parent.context
        return ViewHolder(view)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val item = listpegawai[position]
        holder.tvID.text = item.tvCard_Pegawai_Id
        holder.tvNama.text = item.tvCard_Nama_Pegawai
        holder.tvAlamat.text = "Alamat : ${item.tvCard_Pegawai_Alamat}"
        holder.tvNoHP.text = "No HP : ${item.tvCard_Pegawai_noHP}"
        holder.tvcabang.text = "Cabang : ${item.tvCard_Pegawai_Cabang}"

        holder.cvCard.setOnClickListener {
        val intent = Intent(appContext, TambahPegawai::class.java)
        intent.putExtra("judul", "Edit Pegawai")
        intent.putExtra("idPegawai", item.tvCard_Pegawai_Id)
        intent.putExtra("namapegawai", item.tvCard_Nama_Pegawai)
        intent.putExtra("noHPPegawai", item.tvCard_Pegawai_noHP)
        intent.putExtra("alamatPegawai", item.tvCard_Pegawai_Alamat)
        intent.putExtra("idCabang", item.tvCard_Pegawai_Cabang)
        appContext.startActivity(intent)
    }

        holder.btCard_Pegawai_Hubungi.setOnClickListener {

        }
        holder.btCard_Pegawai_Lihat.setOnClickListener {

        }
        }


    override fun getItemCount(): Int {
        return listpegawai.size
    }

    class ViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        val cvCard = itemView.findViewById<View>(R.id.cvCard_Pegawai)
        val tvID: TextView = itemView.findViewById(R.id.tvCard_Pegawai_Id)
        val tvNama: TextView = itemView.findViewById(R.id.tvCard_Nama_Pegawai)
        val tvAlamat: TextView = itemView.findViewById(R.id.tvCard_Pegawai_Alamat)
        val tvNoHP: TextView = itemView.findViewById(R.id.tvCard_Pegawai_No)
        val tvcabang: TextView = itemView.findViewById(R.id.tvCard_Pegawai_Cabang)
        val btCard_Pegawai_Hubungi: TextView = itemView.findViewById(R.id.btCard_Pegawai_Hubungi)
        val btCard_Pegawai_Lihat: TextView = itemView.findViewById(R.id.btCard_Pegawai_Lihat)
    }
}