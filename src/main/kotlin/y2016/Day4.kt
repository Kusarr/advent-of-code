package y2016

import FileUtil

fun main() {
    val rooms = FileUtil().readLines("2016/day4-input.txt")
        .asSequence()
        .map { it ->
            val name = it.substring(0, it.lastIndexOf('-'))
            val id = it.substring(it.lastIndexOf('-') + 1, it.indexOf('['))
            val checksum = it.substring(it.indexOf('[') + 1, it.indexOf(']'))
            Room(name, id.toInt(), checksum)
        }.toList()

    part1(rooms)
    part2(rooms)
}

private fun part1(rooms: List<Room>) {
    val result = rooms
        .filter { it.isReal() }
        .sumOf { it.id }

    println("Result Part1: $result")
}

private fun part2(rooms: List<Room>) {
    val result = rooms.find { room -> room.decryptedName() == "northpole object storage" }?.id

    println("Result Part2: $result")
}

private data class Room(val name: String, val id: Int, val checksum: String) {

    fun mostUsedChars(): List<Pair<Char, Int>> {
        return name.replace("-", "")
            .groupingBy { it }
            .eachCount()
            .toList()
            .sortedWith(compareByDescending<Pair<Char, Int>> { it.second }.thenBy { it.first })
    }

    fun isReal(): Boolean {
        val topChars = mostUsedChars().take(5).map { it.first }.toSet()
        return checksum.all { topChars.contains(it) }
    }

    fun decryptedName(): String {
        // a-z = 97-122
        val shift = getCharShift()
        return name.split("-").joinToString(" ") {
            it.map { char -> shiftChar(char, shift) }.joinToString("")
        }
    }

    fun shiftChar(c: Char, shift: Int): Char {
        return 'a' + (c - 'a' + shift) % 26
    }

    fun getCharShift(): Int {
        return id % 26
    }
}