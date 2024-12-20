package com.froren.aoc24.day20

import com.froren.aoc24.bootstrap.solveDay
import kotlin.math.abs

private val DIST_2 = listOf(
    Vector(2,0),
    Vector(-2,0),
    Vector(0,2),
    Vector(0,-2),
    Vector(1,1),
    Vector(1,-1),
    Vector(-1,1),
    Vector(-1,-1),
)

fun main() = solveDay(
    day = 20,
    partOneSolver = ::solvePartOne,
    partTwoSolver = ::solvePartTwo,
)


fun solvePartOne(lines: Sequence<String>): Int {
    val racetrack = lines.getRacetrack()

    val distanceFromStart = walkPathNormally(racetrack)
        .mapIndexed { dist, pos ->
            pos to dist
        }
        .toMap()

    return distanceFromStart
        .asSequence()
        .flatMap{ (pos, distAlready) ->
            DIST_2.asSequence().mapNotNull { offset ->
                val cheatEnd = pos + offset
                if (cheatEnd !in distanceFromStart)
                    return@mapNotNull null

                val newDist = distanceFromStart[cheatEnd]!!
                // how much we save
                newDist - (distAlready + 2)
            }
        }
        .count { it >= 100 }
}

fun solvePartTwo(lines: Sequence<String>): Int {
    val racetrack = lines.getRacetrack()

    val distanceFromStart = walkPathNormally(racetrack)
        .mapIndexed { dist, pos ->
            pos to dist
        }
        .toMap()

    val areaPos = racetrack.area.eachPos().toList()

    return distanceFromStart
        .asSequence()
        .flatMap{ (pos, distAlready) ->
            areaPos.asSequence().mapNotNull { cheatEnd ->
                val cheatDist = abs(pos.x - cheatEnd.x) + abs(pos.y - cheatEnd.y)
                if (cheatDist > 20)
                    return@mapNotNull null

                if (cheatEnd !in distanceFromStart)
                    return@mapNotNull null

                val newDist = distanceFromStart[cheatEnd]!!
                // how much we save
                newDist - (distAlready + cheatDist)
            }
        }
        .count { it >= 100 }
}

private fun walkPathNormally(racetrack: Racetrack) = sequence {
    var pos = racetrack.start
    var dir: Dir? = null

    while (pos != racetrack.end) {
        yield(pos)

        for (newDir in Dir.entries) {
            if (newDir.turnCW(2) == dir)
                continue

            val newPos = pos + newDir.v

            if (!racetrack.walls[newPos.y][newPos.x]) {
                pos = newPos
                dir = newDir
                break
            }
        }
    }

    yield(racetrack.end)
}

private data class Racetrack(
    val walls: List<List<Boolean>>,
    val area: Area,
    val start: Vector,
    val end: Vector,
)

private fun Sequence<String>.getRacetrack(): Racetrack {

    lateinit var start: Vector
    lateinit var end: Vector

    val walls = mapIndexed { y, it ->
        it.mapIndexed { x, it ->
            when(it) {
                '#' -> true
                'S' -> false.also { start = Vector(x,y) }
                'E' -> false.also { end = Vector(x,y) }
                else -> false
            }
        }
    }.toList()

    return Racetrack(
        walls = walls,
        start = start,
        end = end,
        area = Area(walls.first().size, walls.size)
    )
}
