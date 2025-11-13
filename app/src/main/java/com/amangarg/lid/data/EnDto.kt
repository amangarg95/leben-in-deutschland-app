package com.amangarg.lid.data


import com.squareup.moshi.Json

data class EnDto(
    @Json(name = "a")
    val a: String,
    @Json(name = "b")
    val b: String,
    @Json(name = "c")
    val c: String,
    @Json(name = "context")
    val context: String,
    @Json(name = "d")
    val d: String,
    @Json(name = "question")
    val question: String
)