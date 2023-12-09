package y2023

import FileUtil

fun main() {
    val lines = FileUtil().readLines("2023/day8-input.txt").filter { it.isNotBlank() }
    part1(lines)
    part2(lines)
}

private val regex = Regex("\\w+")

private fun part1(lines: List<String>) {
    val instructions = createInstructions(lines)

    var state = "AAA"
    var count = 0
    while (state != "ZZZ") {
        lines[0].filter { sign ->
            state = if (sign == 'L') instructions[state]!!.first else instructions[state]!!.second
            count++
            state == "ZZZ"
        }
    }

    println("Result Part1: $count")
}

private fun part2(lines: List<String>) {
    val instructions = createInstructions(lines)

    val states = instructions.filter { it.key.endsWith('A') }.map { State(it.key) }.toList()

    states.forEach { state ->
        var currentState = state.startNode

        do {
            lines[0].filter { sign ->
                val newNode =
                    if (sign == 'L') instructions[currentState]!!.first else instructions[currentState]!!.second
                currentState = newNode
                state.count++
                currentState.endsWith('Z')
            }
        } while (!currentState.endsWith('Z'))
    }

    val result = lcm(states.map { it.count })

    println("Result Part2: $result")
}

private data class State(val startNode: String, var count: Long = 0)

private fun createInstructions(lines: List<String>) = lines.drop(1)
    .map { line -> regex.findAll(line).map { it.value }.toList() }
    .associate { it[0] to Pair(it[1], it[2]) }

private fun gcd(a: Long, b: Long): Long {
    return if (b == 0L) a else gcd(b, a % b)
}

private fun lcm(a: Long, b: Long): Long {
    return (a * b) / gcd(a, b)
}

private fun lcm(numbers: List<Long>): Long {
    var result = 1L
    for (number in numbers) {
        result = lcm(result, number)
    }
    return result
}