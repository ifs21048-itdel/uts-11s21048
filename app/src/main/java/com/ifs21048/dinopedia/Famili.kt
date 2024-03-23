package com.ifs21048.dinopedia

import android.os.Parcelable
import kotlinx.parcelize.Parcelize

@Parcelize
data class Famili (
    var famili : String,
    var gambar : Int,
    var deskripsi : String,
    var periode : String,
    var karakteristik : String,
    var habitat: String,
    var perilaku: String,
    var klasifikasi: String,
    val startDino: Int,
    val endDino : Int
) : Parcelable
    
