package com.ifs21048.dinopedia

import android.content.Intent
import android.os.Build
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.MenuItem
import android.widget.TextView
import com.ifs21048.dinopedia.DinoActivity.Companion.EXTRA_FAMILI
import com.ifs21048.dinopedia.databinding.ActivityDetailFamiliActivtityBinding

class DetailFamiliActivtity : AppCompatActivity() {
    private lateinit var binding: ActivityDetailFamiliActivtityBinding
    private var famili: Famili? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityDetailFamiliActivtityBinding.inflate(layoutInflater)
        setContentView(binding.root)

        famili = if (Build.VERSION.SDK_INT >= 33) {
            intent.getParcelableExtra(EXTRA_FAMILI, Famili::class.java)
        } else {
            @Suppress("DEPRECATION")
            intent.getParcelableExtra(EXTRA_FAMILI)
        }

        supportActionBar?.setDisplayHomeAsUpEnabled(true)
        if (famili != null) {
            supportActionBar?.title = "Famili ${famili!!.famili}"
            loadData(famili!!)
        } else {
            finish()
        }

        val dinoBtn = findViewById<TextView>(R.id.buttonDino)
        dinoBtn.setOnClickListener({
            val intent = Intent(this, DinoActivity::class.java)
            startActivity(intent)
        })

        binding.buttonDino.setOnClickListener{
            val intentWithData = Intent(this@DetailFamiliActivtity, DinoActivity::class.java)
            intentWithData.putExtra(DinoActivity.EXTRA_FAMILI, famili!!)
            startActivity(intentWithData)
        }
    }

    private fun loadData(famili: Famili) {
        binding.ivDetailIcon.setImageResource(famili.gambar)
        binding.tvDetailFamili.text = famili.famili
        binding.tvDetailDescription.text = famili.deskripsi
        binding.tvDetailPeriode.text = famili.periode
        binding.tvDetailKarakteristik.text = famili.karakteristik
        binding.tvDetailHabitat.text = famili.habitat
        binding.tvDetailPerilaku.text = famili.perilaku
        binding.tvDetailKlasifikasi.text = famili.klasifikasi
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        when (item.itemId) {
            android.R.id.home -> {
                finish()
                return true
            }
        }
        return super.onOptionsItemSelected(item)
    }

    companion object {
        const val EXTRA_FAMILI = "extra_famili"
    }

}
