package com.example.notlaruygulamasi

import retrofit2.Call
import retrofit2.http.Field
import retrofit2.http.FormUrlEncoded
import retrofit2.http.GET
import retrofit2.http.POST

interface NotlarDaoInterface {
    @GET ("notlar/tum_notlar.php")

    fun tumNotlar() : Call<NotlarCevap>

    @POST("notlar/delete_not.php")
    @FormUrlEncoded
    fun notSil(@Field("not_id") not_id: String?) : Call<CRUDCevap>

    @POST("notlar/insert_not.php")
    @FormUrlEncoded
    fun notEkle(@Field("ders_adi") ders_adi: String,
                @Field("not1") not1: Int,
                @Field("not2") not2: Int) : Call<CRUDCevap>


    @POST("notlar/update_not.php")
    @FormUrlEncoded
    fun notGuncelle(
        @Field("not_id") not_id: String?,
        @Field("ders_adi") ders_adi: String,
        @Field("not1") not1: Int,
        @Field("not2") not2: Int) : Call<CRUDCevap>
}