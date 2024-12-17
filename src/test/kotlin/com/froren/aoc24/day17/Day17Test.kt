package com.froren.aoc24.day17

import com.froren.aoc24.bootstrap.loadResource
import kotlin.test.Test
import kotlin.test.assertContentEquals
import kotlin.test.assertEquals

private const val RESOURCE_PATH = "/day17testInput.txt"

private const val OUTPUT = "4,6,3,5,6,3,5,2,1,0"

class Day17Test {

    @Test
    fun `Part one solves correctly` () {
        val output = loadResource(RESOURCE_PATH)
            .reader()
            .useLines(::solvePartOne)

        assertEquals(OUTPUT, output)
    }

    @Test
    fun `Example 1 solves correctly` () {
        val vm = VM(2,6).also { it.c = 9 }

        runVM(vm)

        assertEquals(1, vm.b)
    }

    @Test
    fun `Example 2 solves correctly` () {
        val vm = VM(5,0,5,1,5,4).also { it.a = 10 }

        runVM(vm)

        assertContentEquals(listOf(0,1,2), vm.output)
    }

    @Test
    fun `Example 3 solves correctly` () {
        val vm = VM(0,1,5,4,3,0).also { it.a = 2024 }

        runVM(vm)

        assertContentEquals(listOf(4,2,5,6,7,7,7,7,3,1,0), vm.output)
        assertEquals(0, vm.a)
    }

    @Test
    fun `Example 4 solves correctly` () {
        val vm = VM(1,7).also { it.b = 29 }

        runVM(vm)

        assertEquals(26, vm.b)
    }

    @Test
    fun `Example 5 solves correctly` () {
        val vm = VM(4,0).also { it.b = 2024; it.c = 43690 }

        runVM(vm)

        assertEquals(44354, vm.b)
    }

    private fun runVM(vm: VM) {
        println("Running vm")
        vm.println()
        while (!vm.isHalted()) {
            vm.advance()
            vm.println()
        }
    }

//    @Test
//    fun `Part two solves correctly` () {
//        val optimalNodes = loadResource(RESOURCE_PATH)
//            .reader()
//            .useLines(::solvePartTwo)
//
//        assertEquals(OPTIMAL_NODES, optimalNodes)
//    }
}