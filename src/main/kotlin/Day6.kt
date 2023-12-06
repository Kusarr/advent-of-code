fun main() {
    val lines = FileUtil().readLines("day6-input.txt")
    part1(lines)
    part2(lines)
}

private val regex = Regex("\\d+")
private fun part1(lines: List<String>) {
    val times = regex.findAll(lines[0]).map { it.value.toLong() }
    val distances = regex.findAll(lines[1]).map { it.value.toLong() }.toList()

    val result = times.mapIndexed { index, maxTime ->
        getNumberOfSolutions(maxTime, distances[index])
    }.reduce(Int::times)

    println("Result Part1: $result")
}

private fun part2(lines: List<String>) {
    val maxTime = regex.findAll(lines[0]).joinToString("") { it.value }.toLong()
    val distance = regex.findAll(lines[1]).joinToString("") { it.value }.toLong()

    val result = getNumberOfSolutions(maxTime, distance)

    println("Result Part1: $result")
}

private fun getNumberOfSolutions(maxTime: Long, distance: Long): Int {
    return (2 until maxTime).count { (maxTime - it) * it > distance }
}
