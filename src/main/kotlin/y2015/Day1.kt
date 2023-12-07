package y2015

import FileUtil

fun main() {
    val lines = FileUtil().readLines("2015/day1-input.txt")
    part1(lines)
    part2(lines)
}

private fun part1(lines: List<String>) {
    val result = lines[0].count { it == '(' } - lines[0].count { it == ')' }

    println("Result Part1: $result")
}

private fun part2(lines: List<String>) {
    var result = 0
    var floor = 0
    for ((index, it) in lines[0].withIndex()) {
        if (it == '(') floor++ else floor--
        if (floor < 0) {
            result = index + 1
            break
        }
    }

    println("Result Part2: $result")
}