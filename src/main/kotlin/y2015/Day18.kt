package y2015

import FileUtil

private const val GRID_SIZE = 100
private const val DEBUG = true

private val grid: Array<Array<Boolean>> = Array(GRID_SIZE) { Array(GRID_SIZE) { false } }
private val nextGrid: Array<Array<Boolean>> = Array(GRID_SIZE) { Array(GRID_SIZE) { false } }

fun main() {
    val lines = FileUtil().readLines("2015/day18-input.txt")
    lines.forEachIndexed { y, line -> line.forEachIndexed { x, light -> grid[x][y] = light == '#' } }

    part1()
    part2()
}

private fun part1() {
    for (i in 1..4) {
        for (y in grid.indices) {
            for (x in grid[y].indices) {
                determineNextState(x, y)
            }
        }
        activateIteration()
    }

    val result = grid.sumOf { row -> row.count { it } }
    println("Result Part1: $result")
}

private fun activateIteration() {
    println()
    for (y in grid.indices) {
        for (x in grid[y].indices) {
            print(if (nextGrid[x][y]) '#' else '.')
            grid[x][y] = nextGrid[x][y]
        }
        println()
    }
    println()
}

private fun determineNextState(x: Int, y: Int) {
    val onNeighbors = getOnNeighbors(x, y)
    if (grid[x][y]) {
        nextGrid[x][y] = onNeighbors == 2 || onNeighbors == 3
    } else {
        nextGrid[x][y] = onNeighbors == 3
    }
}

private fun getOnNeighbors(x: Int, y: Int): Int {
    var countOn = 0
    for (i in -1..1) {
        if (x + i < 0 || x + i >= GRID_SIZE) {
            continue
        }
        for (j in -1..1) {
            if (y + j < 0 || y + j >= GRID_SIZE || (i == 0 && j == 0)) {
                continue
            }
            if (grid[x + i][y + j]) countOn++
        }
    }
    return countOn
}

private fun part2() {
    val result = null

    println("Result Part2: $result")
}