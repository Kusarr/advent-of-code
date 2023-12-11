package y2015

fun main() {
    val input = "1321131112"
    part1(input)
    part2(input)
}

private val regex = Regex("(\\d)\\1*")

private fun part1(input: String) {
    val result = execute(input, 40)

    println("Result Part1: $result")
}

private fun part2(input: String) {
    val result = execute(input, 50)

    println("Result Part2: $result")
}

private fun execute(input: String, times: Int): Int {
    var puzzle = input
    for (i in 0..<times) {
        puzzle = regex.findAll(puzzle).toList()
            .joinToString("") { it.groups[0]!!.value.length.toString() + it.groups[1]!!.value }
    }
    return puzzle.length
}