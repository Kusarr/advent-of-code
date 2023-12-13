package y2023

import FileUtil
import kotlin.math.abs
import kotlin.math.max
import kotlin.math.min

private const val SPACE = '.'
private const val GALAXY = '#'
private var emptyRows = mutableSetOf<Int>()
private var emptyCols = mutableSetOf<Int>()

fun main() {
    val lines = FileUtil().readLines("2023/day11-input.txt")

    val grid = createGrid(lines)

    part1(grid)
    part2(grid)
}


private fun part1(grid: List<List<Char>>) {
    val galaxies = getGalaxies(grid)
    val result = evaluateTotalPath(galaxies, 2)

    println("Result Part1: $result")
}

private fun part2(grid: List<List<Char>>) {
    val galaxies = getGalaxies(grid)
    val result = evaluateTotalPath(galaxies, 1_000_000)

    println("Result Part2: $result")
}

private fun evaluateTotalPath(galaxies: List<Pair<Int, Int>>, emptySpaceFactor: Int): Long {
    var shortestPathTotal = 0L

    for (i in galaxies.indices) {
        galaxies.drop(i + 1).forEach {
            val xRange = IntRange(min(galaxies[i].first, it.first), max(galaxies[i].first, it.first))
            val yRange = IntRange(min(galaxies[i].second, it.second), max(galaxies[i].second, it.second))
            val countEmptyCols = xRange.count { range -> emptyCols.contains(range) }
            val countEmptyRows = yRange.count { range -> emptyRows.contains(range) }
            val additionalDistance = (countEmptyCols + countEmptyRows) * emptySpaceFactor

            val distance = abs(galaxies[i].first - it.first) + abs(galaxies[i].second - it.second)
            shortestPathTotal += distance + additionalDistance - countEmptyCols - countEmptyRows
        }
    }
    return shortestPathTotal
}

private fun getGalaxies(grid: List<List<Char>>): List<Pair<Int, Int>> {
    val galaxies = grid.flatMapIndexed { rowIndex, row ->
        row.mapIndexedNotNull { colIndex, value ->
            if (value == GALAXY) colIndex to rowIndex else null
        }
    }
    return galaxies
}

private fun createGrid(lines: List<String>): MutableList<MutableList<Char>> {
    val grid = mutableListOf<MutableList<Char>>()
    lines.forEach { line ->
        grid.add(line.toMutableList())
    }

    for (i in 0..<grid.size) {
        if (grid[i].all { it == SPACE }) emptyRows.add(i)
    }
    for (i in 0..<grid[0].size) {
        if (grid.all { it[i] == SPACE }) emptyCols.add(i)
    }

    return grid
}