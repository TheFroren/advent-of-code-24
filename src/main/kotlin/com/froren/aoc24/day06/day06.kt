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
        if (map.hasInbound(guard.cell + guard.dir) && loopAfterTurnRight(guard.copy(), map))
            addedObstructions.add(guard.cell + guard.dir)

        guard.walk(map)
    }

    return addedObstructions.size
}

fun loopAfterTurnRight(guard: Guard, map: Map): Boolean {
    val obstruction = guard.cell + guard.dir
    val history = mutableSetOf<Guard>()

    while (map.hasInbound(guard.cell)) {
        guard.walk(map, additionalObstruction = obstruction)

        if (guard in history)
            return true.also { printLoop(map, history, obstruction, it) }

        history.add(guard.copy())
    }

    return false.also { printLoop(map, history, obstruction, it) }
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

fun clearConsole() {
    print("\u001b[H\u001b[2J")
    System.out.flush()
}

fun printLoop(map: Map, history: Collection<Guard>, obstruction: Cell, isLoop: Boolean) {
    clearConsole()
    println(CharArray(map.width) { '=' })

    val grid = Array(map.height) { CharArray(map.width) { ' ' } }

    grid[obstruction.y][obstruction.x] = 'O'

    history.forEach {
        if (map.hasInbound(it.cell))
            grid[it.cell.y][it.cell.x] = when(it.dir) {
                Dir.UP -> '^'
                Dir.DOWN -> 'v'
                Dir.RIGHT -> '>'
                Dir.LEFT -> '<'
            }
    }

    map.obstructions.forEach {
        grid[it.y][it.x] = '#'
    }


    grid.forEach(::println)
    println("Start: ${history.first()}")
    println("Loop Detected: $isLoop")
}
