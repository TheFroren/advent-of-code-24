package com.froren.aoc24.day01

import com.froren.aoc24.bootstrap.solveDay
import kotlin.math.abs

const val INPUT_SPACING = 3

fun main() = solveDay(
    day = 1,
    partOneSolver = ::solvePartOne,
    partTwoSolver = ::solvePartTwo,
)

fun solvePartOne(lines: Sequence<String>): Int {
    val (first, second) = parseLocationIds(lines)

    return first.zip(second).sumOf {
        abs(it.first - it.second)
    }
}

fun solvePartTwo(lines: Sequence<String>): Int {
    val (first, second) = parseLocationIds(lines)

    val secondCounts = second.fold(mutableMapOf<Int, Int>()) { counts, id ->
        counts.merge(id, 1) { oldVal, _ ->
            oldVal + 1
        }

        counts
    }

    return first.sumOf {
        it * secondCounts.getOrDefault(it, 0)
    }
}

fun parseLocationIds(lines: Sequence<String>): Pair<MutableList<Int>, MutableList<Int>> {
    val first = mutableListOf<Int>()
    val second = mutableListOf<Int>()

    lines.forEach {
        val vals = it.split(" ")
        first.add(vals[0].toInt())
        second.add(vals[INPUT_SPACING].toInt())
    }

    return Pair(first, second)
}

