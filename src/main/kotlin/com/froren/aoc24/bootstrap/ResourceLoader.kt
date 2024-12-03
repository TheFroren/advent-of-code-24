package com.froren.aoc24.bootstrap

fun loadResource(path: String) =
    object{}.javaClass.getResourceAsStream(path)!!