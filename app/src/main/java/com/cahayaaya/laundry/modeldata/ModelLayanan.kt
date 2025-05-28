data class ModelLayanan(
    val tvCard_Pegawai_Id: String? = null,
    val tvCard_Nama_Pegawai: String? = null,
    val tvCard_Pegawai_Alamat: String? = null
) {
    val tvCard_Layanan_Id: String? get() = tvCard_Pegawai_Id
    val tvCard_Pelanggan_Layanan: String? get() = tvCard_Nama_Pegawai
    val tvCard_Pelanggan_Harga: String? get() = tvCard_Pegawai_Alamat
}
