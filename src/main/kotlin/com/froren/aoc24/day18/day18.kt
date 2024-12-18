package com.froren.aoc24.day18

import com.froren.aoc24.bootstrap.solveDay
import java.util.*
import kotlin.math.sqrt

fun main() = solveDay(
    day = 18,
    partOneSolver = ::solvePartOne,
    partTwoSolver = ::solvePartTwo,
)

fun solvePartOne(lines: Sequence<String>): Int {
    val walls = getWallPos(lines).take(1024).toList()
    val grid = Area (walls.maxOf { it.x } + 1, walls.maxOf { it.y } + 1)

    printWalls(walls)

    val shortestPath = aStar(
        startPos = Vector(0,0),
        targetPos = Vector(grid.width-1,grid.height-1),
        walls = walls,
        grid = grid,
    )!!

    return shortestPath.nodes.size - 1
}

fun solvePartTwo(lines: Sequence<String>): String {
    val walls = getWallPos(lines).toList()
    val grid = Area(walls.maxOf { it.x } + 1, walls.maxOf { it.y } + 1)

    for (i in 1024..<walls.size) {

        println(i)

        val shortestPath = aStar(
            startPos = Vector(0,0),
            targetPos = Vector(grid.width-1,grid.height-1),
            walls = walls.subList(0, i),
            grid = grid,
        )

        if (shortestPath == null) {
            val blocker = walls[i-1]
            return "${blocker.x},${blocker.y}"
        }
    }

    return ""
}

fun aStar(startPos: Vector, targetPos: Vector, walls: List<Vector>, grid: Area): Path? {
    val open = PriorityQueue<Path>(compareBy { it.cost })
    val visited = mutableSetOf<Vector>()

    open.add(Path(cost = 0.0, listOf(startPos)))

    while (open.isNotEmpty()) {
        val currentPath = open.poll()
        val currentNode = currentPath.nodes.last()

        if (currentNode == targetPos)
            return currentPath

        if (currentNode in visited)
            continue

        visited.add(currentNode)

        for (dir in Dir.entries) {
            val newNode = currentNode + dir.v

            if (newNode in visited || newNode !in grid || newNode in walls)
                continue

            val newCost = currentPath.cost + sqrt(
                ((targetPos.x - newNode.x) * (targetPos.x - newNode.x)).toDouble() +
                ((targetPos.y - newNode.y) * (targetPos.y - newNode.y)).toDouble()
            )
            val newPath = currentPath.nodes + newNode

            open.add(Path(cost = newCost, nodes = newPath))
        }
    }

    return null
}

data class Path(
    val cost: Double,
    val nodes: List<Vector>,
)

fun getWallPos(lines: Sequence<String>) =
    lines.map {
        val coords = it.split(",")
        Vector(coords[0].toInt(), coords[1].toInt())
    }

fun printWalls(walls: List<Vector>) {
    val width = walls.maxOf { it.x } + 1
    val height = walls.maxOf { it.y } + 1

    val canvas = Array(height) { CharArray(width) { '.' } }

    walls.forEach {
        canvas[it.y][it.x] = '#'
    }

    canvas.forEach { println(it) }
}