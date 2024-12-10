package com.froren.aoc24.day08

import com.froren.aoc24.bootstrap.loadResource
import kotlin.test.Test
import kotlin.test.assertEquals

private const val RESOURCE_PATH = "/day08testInput.txt"

private const val ANTINODES = 14
private const val EXTENDED_ANTINODES = 34

class Day08Test {

    @Test
    fun `Part one solves correctly` () {
        val antinodes = loadResource(RESOURCE_PATH)
            .reader()
            .useLines(::solvePartOne)

        assertEquals(ANTINODES, antinodes)
    }

    @Test
    fun `Part two solves correctly` () {
        val extendedAntinodes = loadResource(RESOURCE_PATH)
            .reader()
            .useLines(::solvePartTwo)

        assertEquals(EXTENDED_ANTINODES, extendedAntinodes)
    }

}