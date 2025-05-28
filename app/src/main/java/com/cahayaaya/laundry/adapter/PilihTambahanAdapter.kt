package com.cahayaaya.laundry.adapter

import ModelLayanan
import android.app.Activity
import android.content.Context
import android.content.Intent
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Filter
import android.widget.Filterable
import android.widget.TextView
import androidx.cardview.widget.CardView
import androidx.recyclerview.widget.RecyclerView
import com.cahayaaya.laundry.R

class PilihTambahanAdapter(
    private val context: Context,
    private val listTambahan: MutableList<ModelLayanan>,
    private val tvKosong: TextView
) : RecyclerView.Adapter<PilihTambahanAdapter.ViewHolder>(), Filterable {

    private var filteredList: MutableList<ModelLayanan> = listTambahan.toMutableList()

    inner class ViewHolder(view: View) : RecyclerView.ViewHolder(view) {
        val cvCard: CardView = view.findViewById(R.id.cvCard_LayananTambahan)
        val tvID: TextView = view.findViewById(R.id.tvCardIdLayananTambahan)
        val tvNama: TextView = view.findViewById(R.id.tvNamaLayananTambahan)
        val tvHarga: TextView = view.findViewById(R.id.tvHargaLayananTambahan)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val view = LayoutInflater.from(parent.context)
            .inflate(R.layout.card_data_layanan_tambahan, parent, false)
        return ViewHolder(view)
    }

    override fun getItemCount(): Int = filteredList.size

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val nomor = position + 1
        val item = filteredList[position]

        holder.tvID.text = "[$nomor]"
        holder.tvNama.text = item.tvCard_Pelanggan_Layanan ?: "-"
        holder.tvHarga.text = item.tvCard_Pelanggan_Harga ?: "-"

        holder.cvCard.setOnClickListener {
            val intent = Intent().apply {
                putExtra("idLayanan", item.tvCard_Layanan_Id ?: "")
                putExtra("nama", item.tvCard_Pelanggan_Layanan ?: "")
                putExtra("harga", item.tvCard_Pelanggan_Harga ?: "")
            }
            if (context is Activity) {
                context.setResult(Activity.RESULT_OK, intent)
                context.finish()
            }
        }
    }

    override fun getFilter(): Filter {
        return object : Filter() {
            override fun performFiltering(constraint: CharSequence?): FilterResults {
                val keyword = constraint?.toString()?.trim()?.lowercase() ?: ""
                val resultList = if (keyword.isEmpty()) {
                    listLayanan
                } else {
                    listLayanan.filter {
                        it.tvCard_Pelanggan_Layanan?.lowercase()?.contains(keyword) == true
                    }
                }

                return FilterResults().apply {
                    values = resultList
                }
            }

            override fun publishResults(constraint: CharSequence?, results: FilterResults?) {
                filteredList = (results?.values as? List<ModelLayanan>)?.toMutableList() ?: mutableListOf()
                notifyDataSetChanged()
                tvKosong.visibility = if (filteredList.isEmpty()) View.VISIBLE else View.GONE
            }
        }
    }

    fun updateData(newList: List<ModelLayanan>) {
        listLayanan.clear()
        listLayanan.addAll(newList)
        filteredList = newList.toMutableList()
        notifyDataSetChanged()
    }
}
