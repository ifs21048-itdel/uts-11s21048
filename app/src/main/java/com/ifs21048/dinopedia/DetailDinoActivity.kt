package com.ifs21048.dinopedia

import android.os.Build
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.MenuItem
import com.ifs21048.dinopedia.databinding.ActivityDetailDinoBinding

class DetailDinoActivity : AppCompatActivity() {
    private lateinit var binding: ActivityDetailDinoBinding
    private var dino: Dino? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityDetailDinoBinding.inflate(layoutInflater)
        setContentView(binding.root)

        dino = if (Build.VERSION.SDK_INT >= 33) {
            intent.getParcelableExtra(EXTRA_DINO, Dino::class.java)
        } else {
            @Suppress("DEPRECATION")
            intent.getParcelableExtra(EXTRA_DINO)
        }

        supportActionBar?.setDisplayHomeAsUpEnabled(true)
        if (dino != null) {
            supportActionBar?.title = "Dinosaurus ${dino!!.nama}"
            loadData(dino!!)
        } else {
            finish()
        }
    }

    private fun loadData(dino: Dino) {
        binding.ivDetailDinoIcon.setImageResource(dino.gambar)
        binding.tvDetailNamaDino.text = dino.nama
        binding.tvDetailDescription.text = dino.deskripsi
        binding.tvDetailKarakteristik.text = dino.kelompok
        binding.tvDetailKelompokDino.text = dino.karakteristik
        binding.tvDetailHabitatDino.text = dino.habitat
        binding.tvDetailMakananDino.text = dino.makanan
        binding.tvDetailPanjangDino.text = dino.panjang
        binding.tvDetailTinggiDino.text = dino.tinggi
        binding.tvDetailBobotDino.text = dino.bobot
        binding.tvDetailKelemahanDino.text = dino.kelemahan
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        when (item.itemId) {
            android.R.id.home -> {
                finish()
            }
        }
        return super.onOptionsItemSelected(item)
    }

    companion object {
        const val EXTRA_DINO = "extra_dino"
    }
}