package y2023

import FileUtil

fun main() {
    val lines = FileUtil().readLines("2023/day3-input.txt")
    part1(lines)
    part2(lines)
}

val numRegex = Regex("\\d+")

private fun part1(lines: List<String>) {
    val symbolRegex = Regex("[^.\\d\\s]")

    var result = 0
    for (i in lines.indices) {
        val symbolIndices = getLinesToCheck(i, lines.size).distinct().flatMap { symLine ->
            symbolRegex.findAll(lines[symLine]).map { it.range.first }
        }

        numRegex.findAll(lines[i]).forEach { number ->
            if (symbolIndices.any { it in IntRange(number.range.first - 1, number.range.last + 1) }) {
                result += number.value.toInt()
            }
        }
    }

    println("Result Part1: $result")
}

private fun part2(lines: List<String>) {
    val gearRegex = Regex("\\*")

    var result = 0
    for (i in lines.indices) {
        val numberIndices = getLinesToCheck(i, lines.size).distinct().flatMap { symLine ->
            numRegex.findAll(lines[symLine])
                .map { NumberToRange(it.value.toInt(), diagonalRange(it.range)) }
        }

        gearRegex.findAll(lines[i]).forEach { gear ->
            val numbers = numberIndices.filter { it.range.contains(gear.range.first) }
            if (numbers.size == 2) {
                result += numbers[0].number * numbers[1].number
            }
        }
    }

    println("Result Part2: $result")
}

private fun diagonalRange(range: IntRange): IntRange {
    return IntRange(range.first - 1, range.last + 1)
}

private fun getLinesToCheck(index: Int, listSize: Int): List<Int> {
    return listOf(Math.max(index - 1, 0), index, Math.min(index + 1, listSize - 1)).distinct()
}

private class NumberToRange(val number: Int, val range: IntRange)
