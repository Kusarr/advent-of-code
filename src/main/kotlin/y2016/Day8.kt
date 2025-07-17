package y2016

import FileUtil

private const val WIDTH = 50
private const val HEIGTH = 6
private val display: Array<Array<Char>> = Array(WIDTH) { Array(HEIGTH) { ' ' } }

fun main() {
    FileUtil().readLines("2016/day8-input.txt").forEach { line ->
        val parts = line.split(" ")

        if (parts[0] == "rect")
            rect(parts[1])
        else if (parts[1] == "row")
            rotateRow(parts[2].split("=").last().toInt(), parts.last().toInt())
        else
            rotateColumn(parts[2].split("=").last().toInt(), parts.last().toInt())

        printDisplay()
    }

    val result = display.flatten().count { it == '#' }

    println("Result: $result")
}

private fun rect(cmd: String) {
    val dimension = cmd.split("x").map { it.toInt() }.toIntArray()
    for (x in 0 until dimension[0]) {
        for (y in 0 until dimension[1]) {
            display[x][y] = '#'
        }
    }
}

private fun rotateRow(y: Int, offset: Int) {
    val newRow = Array(WIDTH) { ' ' }
    val actualOffset = offset % WIDTH
    for (i in 0..WIDTH - 1) {
        var gridPos = i + actualOffset
        if (gridPos > WIDTH - 1) gridPos = gridPos % WIDTH
        newRow[gridPos] = display[i][y]
    }
    newRow.forEachIndexed { i, ch -> display[i][y] = ch }
}

private fun rotateColumn(x: Int, offset: Int) {
    val newColumn = Array(HEIGTH) { ' ' }
    val actualOffset = offset % HEIGTH
    for (i in 0..HEIGTH - 1) {
        var gridPos = i + actualOffset
        if (gridPos > HEIGTH - 1) gridPos = gridPos % HEIGTH
        newColumn[gridPos] = display[x][i]
    }
    display[x] = newColumn
}

private fun printDisplay() {
    val width = display.size
    val height = display[0].size

    val horizontalBorder = "+" + "-".repeat((width * 2) - 1) + "+"

    println(horizontalBorder)
    for (y in 0 until height) {
        print("|")
        for (x in 0 until width) {
            print(display[x][y])
            if (x != width - 1) print(".")
        }
        println("|")
    }
    println(horizontalBorder)
}