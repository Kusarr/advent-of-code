package y2016

import java.security.MessageDigest

private val md5: MessageDigest = MessageDigest.getInstance("MD5")
private const val input = "reyedfim"

fun main() {
    part1()
    part2()
}

private fun part1() {
    var password = ""
    var index = 0

    while (password.length < 8) {
        val hash = md5.digest((input + index).toByteArray()).toHexString()
        if (hash.startsWith("00000")) {
            password += hash[5]
        }
        index++
    }

    println("Result Part1: $password")
}

private fun part2() {
    var password = "________"
    var index = 0

    while (password.contains('_')) {
        val hash = md5.digest((input + index).toByteArray()).toHexString()
        if (hash.startsWith("00000") && hash[5] < '8') {
            val pos = hash[5].toString().toInt()
            if (password[pos] == '_') {
                password = password.replaceRange(pos, pos + 1, hash[6].toString())
            }
        }
        index++
    }

    println("Result Part2: $password")
}