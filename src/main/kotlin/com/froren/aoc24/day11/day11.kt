package com.froren.aoc24.day11

import com.froren.aoc24.bootstrap.solveDay
import java.math.BigInteger

fun main() = solveDay(
    day = 11,
    partOneSolver = ::solvePartOne,
    partTwoSolver = ::solvePartTwo,
)

fun solvePartOne(lines: Sequence<String>) =
    getStones(lines)
        .sumOf {
            blink(it, blinkCount = 0, blinkLimit = 25)
        }

fun blink(stone: String, blinkCount: Int, blinkLimit: Int): Long =
    if (blinkCount >= blinkLimit) {
        1L
    } else {
        when {
            stone == "0" -> blink("1", blinkCount+1, blinkLimit)
            stone.length % 2 == 0 ->
                blink(stone.substring(0, stone.length/2), blinkCount+1, blinkLimit) +
                        blink(stone.substring(stone.length/2, stone.length).toLong().toString(), blinkCount+1, blinkLimit)
            else -> blink((stone.toLong()*2024).toString(), blinkCount+1, blinkLimit)
        }
    }

fun solvePartTwo(lines: Sequence<String>): BigInteger {
    val initialStones = mutableMapOf<String, BigInteger>()
    getStones(lines).forEach {
        initialStones.merge(it, BigInteger.ONE) { old, new ->  old + new }
    }

    return (1..75)
        .fold(initialStones) { stones: Map<String, BigInteger>, _ ->
            blinkWithCounts(stones)
        }
        .values
        .sumOf { it }
}

fun blinkWithCounts(stones: Map<String, BigInteger>): Map<String, BigInteger> {
    val newStones = mutableMapOf<String, BigInteger>()

    stones.forEach { (stone, count) ->
        when {
            stone == "0" -> newStones.merge("1", count) { old, new ->  old + new }
            stone.length % 2 == 0 -> {
                newStones
                    .merge(stone.substring(0, stone.length/2), count)
                        { old, new ->  old + new }
                newStones
                    .merge(stone.substring(stone.length/2, stone.length).toLong().toString(), count)
                        { old, new ->  old + new }
            }
            else -> newStones.merge((stone.toLong()*2024).toString(), count) { old, new ->  old + new }
        }
    }

    return newStones
}

fun getStones(lines: Sequence<String>) =
    lines
        .joinToString(" ")
        .split(" ")
