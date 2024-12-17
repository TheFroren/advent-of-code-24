package com.froren.aoc24.day16

import com.froren.aoc24.bootstrap.solveDay
import java.util.*

fun main() = solveDay(
    day = 16,
    partOneSolver = ::solvePartOne,
    partTwoSolver = ::solvePartTwo,
)

fun solvePartOne(lines: Sequence<String>): Int {
    val walls = getWalls(lines)

    val targetPos = Vector(walls.first().size-2, 1)
    val start = Node(
        pos = Vector(1, walls.size-2),
        incDir = Dir.RIGHT
    )

    val paths = aStarAllStar(start, targetPos, walls)

    return paths.first().cost
}

fun solvePartTwo(lines: Sequence<String>): Int {
    val walls = getWalls(lines)

    val targetPos = Vector(walls.first().size-2, 1)
    val start = Node(
        pos = Vector(1, walls.size-2),
        incDir = Dir.RIGHT
    )

    val paths = aStarAllStar(start, targetPos, walls)

    return paths
        .flatMapTo(mutableSetOf()) {
            it.nodes.map { it.pos }
        }
        .size
}

fun aStarAllStar(start: Node, targetPos: Vector, walls: List<List<Boolean>>): List<Path> {
    val open = PriorityQueue<Path>(compareBy { it.priority })
    val bestPaths = mutableListOf<Path>()
    val nodeBestCost = mutableMapOf<Node, Int>()

    open.add(Path(cost = 0, priority = 0, listOf(start)))

    while (open.isNotEmpty()) {
        val currentPath = open.poll()
        val currentNode = currentPath.nodes.last()

        if (currentNode.pos == targetPos) {
            if (bestPaths.isEmpty() || bestPaths.first().cost == currentPath.cost)
                bestPaths.add(currentPath)

            continue
        }

        val bestCostHere = nodeBestCost[currentNode] ?: Int.MAX_VALUE
        if (currentPath.cost > bestCostHere)
            continue

        nodeBestCost[currentNode] = currentPath.cost

        for (rotate in -1..1) {
            val newDir = currentNode.incDir.turnCW(rotate)
            val newPos = currentNode.pos + newDir.v

            if (walls[newPos.y][newPos.x])
                continue

            val newNode = Node(newPos, newDir)
            val newCost = currentPath.cost + 1 + if (rotate != 0) 1000 else 0
            val bestTargetCost = nodeBestCost[newNode] ?: Int.MAX_VALUE

            if (newCost > bestTargetCost)
                continue

            val newPath = currentPath.nodes + newNode
            val priority = newCost + if (newDir == Dir.LEFT || newDir == Dir.DOWN) 2000 else 0

            open.add(Path(cost = newCost, priority = priority, nodes = newPath))
        }
    }

    return bestPaths
}

data class Node(
    val pos: Vector,
    val incDir: Dir,
)

data class Path(
    val cost: Int,
    val priority: Int,
    val nodes: List<Node>,
)

fun getWalls(lines: Sequence<String>) =
    lines.map {
        it.map { it == '#' }
    }.toList()
