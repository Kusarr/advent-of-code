package y2015

import java.math.BigInteger
import java.security.MessageDigest

private val md5: MessageDigest = MessageDigest.getInstance("MD5")
private const val input = "iwrupvqb"

fun main() {
    part1()
    part2()
}

private fun part1() {
    val count = findLowestNumber("00000")

    println("Result Part1: $count")
}

private fun part2() {
    val count = findLowestNumber("000000")

    println("Result Part2: $count")
}

private fun findLowestNumber(condition: String): Int {
    var hash = ""
    var count = 0
    while (!hash.startsWith(condition)) {
        count++
        hash = BigInteger(1, md5.digest((input + count).toByteArray())).toString(16).padStart(32, '0')
    }
    return count
}