package com.ifs21048.dinopedia

import android.annotation.SuppressLint
import android.content.Intent
import android.content.res.Configuration
import android.os.Build
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.LinearLayoutManager
import com.ifs21048.dinopedia.databinding.ActivityDinoBinding

class DinoActivity : AppCompatActivity() {
    private lateinit var binding: ActivityDinoBinding
    private val dataDino = ArrayList<Dino>()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityDinoBinding.inflate(layoutInflater)
        setContentView(binding.root)
        binding.rvDino.setHasFixedSize(true) // Changed to true
        dataDino.addAll(getListDino())
        showRecyclerList()
    }

    @SuppressLint("Recycle")
    private fun getListDino(): ArrayList<Dino> {

        val family = if (Build.VERSION.SDK_INT >= 33) {
            intent.getParcelableExtra(
                EXTRA_FAMILI,
                Famili::class.java
            )
        } else {
            @Suppress("DEPRECATION")
            intent.getParcelableExtra(EXTRA_FAMILI)
        }

        val dataDino = resources.getStringArray(R.array.nama_dino)
        val dataIconDino = resources.obtainTypedArray(R.array.gambar_dino)
        val dataDescriptionDino = resources.getStringArray(R.array.deskripsi_dino)
        val dataKarakteristikDino = resources.getStringArray(R.array.karakteristik_dino)
        val dataKelompokDino = resources.getStringArray(R.array.kelompok_dino)
        val dataHabitatDino = resources.getStringArray(R.array.habitat_dino)
        val dataMakananDino = resources.getStringArray(R.array.makanan_dino)
        val dataPanjangDino = resources.getStringArray(R.array.panjang_dino)
        val dataTinggiDino = resources.getStringArray(R.array.tinggi_dino)
        val dataBobotDino = resources.getStringArray(R.array.bobot_dino)
        val dataKelemahanDino = resources.getStringArray(R.array.kelemahan_dino)

        val startIndex = family?.startDino
        val endIndex = family?.endDino

        val dinoList = ArrayList<Dino>()
        for (i in startIndex!!..endIndex!!) {
            val dino = Dino(
                dataDino[i],
                dataIconDino.getResourceId(i, -1),
                dataDescriptionDino[i],
                dataKarakteristikDino[i],
                dataKelompokDino[i],
                dataHabitatDino[i],
                dataMakananDino[i],
                dataPanjangDino[i],
                dataTinggiDino[i],
                dataBobotDino[i],
                dataKelemahanDino[i])
            dinoList.add(dino)
        }
        return dinoList
    }


    private fun showRecyclerList() {
        if (resources.configuration.orientation == Configuration.ORIENTATION_LANDSCAPE) {
            GridLayoutManager(this, 2)
        } else {
            binding.rvDino.layoutManager = LinearLayoutManager(this)
        }

        val listDinoAdapter = ListDinoAdapter(dataDino)
        binding.rvDino.adapter = listDinoAdapter

        listDinoAdapter.setOnItemClickCallback(object : ListDinoAdapter.OnItemClickCallback {
            override fun onItemClicked(data: Dino) {
                showSelectedDino(data)
            }
        })
    }

    private fun showSelectedDino(dino: Dino) {
        val intentWithData = Intent(this@DinoActivity, DetailDinoActivity::class.java)
        intentWithData.putExtra(DetailDinoActivity.EXTRA_DINO, dino)
        startActivity(intentWithData)
    }

    companion object{
        const val EXTRA_FAMILI = "extra_famili"
    }
}
