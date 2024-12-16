package com.froren.aoc24.day14

import com.froren.aoc24.bootstrap.solveDay

private val ROBOT_REGEX = Regex("""p=([-\d]+),([-\d]+).v=([-\d]+),([-\d]+)""")

private const val SECONDS = 100

fun main() = solveDay(
    day = 14,
    partOneSolver = ::solvePartOne,
    partTwoSolver = ::solvePartTwo,
)

fun solvePartOne(lines: Sequence<String>): Int {
    val (grid, robots) = getGridAndRobots(lines)
    val quadrants = getQuadrants(grid)

    robots.forEach { robot ->
        for (i in 1..SECONDS) {
            robot.p = grid.wrap(robot.p + robot.v)
        }
    }

    return quadrants
        .map { quadrant ->
            robots.count { it.p in quadrant }
        }
        .reduce { a, b -> a * b }
}

fun getQuadrants(grid: Area): List<Area> {
    val midCol = grid.width / 2
    val midRow = grid.height / 2

    return listOf(
        Area(0..<midCol,                0..<midRow),
        Area(0..<midCol,                (midRow+1)..<grid.height),
        Area((midCol+1)..<grid.width,   0..<midRow),
        Area((midCol+1)..<grid.width,   (midRow+1)..<grid.height),
    )
}

fun solvePartTwo(lines: Sequence<String>): Int {
    val (grid, robots) = getGridAndRobots(lines)

    return (1..SECONDS*1000)
        .asSequence()
        .map {
            robots.forEach { robot ->
                robot.p = grid.wrap(robot.p + robot.v)
            }

            variance(robots.map { it.p.x }) * variance(robots.map { it.p.y })
        }
        .toList()
        .let {
            it.indexOf(it.min()) + 1
        }
}

fun variance(a: Collection<Int>): Double {
    var n = 0
    var s = 0
    var s2 = 0

    a.forEach {
        n += 1
        s += it
        s2 += it * it
    }

    val sn = s / n.toDouble()
    val s2n = s2 / n.toDouble()

    return s2n - sn * sn
}

fun getGridAndRobots(lines: Sequence<String>): Pair<Area, List<Robot>> {
    var width = 0
    var height = 0

    val robots = lines
        .mapIndexedNotNull{ i, line ->
            when (i) {
                0 -> {
                    width = line.toInt()
                    null
                }
                1 -> {
                    height = line.toInt()
                    null
                }
                else -> line
            }
        }
        .map { line ->
            ROBOT_REGEX.matchEntire(line)!!.groupValues.let {
                Robot(
                    p = Vector(it[1].toInt(), it[2].toInt()),
                    v = Vector(it[3].toInt(), it[4].toInt()),
                )
            }
        }
        .toList()

    return Pair(Area(width, height), robots)
}

data class Robot(
    val v: Vector,
    var p: Vector,
)
