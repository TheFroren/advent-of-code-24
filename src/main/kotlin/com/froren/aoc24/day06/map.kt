package com.froren.aoc24.day06

data class Map(
    val width: Int,
    val height: Int,
    val obstructions: List<Cell>,
    val guardInitial: Cell,
)

data class Cell(
    val x: Int,
    val y: Int,
)

enum class Dir(
    val x: Int,
    val y: Int,
) {
    UP(0,-1),
    RIGHT(1,0),
    DOWN(0,1),
    LEFT(-1,0);

    fun turnCW(amount: Int) =
        Dir.entries[(this.ordinal + amount) % 4]
    fun turnRight() = turnCW(1)
}

operator fun Cell.plus(dir: Dir) =
    Cell(x + dir.x, y + dir.y)

fun Map.hasInbound(cell: Cell) =
    cell.x in 0..<width &&  cell.y in 0..<height

data class Guard(
    var cell: Cell,
    var dir: Dir,
)

fun Guard.walk(map: Map, additionalObstruction: Cell? = null) {
    var nextCell = cell + dir

    while (nextCell in map.obstructions || nextCell == additionalObstruction) {
        dir = dir.turnRight()
        nextCell = cell + dir
    }

    cell += dir
}
