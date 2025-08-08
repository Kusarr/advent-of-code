package y2017

import FileUtil
import spaceRegex

fun main() {
    val lines = FileUtil().readLines("2017/day2-input.txt")
    part1(lines)
    part2(lines)
}

private fun part1(lines: List<String>) {
    val result = lines
        .map { spaceRegex.split(it) }
        .map { it.map { it.toInt() } }
        .sumOf { it.max() - it.min() }

    println("Result Part1: $result")
}

private fun part2(lines: List<String>) {
    val result = lines
        .map { spaceRegex.split(it) }
        .map { it.map { it.toInt() } }
        .sumOf { getEvenDivisionResult(it) }

    println("Result Part2: $result")
}

private fun getEvenDivisionResult(numbers: List<Int>): Int {
    for (i in numbers.indices) {
        for (j in numbers.indices) {
            if (i != j) {
                val a = numbers[i]
                val b = numbers[j]

                if (a % b == 0) {
                    return a / b
                }
                if (b % a == 0) {
                    return b / a
                }
            }
        }
    }
    throw IllegalArgumentException("Invalid number")
}