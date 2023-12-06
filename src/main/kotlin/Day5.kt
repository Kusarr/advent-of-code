import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch
import kotlin.math.min

suspend fun main() {
    val lines = FileUtil().readLines("day5-input.txt")
    part1(lines)
    val start = System.currentTimeMillis()
    part2(lines)
    println("Runtime in seconds: ${(System.currentTimeMillis() - start) / 1000}")
}

private fun part1(lines: List<String>) {
    val seeds = lines[0].split(':')[1].trim().split(' ').associateWith { it.toLong() }.toMutableMap()

    val result = findLowestLocation(seeds, lines)

    println("Result Part1: $result")
}

private suspend fun part2(lines: List<String>) {
    // TODO too slow...
    val seedsToRanges = lines[0].split(':')[1].trim().split(' ').map { it.toLong() }

    val result = mutableSetOf<Long>()

    val items = seedsToRanges.indices.toSet().groupBy { it % 2 == 0 }[true]
    val jobs = items!!.map { item ->
        GlobalScope.launch {
            println("Start job $item.")
            var lowestLocation = Long.MAX_VALUE

            LongRange(seedsToRanges[item], seedsToRanges[item] + seedsToRanges[item + 1] - 1).asSequence()
                .forEach { number ->
                    val location = findLowestLocation(mutableMapOf(number.toString() to number), lines)
                    lowestLocation = min(lowestLocation, location)
                }
            result.add(lowestLocation)
            println("Finished job $item.")
        }
    }

    jobs.forEach { it.join() }

    println("Result Part2: $result")
}

private class Mapping(val target: Long, val source: Long, val range: Long) {
    fun getTarget(value: Long): Long? {
        return if (LongRange(source, source + range - 1).contains(value))
            target + (value - source)
        else null
    }
}

private fun findLowestLocation(seeds: MutableMap<String, Long>, lines: List<String>): Long {
    seeds.forEach { seed ->
        var value = seed.value
        var touched = false
        lines.filter { it.isNotBlank() }.forEach skip@{ line ->
            if (touched && line[0].isDigit()) {
                return@skip
            }
            if (!line[0].isDigit()) {
                touched = false
                return@skip
            }

            val values = line.split(' ')
            val target = Mapping(values[0].toLong(), values[1].toLong(), values[2].toLong()).getTarget(value)
            if (target != null) {
                value = target
                touched = true
            }
        }
        seeds[seed.key] = value
    }
    return seeds.minBy { it.value }.value
}