package com.amangarg.lid.data.entity

data class QuestionDto(
    val num: String,
    val question: String?,
    val a: String?,
    val b: String?,
    val c: String?,
    val d: String?,
    val solution: String?,
    val image: String?,
    val translation: Map<String, TranslatedDto>?, // languageCode -> translated object
    val category: String?,
    val context: String?,
    val id: String
)
