package com.ifs21048.dinopedia

import android.os.Parcelable
import kotlinx.parcelize.Parcelize

@Parcelize
data class Dino (
    var nama : String,
    var gambar : Int,
    var deskripsi : String,
    var kelompok : String,
    var karakteristik : String,
    var habitat: String,
    var makanan: String,
    var panjang: String,
) : Parcelable {
}