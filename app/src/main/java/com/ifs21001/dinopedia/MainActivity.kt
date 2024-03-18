package com.ifs21001.dinopedia

import android.annotation.SuppressLint
import android.content.Intent
import android.content.res.Configuration
import android.os.Bundle
import android.view.Menu
import android.view.MenuItem
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.LinearLayoutManager
import com.ifs21001.dinopedia.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {
    private lateinit var binding: ActivityMainBinding
    private val dataFruits = ArrayList<Fruit>()
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)
        binding.rvFruits.setHasFixedSize(false)
        dataFruits.addAll(getListFruits())
        showRecyclerList()
    }

    override fun onCreateOptionsMenu(menu: Menu): Boolean {
        menuInflater.inflate(R.menu.about_main, menu)
        return true
        }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        return when (item.itemId) {
            R.id.menu_about -> {
                val intent = Intent(this, ProfileActivity::class.java)
                startActivity(intent)
                true
            }
            else -> super.onOptionsItemSelected(item)
        }
    }

    @SuppressLint("Recycle")
    private fun getListFruits(): ArrayList<Fruit> {
        val dataName =
            resources.getStringArray(R.array.fruit_name)
        val dataIcon =
            resources.obtainTypedArray(R.array.fruit_icon)
        val dataDescription =
            resources.getStringArray(R.array.fruit_description)
        val dataCharacteristic =
            resources.getStringArray(R.array.fruit_characteristic)
        val dataKelompok =
            resources.getStringArray(R.array.fruit_kelompok)
        val dataHabitat =
            resources.getStringArray(R.array.fruit_habitat)
        val dataMakanan =
            resources.getStringArray(R.array.fruit_makanan)
        val dataPanjang =
            resources.getStringArray(R.array.fruit_panjang)
        val dataTinggi =
            resources.getStringArray(R.array.fruit_tinggi)
        val dataBobot =
            resources.getStringArray(R.array.fruit_bobot)
        val dataKelemahan=
            resources.getStringArray(R.array.fruit_kelemahan)
        val listFruit = ArrayList<Fruit>()
        for (i in dataName.indices) {
            val fruit = Fruit(dataName[i],
                dataIcon.getResourceId(i, -1), dataDescription[i],
                dataCharacteristic[i], dataKelompok[i], dataHabitat[i], dataMakanan[i],dataPanjang[i], dataTinggi[i], dataBobot[i], dataKelemahan[i])
            listFruit.add(fruit)
        }
        return listFruit
    }
    private fun showRecyclerList() {
        if (resources.configuration.orientation ==
            Configuration.ORIENTATION_LANDSCAPE) {
            binding.rvFruits.layoutManager =
                GridLayoutManager(this, 2)
        } else {
            binding.rvFruits.layoutManager =
                LinearLayoutManager(this)
        }
        val listFruitAdapter = ListFruitAdapter(dataFruits)
        binding.rvFruits.adapter = listFruitAdapter
        listFruitAdapter.setOnItemClickCallback(object :
            ListFruitAdapter.OnItemClickCallback {
            override fun onItemClicked(data: Fruit) {
                showSelectedFruit(data)
            }
        })
    }
    private fun showSelectedFruit(fruit: Fruit) {
        val intentWithData = Intent(this@MainActivity,
            DetailActivity::class.java)
        intentWithData.putExtra(DetailActivity.EXTRA_FRUIT, fruit)
        startActivity(intentWithData)
    }
}
