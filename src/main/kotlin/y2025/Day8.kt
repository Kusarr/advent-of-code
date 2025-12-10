package y2025

import FileUtil
import kotlin.math.sqrt

fun main() {
    val lines = FileUtil().readLines("2025/day8-input.txt")
    part1(lines)
    part2(lines)
}

private fun part1(lines: List<String>) {
    val sortedDistances = getSortedDistances(lines)

    val curcuites = mutableListOf<MutableSet<Box>>()
    sortedDistances.subList(0, 1000).forEach { connectBoxes(curcuites, it) }

    curcuites.sortByDescending { it.size }
    val result = curcuites.subList(0, 3).fold(1) { acc, cur -> acc * cur.size }

    println("Result Part1: $result")
}

private fun part2(lines: List<String>) {
    val sortedDistances = getSortedDistances(lines)

    val curcuites = mutableListOf<MutableSet<Box>>()
    for (dist in sortedDistances) {
        connectBoxes(curcuites, dist)

        if (curcuites.size == 1 && curcuites.first().size == lines.size) {
            val result = dist.second.x.toLong() * dist.third.x.toLong()
            println("Result Part2: $result")
            break
        }
    }
}

private fun distance(a: Box, b: Box): Double {
    val dx = a.x - b.x
    val dy = a.y - b.y
    val dz = a.z - b.z
    return sqrt(dx * dx + dy * dy + dz * dz)
}

private fun getSortedDistances(lines: List<String>): List<Triple<Double, Box, Box>> {
    val boxes = lines.map { Box(it) }

    val distances = mutableListOf<Triple<Double, Box, Box>>()
    for (i in boxes.indices) {
        for (j in i + 1 until boxes.size) {
            val distance = distance(boxes[i], boxes[j])
            distances.add(Triple(distance, boxes[i], boxes[j]))
        }
    }

    return distances.sortedWith(compareBy { it.first })
}

private fun connectBoxes(curcuites: MutableList<MutableSet<Box>>, dist: Triple<Double, Box, Box>) {
    val curcuitA = curcuites.find { cur -> dist.second in cur }
    val curcuitB = curcuites.find { cur -> dist.third in cur }

    if (curcuitA != null && curcuitB != null && curcuitA != curcuitB) {
        curcuitA.addAll(curcuitB)
        curcuites.remove(curcuitB)
    } else if (curcuitA == null && curcuitB == null) {
        val newCurcuit = mutableSetOf<Box>()
        newCurcuit.add(dist.second)
        newCurcuit.add(dist.third)
        curcuites.add(newCurcuit)
    } else {
        curcuitA?.add(dist.third)
        curcuitB?.add(dist.second)
    }
}

private data class Box(val line: String) {

    val x: Double
    val y: Double
    val z: Double

    init {
        val (x, y, z) = line.split(",")
        this.x = x.toDouble()
        this.y = y.toDouble()
        this.z = z.toDouble()
    }
}