package y2025

import FileUtil
import kotlin.math.abs

fun main() {
    val lines = FileUtil().readLines("2025/day9-input.txt")
    part1(lines)
    part2(lines)
}

private fun part1(lines: List<String>) {
    val redTiles = lines.map { RedTile(it) }

    var result = 0L
    for (i in redTiles.indices) {
        for (j in i + 1 until redTiles.size) {
            val x = abs(redTiles[i].x - redTiles[j].x) + 1
            val y = abs(redTiles[i].y - redTiles[j].y) + 1
            result = maxOf(result, x * y)
        }
    }

    println("Result Part1: $result")
}

private fun part2(lines: List<String>) {
    val result = null

    println("Result Part2: $result")
}

private data class RedTile(val line: String) {

    val x: Long
    val y: Long

    init {
        val (x, y) = line.split(",")
        this.x = x.toLong()
        this.y = y.toLong()
    }
}