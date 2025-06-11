package com.cahayaaya.laundry.transaksi

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.cahayaaya.laundry.R
import com.cahayaaya.laundry.modeldata.ModelTambahan

class AdapterKonfirmasiData(private val list: List<ModelTambahan>) :
    RecyclerView.Adapter<AdapterKonfirmasiData.ViewHolder>() {

    class ViewHolder(view: View) : RecyclerView.ViewHolder(view) {
        val namaTambahan: TextView = view.findViewById(R.id.tvNamaTambahan)
        val hargaTambahan: TextView = view.findViewById(R.id.tvHargaTambahan)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val view = LayoutInflater.from(parent.context)
            .inflate(R.layout.card_konfirmasi_datatambahan, parent, false)
        return ViewHolder(view)
    }

    override fun getItemCount(): Int = list.size

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val item = list[position]
        holder.namaTambahan.text = item.namatambahan
        holder.hargaTambahan.text = "Rp ${item.hargatambahan}"
    }
}
