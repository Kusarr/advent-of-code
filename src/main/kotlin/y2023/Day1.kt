package y2023

import FileUtil
import digitRegex

fun main() {
    part1()
    part2()
}

private fun part1() {
    val sum = FileUtil().readLines("2023/day1-input.txt").sumOf { line ->
        val firstDigit = digitRegex.find(line)!!.value
        val lastDigit = digitRegex.find(line.reversed())!!.value
        "$firstDigit$lastDigit".toInt()
    }

    println("Result Part1: $sum")
}

private fun part2() {
    val numberMap = mapOf(
        "1" to 1, "2" to 2, "3" to 3, "4" to 4, "5" to 5, "6" to 6, "7" to 7, "8" to 8, "9" to 9,
        "one" to 1, "two" to 2, "three" to 3, "four" to 4,
        "five" to 5, "six" to 6, "seven" to 7, "eight" to 8, "nine" to 9
    )
    val regex = Regex("(${numberMap.keys.joinToString("|")})")
    val regexReverse = Regex("(${numberMap.keys.joinToString("|").reversed()})")

    val sum = FileUtil().readLines("2023/day1-input.txt").sumOf { line ->
        val firstDigit = numberMap[regex.find(line)!!.value]
        val lastDigit = numberMap[regexReverse.find(line.reversed())!!.value.reversed()]
        "$firstDigit$lastDigit".toInt()
    }

    println("Result Part2: $sum")
}