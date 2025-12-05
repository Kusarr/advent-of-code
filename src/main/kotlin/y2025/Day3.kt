package y2025

import FileUtil

fun main() {
    val lines = FileUtil().readLines("2025/day3-input.txt")
    part1(lines)
    part2(lines)
}

private fun part1(lines: List<String>) {
    var result = 0
    lines.forEach { line ->
        val sortedDigits = line.dropLast(1).toSortedSet().reversed()
        val firstigitIndex = line.indexOf(sortedDigits.first())

        val firstDigit = line[firstigitIndex]
        val secondDigit = line.substring(firstigitIndex + 1).toSortedSet().last()

        result += "$firstDigit$secondDigit".toInt()
    }

    println("Result Part1: $result")
}

private fun part2(lines: List<String>) {
    val joltageLength = 12
    var result = 0L

    lines.forEach { line ->
        val sortedDigits = line.dropLast(joltageLength - 1).toSortedSet().reversed()
        val firstigitIndex = line.indexOf(sortedDigits.first())

        var joltage = line.substring(firstigitIndex)
        outer@ while (joltage.length > 12) {
            for ((index, ch) in joltage.withIndex()) {
                if (index + 1 < joltage.length && joltage[index + 1] > ch) {
                    joltage = joltage.replaceFirst(ch.toString(), "")
                    continue@outer
                }
            }
            // nothing got dropped so just drop the last digits so it has the right length
            joltage = joltage.dropLast(joltage.length - joltageLength)
        }
        result += joltage.toLong()
    }

    println("Result Part2: $result")
}