package y2025

import FileUtil

private val line = FileUtil().readLines("2025/day2-input.txt")[0]

private val part1Regex = Regex("^(\\d+)\\1\$")
private val part2Regex = Regex("^(\\d+)(\\1)+\$")

fun main() {
    part1()
    part2()
}

private fun part1() {
    val result = sumUpInvalidIds(part1Regex)
    println("Result Part1: $result")
}

private fun part2() {
    val result = sumUpInvalidIds(part2Regex)
    println("Result Part2: $result")
}

private fun sumUpInvalidIds(regex: Regex): Long {
    return line.split(",")
        .map { it.split("-") }
        .map { LongRange(it[0].toLong(), it[1].toLong()) }
        .sumOf { range ->
            range.filter { regex.matches(it.toString()) }.sum()
        }
}