package y2016

import FileUtil
import numberRegex

fun main() {
    val lines = FileUtil().readLines("2016/day3-input.txt")
    part1(lines)
    part2(lines)
}

private fun part1(lines: List<String>) {
    val result = lines.asSequence()
        .map { line -> numberRegex.findAll(line).map { it.value.toInt() } }
        .map { isTriangle(it.toList()) }
        .count { it }

    println("Result Part1: $result")
}

private fun part2(lines: List<String>) {
    val result = lines.asSequence()
        .map { numberRegex.findAll(it).map { m -> m.value.toInt() }.toList() }
        .chunked(3)  // group rows into groups of 3
        .flatMap { triple ->
            (0..2).map { i ->
                listOf(triple[0][i], triple[1][i], triple[2][i])
            }
        }
        .count { isTriangle(it) }

    println("Result Part2: $result")
}

private fun isTriangle(numbers: List<Int>): Boolean {
    return numbers[0] + numbers[1] > numbers[2]
            && numbers[0] + numbers[2] > numbers[1]
            && numbers[1] + numbers[2] > numbers[0]
}