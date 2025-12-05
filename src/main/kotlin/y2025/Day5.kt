package y2025

import FileUtil

private var ranges: MutableList<LongRange> = mutableListOf()
private var ids: MutableSet<Long> = mutableSetOf()

fun main() {
    val lines = FileUtil().readLines("2025/day5-input.txt")
    lines.forEach { line ->
        if (line.contains('-')) {
            val (a, b) = line.split("-")
            ranges.add(LongRange(a.toLong(), b.toLong()))
        } else if (line.isNotBlank()) {
            ids.add(line.toLong())
        }
    }

    part1()
    part2()
}

private fun part1() {
    val result = ids.sumOf { id ->
        if (ranges.firstOrNull { id in it } != null) 1 else 0
    }

    println("Result Part1: $result")
}

private fun part2() {
    val result = mergeRanges().sumOf { it.last - it.first + 1 }

    println("Result Part2: $result")
}

fun mergeRanges(): List<LongRange> {
    val sorted = ranges.sortedBy { it.first }

    val merged = mutableListOf<LongRange>()
    var current = sorted[0]

    for (next in sorted.drop(1)) {
        if (next.first <= current.last + 1) {
            // overlap or contiguous → merge
            current = current.first..maxOf(current.last, next.last)
        } else {
            merged.add(current)
            current = next
        }
    }

    merged.add(current)
    return merged
}