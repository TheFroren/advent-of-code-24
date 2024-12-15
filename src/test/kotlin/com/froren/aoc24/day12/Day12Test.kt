package com.froren.aoc24.day12

import com.froren.aoc24.bootstrap.loadResource
import kotlin.test.Test
import kotlin.test.assertEquals

private const val RESOURCE_PATH = "/day12testInput.txt"

private const val PRICE = 1930
private const val PRICE_SIDES = 1206
class Day12Test {

    @Test
    fun `Part one solves correctly` () {
        val price = loadResource(RESOURCE_PATH)
            .reader()
            .useLines(::solvePartOne)

        assertEquals(PRICE, price)
    }

    @Test
    fun `Part two solves correctly` () {
        val price = loadResource(RESOURCE_PATH)
            .reader()
            .useLines(::solvePartTwo)

        assertEquals(PRICE_SIDES, price)
    }

}