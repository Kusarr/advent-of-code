package y2017

import FileUtil

fun main() {
    val line = FileUtil().readLines("2017/day1-input.txt")[0]
    part1(line)
    part2(line)
}

private fun part1(line: String) {
    val result = (line + line[0]).zipWithNext()
        .sumOf { (a, b) -> if (a == b) a.digitToInt() else 0 }

    println("Result Part1: $result")
}

private fun part2(line: String) {
    val halfs = line.chunked(line.length / 2)
    val result = halfs[0].zip(halfs[1])
        .sumOf { (a, b) -> if (a == b) a.digitToInt() else 0 } * 2

    println("Result Part2: $result")
}