package y2015

import FileUtil

fun main() {
    val lines = FileUtil().readLines("2015/day13-input.txt")
    part1(lines)
    part2(lines)
}

private fun part1(lines: List<String>) {
    val relations = lines.map { line ->
        val terms = line.split(" ")
        val score = if (terms[2] == "lose") terms[3].toInt() * -1 else terms[3].toInt()
        val personNextTo = terms.last().replace(".", "")
        Relation(terms[0], personNextTo, score)
    }.sortedByDescending { it.score }.toList()

    val attendees = mutableListOf(relations[0].person1, relations[0].person2)
    relations.drop(1).forEach { rel ->
        if (attendees.containsAll(listOf(rel.person1, rel.person2))) return@forEach
        if (attendees.first() == rel.person1) {
            attendees.addFirst(rel.person2)
            return@forEach
        }
        if (attendees.first() == rel.person2) {
            attendees.addFirst(rel.person1)
            return@forEach
        }
        if (attendees.last() == rel.person1) {
            attendees.add(rel.person2)
            return@forEach
        }
        if (attendees.last() == rel.person2) {
            attendees.add(rel.person1)
            return@forEach
        }
    }

    var result = 0
    for (i in attendees.indices) {
        val nextIndex = if (i == attendees.size - 1) 0 else i + 1
        val prevIndex = if (i == 0) attendees.size - 1 else i - 1
        result += relations.filter { it.person1 == attendees[i] && (it.person2 == attendees[nextIndex] || it.person2 == attendees[prevIndex]) }
            .sumOf { it.score }
    }

    // 674 too low
    println("Result Part1: $result")
}

private fun part2(lines: List<String>) {
    val result = null

    println("Result Part2: $result")
}

private data class Relation(val person1: String, val person2: String, val score: Int)