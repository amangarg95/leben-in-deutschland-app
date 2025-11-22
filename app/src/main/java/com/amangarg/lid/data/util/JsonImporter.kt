package com.amangarg.lid.data.util

import android.content.Context
import com.amangarg.lid.data.entity.QuestionDto
import com.amangarg.lid.data.local.model.Question
import com.amangarg.lid.data.local.model.Translation
import com.google.gson.Gson
import com.google.gson.reflect.TypeToken
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import java.io.InputStreamReader
import java.util.regex.Pattern

suspend fun readQuestionsFromAsset(context: Context, assetFileName: String): List<QuestionDto> {
    return withContext(Dispatchers.IO) {
        val gson = Gson()
        context.assets.open(assetFileName).use { input ->
            InputStreamReader(input, "UTF-8").use { reader ->
                val type = object : TypeToken<List<QuestionDto>>() {}.type
                gson.fromJson(reader, type)
            }
        }
    }
}

/**
 * Utility: extract state code from num like "HB-1" -> "HB"
 */
fun extractStateCodeFromNum(num: String): String? {
    val pattern = Pattern.compile("^([A-Z]{2})-\\d+", Pattern.CASE_INSENSITIVE)
    val m = pattern.matcher(num)
    return if (m.find()) m.group(1)?.uppercase() else null
}

/**
 * Map DTO -> Question entity
 * caller must pass resolved stateId (nullable)
 */
fun questionDtoToQuestion(dto: QuestionDto, stateId: Long?): Question {
    val isState = extractStateCodeFromNum(dto.num) != null
    return Question(
        id = dto.id,
        num = dto.num,
        question = dto.question ?: "",
        a = dto.a ?: "",
        b = dto.b ?: "",
        c = dto.c ?: "",
        d = dto.d ?: "",
        solution = dto.solution ?: "",
        image = dto.image,
        category = dto.category,
        context = dto.context,
        stateId = stateId,
        isStateSpecific = isState,
        difficulty = null
    )
}

/**
 * Map DTO translations -> List<Translation>
 */
fun questionDtoToTranslations(dto: QuestionDto): List<Translation> {
    val list = mutableListOf<Translation>()
    dto.translation?.forEach { (lang, trans) ->
        list.add(
            Translation(
                questionId = dto.id,
                languageCode = lang,
                question = trans.question,
                a = trans.a,
                b = trans.b,
                c = trans.c,
                d = trans.d,
                context = trans.context
            )
        )
    }
    return list
}