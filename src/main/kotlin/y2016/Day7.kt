package y2016

import FileUtil

private val positiveABBARegex = Regex("""(.)(?!\1)(.)\2\1""")
private val negativeABBARegex = Regex("""\[(?:[^\[\]]*?)(.)(?!\1)(.)\2\1(?:[^\[\]]*?)\]""")
private val valuesInsideBracketsRegex = Regex("\\[([^]]+)]")

fun main() {
    val lines = FileUtil().readLines("2016/day7-input.txt")
    part1(lines)
    part2(lines)
}

private fun part1(lines: List<String>) {
    val result = lines.count { !negativeABBARegex.containsMatchIn(it) && positiveABBARegex.containsMatchIn(it) }
    println("Result Part1: $result")
}

private fun part2(lines: List<String>) {
    val result = lines.count { isABA(it) }
    println("Result Part2: $result")
}

private fun isABA(line: String): Boolean {
    val valsInsideBrackets = valuesInsideBracketsRegex.findAll(line)
        .map { it.groupValues[1] }
        .flatMap { findAllOverlappingABAs(it) }
        .map { "${it[1]}${it[0]}${it[1]}" } // invert
        .toSet()
    val valsOutsideBrackets = Regex("(?:^|])([^\\[]+)(?=\\[|$)").findAll(line)
        .map { it.groupValues[1] }
        .toSet()

    return valsOutsideBrackets.any { item1 -> valsInsideBrackets.any { item2 -> item1.contains(item2) } }
}

private fun findAllOverlappingABAs(s: String): List<String> {
    val result = mutableListOf<String>()
    for (i in 0 until s.length - 2) {
        val a = s[i]
        val b = s[i + 1]
        val c = s[i + 2]
        if (a == c && a != b) {
            result.add("$a$b$c")
        }
    }
    return result
}