package y2017

import FileUtil
import spaceRegex

fun main() {
    val lines = FileUtil().readLines("2017/day4-input.txt")
    part1(lines)
    part2(lines)
}

private fun part1(lines: List<String>) {
    val result = lines.count {
        spaceRegex.split(it).groupingBy { it }.eachCount().maxBy { it.value }.value == 1
    }
    println("Result Part1: $result")
}

private fun part2(lines: List<String>) {
    val result = lines.count {
        spaceRegex.split(it)
            .map { it.toCharArray().sorted().joinToString("") }
            .groupingBy { it }.eachCount().maxBy { it.value }.value == 1
    }

    println("Result Part2: $result")
}