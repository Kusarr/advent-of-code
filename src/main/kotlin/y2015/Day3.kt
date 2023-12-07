package y2015

import FileUtil

fun main() {
    val lines = FileUtil().readLines("2015/day3-input.txt")
    part1(lines)
    part2(lines)
}

private fun part1(lines: List<String>) {
    val array: Array<Array<Int>> = Array(lines[0].length) { Array(lines[0].length) { 0 } }
    runTheMap(lines, lines[0], array)

    val result = array.sumOf { row -> row.count { it > 0 } }

    println("Result Part1: $result")
}

private fun part2(lines: List<String>) {
    val array: Array<Array<Int>> = Array(lines[0].length) { Array(lines[0].length) { 0 } }
    val santa = lines[0].filterIndexed { index, _ -> index % 2 == 0 }
    val robot = lines[0].filterIndexed { index, _ -> index % 2 != 0 }

    listOf(santa, robot).forEach { runTheMap(lines, it, array) }

    val result = array.sumOf { row -> row.count { it > 0 } }

    println("Result Part2: $result")
}

private fun runTheMap(lines: List<String>, it: String, array: Array<Array<Int>>) {
    var x = lines[0].length / 2
    var y = x

    it.forEach { char ->
        array[x][y]++
        when (char) {
            '^' -> y++
            '>' -> x++
            'v' -> y--
            '<' -> x--
        }
    }
}