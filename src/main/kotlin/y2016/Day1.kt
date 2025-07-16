package y2016

import FileUtil
import kotlin.math.abs


fun main() {
    val line = FileUtil().readLines("2016/day1-input.txt")[0].split(",").map { it.trim() }
    part1(line)
    part2(line)
}

private fun part1(lines: List<String>) {
    val directions = mutableListOf(0, 0, 0, 0) // N,E,S,W
    var currentDir = 0

    lines.forEach {
        currentDir = if (it[0] == 'L') (currentDir - 1).sizedIndex(4) else (currentDir + 1).sizedIndex(4)
        directions[currentDir] = directions[currentDir] + it.substring(1).toInt()
    }

    val result = abs(directions[0] - directions[2]) + abs(directions[1] - directions[3])

    println("Result Part1: $result")
}

private fun part2(lines: List<String>) {
    val size = 500
    val startPoint = size / 2
    val coordinates: Array<Array<Char>> = Array(size) { Array(size) { '□' } }
    var x = startPoint
    var y = startPoint
    coordinates[y][x] = 'X'

    var currentDir = 0

    lines.forEach {
        currentDir = if (it[0] == 'L') (currentDir - 1).sizedIndex(4) else (currentDir + 1).sizedIndex(4)

        for (i in 1..it.substring(1).toInt()) {
            when (currentDir) {
                0 -> y--
                1 -> x++
                2 -> y++
                3 -> x--
            }
            if (coordinates[y][x] == 'X') {
                val result = abs(x - startPoint) + abs(y - startPoint)
                println("Result Part2: $result")
                return
            }

            coordinates[y][x] = 'X'
        }

    }
    throw IllegalStateException("No location reached two times. Should not happen!")
}

fun Int.sizedIndex(size: Int): Int {
    if (this < 0) return size - 1
    if (this > 3) return 0
    return this
}