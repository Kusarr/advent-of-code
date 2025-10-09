package y2017

import FileUtil
import spaceRegex

fun main() {
    val lines = FileUtil().readLines("2017/day6-input.txt")[0]
        .split(spaceRegex).map { it.toInt() }
    part1(lines)
    part2(lines)
}

private fun part1(lines: List<Int>) {
    val banks = lines.toMutableList()
    val configs = mutableSetOf<String>()
    var runs = 0

    while (true) {
        val config = banks.joinToString { it.toString() }
        if (configs.contains(config)) break
        configs.add(config)

        runs++

        var blocks = banks.max()
        var bankIdx = banks.indexOf(blocks)
        banks[bankIdx] = 0

        while (blocks > 0) {
            bankIdx = (bankIdx + 1) % banks.size
            banks[bankIdx]++
            blocks--
        }
    }

    println("Result Part1: $runs")
}

private fun part2(lines: List<Int>) {
    val banks = lines.toMutableList()
    val configs = mutableSetOf<String>()
    var runs = 0
    var loopState = ""
    var loopStart = 0

    while (true) {
        runs++
        configs.add(banks.joinToString { it.toString() })

        var blocks = banks.max()
        var bankIdx = banks.indexOf(blocks)
        banks[bankIdx] = 0

        while (blocks > 0) {
            bankIdx = (bankIdx + 1) % banks.size
            banks[bankIdx]++
            blocks--
        }

        val currentConfig = banks.joinToString { it.toString() }
        if (configs.contains(currentConfig)) {
            if (loopState.isEmpty()) {
                loopState = currentConfig
                loopStart = runs
            } else if (loopState == currentConfig) {
                break
            }
        }
    }

    println("Result Part2: ${runs - loopStart}")
}