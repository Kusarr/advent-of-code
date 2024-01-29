package y2015

import FileUtil

private const val GRID_SIZE = 100
private const val DEBUG = false
private const val ITERATIONS = 100

private val grid: Array<Array<Boolean>> = Array(GRID_SIZE) { Array(GRID_SIZE) { false } }
private val nextGrid: Array<Array<Boolean>> = Array(GRID_SIZE) { Array(GRID_SIZE) { false } }
private var cornersAlwaysOn = false

fun main() {
    val lines = FileUtil().readLines("2015/day18-input.txt")

    lines.forEachIndexed { y, line -> line.forEachIndexed { x, light -> grid[x][y] = light == '#' } }
    part1()

    lines.forEachIndexed { y, line -> line.forEachIndexed { x, light -> grid[x][y] = light == '#' } }
    part2()
}

private fun part1() {
    repeat(ITERATIONS) { iterateStep() }

    val result = grid.sumOf { row -> row.count { it } }
    println("Result Part1: $result")
}

private fun part2() {
    cornersAlwaysOn = true
    repeat(ITERATIONS) { iterateStep() }

    val result = grid.sumOf { row -> row.count { it } }
    println("Result Part2: $result")
}

private fun iterateStep() {
    if (cornersAlwaysOn) cornersOn()
    for (y in grid.indices) {
        for (x in grid[y].indices) {
            determineNextState(x, y)
        }
    }
    activateIteration()
    if (cornersAlwaysOn) cornersOn()
}

private fun activateIteration() {
    if (DEBUG) println()
    for (y in grid.indices) {
        for (x in grid[y].indices) {
            if (DEBUG) print(if (nextGrid[x][y]) '#' else '.')
            grid[x][y] = nextGrid[x][y]
        }
        if (DEBUG) println()
    }
    if (DEBUG) println()
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

private fun cornersOn() {
    grid[0][0] = true
    grid[0][GRID_SIZE - 1] = true
    grid[GRID_SIZE - 1][0] = true
    grid[GRID_SIZE - 1][GRID_SIZE - 1] = true
}