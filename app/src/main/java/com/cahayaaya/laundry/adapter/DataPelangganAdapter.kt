package com.cahayaaya.laundry.adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.cardview.widget.CardView
import androidx.recyclerview.widget.RecyclerView
import com.cahayaaya.laundry.modeldata.ModelPelanggan
import com.cahayaaya.laundry.R

class DataPelangganAdapter(
    private val listpelanggan: ArrayList<ModelPelanggan>
) : RecyclerView.Adapter<DataPelangganAdapter.ViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val view = LayoutInflater.from(parent.context)
            .inflate(R.layout.card_data_pelanggan, parent, false)
        return ViewHolder(view)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val item = listpelanggan[position]
        holder.tvID.text = item.idPelanggan
        holder.tvNama.text = item.namaPelanggan
        holder.tvAlamat.text = item.alamatPelanggan
        holder.tvNoHP.text = item.noHPPelanggan

        holder.btCard_Pelanggan_Hubungi.setOnClickListener {

        }
        holder.btCard_Pelanggan_Lihat.setOnClickListener {

        }
    }

    override fun getItemCount(): Int {
        return listpelanggan.size
    }

    class ViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        val tvID: TextView = itemView.findViewById(R.id.tvCard_Pelanggan_Id)
        val tvNama: TextView = itemView.findViewById(R.id.tvCard_Pelanggan_Nama)
        val tvAlamat: TextView = itemView.findViewById(R.id.tvCard_Pelanggan_Alamat)
        val tvNoHP: TextView = itemView.findViewById(R.id.tvCard_Pelanggan_No)
        val btCard_Pelanggan_Hubungi: TextView = itemView.findViewById(R.id.btCard_Pelanggan_Hubungi)
        val btCard_Pelanggan_Lihat: TextView = itemView.findViewById(R.id.btCard_Pelanggan_Lihat)
    }
}
