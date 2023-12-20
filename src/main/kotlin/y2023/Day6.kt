package y2023

import FileUtil
import positivNumberRegex

fun main() {
    val lines = FileUtil().readLines("2023/day6-input.txt")
    part1(lines)
    part2(lines)
}

private fun part1(lines: List<String>) {
    val times = positivNumberRegex.findAll(lines[0]).map { it.value.toLong() }
    val distances = positivNumberRegex.findAll(lines[1]).map { it.value.toLong() }.toList()

    val result = times.mapIndexed { index, maxTime ->
        getNumberOfSolutions(maxTime, distances[index])
    }.reduce(Int::times)

    println("Result Part1: $result")
}

private fun part2(lines: List<String>) {
    val maxTime = positivNumberRegex.findAll(lines[0]).joinToString("") { it.value }.toLong()
    val distance = positivNumberRegex.findAll(lines[1]).joinToString("") { it.value }.toLong()

    val result = getNumberOfSolutions(maxTime, distance)

    println("Result Part2: $result")
}

private fun getNumberOfSolutions(maxTime: Long, distance: Long): Int {
    return (2 until maxTime).count { (maxTime - it) * it > distance }
}
