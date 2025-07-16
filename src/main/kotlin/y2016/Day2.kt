package y2016

import FileUtil
import kotlin.math.abs
import kotlin.math.max
import kotlin.math.min

fun main() {
    val lines = FileUtil().readLines("2016/day2-input.txt")
    part1(lines)
    part2(lines)
}

private fun part1(lines: List<String>) {
    val code = mutableListOf<Pair<Int, Int>>()
    var x = 1
    var y = 1
    lines.forEach { line ->
        line.toCharArray().forEach { char ->
            when (char) {
                'U' -> y = min(2, y + 1)
                'D' -> y = max(0, y - 1)
                'R' -> x = min(2, x + 1)
                'L' -> x = max(0, x - 1)
            }
        }
        code.add(x to y)
    }

    println("Result Part1: $code")
}

private fun part2(lines: List<String>) {
    val code = mutableListOf<Pair<Int, Int>>()
    var x = -2
    var y = 0
    lines.forEach { line ->
        line.toCharArray().forEach { char ->
            when (char) {
                'U' -> y = min(getMax(x), y + 1)
                'D' -> y = max(getMax(x) * -1, y - 1)
                'R' -> x = min(getMax(y), x + 1)
                'L' -> x = max(getMax(y) * -1, x - 1)
            }
        }
        code.add(x to y)
    }

    println("Result Part2: $code")
}

private fun getMax(otherAxis: Int): Int {
    return when (abs(otherAxis)) {
        0 -> 2
        1 -> 1
        2 -> 0
        else -> throw IllegalArgumentException("Houston we have a problem")
    }
}