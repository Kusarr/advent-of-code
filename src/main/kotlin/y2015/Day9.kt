package y2015

import FileUtil

private val cities = mutableMapOf<String, MutableMap<String, Int>>()

fun main() {
    val lines = FileUtil().readLines("2015/day9-input.txt")

    val regex = Regex(" to | = ")
    lines.forEach { line ->
        val values = line.split(regex)
        cities.computeIfAbsent(values[0]) { mutableMapOf() }[values[1]] = values[2].toInt()
        cities.computeIfAbsent(values[1]) { mutableMapOf() }[values[0]] = values[2].toInt()
    }

    val distances = Array(cities.keys.size) { IntArray(cities.keys.size) { 0 } }
    cities.onEachIndexed { row, city ->
        city.value.forEach { entry ->
            val col = cities.keys.indexOf(entry.key)
            distances[col][row] = entry.value
        }
    }

    part1(distances)
    part2(distances)
}

private fun part1(distances: Array<IntArray>) {
    val result = findDistance(cities.keys.toList(), distances, PathType.SHORTEST)

    println("Result Part1: $result")
}

private fun part2(distances: Array<IntArray>) {
    val result = findDistance(cities.keys.toList(), distances, PathType.LONGEST)

    println("Result Part2: $result")
}


fun findDistance(cities: List<String>, distances: Array<IntArray>, type: PathType): Int {
    val n = cities.size
    val indices = (0 until n).toList()

    var targetDistance = if (type == PathType.LONGEST) Int.MIN_VALUE else Int.MAX_VALUE

    val permutations = indices.permutations()

    for (permutation in permutations) {
        var totalDistance = 0
        var validPath = true

        for (i in 0 until permutation.size - 1) {
            val fromCity = permutation[i]
            val toCity = permutation[i + 1]
            val distance = distances[fromCity][toCity]

            if (distance == 0) {
                validPath = false
                break
            }

            totalDistance += distance
        }

        if (validPath && distances[permutation.last()][permutation.first()] != 0) {
            if (type == PathType.LONGEST && totalDistance > targetDistance) {
                targetDistance = totalDistance
            }
            if (type == PathType.SHORTEST && totalDistance < targetDistance) {
                targetDistance = totalDistance
            }
        }
    }

    return targetDistance
}

fun <T> List<T>.permutations(): List<List<T>> {
    if (this.isEmpty()) return emptyList()
    if (this.size == 1) return listOf(this)

    val result = mutableListOf<List<T>>()
    val element = this[0]
    val permutations = this.drop(1).permutations()

    for (perm in permutations) {
        for (i in 0..perm.size) {
            val newPerm = perm.toMutableList()
            newPerm.add(i, element)
            result.add(newPerm)
        }
    }

    return result
}

enum class PathType {
    SHORTEST,
    LONGEST
}