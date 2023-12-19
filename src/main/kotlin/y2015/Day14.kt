package y2015

import FileUtil
import numberRegex

fun main() {
    val lines = FileUtil().readLines("2015/day14-input.txt")
    part1(lines)
    part2(lines)
}

private const val TRAVEL_TIME = 2503

private fun part1(lines: List<String>) {
    val result = lines.map { Reindeer(it) }.maxOfOrNull {
        val totalDistance = it.calcTravelingDistance(TRAVEL_TIME)
        println("${it.name}: $totalDistance")
        totalDistance
    }

    println("Result Part1: $result")
}

private fun part2(lines: List<String>) {
    val reindeers = lines.map { Reindeer(it) }.toSet()
    for (i in 0..<TRAVEL_TIME) {
        reindeers.forEach { deer ->
            if (i % deer.cycleTime < deer.flyTime) deer.currentDistance += deer.speed
        }
        val best = reindeers.maxByOrNull { it.currentDistance }?.currentDistance
        reindeers.filter { it.currentDistance == best }.forEach { it.points++ }
    }

    val result = reindeers.maxOfOrNull {
        println("${it.name} - Distance: ${it.currentDistance} - Points: ${it.points}")
        it.points
    }

    println("Result Part2: $result")
}

private data class Reindeer(val line: String) {
    val name: String = line.split(" ")[0]
    val speed: Int
    val flyTime: Int
    val restTime: Int
    val cycleTime: Int
    var currentDistance = 0
    var points = 0

    init {
        val numbers = numberRegex.findAll(line).map { it.value }.toList()
        this.speed = numbers[0].toInt()
        this.flyTime = numbers[1].toInt()
        this.restTime = numbers[2].toInt()
        this.cycleTime = flyTime + restTime
    }

    fun calcTravelingDistance(travelingTime: Int): Int {
        val completeCycles = travelingTime / cycleTime
        val remainingTime = travelingTime % cycleTime
        return (completeCycles * flyTime + minOf(remainingTime, flyTime)) * speed
    }
}

