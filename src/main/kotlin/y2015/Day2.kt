package y2015

import FileUtil

fun main() {
    val lines = FileUtil().readLines("2015/day2-input.txt")
    part1(lines)
    part2(lines)
}

private fun part1(lines: List<String>) {
    val result = getDimensions(lines).sumOf {
        2 * it[0] * it[1] + 2 * it[1] * it[2] + 2 * it[2] * it[0] + it.sorted().dropLast(1).reduce { a, b -> a * b }
    }

    println("Result Part1: $result")
}

private fun part2(lines: List<String>) {
    val result = getDimensions(lines).sumOf {
        it.reduce { a, b -> a * b } + it.sorted().dropLast(1).reduce { a, b -> a + a + b + b }
    }

    println("Result Part2: $result")
}

private fun getDimensions(lines: List<String>) = lines.map { line ->
    line.split('x').map { it.toInt() }
}