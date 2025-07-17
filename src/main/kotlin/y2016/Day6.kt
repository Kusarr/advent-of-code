package y2016

import FileUtil

fun main() {
    val lines = FileUtil().readLines("2016/day6-input.txt")
    part1(lines)
    part2(lines)
}

private fun part1(lines: List<String>) {
    val charCounting = countCharOccurences(lines)
    val result = charCounting.map { it.maxBy { it.value }.key }.joinToString("")

    println("Result Part1: $result")
}

private fun part2(lines: List<String>) {
    val charCounting = countCharOccurences(lines)
    val result = charCounting.map { it.minBy { it.value }.key }.joinToString("")

    println("Result Part2: $result")
}

private fun countCharOccurences(lines: List<String>): Array<MutableMap<Char, Int>> {
    val charCounting = Array(lines[0].length) { mutableMapOf<Char, Int>() }

    lines.forEach {
        it.onEachIndexed { index, c ->
            val map = charCounting[index]
            map[c] = (map[c] ?: 0) + 1
        }
    }

    return charCounting
}