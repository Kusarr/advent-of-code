package y2024

import FileUtil

private var yMax = -1
private var xMax = -1
private var grid: Array<Array<Char>> = emptyArray()

fun main() {
    val lines = FileUtil().readLines("2024/day4-input.txt")
    yMax = lines.size
    xMax = lines[0].length
    grid = Array(yMax) { Array(xMax) { ' ' } }
    lines.forEachIndexed { y, line -> line.forEachIndexed { x, letter -> grid[y][x] = letter } }

    part1(lines)
    part2(lines)
}

private fun part1(lines: List<String>) {
    var result = 0
    for (y in 0 until yMax) {
        for (x in 0 until xMax) {
            result += up(x, y)
            result += upRight(x, y)
            result += right(x, y)
            result += downRight(x, y)
            result += down(x, y)
            result += downLeft(x, y)
            result += left(x, y)
            result += upLeft(x, y)
        }

    }

    println("Result Part1: $result")
}

private fun part2(lines: List<String>) {
    var result = 0
    for (y in 1 until yMax - 1) {
        for (x in 1 until xMax - 1) {
            if (grid[y][x] != 'A') continue

            var sum1 = if (grid[y - 1][x] == 'M' && grid[y + 1][x] == 'S') 1 else 0
            sum1 += if (grid[y][x + 1] == 'M' && grid[y][x - 1] == 'S') 1 else 0
            sum1 += if (grid[y + 1][x] == 'M' && grid[y - 1][x] == 'S') 1 else 0
            sum1 += if (grid[y][x - 1] == 'M' && grid[y][x + 1] == 'S') 1 else 0

            var sum2 = if (grid[y - 1][x + 1] == 'M' && grid[y + 1][x - 1] == 'S') 1 else 0
            sum2 += if (grid[y + 1][x + 1] == 'M' && grid[y - 1][x - 1] == 'S') 1 else 0
            sum2 += if (grid[y + 1][x - 1] == 'M' && grid[y - 1][x + 1] == 'S') 1 else 0
            sum2 += if (grid[y - 1][x - 1] == 'M' && grid[y + 1][x + 1] == 'S') 1 else 0
            
            result += if (sum1 > 1 || sum2 > 1) 1 else 0
        }

    }

    println("Result Part2: $result")
}

private fun up(x: Int, y: Int): Int {
    if (y - 3 < 0) return 0
    return if (grid[y][x] == 'X' && grid[y - 1][x] == 'M' && grid[y - 2][x] == 'A' && grid[y - 3][x] == 'S') 1 else 0
}

private fun upRight(x: Int, y: Int): Int {
    if (y - 3 < 0 || grid[0].size <= x + 3) return 0
    return if (grid[y][x] == 'X' && grid[y - 1][x + 1] == 'M' && grid[y - 2][x + 2] == 'A' && grid[y - 3][x + 3] == 'S') 1 else 0
}

private fun right(x: Int, y: Int): Int {
    if (grid[0].size <= x + 3) return 0
    return if (grid[y][x] == 'X' && grid[y][x + 1] == 'M' && grid[y][x + 2] == 'A' && grid[y][x + 3] == 'S') 1 else 0
}

private fun downRight(x: Int, y: Int): Int {
    if (grid.size <= y + 3 || grid[0].size <= x + 3) return 0
    return if (grid[y][x] == 'X' && grid[y + 1][x + 1] == 'M' && grid[y + 2][x + 2] == 'A' && grid[y + 3][x + 3] == 'S') 1 else 0
}

private fun down(x: Int, y: Int): Int {
    if (grid.size <= y + 3) return 0
    return if (grid[y][x] == 'X' && grid[y + 1][x] == 'M' && grid[y + 2][x] == 'A' && grid[y + 3][x] == 'S') 1 else 0
}

private fun downLeft(x: Int, y: Int): Int {
    if (grid.size <= y + 3 || x - 3 < 0) return 0
    return if (grid[y][x] == 'X' && grid[y + 1][x - 1] == 'M' && grid[y + 2][x - 2] == 'A' && grid[y + 3][x - 3] == 'S') 1 else 0
}

private fun left(x: Int, y: Int): Int {
    if (x - 3 < 0) return 0
    return if (grid[y][x] == 'X' && grid[y][x - 1] == 'M' && grid[y][x - 2] == 'A' && grid[y][x - 3] == 'S') 1 else 0
}

private fun upLeft(x: Int, y: Int): Int {
    if (x - 3 < 0 || y - 3 < 0) return 0
    return if (grid[y][x] == 'X' && grid[y - 1][x - 1] == 'M' && grid[y - 2][x - 2] == 'A' && grid[y - 3][x - 3] == 'S') 1 else 0
}