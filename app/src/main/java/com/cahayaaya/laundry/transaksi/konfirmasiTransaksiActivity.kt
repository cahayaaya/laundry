package com.cahayaaya.laundry.transaksi

import android.app.AlertDialog
import android.content.Intent
import android.os.Bundle
import android.view.LayoutInflater
import android.widget.Button
import android.widget.TextView
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.cahayaaya.laundry.R
import com.cahayaaya.laundry.adapter.KonfirmasiDataAdapter
import com.cahayaaya.laundry.modeldata.ModelTambahan
import java.text.NumberFormat
import java.text.SimpleDateFormat
import java.util.*

class konfirmasiTransaksiActivity : AppCompatActivity() {

    private lateinit var tvNama: TextView
    private lateinit var tvHP: TextView
    private lateinit var tvLayanan: TextView
    private lateinit var tvHarga: TextView
    private lateinit var tvTotal: TextView
    private lateinit var recyclerView: RecyclerView
    private lateinit var adapterKonfirmasi: KonfirmasiDataAdapter
    private lateinit var prosesBtn: Button

    private val listTambahan = mutableListOf<ModelTambahan>()
    private var hargaLayanan = 0
    private var totalBayar = 0

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_konfirmasidata)

        // View binding
        tvNama = findViewById(R.id.tvnmKonfirmasiTam)
        tvHP = findViewById(R.id.tvnoKonfirmasiTam)
        tvLayanan = findViewById(R.id.tvnmLayananKonfirm)
        tvHarga = findViewById(R.id.tvhargaLayananKonfirm)
        tvTotal = findViewById(R.id.TvTotalBayar)
        recyclerView = findViewById(R.id.rv_data_Tambahan)
        prosesBtn = findViewById(R.id.prosestransaksi)

        // Ambil data dari intent
        val nama = intent.getStringExtra("namaPelanggan") ?: "-"
        val hp = intent.getStringExtra("noHP") ?: "-"
        val layanan = intent.getStringExtra("namaLayanan") ?: "-"
        val harga = intent.getStringExtra("hargaLayanan") ?: "0"
        val tambahanList = intent.getParcelableArrayListExtra<ModelTambahan>("dataTambahan") ?: arrayListOf()

        // Tampilkan
        tvNama.text = nama
        tvHP.text = hp
        tvLayanan.text = layanan

        hargaLayanan = harga.replace("[^\\d]".toRegex(), "").toIntOrNull() ?: 0
        tvHarga.text = formatRupiah(hargaLayanan)

        listTambahan.addAll(tambahanList)

        // Set adapter
        adapterKonfirmasi = KonfirmasiDataAdapter(this, listTambahan)
        recyclerView.layoutManager = LinearLayoutManager(this)
        recyclerView.adapter = adapterKonfirmasi

        hitungTotalBayar()

        prosesBtn.setOnClickListener {
            showPembayaranDialog(nama, layanan)
        }
    }

    private fun hitungTotalBayar() {
        val totalTambahan = listTambahan.sumOf {
            it.hargatambahan?.replace("[^\\d]".toRegex(), "")?.toIntOrNull() ?: 0
        }
        totalBayar = hargaLayanan + totalTambahan
        tvTotal.text = formatRupiah(totalBayar)
    }

    private fun showPembayaranDialog(namaPelanggan: String?, namaLayanan: String?) {
        val view = LayoutInflater.from(this).inflate(R.layout.card_pembayaran, null)

        val dialog = AlertDialog.Builder(this)
            .setView(view)
            .setCancelable(true)
            .create()

        view.findViewById<Button>(R.id.btnBayarNanti).setOnClickListener {
            val intent = Intent(this, activity_bayarnanti::class.java)
            intent.putExtra("idTransaksi", "TRX-${System.currentTimeMillis()}")
            intent.putExtra("tanggal", getTodayDate())
            intent.putExtra("namaPelanggan", namaPelanggan)
            intent.putExtra("namaPegawai", "Admin")
            intent.putExtra("hargaLayanan", hargaLayanan)
            intent.putExtra("namaLayanan", namaLayanan)
            intent.putParcelableArrayListExtra("listTambahan", ArrayList(listTambahan))
            startActivity(intent)
            dialog.dismiss()
        }

        view.findViewById<Button>(R.id.btnTunai).setOnClickListener {
            Toast.makeText(this, "Tunai dipilih", Toast.LENGTH_SHORT).show()
            dialog.dismiss()
        }

        view.findViewById<Button>(R.id.btnQRis).setOnClickListener {
            Toast.makeText(this, "QRIS dipilih", Toast.LENGTH_SHORT).show()
            dialog.dismiss()
        }

        view.findViewById<Button>(R.id.btnDANA).setOnClickListener {
            Toast.makeText(this, "DANA dipilih", Toast.LENGTH_SHORT).show()
            dialog.dismiss()
        }

        view.findViewById<Button>(R.id.btnGoPay).setOnClickListener {
            Toast.makeText(this, "GoPay dipilih", Toast.LENGTH_SHORT).show()
            dialog.dismiss()
        }

        view.findViewById<Button>(R.id.btnOVO).setOnClickListener {
            Toast.makeText(this, "OVO dipilih", Toast.LENGTH_SHORT).show()
            dialog.dismiss()
        }

        view.findViewById<TextView>(R.id.tvBatal).setOnClickListener {
            dialog.dismiss()
        }

        dialog.show()
    }

    private fun formatRupiah(number: Int): String {
        return NumberFormat.getCurrencyInstance(Locale("in", "ID")).format(number)
    }

    private fun getTodayDate(): String {
        val formatter = SimpleDateFormat("yyyy-MM-dd HH:mm:ss", Locale.getDefault())
        return formatter.format(Date())
    }
}
