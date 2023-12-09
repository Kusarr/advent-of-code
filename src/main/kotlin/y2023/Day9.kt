package y2023

import FileUtil

fun main() {
    val lines = FileUtil().readLines("2023/day9-input.txt")
    part1(lines)
    part2(lines)
}

private fun part1(lines: List<String>) {
    val result = lines.sumOf { line -> getNextHistoryValue(line) }

    println("Result Part1: $result")
}

private fun part2(lines: List<String>) {
    val result = lines.sumOf { line -> getPreviousHistoryValue(line) }

    println("Result Part2: $result")
}

private fun getNextHistoryValue(line: String): Int {
    val history = createCompleteHistory(line)

    val reversedHistory = history.reversed().drop(1)
    reversedHistory.forEachIndexed { i, diffs ->
        val lastValue = diffs.last()
        val lastValueLineBelow = if (i == 0) 0 else reversedHistory[i - 1].last()
        diffs.add(lastValue + lastValueLineBelow)
    }

    return history.first().last()
}

private fun getPreviousHistoryValue(line: String): Int {
    val history = createCompleteHistory(line)

    val reversedHistory = history.reversed().drop(1)
    reversedHistory.forEachIndexed { i, diffs ->
        val firstValue = diffs.first()
        val firstValueLineBelow = if (i == 0) 0 else reversedHistory[i - 1].first()
        diffs.add(0, firstValue - firstValueLineBelow)
    }

    return history.first().first()
}

private fun createCompleteHistory(line: String): MutableList<MutableList<Int>> {
    val history = mutableListOf(line.split(' ').map { it.toInt() }.toMutableList())

    while (!history[history.size - 1].all { it == 0 }) {
        val currentHistory = history[history.size - 1]
        val newHistory = mutableListOf<Int>()
        for (i in 0..<currentHistory.size - 1) {
            newHistory.add(currentHistory[i + 1] - currentHistory[i])
        }
        history.add(newHistory)
    }

    return history
}