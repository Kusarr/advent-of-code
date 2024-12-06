package y2024

import FileUtil
import spaceRegex
import kotlin.math.abs

fun main() {
    val lines = FileUtil().readLines("2024/day2-input.txt")
    part1(lines)
    part2(lines)
}

private fun part1(lines: List<String>) {
    val result = lines
        .map { line -> line.split(spaceRegex).map { it.toInt() } }
        .sumOf { if (levelsCorrect(it)) 1L else 0 }

    println("Result Part1: $result")
}

private fun part2(lines: List<String>) {
    val result = lines
        .map { line -> line.split(spaceRegex).map { it.toInt() } }
        .sumOf { levels ->
            outer@ for (skip in -1 until levels.size) {
                val innerLevels = levels.toMutableList()
                if (skip >= 0) innerLevels.removeAt(skip)
                if (levelsCorrect(innerLevels)) return@sumOf 1L
            }
            0
        }

    println("Result Part2: $result")
}

private fun levelsCorrect(levels: List<Int>): Boolean {
    var incPrev: Boolean? = null
    for (i in 0 until levels.size - 1) {
        val dist = levels[i] - levels[i + 1]
        val abs = abs(dist)
        val inc = dist < 0

        if (incPrev != null && incPrev != inc) return false
        if (abs < 1 || abs > 3) return false
        incPrev = inc
    }
    return true
}