package com.example.notlaruygulamasi

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.text.TextUtils
import com.example.notlaruygulamasi.databinding.ActivityNotKayitBinding
import com.google.android.material.snackbar.Snackbar
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class NotKayitActivity : AppCompatActivity() {
    private lateinit var ndi: NotlarDaoInterface
    private var _binding: ActivityNotKayitBinding? = null
    private val binding get() = _binding!!
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        _binding = ActivityNotKayitBinding.inflate(layoutInflater)
        setContentView(binding.root)


        binding.toolbarNotKayit.title = "Not Kayıt"
        setSupportActionBar(binding.toolbarNotKayit)


        ndi = ApiUtils.getKelimelerDoaInterface()




        binding.buttonKaydet.setOnClickListener {

            val ders_adi = binding.editTextTextDers.text.toString().trim()
            val not1 = binding.editTextTextNot1.text.toString().trim()
            val not2 = binding.editTextTextNot2.text.toString().trim()

            if (TextUtils.isEmpty(ders_adi)) {
                Snackbar.make(binding.toolbarNotKayit, "Ders adını giriniz", Snackbar.LENGTH_LONG)
                    .show()
                return@setOnClickListener
            }
            if (TextUtils.isEmpty(not1)) {
                Snackbar.make(binding.toolbarNotKayit, "1. notu giriniz", Snackbar.LENGTH_LONG)
                    .show()
                return@setOnClickListener
            }
            if (TextUtils.isEmpty(not2)) {
                Snackbar.make(binding.toolbarNotKayit, "2. notu giriniz", Snackbar.LENGTH_LONG)
                    .show()
                return@setOnClickListener
            }
            ndi.notEkle(ders_adi, not1.toInt(), not2.toInt()).enqueue(object : Callback<CRUDCevap> {
                override fun onResponse(call: Call<CRUDCevap>, response: Response<CRUDCevap>) {

                }

                override fun onFailure(call: Call<CRUDCevap>, t: Throwable) {
                }

            })
            startActivity(Intent(this@NotKayitActivity, MainActivity::class.java))
            finish()
        }
    }
}