package y2015

import kotlin.math.sqrt

private const val INPUT = 34_000_000
private const val STEP = 10

private var startingHouse = sqrt(INPUT * 2.0 / STEP).toInt()

fun main() {
    part1()
    part2()
}

private fun part1() {
    var currentHouse = startingHouse - 1
    var result = STEP

    while (result < INPUT) {
        result = STEP + currentHouse * STEP
        currentHouse++

        for (i in 2..sqrt(currentHouse.toDouble()).toInt()) {
            if (currentHouse % i == 0) {
                result += i * STEP
                val div = currentHouse / i
                if (div != i) result += div * STEP
            }
        }

        println("House:$currentHouse -> Result: $result")
    }

    println("Result Part1: $currentHouse")
}

private fun part2() {
    val result = null

    println("Result Part2: $result")
}