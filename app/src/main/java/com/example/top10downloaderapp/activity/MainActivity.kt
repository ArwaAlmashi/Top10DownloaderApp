package com.example.top10downloaderapp.activity

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.recyclerview.widget.RecyclerView
import com.example.top10downloaderapp.databinding.ActivityMainBinding
import com.example.top10downloaderapp.model.Application
import com.example.top10downloaderapp.model.XmlParser
import com.example.top10downloaderapp.recyclerview.MyAdapter
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.async
import kotlinx.coroutines.launch
import java.io.IOException

class MainActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMainBinding
    private lateinit var applications: ArrayList<Application>
    private lateinit var recyclerView: RecyclerView
    private lateinit var myAdapter: MyAdapter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        applications = arrayListOf()
        recyclerView = binding.recyclerview
        myAdapter = MyAdapter(applications)
        recyclerView.adapter = myAdapter

        parseRSS()


    }

    private fun parseRSS() {
        CoroutineScope(Dispatchers.IO).launch {
            val data = async {
                val parser = XmlParser()
                parser.parse()
            }.await()
            try {
                runOnUiThread{
                    myAdapter.update(data)
                }

            } catch (e: IOException){
                e.printStackTrace()
            }
        }
    }


}