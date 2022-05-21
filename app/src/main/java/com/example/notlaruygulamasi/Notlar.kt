package com.example.notlaruygulamasi


import com.google.gson.annotations.SerializedName
import java.io.Serializable

data class Notlar(
    @SerializedName("ders_adi")
    val dersAdi: String?,
    @SerializedName("not1")
    val not1: String?,
    @SerializedName("not2")
    val not2: String?,
    @SerializedName("not_id")
    val notİd: String?
): Serializable