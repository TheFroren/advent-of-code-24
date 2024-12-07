package com.froren.aoc24.day07

import com.froren.aoc24.bootstrap.solveDay

fun main() = solveDay(
    day = 7,
    partOneSolver = ::solvePartOne,
    partTwoSolver = ::solvePartTwo,
)

fun solvePartOne(lines: Sequence<String>) =
    getCalibrations(lines)
        .filter { canBeMadeEqual(it.first, it.second, listOf('+', '*')) }
        .sumOf { it.first }

fun solvePartTwo(lines: Sequence<String>) =
    getCalibrations(lines)
        .filter { canBeMadeEqual(it.first, it.second, listOf('+', '*', '|')) }
        .sumOf { it.first }

fun canBeMadeEqual(result: Long, rhs: List<Long>, operands: List<Char>) =
    operatorsSeq(operands, n = rhs.size).map { ops ->
        rhs.reduceIndexed { i, acc, term ->
            when(ops[i]) {
                '+' -> acc + term
                '*' -> acc * term
                '|' -> "$acc$term".toLong()
                else -> acc.also { println("Unknown operator ${ops[i]}") }
            }
        }
    }
    .any {
        it == result
    }

fun operatorsSeq(operands: List<Char>, n: Int) = sequence {
    val currentOps = Array(n) { ' ' }

    suspend fun SequenceScope<List<Char>>.generateOpsRecursive(index: Int) {
        if (index == n) {
            yield(currentOps.toList())
            return
        }

        for (op in operands) {
            currentOps[index] = op
            generateOpsRecursive(index + 1)
        }
    }

    generateOpsRecursive(0)
}

fun getCalibrations(lines: Sequence<String>) =
    lines.map { line ->
        val eq = line.split(": ")
        val result = eq[0].toLong()
        val rhs = eq[1].split(" ").map(String::toLong)

        result to rhs
    }
