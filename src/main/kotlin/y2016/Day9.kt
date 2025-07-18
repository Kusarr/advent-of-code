package y2016

import FileUtil

private var decompressedInput = StringBuilder()

fun main() {
    val input = FileUtil().readLines("2016/day9-input.txt")[0]
    part1(input)
    part2(input)
}

private fun part1(input: String) {
    decompressedInput = StringBuilder(input)
    var index = 0
    while (index < decompressedInput.length) {
        if (decompressedInput[index] == '(') {
            index = decompress(index)
        } else index++
    }

    println("Result Part1: $index")
}

private fun part2(input: String) {
    decompressedInput = StringBuilder(input)
    var index = 0
    var totalIndex = 0L
    while (index < decompressedInput.length) {
        if (decompressedInput[index] == '(') {
            decompress(index)
        } else index++

        // logging and garbage collection
        if (index >= 5_000_000) {
            decompressedInput.delete(0, index)
            totalIndex += index
            index = 0
            println("Deleted processed data. Total Index: $totalIndex")
        }

    }

    val result = totalIndex + index

    println("Result Part2: $result")
}

private fun decompress(index: Int): Int {
    val endMarker = decompressedInput.indexOf(')', index)
    val (length, repeat) = getMarker(index + 1, endMarker)
    val segmentStart = endMarker + 1
    val segmentEnd = segmentStart + length
    val target = decompressedInput.substring(segmentStart, segmentEnd)

    decompressedInput.replace(index, segmentEnd, target.repeat(repeat))

    return index + target.length * repeat
}

private fun getMarker(from: Int, to: Int): Pair<Int, Int> {
    return decompressedInput
        .substring(from, to)
        .split("x")
        .let { it[0].toInt() to it[1].toInt() }
}