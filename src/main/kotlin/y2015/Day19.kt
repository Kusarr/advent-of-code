package y2015

import FileUtil

private val moleculeMap = mutableMapOf<String, MutableSet<String>>()
private var input = ""

fun main() {
    val lines = FileUtil().readLines("2015/day19-input.txt")
    lines.filter { it.isNotBlank() }.forEach { line ->
        if (line.contains("=>")) {
            val (key, value) = line.split(" => ")
            moleculeMap.computeIfAbsent(key) { mutableSetOf() }.add(value)
        } else {
            input = line
        }
    }

    part1()
    part2()
}

private fun part1() {
    val result = mutableSetOf<String>()

    for (i in input.indices) {
        moleculeMap.forEach { (key, values) ->
            if (input.length - 1 > i + key.length && input.substring(i, i + key.length) == key) {
                values.forEach { result.add(input.replaceRange(i, i + key.length, it)) }
            }
        }
    }

    println("Result Part1: ${result.size}")
}

private fun part2() {
    val result = null

    println("Result Part2: $result")
}
