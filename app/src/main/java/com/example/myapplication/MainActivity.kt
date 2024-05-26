package com.example.myapplication

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.appcompat.widget.SearchView
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import java.util.*
import kotlin.collections.ArrayList

class MainActivity : AppCompatActivity() {

    private lateinit var recyclerView: RecyclerView
    private lateinit var dataList: ArrayList<DataClass>
    lateinit var imageList:Array<Int>
    lateinit var titleList:Array<String>
    lateinit var descList: Array<String>
    lateinit var detailImageList: Array<Int>
    private lateinit var myAdapter: AdapterClass
    private lateinit var searchView: SearchView
    private lateinit var searchList: ArrayList<DataClass>

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        imageList = arrayOf(
            R.drawable.iconlist,
            R.drawable.iconlist,
            R.drawable.iconlist,
            R.drawable.iconlist,
            R.drawable.iconlist,
            R.drawable.iconlist,
            R.drawable.iconlist,
            R.drawable.iconlist,
            R.drawable.iconlist,
            R.drawable.iconlist)

        titleList = arrayOf(
            "Ya Nabi Salam ’Alaika",
            "Ya Thoybah",
            "Ya Robbi Bil Mustofa",
            "Bismillah",
            "Lil Abi Wal Umi",
            "Sidnan Nabi",
            "Sholawat Badar",
            "Ya Abaz Zahro",
            "Ummi",
            "Nurul Musthofa",
            "Ya Sayyidi Ya Rosulallah",
            "Annabiy Shollu 'Alaihi",
            "Salamun'Alaik",
            "Ya Ghoffar",
            "Maulaya",
            "Marhaban Ya Nurul Aini",
            "Ya Zahro",
            "Allah Allah",
            "Laka Ya Robb",
            "Padang Bulan",
            "Sholawat Pepali Ki Ageng Selo",
            "Allahul Kafi",
            "Turi Putih",
            "Cinta Sahabat Nabi Muhammad",
            "Astagfirullah"
            )

        descList = arrayOf(
            getString(R.string.Ya_Nabi_Salam_Alaika),
            getString(R.string.yathoybah),
            getString(R.string.yarobbibilmustofa),
            getString(R.string.bismillah),
            getString(R.string.lilabi),
            getString(R.string.sidnan),
            getString(R.string.sholawatbadar),
            getString(R.string.textview),
            getString(R.string.edit),
            getString(R.string.camera))

        detailImageList = arrayOf(
            R.drawable.bingkai,
            R.drawable.bingkai,
            R.drawable.bingkai,
            R.drawable.bingkai,
            R.drawable.bingkai,
            R.drawable.bingkai,
            R.drawable.bingkai,
            R.drawable.bingkai,
            R.drawable.bingkai,
            R.drawable.bingkai,
            R.drawable.bingkai,
            R.drawable.bingkai,
            R.drawable.bingkai,
            R.drawable.bingkai,
            R.drawable.bingkai,
            R.drawable.bingkai,
            R.drawable.bingkai,
            R.drawable.bingkai,
            R.drawable.bingkai,
            R.drawable.bingkai,
            R.drawable.bingkai,
            R.drawable.bingkai,
            R.drawable.bingkai,
            R.drawable.bingkai,
            R.drawable.bingkai)

        recyclerView = findViewById(R.id.recyclerView)
        searchView = findViewById(R.id.search)
        recyclerView.layoutManager = LinearLayoutManager(this)
        recyclerView.setHasFixedSize(true)

        dataList = arrayListOf<DataClass>()
        searchList = arrayListOf<DataClass>()
        getData()

        searchView.clearFocus()
        searchView.setOnQueryTextListener(object : SearchView.OnQueryTextListener{
            override fun onQueryTextSubmit(query: String?): Boolean {
                searchView.clearFocus()
                return true
            }

            override fun onQueryTextChange(newText: String?): Boolean {
                searchList.clear()
                val searchText = newText!!.toLowerCase(Locale.getDefault())
                if (searchText.isNotEmpty()){
                    dataList.forEach{
                        if (it.dataTitle.toLowerCase(Locale.getDefault()).contains(searchText)) {
                            searchList.add(it)
                        }
                    }
                    recyclerView.adapter!!.notifyDataSetChanged()
                } else {
                    searchList.clear()
                    searchList.addAll(dataList)
                    recyclerView.adapter!!.notifyDataSetChanged()
                }
                return false
            }

        })

        myAdapter = AdapterClass(searchList)
        recyclerView.adapter = myAdapter

        myAdapter.onItemClick = {
            val intent = Intent(this, DetailActivity::class.java)
            intent.putExtra("android", it)
            startActivity(intent)
        }

    }

    private fun getData(){
        for (i in imageList.indices){
            val dataClass = DataClass(imageList[i], titleList[i], descList[i], detailImageList[i])
            dataList.add(dataClass)
        }
        searchList.addAll(dataList)
        recyclerView.adapter = AdapterClass(searchList)
    }
}