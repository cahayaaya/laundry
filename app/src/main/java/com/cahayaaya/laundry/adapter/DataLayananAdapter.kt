package com.cahayaaya.laundry.adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.cardview.widget.CardView
import androidx.recyclerview.widget.RecyclerView
import com.cahayaaya.laundry.modeldata.ModelPegawai
import com.cahayaaya.laundry.R
import com.cahayaaya.laundry.modeldata.ModelLayanan

class DataLayananAdapter(
    private val listlayanan: ArrayList<ModelLayanan>
) : RecyclerView.Adapter<DataLayananAdapter.ViewHolder>() {
    override fun onCreateViewHolder(
        parent: ViewGroup,
        viewType: Int
    ): ViewHolder {
        val view = LayoutInflater.from(parent.context)
            .inflate(R.layout.card_data_layanan, parent, false)
        return ViewHolder(view)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val item = listlayanan[position]
        holder.tvID.text = item.tvCard_Layanan_Id
        holder.tvNama.text = item.tvCard_Pelanggan_Layanan
        holder.tvAlamat.text = item.tvCard_Pelanggan_Harga
        holder.tvcabang.text = item.tvCard_Pelanggan_NamaCabang

        holder.btCard_Pegawai_Hubungi.setOnClickListener {

        }
        holder.btCard_Pegawai_Lihat.setOnClickListener {

        }
    }

    override fun getItemCount(): Int {
        return listlayanan.size
    }

    class ViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        val tvID: TextView = itemView.findViewById(R.id.tvCard_Layanan_Id)
        val tvNama: TextView = itemView.findViewById(R.id.tvCard_Pelanggan_Layanan)
        val tvAlamat: TextView = itemView.findViewById(R.id.tvCard_Pelanggan_Harga)
        val tvcabang: TextView = itemView.findViewById(R.id.tvCard_Pegawai_Cabang)
        val btCard_Pegawai_Hubungi: TextView = itemView.findViewById(R.id.btCard_Pegawai_Hubungi)
        val btCard_Pegawai_Lihat: TextView = itemView.findViewById(R.id.btCard_Pegawai_Lihat)
    }
}