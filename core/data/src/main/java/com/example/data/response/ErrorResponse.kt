package com.example.data.response

import com.google.gson.annotations.SerializedName

data class ErrorResponse(
    @SerializedName("errorCode")
    val errorCode: String,
    @SerializedName("errorMessage")
    val errorMessage: String,
    @SerializedName("errorFieldList")
    val errorFiledList: List<String>,
)