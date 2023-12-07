package y2023

import FileUtil

private val cardMap = mutableMapOf(
    'A' to 14, 'K' to 13, 'Q' to 12, 'J' to 11, 'T' to 10, '9' to 9,
    '8' to 8, '7' to 7, '6' to 6, '5' to 5, '4' to 4, '3' to 3, '2' to 2
)

fun main() {
    val lines = FileUtil().readLines("2023/day7-input.txt")
    part1(lines)
    part2(lines)
}

private fun part1(lines: List<String>) {
    val sortedList = lines.map { it.split(' ') }
        .map { Hand(getType(it[0]), it[0], it[1].toInt()) }
        .sorted()
    val result = sortedList.mapIndexed { i, hand -> hand.bid * (i + 1) }.sum()

    println("Result Part1: $result")
}

private fun part2(lines: List<String>) {
    cardMap['J'] = 1 // make J the weakest

    val sortedList = lines.map { it.split(' ') }.map { (hand, bid) ->
        val type = if (hand.contains('J'))
            cardMap.keys.maxOfOrNull { getType(hand.replace('J', it)) }
        else getType(hand)
        Hand(type!!, hand, bid.toInt())
    }.sorted()
    
    val result = sortedList.mapIndexed { i, hand -> hand.bid * (i + 1) }.sum()

    println("Result Part2: $result")
}

private fun getType(hand: String): Int {
    val groupBy = hand.groupingBy { it }.eachCount()
    return when {
        groupBy.size == 1 -> 6
        groupBy.containsValue(4) -> 5
        groupBy.containsValue(3) && groupBy.containsValue(2) -> 4
        groupBy.containsValue(3) -> 3
        groupBy.filter { it.value == 2 }.size == 2 -> 2
        groupBy.containsValue(2) -> 1
        else -> 0
    }
}

private data class Hand(val type: Int, val cards: String, val bid: Int) : Comparable<Hand> {

    override fun compareTo(other: Hand): Int {
        if (this.type != other.type) return this.type - other.type
        val index = this.cards.zip(other.cards).indexOfFirst { it.first != it.second }
        return cardMap[this.cards[index]]!! - cardMap[other.cards[index]]!!
    }
}