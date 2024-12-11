package com.froren.aoc24.day11

import com.froren.aoc24.bootstrap.loadResource
import kotlin.test.Test
import kotlin.test.assertEquals


private const val RESOURCE_PATH = "/day11testInput.txt"

private const val STONES = 22
//private const val TRAILHEAD_RATINGS = 81

class Day11Test {

    @Test
    fun `Part one solves correctly` () {
        val stones = loadResource(RESOURCE_PATH)
            .reader()
            .useLines(::solvePartOne)

        assertEquals(STONES, stones)
    }

//    @Test
//    fun `Part two solves correctly` () {
//        val trailheadRatings = loadResource(RESOURCE_PATH)
//            .reader()
//            .useLines(::solvePartTwo)
//
//        assertEquals(TRAILHEAD_RATINGS, trailheadRatings)
//    }

}