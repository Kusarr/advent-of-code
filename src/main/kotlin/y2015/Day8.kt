package y2015

import FileUtil

fun main() {
    val lines = FileUtil().readLines("2015/day8-input.txt")
    part1(lines)
    part2(lines)
}

private fun part1(lines: List<String>) {
    val regex = Regex("""\\"|\\{2}|\\x([0-9A-Fa-f]{2})""")
    val result = lines.sumOf { line ->
        val a = regex.replace(line, "a").replace("\"", "")
        line.length - a.length
    }

    println("Result Part1: $result")
}

private fun part2(lines: List<String>) {
    val result = lines.sumOf { line ->
        val a = line.replace("\\", "\\\\").replace("\"", "\\\"")
        a.length - line.length + 2
    }

    println("Result Part2: $result")
}