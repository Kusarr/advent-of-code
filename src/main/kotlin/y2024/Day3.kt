package y2024

import FileUtil
import positivNumberRegex

fun main() {
    val lines = FileUtil().readLines("2024/day3-input.txt")
    part1(lines)
    part2(lines)
}


private fun part1(lines: List<String>) {
    val mulRegex = Regex("mul\\(\\d{1,3},\\d{1,3}\\)")

    val result = lines.sumOf { line ->
        mulRegex.findAll(line)
            .map { evalMul(it.value) }
            .sum()
    }

    println("Result Part1: $result")
}

private fun part2(lines: List<String>) {
    val mulRegex = Regex("do\\(\\)|don't\\(\\)|mul\\(\\d{1,3},\\d{1,3}\\)")

    val result = lines.sumOf { line ->
        var enabled = true
        var sum: Long = 0
        mulRegex.findAll(line).map { it.value }.forEach {
            if (it == "do()") {
                enabled = true
                return@forEach
            }
            if (it == "don't()") {
                enabled = false
                return@forEach
            }
            if (enabled) sum += evalMul(it)
        }
        sum
    }

    println("Result Part2: $result")
}

private fun evalMul(mul: String): Int {
    return positivNumberRegex.findAll(mul)
        .map { num -> num.value.toInt() }
        .take(2)
        .reduce { d1, d2 -> d1 * d2 }
}