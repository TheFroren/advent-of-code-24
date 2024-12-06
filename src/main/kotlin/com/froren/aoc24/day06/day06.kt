package com.froren.aoc24.day06

import com.froren.aoc24.bootstrap.solveDay

fun main() = solveDay(
    day = 6,
    partOneSolver = ::solvePartOne,
    partTwoSolver = ::solvePartTwo,
)

fun solvePartOne(lines: Sequence<String>): Int {
    val map = getMap(lines)

    val guardPathPoints = mutableSetOf<Cell>()

    val guard = Guard(
        cell = map.guardInitial,
        dir = Dir.UP,
    )

    while (map.hasInbound(guard.cell)) {
        guardPathPoints.add(guard.cell)
        guard.walk(map)
    }

    return guardPathPoints.size
}

fun solvePartTwo(lines: Sequence<String>): Int {
    val map = getMap(lines)

    val addedObstructions = mutableSetOf<Cell>()

    val guard = Guard(
        cell = map.guardInitial,
        dir = Dir.UP,
    )

    while (map.hasInbound(guard.cell)) {
        if (loopAfterTurnRight(guard.copy(), map))
            addedObstructions.add(guard.cell + guard.dir)

        guard.walk(map)
    }

    return addedObstructions.size
}

fun loopAfterTurnRight(guard: Guard, map: Map): Boolean {
    val history = mutableSetOf(guard.copy())

    guard.dir = guard.dir.turnRight()

    while (map.hasInbound(guard.cell)) {
        history.add(guard.copy())

        guard.walk(map)

        if (guard in history)
            return true
    }

    return false
}


fun getMap(lines: Sequence<String>): Map {
    val obstructions = mutableListOf<Cell>()
    lateinit var guard: Cell
    var width = 0
    var height = 0

    lines.forEachIndexed { y, line ->
        line.forEachIndexed { x, cell ->
            when (cell) {
                '#' -> obstructions.add(Cell(x,y))
                '^' -> guard = Cell(x,y)
            }
        }

        width = line.length
        height++
    }

    return Map(
        width = width,
        height = height,
        obstructions = obstructions,
        guardInitial = guard,
    )
}
