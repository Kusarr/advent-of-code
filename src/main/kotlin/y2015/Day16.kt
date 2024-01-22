package y2015

import FileUtil
import positivNumberRegex

private val targetAunt = Aunt("children: 3,cats: 7,samoyeds: 2,pomeranians: 3,akitas: 0,vizslas: 0,goldfish: 5,trees: 3,cars: 2,perfumes: 1")

fun main() {
    val lines = FileUtil().readLines("2015/day16-input.txt")
    part1(lines)
    part2(lines)
}

private fun part1(lines: List<String>) {
    val result = lines.map { Aunt(it) }.first { it.matchesAunt(targetAunt) }.id

    println("Result Part1: $result")
}

private fun part2(lines: List<String>) {
    val result = lines.map { Aunt(it) }.first { it.matchesAuntPart2(targetAunt) }.id

    println("Result Part2: $result")
}

private class Aunt(line: String) {

    val compoundRegex = Regex("(\\w+):\\s(\\d+)")

    var id: Int? = null
    var children: Int? = null
    var cats: Int? = null
    var samoyeds: Int? = null
    var pomeranians: Int? = null
    var akitas: Int? = null
    var vizslas: Int? = null
    var goldfish: Int? = null
    var trees: Int? = null
    var cars: Int? = null
    var perfumes: Int? = null

    init {
        id = positivNumberRegex.find(line)!!.value.toInt()
        val values = compoundRegex.findAll(line)
            .map { Pair(it.groups[1]!!.value, it.groups[2]!!.value.toInt()) }
            .toList()
        values.forEach {
            when (it.first) {
                "children" -> children = it.second
                "cats" -> cats = it.second
                "samoyeds" -> samoyeds = it.second
                "pomeranians" -> pomeranians = it.second
                "akitas" -> akitas = it.second
                "vizslas" -> vizslas = it.second
                "goldfish" -> goldfish = it.second
                "trees" -> trees = it.second
                "cars" -> cars = it.second
                "perfumes" -> perfumes = it.second
            }
        }
    }

    fun matchesAunt(aunt: Aunt): Boolean {
        return matchesCompound(children, aunt.children)
                && matchesCompound(cats, aunt.cats)
                && matchesCompound(samoyeds, aunt.samoyeds)
                && matchesCompound(pomeranians, aunt.pomeranians)
                && matchesCompound(akitas, aunt.akitas)
                && matchesCompound(vizslas, aunt.vizslas)
                && matchesCompound(goldfish, aunt.goldfish)
                && matchesCompound(trees, aunt.trees)
                && matchesCompound(cars, aunt.cars)
                && matchesCompound(perfumes, aunt.perfumes)
    }

    fun matchesAuntPart2(aunt: Aunt): Boolean {
        return matchesCompound(children, aunt.children)
                && greaterThanCompound(cats, aunt.cats!!)
                && matchesCompound(samoyeds, aunt.samoyeds)
                && lessThanCompound(pomeranians, aunt.pomeranians!!)
                && matchesCompound(akitas, aunt.akitas)
                && matchesCompound(vizslas, aunt.vizslas)
                && lessThanCompound(goldfish, aunt.goldfish!!)
                && greaterThanCompound(trees, aunt.trees!!)
                && matchesCompound(cars, aunt.cars)
                && matchesCompound(perfumes, aunt.perfumes)
    }

    private fun matchesCompound(val1: Int?, val2: Int?): Boolean {
        return val1 == null || val1 == val2
    }

    private fun greaterThanCompound(val1: Int?, val2: Int): Boolean {
        return val1 == null || val1 > val2
    }

    private fun lessThanCompound(val1: Int?, val2: Int): Boolean {
        return val1 == null || val1 < val2
    }
}