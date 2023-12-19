package y2015

import FileUtil

fun main() {
    val lines = FileUtil().readLines("2015/day13-input.txt")
    part1(lines)
    part2(lines)
}

private fun part1(lines: List<String>) {
    val happinessMap = createSeatMap(lines)

    val result = findBestSeating(happinessMap)

    println("Result Part1: $result")
}

private fun part2(lines: List<String>) {
    val happinessMap = createSeatMap(lines)

    val guests = happinessMap.keys.toList()
    guests.forEach { guest ->
        happinessMap.getOrPut("me") { mutableMapOf() }[guest] = 0
        happinessMap.getOrPut(guest) { mutableMapOf() }["me"] = 0
    }

    val result = findBestSeating(happinessMap)

    println("Result Part2: $result")
}

private fun findBestSeating(happinessMap: MutableMap<String, MutableMap<String, Int>>): Int {
    val guests = happinessMap.keys.toList()
    var maxHappiness = Int.MIN_VALUE

    val permutations = guests.permute()

    for (permutation in permutations) {
        var totalHappiness = 0

        for (i in permutation.indices) {
            val currentGuest = permutation[i]
            val nextGuest = permutation[(i + 1) % permutation.size]

            totalHappiness += happinessMap[currentGuest]?.get(nextGuest) ?: 0
            totalHappiness += happinessMap[nextGuest]?.get(currentGuest) ?: 0
        }

        if (totalHappiness > maxHappiness) {
            maxHappiness = totalHappiness
        }
    }
    return maxHappiness
}

private fun createSeatMap(lines: List<String>): MutableMap<String, MutableMap<String, Int>> {
    val happinessMap = mutableMapOf<String, MutableMap<String, Int>>()
    lines.forEach { line ->
        val terms = line.split(" ")
        val score = if (terms[2] == "lose") terms[3].toInt() * -1 else terms[3].toInt()
        val personNextTo = terms.last().replace(".", "")
        happinessMap.getOrPut(terms[0]) { mutableMapOf() }[personNextTo] = score
    }
    return happinessMap
}

// Extension function to generate all permutations of a list
fun <T> List<T>.permute(): List<List<T>> =
    if (size <= 1) listOf(this)
    else flatMap { element ->
        (this - element).permute().map { permutation -> listOf(element) + permutation }
    }
