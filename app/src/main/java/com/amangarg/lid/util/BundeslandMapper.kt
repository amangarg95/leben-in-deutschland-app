package com.amangarg.lid.util

object BundeslandMapper {
    data class StateInfo(
        val german: String,
        val english: String,
        val flagResourceName: String // e.g. "flag_baden_wuerttemberg"
    )
    val states = listOf(

        StateInfo("Baden-Württemberg", "Baden-Württemberg", "flag_baden_wuerttemberg"),
        StateInfo("Bayern", "Bavaria", "flag_bavaria"),
        StateInfo("Berlin", "Berlin", "flag_berlin"),
        StateInfo("Brandenburg", "Brandenburg", "flag_brandenburg"),
        StateInfo("Bremen", "Bremen", "flag_bremen"),
        StateInfo("Hamburg", "Hamburg", "flag_hamburg"),
        StateInfo("Hessen", "Hesse", "flag_hesse"),
        StateInfo(
            "Mecklenburg-Vorpommern",
            "Mecklenburg-Western Pomerania",
            "flag_mecklenburg_vorpommern"
        ),
        StateInfo("Niedersachsen", "Lower Saxony", "flag_lower_saxony"),
        StateInfo("Nordrhein-Westfalen", "North Rhine-Westphalia", "flag_north_rhine_westphalia"),
        StateInfo("Rheinland-Pfalz", "Rhineland-Palatinate", "flag_rhineland_palatinate"),
        StateInfo("Saarland", "Saarland", "flag_saarland"),
        StateInfo("Sachsen", "Saxony", "flag_saxony"),
        StateInfo("Sachsen-Anhalt", "Saxony-Anhalt", "flag_saxony_anhalt"),
        StateInfo("Schleswig-Holstein", "Schleswig-Holstein", "flag_schleswig_holstein"),
        StateInfo("Thüringen", "Thuringia", "flag_thuringia")
    )

    val germanToInfo = states.associateBy { it.german }
    val englishToInfo = states.associateBy { it.english }

    fun getFlagResourceByGerman(name: String): String? = germanToInfo[name]?.flagResourceName
    fun getFlagResourceByEnglish(name: String): String? = englishToInfo[name]?.flagResourceName
    fun getEnglishName(german: String): String? = germanToInfo[german]?.english
    fun getGermanName(english: String): String? = englishToInfo[english]?.german

}
