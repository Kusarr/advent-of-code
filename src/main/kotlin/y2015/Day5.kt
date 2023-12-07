package y2015

import FileUtil

fun main() {
    val lines = FileUtil().readLines("2015/day5-input.txt")
    part1(lines)
    part2(lines)
}

private fun part1(lines: List<String>) {
    val vowelRegex = Regex("[aeiou]")
    val doubleCharRegex = Regex("(.)\\1")
    val forbiddenRegex = Regex("ab|cd|pq|xy")

    val result = lines
        .filter { vowelRegex.findAll(it).count() > 2 }
        .filter { doubleCharRegex.containsMatchIn(it) }
        .count { !forbiddenRegex.containsMatchIn(it) }

    println("Result Part1: $result")
}

private fun part2(lines: List<String>) {
    val regex1 = Regex("(\\w{2})(?=.*\\1)")
    val regex2 = Regex("(\\w)\\w\\1")

    val result = lines
        .filter { regex1.containsMatchIn(it) }
        .count { regex2.containsMatchIn(it) }

    println("Result Part2: $result")
}