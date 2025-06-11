package com.cahayaaya.laundry.adapter

import android.app.AlertDialog
import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.cahayaaya.laundry.R
import com.cahayaaya.laundry.modeldata.ModelTambahan
import java.text.NumberFormat
import java.util.*

class KonfirmasiDataAdapter(
    private val context: Context,
    private val list: List<ModelTambahan>
) : RecyclerView.Adapter<KonfirmasiDataAdapter.ViewHolder>() {

    inner class ViewHolder(view: View) : RecyclerView.ViewHolder(view) {
        val tvId: TextView = view.findViewById(R.id.idKonfirmasiTam)
        val tvNama: TextView = view.findViewById(R.id.tvnamaKonfirmasiTam)
        val tvHarga: TextView = view.findViewById(R.id.tvhargaKonfirmasiTam)

        init {
            view.setOnClickListener {
                showPembayaranDialog()
            }
        }

        private fun showPembayaranDialog() {
            val dialogView = LayoutInflater.from(context).inflate(R.layout.card_pembayaran, null)

            val dialog = AlertDialog.Builder(context)
                .setView(dialogView)
                .create()

            dialog.show()

            // Tombol batal
            val tvBatal = dialogView.findViewById<TextView>(R.id.tvBatal)
            tvBatal.setOnClickListener {
                dialog.dismiss()
            }

            // Contoh tombol tunai
            val btnTunai = dialogView.findViewById<Button>(R.id.btnTunai)
            btnTunai.setOnClickListener {
                // Lakukan aksi pembayaran tunai di sini
                dialog.dismiss()
            }

            // Tambahkan logika untuk tombol lainnya jika diperlukan
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val view = LayoutInflater.from(parent.context)
            .inflate(R.layout.card_konfirmasi_datatambahan, parent, false)
        return ViewHolder(view)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val item = list[position]
        holder.tvId.text = "[${position + 1}]"
        holder.tvNama.text = item.namatambahan ?: "-"

        val harga = item.hargatambahan
            ?.replace(".", "")
            ?.replace(",", "")
            ?.trim()
            ?.toIntOrNull() ?: 0

        val formatter = NumberFormat.getCurrencyInstance(Locale("in", "ID"))
        holder.tvHarga.text = formatter.format(harga)
    }

    override fun getItemCount(): Int = list.size
}
