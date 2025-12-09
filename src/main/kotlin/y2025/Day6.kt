package y2025

import FileUtil
import spaceRegex

fun main() {
    val lines = FileUtil().readLines("2025/day6-input.txt")
    part1(lines)
    part2(lines)
}

private fun part1(lines: List<String>) {
    val tasks = MutableList(spaceRegex.split(lines[0].trim()).size) { mutableListOf<Int>() }

    lines.dropLast(1).forEach { line ->
        spaceRegex.split(line.trim()).forEachIndexed { i, value -> tasks[i].add(value.toInt()) }
    }

    val result = spaceRegex.split(lines.last().trim()).mapIndexed { i, operand ->
        when (operand) {
            "*" -> tasks[i].fold(1L) { acc, v -> acc * v }
            "+" -> tasks[i].sumOf { it.toLong() }
            else -> throw IllegalArgumentException("Unrecognised operand: $operand")
        }
    }.sum()

    println("Result Part1: $result")
}

private fun part2(lines: List<String>) {
    val operands = spaceRegex.split(lines.last().trim()).toMutableList()

    var result: Long = 0L

    var index = 0
    var currentNumber: Long? = null
    do {
        var stringNumber = ""
        for (line in 0..<lines.lastIndex) {
            if (lines[line].length > index) {
                val s = lines[line][index]
                if (s != ' ') stringNumber += s
            }
        }

        if (stringNumber.isBlank()) {
            operands.removeAt(0)
            result += currentNumber!!
            currentNumber = null
        } else {
            if (currentNumber == null) currentNumber = stringNumber.toLong()
            else {
                when (operands.first()) {
                    "*" -> currentNumber *= stringNumber.toLong()
                    "+" -> currentNumber += stringNumber.toLong()
                    else -> throw IllegalArgumentException("Unrecognised operand.")
                }
            }
        }
        index++
    } while (operands.isNotEmpty())

    println("Result Part2: $result")
}