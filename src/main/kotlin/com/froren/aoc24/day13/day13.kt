package com.froren.aoc24.day13

import com.froren.aoc24.bootstrap.solveDay
import kotlin.math.abs

private val valRegex = Regex("""X[+=](\d+),.Y[+=](\d+)""")
private const val OFFSET = 10000000000000

fun main() = solveDay(
    day = 13,
    partOneSolver = ::solvePartOne,
    partTwoSolver = ::solvePartTwo,
)

fun solvePartOne(lines: Sequence<String>) =
    getClawMachines(lines)
        .sumOf { it.coins() }

fun solvePartTwo(lines: Sequence<String>) =
    getClawMachines(lines)
        .onEach { it.prize = Vector(it.prize.x+OFFSET, it.prize.y+OFFSET) }
        .sumOf { it.coins() }

fun ClawMachine.coins(): Long {
    val det = (a.x*b.y - a.y*b.x).toDouble()

    val aTimes = (b.x * prize.y - b.y * prize.x).toDouble() / det
    val bTimes = (a.x * prize.y - a.y * prize.x).toDouble() / det

    if (!aTimes.isWhole() || !bTimes.isWhole())
        return 0L

    return -3*aTimes.toLong() + bTimes.toLong() // ??!!
}

fun Double.isWhole(eps: Double = 0.00001) = abs(this - this.toLong()) < eps

fun getClawMachines(lines: Sequence<String>) =
    lines.windowed(size = 4, step = 4, partialWindows = true) {
        val a = valRegex.find(it[0])!!.let {
            Vector(
                it.groups[1]?.value?.toLong()!!,
                it.groups[2]?.value?.toLong()!!,
            )
        }

        val b = valRegex.find(it[1])!!.let {
            Vector(
                it.groups[1]?.value?.toLong()!!,
                it.groups[2]?.value?.toLong()!!,
            )
        }

        val prize = valRegex.find(it[2])!!.let {
            Vector(
                it.groups[1]?.value?.toLong()!!,
                it.groups[2]?.value?.toLong()!!,
            )
        }

        ClawMachine(a,b,prize)
    }

data class Vector(
    val x: Long,
    val y: Long,
)

data class ClawMachine(
    val a: Vector,
    val b: Vector,
    var prize: Vector,
)