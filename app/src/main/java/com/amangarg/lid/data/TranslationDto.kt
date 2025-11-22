package com.amangarg.lid.data

import com.squareup.moshi.Json

data class TranslationDto(
    @Json(name = "en")
    val en: EnglishDto
)