package y2017

import kotlin.math.abs
import kotlin.math.pow

private const val INPUT = 325489
private const val ARRAY_SIZE = 15

fun main() {
    part1()
    part2()
}

private fun part1() {
    val layer = (1..1000).first { (2.0 * it + 1).pow(2) >= INPUT }
    val sideLength = layer * 2
    val highestNum = (2.0 * layer + 1).pow(2)
    val distA = abs(((highestNum - INPUT) % sideLength) - layer)

    val result = distA + layer
    println("Result Part1: $result")
}

private fun part2() {
    // initialize
    val array: Array<IntArray> = Array(ARRAY_SIZE) { IntArray(ARRAY_SIZE) }
    array[ARRAY_SIZE / 2][ARRAY_SIZE / 2] = 1
    var layer = 1
    var layerSideLength = layer * 2
    var layerStepCount = 1
    var x = ARRAY_SIZE / 2 + 1
    var y = ARRAY_SIZE / 2
    var currentNumber = 1

    while (currentNumber < INPUT) {
        //CALC NUMBER
        currentNumber = calcNewNumber(array, x, y)
        array[y][x] = currentNumber
        debug(array)

        // MOVE
        if (layerStepCount == layerSideLength * 4) {
            // NEXT LAYER
            layer++
            layerSideLength = layer * 2
            layerStepCount = 1
            x++
        } else {
            // MOVE
            when (layerStepCount / layerSideLength) {
                0 -> y--
                1 -> x--
                2 -> y++
                3 -> x++
            }
            layerStepCount++
        }
    }

    println("Result Part2: $currentNumber")
}

private fun calcNewNumber(array: Array<IntArray>, x: Int, y: Int): Int {
    var sum = 0
    for (i in y - 1..y + 1) {
        for (j in x - 1..x + 1) {
            sum += array[i][j]
        }
    }
    return sum
}

fun debug(array: Array<IntArray>) {
    println("#######".repeat(ARRAY_SIZE))
    for (row in array) {
        println(row.joinToString(" ") { it.toString().padStart(6, ' ') })
    }
}