package y2023

import FileUtil

private val demagedRgx = Regex("#+")

fun main() {
    val lines = FileUtil().readLines("2023/day12-input.txt")
    part1(lines)
    //part2(lines)
}

private fun part1(lines: List<String>) {
    var result = 0
    lines.forEach { line ->
        val (row, counts) = line.split(' ')
        val countings = counts.split(',').map { it.toInt() }
        result += countArrangements(row, countings)
    }

    println("Result Part1: $result")
}

private fun part2(lines: List<String>) {
    var result = 0
    lines.forEach { line ->
        val (row, counts) = line.split(' ')
        val countings = counts.split(',').map { it.toInt() }.toMutableList()
        val extendedCountings = MutableList(5) { countings }.flatten() // Creating a new list with five copies

        println(row)
        result += countArrangements("$row?$row?$row?$row?$row", extendedCountings)
    }

    println("Result Part2: $result")
}

fun countArrangements(row: String, groups: List<Int>): Int {
    // Function to check if a given arrangement fits the criteria
    fun isValid(arrangement: String): Boolean {
        val matches = demagedRgx.findAll(arrangement).map { it.value.length }.toList()
        if (matches.size != groups.size) return false

        return matches.zip(groups).all { it.first == it.second }
    }

    // Function to generate all possible arrangements
    fun generateArrangements(arrangement: String, index: Int): Int {
        if (index == row.length) {
            return if (isValid(arrangement)) 1 else 0
        }
        if (row[index] == '?') {
            return generateArrangements("$arrangement.", index + 1) +
                    generateArrangements("$arrangement#", index + 1)
        }
        return generateArrangements(arrangement + row[index], index + 1)
    }

    return generateArrangements("", 0)
}