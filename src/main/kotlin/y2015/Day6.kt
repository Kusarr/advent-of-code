package y2015

import FileUtil
import numberRegex
import kotlin.math.max

fun main() {
    val lines = FileUtil().readLines("2015/day6-input.txt")
    part1(lines)
    part2(lines)
}

private fun part1(lines: List<String>) {
    val array: Array<Array<Boolean>> = Array(1000) { Array(1000) { false } }

    lines.forEach { line ->
        val coords = numberRegex.findAll(line).map { it.value.toInt() }.toList()
        val op = getOp(line)

        for (i in coords[1]..coords[3]) {
            for (j in coords[0]..coords[2]) {
                array[j][i] = op ?: !array[j][i]
            }
        }
    }

    val result = array.sumOf { row -> row.count { it } }

    println("Result Part1: $result")
}

private fun getOp(line: String): Boolean? {
    return when {
        line.contains("on") -> true
        line.contains("off") -> false
        else -> null
    }
}

private fun part2(lines: List<String>) {
    val array: Array<Array<Int>> = Array(1000) { Array(1000) { 0 } }

    lines.forEach { line ->
        val coords = numberRegex.findAll(line).map { it.value.toInt() }.toList()
        val op = getOp2(line)

        for (i in coords[1]..coords[3]) {
            for (j in coords[0]..coords[2]) {
                array[j][i] = max(0, array[j][i] + op)
            }
        }
    }

    val result = array.sumOf { row -> row.sumOf { it } }

    println("Result Part2: $result")
}

private fun getOp2(line: String): Int {
    return when {
        line.contains("on") -> 1
        line.contains("off") -> -1
        else -> 2
    }
}