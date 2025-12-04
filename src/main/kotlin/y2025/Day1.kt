package y2025

import FileUtil

fun main() {
    val lines = FileUtil().readLines("2025/day1-input.txt")
    part1(lines)
    part2(lines)
}

private fun part1(lines: List<String>) {
    var dial = 50
    var result = 0

    for (line in lines) {
        val direction = line[0]
        var distance = line.drop(1).toInt()

        if (direction == 'L') distance = -distance

        dial = Math.floorMod(dial + distance, 100)

        if (dial == 0) result++
    }

    println("Result Part1: $result")
}

private fun part2(lines: List<String>) {
    var dial = 50
    var result = 0

    for (line in lines) {
        val dir = line[0]
        val dist = line.substring(1).toInt()

        val step = if (dir == 'L') -1 else 1

        repeat(dist) {
            dial = (dial + step + 100) % 100
            if (dial == 0) result++
        }
    }

    println("Result Part2: $result")
}