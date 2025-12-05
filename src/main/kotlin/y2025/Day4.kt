package y2025

import FileUtil
import kotlin.math.max
import kotlin.math.min

// grid[y][x]
private var grid: Array<Array<Char>> = emptyArray()

fun main() {
    val lines = FileUtil().readLines("2025/day4-input.txt")

    part1(lines)
    part2(lines)
}

private fun part1(lines: List<String>) {
    init(lines)

    val result = markPapersToRemove()
    println("Result Part1: $result")
}

private fun part2(lines: List<String>) {
    init(lines)

    var result = 0

    var removedCount: Int
    do {
        removedCount = markPapersToRemove()
        result += removedCount

        // clean up
        for (y in grid.indices)
            for (x in grid[y].indices)
                if (grid[y][x] == 'X') grid[y][x] = '.'

    } while (removedCount > 0)

    println("Result Part2: $result")
}

private fun markPapersToRemove(): Int {
    var removedCount = 0
    grid.forEachIndexed { y, row ->
        row.forEachIndexed inner@{ x, currentChar ->
            if (grid[y][x] != '@') return@inner
            val xRange = IntRange(max(0, x - 1), min(grid.size - 1, x + 1))
            val yRange = IntRange(max(0, y - 1), min(row.size - 1, y + 1))

            var paperCount = 0
            yRange.forEach { iy ->
                xRange.forEach { ix ->
                    if (!(iy == y && ix == x) && (grid[iy][ix] == '@' || grid[iy][ix] == 'X')) paperCount++
                }
            }
            if (paperCount < 4) {
                grid[y][x] = 'X'
                removedCount++
            }
        }
    }
    return removedCount
}

private fun init(lines: List<String>) {
    grid = Array(lines.size) { Array(lines[0].length) { ' ' } }
    lines.forEachIndexed { i, line ->
        line.forEachIndexed { j, ch ->
            grid[i][j] = ch
        }
    }
}