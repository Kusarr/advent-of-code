package y2023

import FileUtil

fun main() {
    val lines = FileUtil().readLines("2023/day10-input.txt")

    val grid = Array(lines.size) { CharArray(lines[0].length) }
    lines.forEachIndexed { i, row -> row.forEachIndexed { j, col -> grid[i][j] = col } }

    part1(grid)
    part2(grid)
}

private fun part1(grid: Array<CharArray>) {
    val finalGrid = buildMap(grid)

    val result = finalGrid.flatMap { it.toList() }.count { it != '.' } / 2

    println("Result Part1: $result")
}

private fun part2(grid: Array<CharArray>) {
    val finalGrid = buildMap(grid)

    // TODO
    val result = null
    println("Result Part2: $result")
}

private fun buildMap(grid: Array<CharArray>): Array<CharArray> {
    val start = grid.withIndex().find { row -> 'S' in row.value }?.let { Pair(it.index, it.value.indexOf('S')) }!!

    val finalGrid = Array(grid.size) { CharArray(grid[0].size) { '.' } }
    finalGrid[start.first][start.second] = 'S'

    var (y, x, comingFrom) = getStartingDirection(grid, start)

    while (grid[y][x] != 'S') {
        finalGrid[y][x] = grid[y][x]
        when (grid[y][x]) {
            '|' -> {
                if (comingFrom == Direction.NORTH) y++ else y--
                continue
            }

            '-' -> {
                if (comingFrom == Direction.WEST) x++ else x--
                continue
            }

            'F' -> {
                if (comingFrom == Direction.SOUTH) {
                    x++
                    comingFrom = Direction.WEST
                } else {
                    y++
                    comingFrom = Direction.NORTH
                }
                continue
            }

            '7' -> {
                if (comingFrom == Direction.WEST) {
                    y++
                    comingFrom = Direction.NORTH
                } else {
                    x--
                    comingFrom = Direction.EAST
                }
                continue
            }

            'J' -> {
                if (comingFrom == Direction.NORTH) {
                    x--
                    comingFrom = Direction.EAST
                } else {
                    y--
                    comingFrom = Direction.SOUTH
                }
                continue
            }

            'L' -> {
                if (comingFrom == Direction.NORTH) {
                    x++
                    comingFrom = Direction.WEST
                } else {
                    y--
                    comingFrom = Direction.SOUTH
                }
                continue
            }
        }
    }

    return finalGrid
}

private fun getStartingDirection(grid: Array<CharArray>, start: Pair<Int, Int>): Triple<Int, Int, Direction> {
    val north = grid[start.first - 1][start.second]
    if (north == '|' || north == 'F' || north == '7') {
        return Triple(start.first - 1, start.second, Direction.SOUTH)
    }
    val east = grid[start.first][start.second + 1]
    if (east == '-' || east == '7' || east == 'J') {
        return Triple(start.first, start.second + 1, Direction.WEST)
    }
    val south = grid[start.first + 1][start.second]
    if (south == '|' || south == 'L' || south == 'J') {
        return Triple(start.first + 1, start.second, Direction.NORTH)
    }
    return Triple(start.first, start.second - 1, Direction.EAST)
}

private enum class Direction {
    NORTH, SOUTH, EAST, WEST
}

