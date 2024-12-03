package com.froren.aoc24.bootstrap

fun solveDay(
    day: Int,
    partOneSolver: (Sequence<String>) -> Any?,
    partTwoSolver: ((Sequence<String>) -> Any?)? = null,
    ) {
    val dayStr = day.toString().padStart(2, '0')

    loadResource("/day${dayStr}input.txt")
        .reader()
        .useLines { lines ->
            partOneSolver(lines)
                .also { println("Day $dayStr part one result is $it") }
                .also { getOutputFile("day${dayStr}output.txt").writeText("$it") }
        }

    if (partTwoSolver != null)
        loadResource("/day${dayStr}input.txt")
            .reader()
            .useLines { lines ->
                partTwoSolver(lines)
                    .also { println("Day $dayStr part two result is $it") }
                    .also { getOutputFile("day${dayStr}output.txt").appendText("\n$it") }
            }
}
