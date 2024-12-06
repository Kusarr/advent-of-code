package y2024

import FileUtil
import spaceRegex
import kotlin.math.abs

fun main() {
    val lines = FileUtil().readLines("2024/day1-input.txt")
    part1(lines)
    part2(lines)
}

private fun part1(lines: List<String>) {
    val left = mutableListOf<Int>()
    val right = mutableListOf<Int>()

    lines.map { l -> l.split(spaceRegex) }
        .forEach { l -> left.add(l[0].toInt()); right.add(l[1].toInt()) }

    left.sort()
    right.sort()

    val result = left.zip(right).sumOf { (l, r) -> abs(l - r) }
    println("Result Part1: $result")
}

private fun part2(lines: List<String>) {
    val left = mutableListOf<Int>()
    val right = mutableMapOf<Int, Int>()

    lines.map { l -> l.split(spaceRegex) }
        .forEach { l ->
            left.add(l[0].toInt())
            right[l[1].toInt()] = right.getOrDefault(l[1].toInt(), 0) + 1
        }

    val result = left.sumOf { l -> l * right.getOrDefault(l, 0) }

    println("Result Part2: $result")
}