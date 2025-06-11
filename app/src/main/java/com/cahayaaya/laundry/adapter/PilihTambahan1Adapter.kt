package com.cahayaaya.laundry.adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.cardview.widget.CardView
import androidx.recyclerview.widget.RecyclerView
import com.cahayaaya.laundry.R
import com.cahayaaya.laundry.modeldata.ModelTambahan

class PilihTambahan1Adapter(
    private var list: List<ModelTambahan>,
    private val tvKosong: TextView
) : RecyclerView.Adapter<PilihTambahan1Adapter.ViewHolder>() {

    private var listener: OnItemClickListener? = null

    interface OnItemClickListener {
        fun onItemClick(item: ModelTambahan)
    }

    fun setOnItemClickListener(listener: OnItemClickListener) {
        this.listener = listener
    }

    fun updateList(newList: List<ModelTambahan>) {
        list = newList
        notifyDataSetChanged()
        tvKosong.visibility = if (list.isEmpty()) View.VISIBLE else View.GONE
    }

    inner class ViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        val cvCard: CardView = itemView.findViewById(R.id.cvCard_PilihTambahan)
        val tvID: TextView = itemView.findViewById(R.id.tvCardIdPilihTambahan)
        val tvNama: TextView = itemView.findViewById(R.id.tvNamaPilihTambahan)
        val tvHarga: TextView = itemView.findViewById(R.id.tvHargaPilihTambahan)

        init {
            itemView.setOnClickListener {
                val position = adapterPosition
                if (position != RecyclerView.NO_POSITION) {
                    listener?.onItemClick(list[position])
                }
            }
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.card_pilih_tambahan, parent, false)
        return ViewHolder(view)
    }

    override fun getItemCount(): Int = list.size

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val item = list[position]
        val nomor = position + 1

        holder.tvID.text = "[$nomor]"
        holder.tvNama.text = item.namatambahan ?: "-"
        holder.tvHarga.text = item.hargatambahan ?: "-"
    }
}
