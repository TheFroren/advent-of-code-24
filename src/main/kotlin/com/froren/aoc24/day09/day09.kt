package com.froren.aoc24.day09

import com.froren.aoc24.bootstrap.solveDay

fun main() = solveDay(
    day = 9,
    partOneSolver = ::solvePartOne,
    partTwoSolver = ::solvePartTwo,
)

fun solvePartOne(lines: Sequence<String>): Long {
    val memoryBlocks = getBlocks(lines)

    val memorySize = memoryBlocks.sumOf(MemoryBlock::size)
    val memory = Array(memorySize) { -1 }

    val (correctBlocks, blocksToMove) = memoryBlocks
        .partition {
            it.size + it.index <= memorySize
        }

    correctBlocks.forEach {
        for (i in it.index..<(it.index+it.size)) {
            memory[i] = it.id
        }
    }

    var moveIndex = 0
    blocksToMove
        .asReversed()
        .forEach { blockToMove ->
            var toAllocate = blockToMove.size
            while (toAllocate > 0) {
                if (memory[moveIndex] == -1) {
                    toAllocate -= 1
                    memory[moveIndex] = blockToMove.id
                }
                moveIndex += 1
            }
        }

    printMemory(memory)

    var result = 0L
    for (i in memory.indices) {
        result += i * memory[i]
    }
    return result
}

fun solvePartTwo(lines: Sequence<String>): Long {
    val memoryBlocks = getBlocks(lines)

    val memorySize = memoryBlocks.last().let { it.index + it.size }
    val memory = Array(memorySize) { -1 }

    memoryBlocks.forEach {
        for (i in it.index..<(it.index+it.size)) {
            memory[i] = it.id
        }
    }

    memoryBlocks
        .asReversed()
        .forEach {
            val targetIndex = nextEmptySpaceRight(
                limit = it.index,
                size = it.size,
                memory = memory,
            )

            if (targetIndex != -1)
                moveBlock(
                    target = targetIndex,
                    start = it.index,
                    size = it.size,
                    memory = memory,
                )
        }

    printMemory(memory)

    var result = 0L
    for (i in memory.indices) {
        if (memory[i] != -1)
            result += i * memory[i]
    }
    return result
}

fun nextEmptySpaceRight(limit: Int, size: Int, memory: Array<Int>): Int {
    var currGapSize = 0
    for (i in 0 ..< limit) {
        if (memory[i] == -1) {
            currGapSize++
        } else {
            currGapSize = 0
        }

        if (currGapSize == size)
            return i - size + 1
    }

    return -1
}

fun moveBlock(target: Int, start: Int, size: Int, memory: Array<Int>) {
    val offset = target - start
    for (i in start..<(start+size)) {
        memory[i+offset] = memory[i]
        memory[i] = -1
    }
}

fun getBlocks(lines: Sequence<String>): List<MemoryBlock> {
    var index = 0

    val memoryBlocks = mutableListOf<MemoryBlock>()

    lines.joinToString("")
        .asSequence()
        .windowed(size = 2, step = 2, partialWindows = true) {
            Pair(
                it[0].toString().toInt(),
                it.getOrNull(1)?.toString()?.toInt() ?: 0,
            )
        }
        .forEachIndexed { id, (memorySize, emptySize) ->
            memoryBlocks.add(
                MemoryBlock(
                    id = id,
                    index = index,
                    size = memorySize,
                )
            )

            index += memorySize + emptySize
        }

    return memoryBlocks
}

data class MemoryBlock(
    val id: Int,
    val index: Int,
    val size: Int,
)

fun printMemory(memory: Array<Int>) {
    memory
        .asSequence()
        .map {
            if (it == -1)
                "."
            else
                "$it|"
        }
        .joinToString("")
        .let {
            println(it)
        }
}
