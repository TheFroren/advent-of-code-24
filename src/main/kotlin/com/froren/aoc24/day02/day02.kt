package com.froren.aoc24.day02

import com.froren.aoc24.bootstrap.solveDay
import kotlin.math.abs

fun main() = solveDay(
    day = 2,
    partOneSolver = ::solvePartOne,
    partTwoSolver = ::solvePartTwo,
)

fun solvePartOne(lines: Sequence<String>) =
    parseReports(lines)
        .count { report ->
            val diff = report.zipWithNext { a, b ->
                a - b
            }
            .toList()

            diff.all { it in 1..3 } || diff.all { it in -3..-1 }
        }


fun solvePartTwo(lines: Sequence<String>) =
    parseReports(lines)
        .count(::isSafeDampened)

fun parseReports(lines: Sequence<String>) = lines.map {
    it.splitToSequence(" ")
        .map(String::toInt)
}

fun isSafe(report: Sequence<Int>): Boolean {
    val diff = report.zipWithNext { a, b ->
        a - b
    }.toList()

    return diff.all { it in 1..3 } || diff.all { it in -3..-1 }
}

fun isSafeDampened(reportSeq: Sequence<Int>): Boolean {
    val report = reportSeq.toList()

    return report.indices.asSequence().map { index ->
        report
            .asSequence()
            .filterIndexed { i, _ -> i != index }
    }
    .any(::isSafe)
}
