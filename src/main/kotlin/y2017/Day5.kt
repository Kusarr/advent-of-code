package y2017

import FileUtil

fun main() {
    val lines = FileUtil().readLines("2017/day5-input.txt")
        .map { it.toInt() }.toList()
    part1(lines)
    part2(lines)
}

private fun part1(lines: List<Int>) {
    val steps = countSteps(lines) { jumps, index, _ -> jumps[index]++ }
    println("Result Part1: $steps")
}

private fun part2(lines: List<Int>) {
    val steps = countSteps(lines) { jumps, index, currNumber ->
        if (currNumber > 2) jumps[index]-- else jumps[index]++
    }

    println("Result Part2: $steps")
}

private fun countSteps(lines: List<Int>, updateRule: (MutableList<Int>, Int, Int) -> Unit): Int {
    val jumps = lines.toMutableList()
    var index = 0
    var steps = 0

    while (index >= 0 && index < jumps.size) {
        val currNumber = jumps[index]
        updateRule(jumps, index, currNumber)
        index += currNumber
        steps++
    }

    return steps
}