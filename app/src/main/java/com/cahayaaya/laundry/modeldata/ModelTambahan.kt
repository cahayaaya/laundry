package com.cahayaaya.laundry.modeldata

import android.os.Parcelable
import kotlinx.parcelize.Parcelize

@Parcelize
data class ModelTambahan(
    var idtambahan: String? = null,
    var namatambahan: String? = null,
    var hargatambahan: String? = null,
    var cabangtambahan: String? = null
) : Parcelable
