package y2015

private val forbiddenChars = setOf('i', 'o', 'l')

fun main() {
    part1("cqjxjnds")
    part2("cqjxxyzz")
}

private fun part1(input: String) {
    val password = getPassowrd(input)

    println("Result Part1: $password")
}

private fun part2(input: String) {
    val password = getPassowrd(input)

    println("Result Part2: $password")
}

private fun getPassowrd(input: String): String {
    val password = input.toCharArray()

    do {
        increase(password, password.size - 1)
    } while (!passwordValid(password))

    return password.joinToString("")
}

private fun increase(password: CharArray, index: Int) {
    if (password[index] == 'z') {
        password[index] = 'a'
        increase(password, index - 1)
    } else {
        password[index] = password[index].inc()
    }

    if (forbiddenChars.contains(password[index])) {
        password[index] = password[index].inc()
    }
}

fun passwordValid(password: CharArray): Boolean {
    var straightIncreases = 0
    var pairs = 0
    var firstPairChar = '.'
    var prevChar = '.'

    for (char in password) {
        if (forbiddenChars.contains(char)) return false
        if (prevChar == char && firstPairChar != char) {
            pairs++
            firstPairChar = char
        }
        if (prevChar.inc() == char) straightIncreases++ else if (straightIncreases < 2) straightIncreases = 0
        prevChar = char
    }

    return pairs > 1 && straightIncreases > 1
}