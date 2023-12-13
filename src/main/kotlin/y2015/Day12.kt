package y2015

import FileUtil
import com.google.gson.Gson
import com.google.gson.JsonElement
import com.google.gson.JsonObject

fun main() {
    val jsonString = FileUtil().readLines("2015/day12-input.txt")[0]
    part1(jsonString)
    part2(jsonString)
}

private fun part1(jsonString: String) {
    val result = Regex("-?\\d+").findAll(jsonString).sumOf { it.value.toInt() }
    println("Result Part1: $result")
}

private fun part2(jsonString: String) {
    val json = Gson().fromJson(jsonString, JsonElement::class.java)

    val result = sumNumbersInJson(json)

    println("Result Part2: $result")
}

fun sumNumbersInJson(jsonElement: JsonElement): Int {
    return when {
        jsonElement.isJsonPrimitive && jsonElement.asJsonPrimitive.isNumber -> jsonElement.asJsonPrimitive.asInt

        jsonElement.isJsonObject -> {
            val jsonObject = jsonElement.asJsonObject
            if (containsRedValue(jsonObject)) 0
            else jsonObject.entrySet().sumOf { sumNumbersInJson(it.value) }
        }

        jsonElement.isJsonArray -> jsonElement.asJsonArray.sumOf { sumNumbersInJson(it) }

        else -> 0
    }
}

private fun containsRedValue(jsonObject: JsonObject) = jsonObject.entrySet()
    .any { it.value.isJsonPrimitive && it.value.asJsonPrimitive.isString && it.value.asString == "red" }