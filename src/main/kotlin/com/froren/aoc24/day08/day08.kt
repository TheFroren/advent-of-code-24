package com.froren.aoc24.day08

import com.froren.aoc24.bootstrap.solveDay

fun main() = solveDay(
    day = 8,
    partOneSolver = ::solvePartOne,
    partTwoSolver = ::solvePartTwo,
)

fun solvePartOne(lines: Sequence<String>): Int {
    val (area, antennas) = getMap(lines)

    return antennas
        .groupBy(Antenna::frequency)
        .values
        .asSequence()
        .flatMap(List<Antenna>::pairs)
        .filter { (a, b) ->
            a.position != b.position
        }
        .map { (a, b) ->
            a.position + a.position - b.position
        }
        .filter {
            it in area
        }
        .toSet()
        .size
}

fun solvePartTwo(lines: Sequence<String>): Int {
    val (area, antennas) = getMap(lines)

    return antennas
        .groupBy(Antenna::frequency)
        .values
        .asSequence()
        .flatMap(List<Antenna>::pairs)
        .filter { (a, b) ->
            a.position != b.position
        }
        .flatMap { (a, b) -> sequence {
                val dir = b.position - a.position
                var next = a.position

                while (next in area) {
                    yield(next)
                    next -= dir
                }
            }
        }
        .toSet()
        .size
}

fun <T> List<T>.pairs() = sequence {
    for (i in indices) {
        for (j in indices) {
            yield(get(i) to get(j))
        }
    }
}

data class Antenna(
    val frequency: Char,
    val position: Vector,
)

fun getMap(lines: Sequence<String>): Pair<Area, List<Antenna>> {
    var width = 0
    var height = 0
    val antennas = mutableListOf<Antenna>()


    lines.forEachIndexed { y, line ->
        line.forEachIndexed { x, symbol ->
            if (symbol != '.')
                antennas.add(
                    Antenna(
                        frequency = symbol,
                        position = Vector(x,y)
                    )
                )
        }

        width = line.length
        height++
    }

    return Area(width, height) to antennas
}

fun printVecs(width: Int, heigth: Int, vecs: Collection<Vector>) {
    val grid = Array(heigth) { CharArray(width) { '.' } }
    vecs.forEach {
        grid[it.y][it.x] = '#'
    }
    grid.forEach(::println)
}
