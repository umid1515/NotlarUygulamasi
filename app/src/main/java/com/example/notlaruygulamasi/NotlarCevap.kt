package com.example.notlaruygulamasi


import com.google.gson.annotations.SerializedName

data class NotlarCevap(
    @SerializedName("notlar")
    val notlar: List<Notlar>?,
    @SerializedName("success")
    val success: Int?
)