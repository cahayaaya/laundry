package com.cahayaaya.laundry.adapter

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
import com.cahayaaya.laundry.modeldata.ModelPelanggan

class PilihPelangganAdapter(
    private val context: Context,
    private var listPelanggan: List<ModelPelanggan>,
    private val tvKosong: TextView
) : RecyclerView.Adapter<PilihPelangganAdapter.ViewHolder>(), Filterable {

    private var filteredList: MutableList<ModelPelanggan> = listPelanggan.toMutableList()

    class ViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        val cvCard: CardView = itemView.findViewById(R.id.cvCard_Pelanggan)
        val tvID: TextView = itemView.findViewById(R.id.tvCard_Pelanggan_Id)
        val tvNama: TextView = itemView.findViewById(R.id.tvCard_Pelanggan_Nama)
        val tvAlamat: TextView = itemView.findViewById(R.id.tvCard_Pelanggan_Alamat)
        val tvNoHp: TextView = itemView.findViewById(R.id.tvCard_Pelanggan_No)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val view = LayoutInflater.from(parent.context)
            .inflate(R.layout.card_data_pilih_pelanggan, parent, false)
        return ViewHolder(view)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val nomor = position + 1
        val item = filteredList[position]

        holder.tvID.text = "[$nomor]"
        holder.tvNama.text = item.namaPelanggan
        holder.tvAlamat.text = "Alamat: ${item.alamatPelanggan}"
        holder.tvNoHp.text = "No HP: ${item.noHPPelanggan}"

        holder.cvCard.setOnClickListener {
            val intent = Intent().apply {
                putExtra("idPelanggan", item.idPelanggan)
                putExtra("nama", item.namaPelanggan)
                putExtra("noHP", item.noHPPelanggan)
            }
            if (context is Activity) {
                (context as Activity).setResult(Activity.RESULT_OK, intent)
                (context as Activity).finish()
            }
        }
    }

    override fun getItemCount(): Int = filteredList.size

    // ✅ Tambahkan fungsi ini agar adapter bisa menerima data dari Firebase
    fun updateData(newData: List<ModelPelanggan>) {
        listPelanggan = newData
        filteredList = newData.toMutableList()
        notifyDataSetChanged()
        tvKosong.visibility = if (filteredList.isEmpty()) View.VISIBLE else View.GONE
    }

    override fun getFilter(): Filter {
        return object : Filter() {
            override fun performFiltering(constraint: CharSequence?): FilterResults {
                val keyword = constraint?.toString()?.lowercase()?.trim() ?: ""
                val resultList = if (keyword.isEmpty()) {
                    listPelanggan
                } else {
                    listPelanggan.filter {
                        it.namaPelanggan?.lowercase()?.contains(keyword) == true ||
                                it.noHPPelanggan?.contains(keyword) == true
                    }
                }

                return FilterResults().apply { values = resultList }
            }

            override fun publishResults(constraint: CharSequence?, results: FilterResults?) {
                filteredList = (results?.values as? List<*>)?.filterIsInstance<ModelPelanggan>()?.toMutableList()
                    ?: mutableListOf()
                notifyDataSetChanged()
                tvKosong.visibility = if (filteredList.isEmpty()) View.VISIBLE else View.GONE
            }
        }
    }
}
