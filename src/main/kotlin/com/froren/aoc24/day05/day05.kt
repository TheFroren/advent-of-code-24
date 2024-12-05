package com.froren.aoc24.day05

import com.froren.aoc24.bootstrap.solveDay

fun main() = solveDay(
    day = 5,
    partOneSolver = ::solvePartOne,
    partTwoSolver = ::solvePartTwo,
)

fun solvePartOne(lines: Sequence<String>): Int {
    val (rules, updates) = getRulesAndUpdates(lines)

    val mustBeBefore = mutableMapOf<Int, MutableSet<Int>>()
    for (rule in rules) {
        mustBeBefore
            .getOrPut(rule.second) { mutableSetOf() }
            .add(rule.first)
    }

    return updates
        .filter {
            validateUpdate(it, mustBeBefore)
        }
        .sumOf {
            it[(it.size/2)]
        }
}

fun validateUpdate(update: List<Int>, mustBeBefore: Map<Int,Set<Int>>): Boolean {
    val seen = mutableSetOf<Int>()

    for (i in update.indices.reversed()) {
        val page = update[i]
        val mustBeBeforePage = mustBeBefore.getOrElse(page) { emptySet() }

        if (mustBeBeforePage.intersect(seen).isNotEmpty())
            return false

        seen.add(page)
    }

    return true
}

fun solvePartTwo(lines: Sequence<String>): Int {
    val (rules, updates) = getRulesAndUpdates(lines)

    return updates
        .mapNotNull { update ->
            val sorted = update.sortedWith { a, b ->
                when {
                    (a to b) in rules -> -1
                    (b to a) in rules -> 1
                    else -> 0
                }
            }

            if (update == sorted)
                null
            else
                sorted
        }
        .sumOf {
            it[(it.size/2)]
        }
}

fun getRulesAndUpdates(lines: Sequence<String>): Pair<List<Pair<Int,Int>>, List<List<Int>>> {
    var firstSection = true
    val rules = mutableListOf<Pair<Int,Int>>()
    val updates = mutableListOf<List<Int>>()

    lines.forEach { line ->
        if (line.isEmpty()) {
            firstSection = false
            return@forEach
        }

        if (firstSection) {
            val rule = line.split('|', limit = 2)
            rules.add(rule[0].toInt() to rule[1].toInt())
        } else {
            val update = line.splitToSequence(',').map(String::toInt).toList()
            updates.add(update)
        }
    }

    return Pair(rules, updates)
}


