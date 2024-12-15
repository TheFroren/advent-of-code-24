package com.froren.aoc24.day13

import com.froren.aoc24.bootstrap.loadResource
import kotlin.test.Test
import kotlin.test.assertEquals

private const val RESOURCE_PATH = "/day13testInput.txt"

private const val MIN_COINS = 480

class Day13Test {

    @Test
    fun `Part one solves correctly` () {
        val minCoins = loadResource(RESOURCE_PATH)
            .reader()
            .useLines(::solvePartOne)

        assertEquals(MIN_COINS.toLong(), minCoins)
    }

//    @Test
//    fun `Part two solves correctly` () {
//        val price = loadResource(RESOURCE_PATH)
//            .reader()
//            .useLines(::solvePartTwo)
//
//        assertEquals(PRICE_SIDES, price)
//    }

}