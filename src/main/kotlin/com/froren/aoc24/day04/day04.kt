package com.froren.aoc24.day04

import com.froren.aoc24.bootstrap.solveDay

fun main() = solveDay(
    day = 4,
    partOneSolver = ::solvePartOne,
    partTwoSolver = ::solvePartTwo,
)

fun solvePartOne(lines: Sequence<String>): Int {
    val (matrix, rowCount) = loadColumnMajor(lines)

    return (
        countXmas(rowWiseSequence(matrix, rowCount))
        + countXmas(columnWiseSequence(matrix, rowCount))
        + countXmas(majorDiagWiseSequence(matrix, rowCount))
        + countXmas(minorDiagWiseSequence(matrix, rowCount))
    )
}

fun countXmas(src: Sequence<Char>) =
    src.windowed(4)
        .count {
            val testStr = it.joinToString("")
            testStr == "XMAS" || testStr == "SAMX"
        }

fun solvePartTwo(lines: Sequence<String>): Int {
    val (matrix, rowCount) = loadColumnMajor(lines)
    val columnCount = matrix.length / rowCount

    var count = 0

    for (row in 1.. (rowCount - 2)) {
        for (column in 1.. (columnCount - 2)) {
            if (
                matrix.getColumnMajor(row, column, rowCount) == 'A'
                && (
                    matrix.checkMajorCorners(row, column, rowCount, 'M', 'S')
                    ||  matrix.checkMajorCorners(row, column, rowCount, 'S', 'M')
                )
                && (
                    matrix.checkMinorCorners(row, column, rowCount, 'M', 'S')
                    ||  matrix.checkMinorCorners(row, column, rowCount, 'S', 'M')
                )
            ) {
                count++
            }
        }
    }

    return count
}

fun String.checkMajorCorners(row: Int, column: Int, rowCount: Int, first: Char, second: Char) =
    this.getColumnMajor(row-1, column-1, rowCount) == first
        && this.getColumnMajor(row+1, column+1, rowCount) == second

fun String.checkMinorCorners(row: Int, column: Int, rowCount: Int, first: Char, second: Char) =
    this.getColumnMajor(row-1, column+1, rowCount) == first
            && this.getColumnMajor(row+1, column-1, rowCount) == second

fun loadColumnMajor(lines: Sequence<String>): Pair<String, Int> {
    var rowCount = 0

    val matrix = lines
        .onEach { rowCount++ }
        .joinToString("")

    return Pair(matrix, rowCount)
}

fun String.getColumnMajor(row: Int, column: Int, rowCount: Int) =
    get((row * (length / rowCount)) + column)
