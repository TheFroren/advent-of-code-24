package com.froren.aoc24.day15.pt1

import com.froren.aoc24.day15.Dir
import com.froren.aoc24.day15.Vector
import com.froren.aoc24.day15.plus

fun solvePartOne(lines: Sequence<String>): Int {
    val (robotPosInitial, map, commands) = getInput(lines)

    var robotPos = robotPosInitial

    commands.forEach { dir ->
        if (map.push(robotPos + dir.v, dir))
            robotPos += dir.v
    }

    var gps = 0

    map.forEachIndexed { y, row ->
        row.forEachIndexed { x, cell ->
            if (cell == Cell.BOX)
                gps += y * 100 + x
        }
    }

    return gps
}

fun Grid.push(pos: Vector, dir: Dir): Boolean =
    when (this[pos.y][pos.x]) {
        Cell.EMPTY -> true
        Cell.WALL -> false
        Cell.BOX -> {
            val target = pos + dir.v
            if (push(target, dir)) {
                this[pos.y][pos.x] = Cell.EMPTY
                this[target.y][target.x] = Cell.BOX
                true
            } else {
                false
            }
        }
    }

fun getInput(lines: Sequence<String>): Input {
    var firstPart = true

    lateinit var robotPos: Vector
    val map = mutableListOf<MutableList<Cell>>()
    val commands = mutableListOf<Dir>()

    lines.forEachIndexed { y, line ->
        if (line.isEmpty()) {
            firstPart = false
            return@forEachIndexed
        }

        if (firstPart) {
            val mapRow = line.mapIndexedTo(mutableListOf()) { x, it ->
                when (it) {
                    '#' -> Cell.WALL
                    'O' -> Cell.BOX
                    '@' -> Cell.EMPTY.also { robotPos = Vector(x,y) }
                    else -> Cell.EMPTY
                }
            }
            map.add(mapRow)
        } else {
            commands += line.mapNotNull {
                when (it) {
                    '^' -> Dir.UP
                    '>' -> Dir.RIGHT
                    'v' -> Dir.DOWN
                    '<' -> Dir.LEFT
                    else -> null
                }
            }
        }
    }

    return Input(robotPos, map, commands)
}

enum class Cell {
    EMPTY,
    BOX,
    WALL
}

typealias Grid = MutableList<MutableList<Cell>>

data class Input(
    val robotPos: Vector,
    val map: Grid,
    val commands: List<Dir>
)
