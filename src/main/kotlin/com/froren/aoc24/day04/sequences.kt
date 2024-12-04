package com.froren.aoc24.day04

fun rowWiseSequence(matrix: String, rowCount: Int) = sequence {
    val columnCount = matrix.length / rowCount

    var row = 0
    var column = 0

    while (row < rowCount) {
        if (column < columnCount) {
            yield(matrix.getColumnMajor(row, column, rowCount))
            column++
        } else {
            yield('\n')
            row++
            column = 0
        }
    }
}

fun columnWiseSequence(matrix: String, rowCount: Int) = sequence {
    val columnCount = matrix.length / rowCount

    var row = 0
    var column = 0

    while (column < columnCount) {
        if (row < rowCount) {
            yield(matrix.getColumnMajor(row, column, rowCount))
            row++
        } else {
            yield('\n')
            column++
            row = 0
        }
    }
}

fun majorDiagWiseSequence(matrix: String, rowCount: Int) = sequence {
    val columnCount = matrix.length / rowCount
    val diagRowRange = (-columnCount + 1) .. (rowCount - 1)  //starting row for the diagonal; may be negative!

    var diag = diagRowRange.first
    var row = diag
    var column = 0

    while (diag in diagRowRange) {
        if (row < 0) {
            column = row * -1
            row = 0
        }

        if (row < rowCount && column < columnCount) {
            yield(matrix.getColumnMajor(row, column, rowCount))
            row++
            column++
        } else {
            yield('\n')
            diag++
            row = diag
            column = 0
        }
    }
}

fun minorDiagWiseSequence(matrix: String, rowCount: Int) = sequence {
    val columnCount = matrix.length / rowCount
    val diagRowRange = (0) .. (rowCount - 1 + columnCount - 1)  //starting row for the diagonal

    var diag = diagRowRange.first
    var row = diag
    var column = 0

    while (diag in diagRowRange) {
        if (row >= rowCount) {
            column = row - (rowCount - 1)
            row = rowCount - 1
        }

        if (row >= 0 && column < columnCount) {
            yield(matrix.getColumnMajor(row, column, rowCount))
            row--
            column++
        } else {
            yield('\n')
            diag++
            row = diag
            column = 0
        }
    }
}
