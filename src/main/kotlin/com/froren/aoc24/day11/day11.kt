package com.froren.aoc24.day11

import com.froren.aoc24.bootstrap.solveDay

fun main() = solveDay(
    day = 11,
    partOneSolver = ::solvePartOne,
//    partTwoSolver = ::solvePartTwo,
)

fun solvePartOne(lines: Sequence<String>) =
    getStones(lines)
        .sumOf {
            blink(it, blinkCount = 0, blinkLimit = 6) + 1
        }

fun blink(stone: String, blinkCount: Int, blinkLimit: Int): Int =
    if (blinkCount > blinkLimit) {
        1
    } else {
        when {
            stone == "0" -> blink("1", blinkCount+1, blinkLimit)
            stone.length % 2 == 0 ->
                blink(stone.substring(stone.length/2), blinkCount+1, blinkLimit) +
                blink(stone.substring(stone.length/2, stone.length), blinkCount+1, blinkLimit)
            else -> blink((stone.toInt()*2024).toString(), blinkCount+1, blinkLimit)
        }
    }

fun getStones(lines: Sequence<String>) =
    lines
        .joinToString(" ")
        .split(" ")