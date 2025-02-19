package com.cahayaaya.laundry.adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.cardview.widget.CardView
import androidx.recyclerview.widget.RecyclerView
import com.cahayaaya.laundry.modeldata.ModelPegawai
import com.cahayaaya.laundry.R

class DataPegawaiAdapter(
    private val listpegawai: ArrayList<ModelPegawai>
) : RecyclerView.Adapter<DataPegawaiAdapter.ViewHolder>() {
    override fun onCreateViewHolder(
        parent: ViewGroup,
        viewType: Int
    ): ViewHolder {
        val view = LayoutInflater.from(parent.context)
            .inflate(R.layout.card_data_pegawai, parent, false)
        return ViewHolder(view)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val item = listpegawai[position]
        holder.tvID.text = item.tvCard_Pegawai_Id
        holder.tvNama.text = item.tvCard_Nama_Pegawai
        holder.tvAlamat.text = item.tvCard_Pegawai_Alamat
        holder.tvNoHP.text = item.tvCard_Pegawai_noHP
        holder.tvterdaftar.text = item.tvCard_Pegawai_Terdaftar
        holder.tvcabang.text = item.tvCard_Pegawai_Cabang

        holder.btCard_Pegawai_Hubungi.setOnClickListener {

        }
        holder.btCard_Pegawai_Lihat.setOnClickListener {

        }
    }

    override fun getItemCount(): Int {
        return listpegawai.size
    }

    class ViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        val tvID: TextView = itemView.findViewById(R.id.tvCard_Pegawai_Id)
        val tvNama: TextView = itemView.findViewById(R.id.tvCard_Nama_Pegawai)
        val tvAlamat: TextView = itemView.findViewById(R.id.tvCard_Pegawai_Alamat)
        val tvNoHP: TextView = itemView.findViewById(R.id.tvCard_Pegawai_No)
        val tvterdaftar: TextView = itemView.findViewById(R.id.tvCard_Pegawai_Terdaftar)
        val tvcabang: TextView = itemView.findViewById(R.id.tvCard_Pegawai_Cabang)
        val btCard_Pegawai_Hubungi: TextView = itemView.findViewById(R.id.btCard_Pegawai_Hubungi)
        val btCard_Pegawai_Lihat: TextView = itemView.findViewById(R.id.btCard_Pegawai_Lihat)
    }
}