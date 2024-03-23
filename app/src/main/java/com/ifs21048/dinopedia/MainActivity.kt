package com.ifs21048.dinopedia

import android.annotation.SuppressLint
import android.content.Intent
import com.ifs21048.dinopedia.databinding.ActivityMainBinding
import android.content.res.Configuration
import android.os.Bundle
import android.view.Menu
import android.view.MenuItem
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.LinearLayoutManager

class MainActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMainBinding
    private val dataFamili = ArrayList<Famili>()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)
        binding.rvFamili.setHasFixedSize(false)
        dataFamili.addAll(getListFruits())
        showRecyclerList()
    }

    override fun onCreateOptionsMenu(menu: Menu): Boolean {
        menuInflater.inflate(R.menu.menu, menu)
        return true
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        when (item.itemId) {
            R.id.menu_about -> {
                startActivity(Intent(this, ProfileActivity::class.java))
            }
            else -> super.onOptionsItemSelected(item)
        }
        return true
    }

    @SuppressLint("Recycle")
    private fun getListFruits(): ArrayList<Famili> {
        val dataFamili = resources.getStringArray(R.array.famili_data)
        val dataIcon = resources.obtainTypedArray(R.array.icon_data)
        val dataDescription = resources.getStringArray(R.array.deskripsi_data)
        val dataPeriode = resources.getStringArray(R.array.periode_data)
        val dataKarakteristik = resources.getStringArray(R.array.karakteristik_data)
        val dataHabitat = resources.getStringArray(R.array.habitat_data)
        val dataPerilaku = resources.getStringArray(R.array.perilaku_data)
        val dataKlasifikasi = resources.getStringArray(R.array.klasifikasi_data)
        val dataIndexStart = resources.getStringArray(R.array.start_dino)
        val dataIndexEnd = resources.getStringArray(R.array.end_dino)

        val listFamili = ArrayList<Famili>()
        for (i in dataFamili.indices) {
            val famili = Famili(
                dataFamili[i],
                dataIcon.getResourceId(i, -1),
                dataDescription[i],
                dataPeriode[i],
                dataKarakteristik[i],
                dataHabitat[i],
                dataPerilaku[i],
                dataKlasifikasi[i],
                dataIndexStart[i].toInt(),
                dataIndexEnd[i].toInt()
            )
            listFamili.add(famili)
        }
        return listFamili
    }

    private fun showRecyclerList() {
        if (resources.configuration.orientation == Configuration.ORIENTATION_LANDSCAPE) {
            binding.rvFamili.layoutManager = GridLayoutManager(this, 2)
        } else {
            binding.rvFamili.layoutManager = LinearLayoutManager(this)
        }
        val listFamilyAdapter = ListFamiliAdapter(dataFamili)
        binding.rvFamili.adapter = listFamilyAdapter
        listFamilyAdapter.setOnItemClickCallback(object : ListFamiliAdapter.OnItemClickCallback {
            override fun onItemClicked(data: Famili) {
                showSelectedFamili(data)
            }
        })
    }

    private fun showSelectedFamili(famili: Famili) {
        val intentWithData = Intent(this@MainActivity, DetailFamiliActivtity::class.java)
        intentWithData.putExtra(DetailFamiliActivtity.EXTRA_FAMILI, famili)
        startActivity(intentWithData)
    }
}