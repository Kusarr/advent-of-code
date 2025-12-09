package y2025

import FileUtil

fun main() {
    val lines = FileUtil().readLines("2025/day7-input.txt")
    part1(lines)
    part2(lines)
}

private fun part1(lines: List<String>) {
    var rays = mutableSetOf<Int>()
    rays.add(lines[0].indexOf('S'))

    var result = 0

    lines.forEach { line ->
        val newRays = mutableSetOf<Int>()
        line.forEachIndexed { i, char ->
            if (char == '^' && rays.contains(i)) {
                newRays.add(i - 1)
                newRays.add(i + 1)
                result++
            } else if (rays.contains(i)) {
                newRays.add(i)
            }
        }
        rays = newRays
    }

    println("Result Part1: $result")
}

private fun part2(lines: List<String>) {
    var rays = MutableList(lines[0].length) { 0L }
    rays[(lines[0].indexOf('S'))] = 1


    lines.forEach { line ->
        val newRays = MutableList(lines[0].length) { 0L }
        line.forEachIndexed { i, char ->
            if (char == '^' && rays[i] > 0) {
                newRays[i - 1] += rays[i]
                newRays[i + 1] += rays[i]
            } else if (rays[i] > 0) {
                newRays[i] += rays[i]
            }
        }

        // debug
        println(rays)
        println(line.toCharArray().toList())
        println(newRays)
        println()

        rays = newRays
    }

    val result = rays.sum()
    println("Result Part2: $result")
}