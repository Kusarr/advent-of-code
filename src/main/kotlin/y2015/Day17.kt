package y2015

private val containers = listOf(43, 3, 4, 10, 21, 44, 4, 6, 47, 41, 34, 17, 17, 44, 36, 31, 46, 9, 27, 38)
private val litersEggnog = 150

fun main() {
    part1()
    part2()
}

private fun part1() {
    val result = findCombinations(litersEggnog).size

    println("Result Part1: $result")
}

private fun part2() {
    val combinations = findCombinations(litersEggnog)

    val result = combinations.filter { it == combinations.min() }.size

    println("Result Part2: $result")
}

fun findCombinations(target: Int): List<Int> {
    val result = mutableListOf<Int>()

    fun findHelper(target: Int, containers: List<Int>, currentCombination: List<Int>, index: Int) {
        if (target == 0) {
            result.add(currentCombination.size)
            return
        }

        if (target < 0 || index >= containers.size) {
            return
        }

        // Include the current container
        findHelper(target - containers[index], containers, currentCombination + containers[index], index + 1)

        // Exclude the current container
        findHelper(target, containers, currentCombination, index + 1)
    }

    findHelper(target, containers.sorted(), emptyList(), 0)

    return result
}