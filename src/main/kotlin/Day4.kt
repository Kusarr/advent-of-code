import kotlin.math.pow

val regex = Regex("\\d+")
fun main() {
    val lines = FileUtil().readLines("day4-input.txt")
    part1(lines)
    part2(lines)
}

private fun part1(lines: List<String>) {
    val result = lines.map { line -> getDuplicationsCount(line) }
        .filter { it > 0 }
        .sumOf { count -> 2.0.pow(count - 1.0) }

    println("Result Part1: $result")
}

private fun part2(lines: List<String>) {
    val countings = IntArray(lines.size) { 1 }

    for (i in lines.indices) {
        val count = getDuplicationsCount(lines[i])
        for (j in i + 1..i + count) {
            countings[j] = countings[j] + countings[i]
        }
    }

    println("Result Part1: ${countings.slice(lines.indices).sum()}")
}

private fun getDuplicationsCount(line: String): Int {
    return regex.findAll(line.split(':')[1])
        .groupBy { it.value }
        .filter { it.value.size > 1 }
        .count()
}
