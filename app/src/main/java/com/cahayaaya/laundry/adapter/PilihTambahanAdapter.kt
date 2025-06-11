package com.cahayaaya.laundry.adapter

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.cahayaaya.laundry.R
import com.cahayaaya.laundry.modeldata.ModelTambahan

class PilihTambahanAdapter(
    private val context: Context,
    private val selectedItems: MutableList<ModelTambahan>,
    private val kosongView: TextView
) : RecyclerView.Adapter<PilihTambahanAdapter.ViewHolder>() {

    inner class ViewHolder(view: View) : RecyclerView.ViewHolder(view) {
        val tvNama: TextView = view.findViewById(R.id.tvNamaPilihTambahan1)
        val tvHarga: TextView = view.findViewById(R.id.tvHargaPilihTambahan1)
        val btnDelete: ImageView = view.findViewById(R.id.hapuslayanan)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val view = LayoutInflater.from(context)
            .inflate(R.layout.card_pilih_tambahan1, parent, false)
        return ViewHolder(view)
    }

    override fun getItemCount(): Int = selectedItems.size

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val item = selectedItems[position]
        holder.tvNama.text = item.namatambahan
        holder.tvHarga.text = item.hargatambahan

        holder.btnDelete.setOnClickListener {
            selectedItems.removeAt(position)
            notifyItemRemoved(position)
            notifyItemRangeChanged(position, selectedItems.size)
            kosongView.visibility = if (selectedItems.isEmpty()) View.VISIBLE else View.GONE
        }
    }

    fun addSelectedItem(item: ModelTambahan) {
        if (selectedItems.none { it.idtambahan == item.idtambahan }) {
            selectedItems.add(item)
            notifyItemInserted(selectedItems.size - 1)
            kosongView.visibility = View.GONE
        }
    }

    fun getSelectedItems(): List<ModelTambahan> = selectedItems
}
