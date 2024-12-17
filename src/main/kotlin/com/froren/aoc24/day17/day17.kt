package com.froren.aoc24.day17

import com.froren.aoc24.bootstrap.solveDay

fun main() = solveDay(
    day = 17,
    partOneSolver = ::solvePartOne,
    partTwoSolver = ::solvePartTwo,
)

fun solvePartOne(lines: Sequence<String>): String {
    val vm = getVM(lines)

    vm.println()

    while (!vm.isHalted()) {
        vm.advance()
        vm.println()
    }

    return vm.output.joinToString(",")
}

fun solvePartTwo(lines: Sequence<String>): Long {
    val vm = getVM(lines)
    val size = vm.program.size
    val program = LongArray(size) { vm.program[it].toLong() }

    val start = 1L shl (size*3)
    val end = 1L shl ((size+1)*3)

    for (a in start ..< end) {
        if (a % 1_000_000_000 == 0L)
            println("$start | $a | $end")

        val result = hardcodedProgram(a, size)

        if (result.contentEquals(program))
            return a
    }

    return 0
}

fun hardcodedProgram(aInitial: Long, size: Int) =
    LongArray(size) {
        val a = aInitial ushr (it*3)
        val b = (a xor 5) % 8
        (b xor 6 xor (a ushr b.toInt())) % 8
    }

fun getVM(lines: Sequence<String>): VM {
    val input = lines.toList()

    return VM(
        program = input[4].substring(9).split(',').map(String::toInt)
    ).also {
        it.a = input[0].substring(12).toInt()
        it.b = input[1].substring(12).toInt()
        it.c = input[2].substring(12).toInt()
    }
}


