package com.froren.aoc24.day03

import com.froren.aoc24.bootstrap.solveDay

val MUL_REGEX = Regex("""mul\((\d{1,3}),(\d{1,3})\)""")
val DO_MUL_REGEX = Regex("""do\(\)|don't\(\)|mul\((\d{1,3}),(\d{1,3})\)""")

fun main() = solveDay(
    day = 3,
    partOneSolver = ::solvePartOne,
    partTwoSolver = ::solvePartTwo,
)

fun solvePartOne(lines: Sequence<String>) =
    MUL_REGEX.findAll(lines.joinToString())
        .sumOf { match ->
            match.groups[1]!!.value.toInt() *
                match.groups[2]!!.value.toInt()
        }

fun solvePartTwo(lines: Sequence<String>): Int {
    var doMul = true
    var sum = 0

    val matches = DO_MUL_REGEX.findAll(lines.joinToString())
    for (match in matches) {
        when(match.groups[0]!!.value) {
            "do()" -> doMul = true
            "don't()" -> doMul = false
            else -> {
                if (doMul)
                    sum += match.groups[1]!!.value.toInt() *
                            match.groups[2]!!.value.toInt()
            }
        }
    }

    return sum
}
