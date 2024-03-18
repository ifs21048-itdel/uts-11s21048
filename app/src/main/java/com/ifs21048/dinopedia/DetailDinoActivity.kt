//package com.ifs21048.dinopedia
//
//import android.os.Build
//import androidx.appcompat.app.AppCompatActivity
//import android.os.Bundle
//import android.view.MenuItem
//import com.ifs21048.dinopedia.databinding.ActivityDetailFamiliActivtityBinding
//
//class DetailDinoActivity : AppCompatActivity() {
//    private lateinit var binding: ActivityDetailFamiliActivtityBinding
//    private var dino: Dino? = null
//
//    override fun onCreate(savedInstanceState: Bundle?) {
//        super.onCreate(savedInstanceState)
//        binding = ActivityDetailFamiliActivtityBinding.inflate(layoutInflater)
//        setContentView(binding.root)
//
//        dino = if (Build.VERSION.SDK_INT >= 33) {
//            intent.getParcelableExtra(EXTRA_DINO, Famili::class.java)
//        } else {
//            @Suppress("DEPRECATION")
//            intent.getParcelableExtra(EXTRA_DINO)
//        }
//
//        supportActionBar?.setDisplayHomeAsUpEnabled(true)
//        if (dino != null) {
//            supportActionBar?.title = "Dinosaurus ${dino!!.dino}"
//            loadData(dino!!)
//        } else {
//            finish()
//        }
//    }
//
//    private fun loadData(dino: Dino) {
//        binding.ivDetailDinoIcon.setImageResource(dino.gambar)
//        binding.tvDetailFamili.text = dino.nama
//        binding.tvDetailDescription.text = dino.deskripsi
//        binding.tvDetailPeriode.text = dino.kelompok
//        binding.tvDetailKarakteristik.text = dino.karakteristik
//        binding.tvDetailHabitat.text = dino.habitat
//        binding.tvDetailMakanan.text = dino.makanan
//        binding.tvDetailPanjang.text = dino.panjang
//    }
//
//    override fun onOptionsItemSelected(item: MenuItem): Boolean {
//        when (item.itemId) {
//            android.R.id.home -> {
//                finish()
//                return true
//            }
//        }
//        return super.onOptionsItemSelected(item)
//    }
//
//    companion object {
//        const val EXTRA_DINO = "extra_dino"
//    }
//}