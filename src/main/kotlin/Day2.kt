fun main() {
    part1()
    part2()
}

private fun part1() {
    val colorMap = mapOf("red" to 12, "green" to 13, "blue" to 14)
    var result = 0

    FileUtil().readLines("day2-input.txt").forEach line@{ line ->
        val idToValues = line.split(':')
        idToValues[1].split(';').forEach { set ->
            set.split(',')
                .map { it.trim().split(' ') }
                .forEach { it ->
                    if (colorMap[it[1]]!! < it[0].toInt()) {
                        return@line
                    }
                }

        }
        result += idToValues[0].split(' ')[1].toInt()
    }

    println("Result Part1: $result")
}

private fun part2() {
    val regex = Regex("[:,;]")

    val result = FileUtil().readLines("day2-input.txt").sumOf { line ->
        val colorMap = mutableMapOf<String, Int>()
        line.split(regex).drop(1).forEach {
            val (count, color) = it.trim().split(' ')
            colorMap.merge(color, count.toInt()) { old, new -> maxOf(old, new) }
        }
        colorMap.values.reduce { acc, i -> acc * i }
    }

    println("Result Part2: $result")
}