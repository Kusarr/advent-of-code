package y2016

import FileUtil
import numberRegex

val bots = mutableMapOf<Int, MutableList<Int>>()
val outputs = mutableMapOf<Int, MutableList<Int>>()
var finalBot = -1

fun main() {
    val lines = FileUtil().readLines("2016/day10-input.txt")
    part1(lines)
    part2(lines)
}

private fun part1(lines: List<String>) {
    val instructions = lines.toMutableList()

    while (instructions.isNotEmpty()) {
        val iter = instructions.iterator()
        while (iter.hasNext()) {
            val instruction = iter.next()

            if (instruction.startsWith("value")) {
                val (value, botId) = numberRegex.findAll(instruction)
                    .map { it.value.toInt() }.toList()
                    .let { it[0] to it[1] }
                val botValues = bots.getOrDefault(botId, mutableListOf())

                if (botValues.size < 2) {
                    botValues.add(value)
                    addValueToTarget("bot", botId, botValues)

                    iter.remove()
                    checkBotValues(botId)
                }
            } else {
                val instruction = InstructionData(instruction)
                val botValues = bots.getOrDefault(instruction.sourceBotId, mutableListOf())
                val lowTarget = getTarget(instruction.lowTargetType, instruction.lowTargetID)
                val highTarget = getTarget(instruction.highTargetType, instruction.highTargetID)
                if (botValues.size == 2 && lowTarget.size < 2 && highTarget.size < 2) {
                    lowTarget.add(botValues.min())
                    addValueToTarget(instruction.lowTargetType, instruction.lowTargetID, lowTarget)

                    highTarget.add(botValues.max())
                    addValueToTarget(instruction.highTargetType, instruction.highTargetID, highTarget)

                    addValueToTarget("bot", instruction.sourceBotId, mutableListOf())

                    iter.remove()
                    checkBotValues(instruction.lowTargetID)
                    checkBotValues(instruction.highTargetID)
                }
            }

        }

    }

    println("Result Part1: $finalBot")
}

private fun part2(lines: List<String>) {
    val result = listOf(0, 1, 2).mapNotNull { outputs[it]?.firstOrNull() }
        .reduce { acc, value -> acc * value }

    println("Result Part2: $result")
}

private fun checkBotValues(botId: Int) {
    if (finalBot >= 0) {
        return
    }
    val values = bots.getOrDefault(botId, mutableListOf())
    if (values.contains(61) && values.contains(17)) {
        finalBot = botId
    }
}

private fun getTarget(type: String, id: Int): MutableList<Int> {
    if (type == "bot") {
        return bots.getOrDefault(id, mutableListOf())
    }
    return outputs.getOrDefault(id, mutableListOf())
}

private fun addValueToTarget(type: String, id: Int, value: MutableList<Int>) {
    if (type == "bot") {
        bots.put(id, value)
    } else {
        outputs.put(id, value)
    }
}

private data class InstructionData(val instruction: String) {

    val sourceBotId: Int
    val lowTargetType: String
    val lowTargetID: Int
    val highTargetType: String
    val highTargetID: Int

    init {
        val parts = instruction.split(" ")
        sourceBotId = parts[1].toInt()
        lowTargetType = parts[5]
        lowTargetID = parts[6].toInt()
        highTargetType = parts[10]
        highTargetID = parts[11].toInt()
    }
}