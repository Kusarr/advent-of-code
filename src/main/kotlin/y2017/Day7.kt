package y2017

import FileUtil
import wordRegex

fun main() {
    val lines = FileUtil().readLines("2017/day7-input.txt")
    part1(lines)
    part2(lines)
}

private fun part1(lines: List<String>) {
    val tree = mutableMapOf<String, Boolean>()

    lines.forEach { line ->
        val discs = wordRegex.findAll(line).map { it.value }.toList()
        tree.putIfAbsent(discs[0], false)
        if (discs.size > 1) {
            discs.drop(1).forEach { tree[it] = true }
        }
    }

    val result = tree.entries.first { !it.value }.key
    println("Result Part1: $result")
}

private fun part2(lines: List<String>) {
    val result = null

    println("Result Part2: $result")
}