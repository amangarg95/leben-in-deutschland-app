package com.amangarg.lid.util

import android.content.Context
import com.amangarg.lid.data.Questions
import com.amangarg.lid.data.QuestionsItemDto
import com.squareup.moshi.Moshi
import com.squareup.moshi.Types
import com.squareup.moshi.kotlin.reflect.KotlinJsonAdapterFactory

fun loadQuestionsFromAssets(context: Context, fileName: String): List<QuestionsItemDto>? {
    val moshi = Moshi.Builder()
        .add(KotlinJsonAdapterFactory())
        .build()

    val type = Types.newParameterizedType(List::class.java, QuestionsItemDto::class.java)
    val adapter = moshi.adapter<List<QuestionsItemDto>>(type)

    val jsonString = context.assets.open(fileName).bufferedReader().use { it.readText() }

    return adapter.fromJson(jsonString)
}

