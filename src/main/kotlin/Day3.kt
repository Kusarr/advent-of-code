fun main() {
    val lines = FileUtil().readLines("day3-input.txt")
    part1(lines)
    part2(lines)
}

val numRegex = Regex("\\d+")

private fun part1(lines: List<String>) {
    val symbolRegex = Regex("[^.\\d\\s]")

    var result = 0
    for (i in lines.indices) {
        val symbolIndices = listOf(Math.max(i - 1, 0), i, Math.min(i + 1, lines.size - 1))
            .distinct().flatMap { symLine ->
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
        val numberIndices = listOf(Math.max(i - 1, 0), i, Math.min(i + 1, lines.size - 1))
            .distinct().flatMap { symLine ->
                numRegex.findAll(lines[symLine])
                    .map { NumberToRange(it.value.toInt(), IntRange(it.range.first - 1, it.range.last + 1)) }
            }

        gearRegex.findAll(lines[i]).forEach { gear ->
            val numbers = numberIndices.filter { it.range.contains(gear.range.first) }
            if (numbers.size == 2) {
                result += numbers[0].number * numbers[1].number
            }
        }
    }

    println("Result Part1: $result")
}

private class NumberToRange(val number: Int, val range: IntRange)
