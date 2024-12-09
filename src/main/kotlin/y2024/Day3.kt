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

    var enabled = true
    var result = 0L
    mulRegex.findAll(lines.joinToString())
        .map { it.value }
        .forEach { instruction ->
            when {
                instruction == "do()" -> enabled = true
                instruction == "don't()" -> enabled = false
                instruction.startsWith("mul") && enabled -> result += evalMul(instruction)
            }
            println(result)
        }

    println("Result Part2: $result")
}

private fun evalMul(mul: String): Int {
    return positivNumberRegex.findAll(mul)
        .map { it.value.toInt() }
        .take(2)
        .reduce { a, b -> a * b }
}