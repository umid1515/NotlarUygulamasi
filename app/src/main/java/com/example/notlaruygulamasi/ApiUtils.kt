package com.example.notlaruygulamasi

class ApiUtils {
    companion object{
        val BASE_URL="http://kasimadalan.pe.hu/"
        fun getKelimelerDoaInterface():NotlarDaoInterface{
            return RetrofitClient.getClient(BASE_URL).create(NotlarDaoInterface::class.java)
        }
    }
}