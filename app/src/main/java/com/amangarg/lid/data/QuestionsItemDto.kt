package com.amangarg.lid.data


import com.squareup.moshi.Json

data class QuestionsItemDto(
    @Json(name = "a")
    val a: String,
    @Json(name = "b")
    val b: String,
    @Json(name = "c")
    val c: String,
    @Json(name = "category")
    val category: String,
    @Json(name = "context")
    val context: String,
    @Json(name = "d")
    val d: String,
    @Json(name = "id")
    val id: String,
    @Json(name = "image")
    val image: String,
    @Json(name = "num")
    val num: String,
    @Json(name = "question")
    val question: String,
    @Json(name = "solution")
    val solution: String,
    @Json(name = "translation")
    val translation: TranslationDto
)