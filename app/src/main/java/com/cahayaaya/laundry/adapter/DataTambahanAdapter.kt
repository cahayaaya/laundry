package com.cahayaaya.laundry.adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.cahayaaya.laundry.R
import com.cahayaaya.laundry.modeldata.ModelTambahan

class DataTambahanAdapter(
    private val listtambahan: List<ModelTambahan>
) : RecyclerView.Adapter<DataTambahanAdapter.ViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val view = LayoutInflater.from(parent.context)
            .inflate(R.layout.card_data_tambahan, parent, false) // GANTI dengan layout yang benar
        return ViewHolder(view)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val item = listtambahan[position]
        holder.tvID.text = item.idtambahan
        holder.tvNama.text = item.namatambahan
        holder.tvHarga.text = item.hargatambahan// tambahkan jika field ini ada di model
    }

    override fun getItemCount(): Int = listtambahan.size

    class ViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        val tvID: TextView = itemView.findViewById(R.id.tvCardIdTambahan)
        val tvNama: TextView = itemView.findViewById(R.id.tvNamaTambahan)
        val tvHarga: TextView = itemView.findViewById(R.id.tvHargaTambahan)
        val tvCabang: TextView = itemView.findViewById(R.id.tvCabangTambahan)
    }
}
