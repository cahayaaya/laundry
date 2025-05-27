package com.cahayaaya.laundry.adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.cardview.widget.CardView
import androidx.recyclerview.widget.RecyclerView
import com.cahayaaya.laundry.modeldata.ModelPegawai
import com.cahayaaya.laundry.R
import com.cahayaaya.laundry.modeldata.ModelCabang
import com.cahayaaya.laundry.modeldata.ModelLayanan

class DataCabangAdapter(
    private val listcabang: ArrayList<ModelCabang>
) : RecyclerView.Adapter<DataCabangAdapter.ViewHolder>() {
    override fun onCreateViewHolder(
        parent: ViewGroup,
        viewType: Int
    ): ViewHolder {
        val view = LayoutInflater.from(parent.context)
            .inflate(R.layout.card_data_cabang, parent, false)
        return ViewHolder(view)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val item = listcabang[position]
        holder.tvID.text = item.idcabang
        holder.tvNama.text = item.namacabang
        holder.tvAlamat.text = item.alamatcabang
        holder.tvNo.text = item.nocabang
        holder.tvLayanan.text = item.layanancabang


    }

    override fun getItemCount(): Int {
        return listcabang.size
    }

    class ViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        val tvID: TextView = itemView.findViewById(R.id.tvCard_cabang_Id)
        val tvNama: TextView = itemView.findViewById(R.id.tvCard_Cabang_Nama)
        val tvAlamat: TextView = itemView.findViewById(R.id.tvCard_Cabang_Alamat)
        val tvNo: TextView = itemView.findViewById(R.id.tvCard_Cabang_No)
        val tvLayanan: TextView = itemView.findViewById(R.id.tvCardlayanan)
        val btHubungiCabang: TextView = itemView.findViewById(R.id.btCard_Cabang_Hubungi)
        val btLihatCabang: TextView = itemView.findViewById(R.id.btCard_Cabang_Lihat)
    }
}