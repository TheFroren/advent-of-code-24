package com.froren.aoc24.day15

import com.froren.aoc24.bootstrap.loadResource
import com.froren.aoc24.day15.pt1.solvePartOne
import com.froren.aoc24.day15.pt2.solvePartTwo

import kotlin.test.Test
import kotlin.test.assertEquals

private const val RESOURCE_PATH = "/day15testInput.txt"

private const val GPS = 10092
private const val GPS2 = 9021
class Day15Test {

    @Test
    fun `Part one solves correctly` () {
        val gps = loadResource(RESOURCE_PATH)
            .reader()
            .useLines(::solvePartOne)

        assertEquals(GPS, gps)
    }

    @Test
    fun `Part two solves correctly` () {
        val gps = loadResource(RESOURCE_PATH)
            .reader()
            .useLines(::solvePartTwo)

        assertEquals(GPS2, gps)
    }
}