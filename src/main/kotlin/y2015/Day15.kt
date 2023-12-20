package y2015

import FileUtil
import numberRegex
import kotlin.math.max

fun main() {
    val lines = FileUtil().readLines("2015/day15-input.txt")
    part1(lines)
    part2(lines)
}

private fun part1(lines: List<String>) {
    val ingredients = lines.map { Ingredient(it) }

    val result = findBestRecipe(ingredients, false)

    println("Result Part1: $result")
}

private fun part2(lines: List<String>) {
    val ingredients = lines.map { Ingredient(it) }

    val result = findBestRecipe(ingredients, true)

    println("Result Part2: $result")
}

private fun findBestRecipe(ingredients: List<Ingredient>, withCalories: Boolean): Int {
    var result = 0
    for (sprinkles in 1..100) {
        ingredients[0].amount = sprinkles
        for (butter in 1..100 - sprinkles) {
            ingredients[1].amount = butter
            for (frosting in 1..100 - sprinkles - butter) {
                val sugar = 100 - sprinkles - butter - frosting
                ingredients[2].amount = frosting
                ingredients[3].amount = sugar
                val cookie = if (withCalories) makeCookieConsideringCalories(ingredients) else makeCookie(ingredients)
                result = max(result, cookie)
            }
        }
    }
    return result
}

private fun makeCookieConsideringCalories(ingredients: List<Ingredient>): Int {
    if (ingredients.sumOf { it.calories * it.amount } != 500) return 0
    return makeCookie(ingredients)
}

private fun makeCookie(ingredients: List<Ingredient>): Int {
    return max(0, ingredients.sumOf { it.capacity * it.amount }) *
            max(0, ingredients.sumOf { it.durability * it.amount }) *
            max(0, ingredients.sumOf { it.flavor * it.amount }) *
            max(0, ingredients.sumOf { it.texture * it.amount })
}

private class Ingredient(line: String) {
    val capacity: Int
    val durability: Int
    val flavor: Int
    val texture: Int
    val calories: Int

    var amount = 0

    init {
        val values = numberRegex.findAll(line).map { it.value.toInt() }.toList()
        capacity = values[0]
        durability = values[1]
        flavor = values[2]
        texture = values[3]
        calories = values[4]
    }
}