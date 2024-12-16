package com.froren.aoc24.day15.pt2

import com.froren.aoc24.day15.Dir
import com.froren.aoc24.day15.Vector
import com.froren.aoc24.day15.plus

fun solvePartTwo(lines: Sequence<String>): Int {
    val (map, commands) = getInput(lines)

    commands.forEach(map::walkRobot)

    return map.boxes.sumOf {
        it.pos.x + it.pos.y * 100
    }
}

fun Grid.walkRobot(dir: Dir) {
//    print(dir)

    val target = robotPos + dir.v

    val boxesToPush = mutableListOf<Box>()

    if (push(target, dir, boxesToPush)) {
        robotPos = target

        boxesToPush.forEach {
            it.pos += dir.v
        }
    }
}

fun Grid.push(pos: Vector, dir: Dir, boxesToPush: MutableList<Box>): Boolean {
    if (walls.any { it == pos })
        return false

    val box = boxes.firstOrNull {
        it.pos.y == pos.y && (it.pos.x == pos.x || it.pos.x + 1 == pos.x)
    }

    if (box == null || box in boxesToPush)
        return true

    boxesToPush.add(box)

    return when (dir) {
        Dir.UP, Dir.DOWN -> {
            push(box.pos + dir.v, dir, boxesToPush)
            && push(box.pos + dir.v + Dir.RIGHT.v, dir, boxesToPush)
        }
        Dir.LEFT -> push(box.pos + dir.v, dir, boxesToPush)
        Dir.RIGHT -> push(box.pos + dir.v + Dir.RIGHT.v, dir, boxesToPush)
    }
}

private fun getInput(lines: Sequence<String>): Input {
    var firstPart = true

    lateinit var robotPos: Vector
    val walls = mutableListOf<Vector>()
    val boxes = mutableListOf<Box>()
    val commands = mutableListOf<Dir>()

    lines.forEachIndexed { y, line ->
        if (line.isEmpty()) {
            firstPart = false
            return@forEachIndexed
        }

        if (firstPart) {
            line.forEachIndexed { x, it ->
                when (it) {
                    '#' -> {
                        walls.add(Vector(x*2,y))
                        walls.add(Vector(x*2 + 1,y))
                    }
                    'O' -> boxes.add(
                            Box(Vector(x*2,y))
                        )
                    '@' -> robotPos = Vector(x*2,y)
                }
            }

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

    return Input(
        Grid(
            walls,
            boxes,
            robotPos,
        ),
        commands,
    )
}

data class Grid(
    val walls: List<Vector>,
    val boxes: MutableList<Box>,
    var robotPos: Vector,
)

data class Box(
    var pos: Vector
)

data class Input(
    val map: Grid,
    val commands: List<Dir>
)

fun Grid.print(dir: Dir) {
    val width = walls.maxOf { it.x } + 1
    val height = walls.maxOf { it.y } + 1

    val canvas = Array(height) { CharArray(width) { ' ' } }

    boxes.forEach {
        canvas[it.pos.y][it.pos.x] = '['
        canvas[it.pos.y][it.pos.x+1] = ']'
    }

    canvas[robotPos.y][robotPos.x] =
        when (dir) {
            Dir.UP -> '^'
            Dir.RIGHT -> '>'
            Dir.DOWN -> 'v'
            Dir.LEFT -> '<'
        }

    walls.forEach {
        canvas[it.y][it.x] = '#'
    }

    canvas.forEach(::println)
}
