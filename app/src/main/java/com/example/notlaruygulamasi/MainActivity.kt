package com.example.notlaruygulamasi

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.notlaruygulamasi.databinding.ActivityMainBinding
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class MainActivity : AppCompatActivity() {
   // private lateinit var notlarArrayList: ArrayList<Notlar>
    private var adapter: NotlarAdapter = NotlarAdapter()
    private lateinit var ndi: NotlarDaoInterface
    private var _binding: ActivityMainBinding? = null
    private val binding get() = _binding!!
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        _binding = ActivityMainBinding.inflate(layoutInflater)

        setContentView(binding.root)

        binding.toolbar.title = "Notlar Uygulaması"
        binding.toolbar.subtitle = "Ortalama : 70"
        setSupportActionBar(binding.toolbar)

        binding.rv.setHasFixedSize(true)
        binding.rv.layoutManager = LinearLayoutManager(this)



        ndi = ApiUtils.getKelimelerDoaInterface()
        tumNotlar()


/*
        notlarArrayList = ArrayList()
        val n1 = Notlar("Tarih", "40", "80", "1")
        val n2 = Notlar("Kimya", "70", "50", "2")
        val n3 = Notlar("Fizik", "30", "60", "3")

        notlarArrayList = ArrayList<Notlar>()
        notlarArrayList.add(n1)
        notlarArrayList.add(n2)
        notlarArrayList.add(n3)



        adapter.updateList(notlarArrayList)
        binding.rv.adapter = adapter*/

        binding.fab.setOnClickListener {
            startActivity(Intent(this@MainActivity, NotKayitActivity::class.java))

        }
    }

    //sehifeden cıxmaq ucun ana ekrana (menuya)donmek ucun
    override fun onBackPressed() {
        val intent=Intent(Intent.ACTION_MAIN)
        intent.addCategory(Intent.CATEGORY_HOME)
        intent.flags=Intent.FLAG_ACTIVITY_NEW_TASK
        startActivity(intent)
    }



    fun tumNotlar() {
        ndi.tumNotlar().enqueue(object : Callback<NotlarCevap> {
            override fun onResponse(
                call: Call<NotlarCevap>, response: Response<NotlarCevap>
            ) {
                val liste = response.body()?.notlar
                  if (liste != null) {

                      with(adapter) { updateList(liste) }
                  }
              //  adapter = NotlarAdapter(this@MainActivity,liste)
                binding.rv.adapter = adapter
                var toplam =0

                // if (liste != null) {
                if (liste != null) {
                    for(n in liste){


                     toplam=(toplam+(n.not1!!.toInt()+ n.not2!!.toInt()))/2


                    }
                }


                //     if (liste != null) {
                if (liste != null) {

                   binding.toolbar.subtitle="Ortalama: ${toplam.toDouble()/liste.size}"
                   // binding.toolbar.subtitle="Ortalama: $toplam"
                }
                //  }
            }
            override fun onFailure(call: Call<NotlarCevap>, t: Throwable) {

            }
        })
    }


            override fun onDestroy() {
                super.onDestroy()
                _binding = null
            }

    }




