package com.cahayaaya.laundry.adapter

import android.app.Activity
import android.content.Intent
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.cardview.widget.CardView
import androidx.recyclerview.widget.RecyclerView
import com.cahayaaya.laundry.R
import com.cahayaaya.laundry.modeldata.ModelPelanggan
import com.cahayaaya.laundry.transaksi.transaksiMainActivity

class PilihPelangganAdapter (
    private val listpelanggan: ArrayList<ModelPelanggan>
) : RecyclerView.Adapter<PilihPelangganAdapter.ViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val view = LayoutInflater.from(parent.context)
            .inflate(R.layout.card_data_pelanggan, parent, false)
        return ViewHolder(view)
    }

    override fun onBindViewHolder(
        holder: ViewHolder,
        position: Int
    ) {
        val nomor = position + 1
        val item = listpelanggan[position]
        holder.tvID.text = "[{nomor}]"
        holder.tvNama.text = item.namaPelanggan
        holder.tvAlamat.text = "Alamat : {item.alamatPelanggan}"
        holder.tvNoHP.text = "No HP : {item.noHPPelanggan}"

        holder.cvCard.setOnClickListener {
            val intent = Intent()
            intent.putExtra("idPelanggan", item.idPelanggan)
            intent.putExtra("nama", item.namaPelanggan)
            intent.putExtra("noHP", item.noHPPelanggan)
            val context = holder.itemView.context as Activity
            context.setResult(Activity.RESULT_OK, intent)
            context.finish()


        }
    }

    override fun getItemCount(): Int {
        return listpelanggan.size
    }

    class ViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        val cvCard: CardView = itemView.findViewById(R.id.cvCard_Pelanggan)
        val tvID: TextView = itemView.findViewById(R.id.tvCard_Pelanggan_Id)
        val tvNama: TextView = itemView.findViewById(R.id.tvCard_Pelanggan_Nama)
        val tvAlamat: TextView = itemView.findViewById(R.id.tvCard_Pelanggan_Alamat)
        val tvNoHP: TextView = itemView.findViewById(R.id.tvCard_Pelanggan_No)
        val btCard_Pelanggan_Hubungi: TextView = itemView.findViewById(R.id.btCard_Pelanggan_Hubungi)
        val btCard_Pelanggan_Lihat: TextView = itemView.findViewById(R.id.btCard_Pelanggan_Lihat)
    }
}
