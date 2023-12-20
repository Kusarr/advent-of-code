package y2015

import FileUtil
import positivNumberRegex

fun main() {
    val lines = FileUtil().readLines("2015/day7-input.txt")
    part1(lines)
    part2(lines)
}

private val wires = mutableMapOf<String, String>()

private fun part1(lines: List<String>) {
    createMap(lines)

    val result = evaluateWire("a")

    println("Result Part1: $result")
}

private fun part2(lines: List<String>) {
    createMap(lines)
    wires["b"] = 16076.toString()

    val result = evaluateWire("a")

    println("Result Part2: $result")
}

private fun createMap(lines: List<String>) {
    lines.forEach { line ->
        val (expression, target) = line.split(" -> ")
        wires[target] = expression
    }
}

private fun evaluateWire(wire: String): UInt {
    if (positivNumberRegex.matches(wire)) {
        return wire.toUInt()
    }

    val expression = wires[wire]!!
    val parts = expression.split(" ")
    return when (parts.size) {
        1 -> evaluateWire(parts[0])
        2 -> evaluateWire(parts[1]).inv() and 65535u
        else -> {
            val leftValue = evaluateWire(parts[0])
            wires[parts[0]] = leftValue.toString()
            val rightValue = evaluateWire(parts[2])
            wires[parts[2]] = rightValue.toString()

            when (parts[1]) {
                "AND" -> leftValue and rightValue
                "OR" -> leftValue or rightValue
                "LSHIFT" -> leftValue shl rightValue.toInt()
                "RSHIFT" -> leftValue shr rightValue.toInt()
                else -> error("something was wrong")
            }
        }
    }
}