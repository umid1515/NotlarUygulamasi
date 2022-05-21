package com.example.notlaruygulamasi

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.text.TextUtils
import android.util.Log
import android.view.Menu
import android.view.MenuItem
import com.example.notlaruygulamasi.databinding.ActivityDetayBinding
import com.google.android.material.snackbar.Snackbar
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class DetayActivity : AppCompatActivity() {
    private lateinit var ndi: NotlarDaoInterface
    private lateinit var not: Notlar
    private var _binding: ActivityDetayBinding?=null
    private val binding get() = _binding!!
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        _binding= ActivityDetayBinding.inflate(layoutInflater)
        setContentView(binding.root)


        binding.toolbarNotDetay.title="Not Detay"
        setSupportActionBar(binding.toolbarNotDetay)



           ndi = ApiUtils.getKelimelerDoaInterface()



           not = intent.getSerializableExtra("nesne") as Notlar




        binding.editTextDetayDers.setText(not.dersAdi)
        binding.editTextDetayNot1.setText(not.not1).toString()
        binding.editTextDetayNot2.setText(not.not2).toString()
    }

    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        menuInflater.inflate(R.menu.toolbar_menu,menu)
        return super.onCreateOptionsMenu(menu)
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        when(item.itemId){
            R.id.action_sil ->{
                Snackbar.make(binding.toolbarNotDetay,"Silinsin mi?",Snackbar.LENGTH_LONG)
                    .setAction("Evet"){
                        ndi.notSil(not.notİd).enqueue(object : Callback<CRUDCevap>{
                            override fun onResponse(
                                call: Call<CRUDCevap>,
                                response: Response<CRUDCevap>
                            ) {

                            }

                            override fun onFailure(call: Call<CRUDCevap>, t: Throwable) {

                            }

                        })
                        startActivity(Intent(this@DetayActivity,MainActivity::class.java))
                        finish()
                }.show()
                return true
            }
            R.id.action_duzenle ->{
                val ders_adi=binding.editTextDetayDers.text.toString().trim()
                val not1=binding.editTextDetayNot1.text.toString().trim()
                val not2=binding.editTextDetayNot2.text.toString().trim()

                if (TextUtils.isEmpty(ders_adi)){
                    Snackbar.make(binding.toolbarNotDetay,"Ders adını giriniz",Snackbar.LENGTH_LONG).show()
                    return false
                }
                if (TextUtils.isEmpty(not1)){
                    Snackbar.make(binding.toolbarNotDetay,"1. notu giriniz",Snackbar.LENGTH_LONG).show()
                    return false
                }
                if (TextUtils.isEmpty(not2)){
                    Snackbar.make(binding.toolbarNotDetay,"2. notu giriniz",Snackbar.LENGTH_LONG).show()
                    return false
                }
                ndi.notGuncelle(not.notİd,ders_adi,not1.toInt(),not2.toInt()).enqueue(object :Callback<CRUDCevap>{
                    override fun onResponse(call: Call<CRUDCevap>, response: Response<CRUDCevap>) {

                        response.body()?.let { Log.e("Durum", it?.message) }
                    }

                    override fun onFailure(call: Call<CRUDCevap>, t: Throwable) {
                    }

                })
                startActivity(Intent(this@DetayActivity,MainActivity::class.java))
                finish()
                return true
            }
            else  -> return false

        }

    }
}