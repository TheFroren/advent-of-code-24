package com.froren.aoc24.day12

import com.froren.aoc24.bootstrap.solveDay

fun main() = solveDay(
    day = 12,
    partOneSolver = ::solvePartOne,
    partTwoSolver = ::solvePartTwo,
)

fun solvePartOne(lines: Sequence<String>): Int {
    val grid = lines.toList()

    val width = grid.first().length
    val height = grid.size

    val assigned = Array(height) { Array(width) { false } }

    fun Plot.addAndSpread(x: Int, y: Int) {
        if (assigned[y][x])
            return

        assigned[y][x] = true
        area++
        perimeter += 4

        if (sameType(grid, x + 1, y)) {
            perimeter--
            addAndSpread(x + 1, y)
        }
        if (sameType(grid, x - 1, y)) {
            perimeter--
            addAndSpread(x - 1, y)
        }
        if (sameType(grid, x, y + 1)) {
            perimeter--
            addAndSpread(x, y + 1)
        }
        if (sameType(grid, x, y - 1)) {
            perimeter--
            addAndSpread(x, y - 1)
        }
    }

    val plots = mutableListOf<Plot>()

    for (y in grid.indices) {
        val gridRow = grid[y]
        for (x in gridRow.indices) {
            if (assigned[y][x])
                continue

            val plot = Plot(gridRow[x])
            plot.addAndSpread(x,y)
            plots.add(plot)
        }
    }

    return plots.sumOf { it.area * it.perimeter }
}

fun solvePartTwo(lines: Sequence<String>): Int {
    val grid = lines.toList()

    val width = grid.first().length
    val height = grid.size

    val assigned = Array(height) { Array(width) { false } }

    fun Plot.addAndSpread(x: Int, y: Int) {
        if (assigned[y][x])
            return

        assigned[y][x] = true
        area++

        val right = sameType(grid, x + 1, y)
        val left = sameType(grid, x - 1, y)
        val bottom = sameType(grid, x, y + 1)
        val top = sameType(grid, x, y - 1)

        if (!left && !top)
            corners++
        if (!top && !right)
            corners++
        if (!right && !bottom)
            corners++
        if (!bottom && !left)
            corners++

        if (left && top && !sameType(grid, x-1, y-1))
            corners++
        if (top && right && !sameType(grid, x+1, y-1))
            corners++
        if (right && bottom && !sameType(grid, x+1, y+1))
            corners++
        if (bottom && left && !sameType(grid, x-1, y+1))
            corners++

        if (right)
            addAndSpread(x + 1, y)
        if (left)
            addAndSpread(x - 1, y)
        if (bottom)
            addAndSpread(x, y + 1)
        if (top)
            addAndSpread(x, y - 1)
    }

    val plots = mutableListOf<Plot>()

    for (y in grid.indices) {
        val gridRow = grid[y]
        for (x in gridRow.indices) {
            if (assigned[y][x])
                continue

            val plot = Plot(gridRow[x])
            plot.addAndSpread(x,y)
            plots.add(plot)
        }
    }

    return plots.sumOf { it.area * it.corners }
}

data class Plot(
    val type: Char,
    var area: Int = 0,
    var perimeter: Int = 0,
    var corners: Int = 0,
)

fun Plot.sameType(grid: List<String>, x: Int, y: Int) =
    x in grid.first().indices
    && y in grid.indices
    && grid[y][x] == type
