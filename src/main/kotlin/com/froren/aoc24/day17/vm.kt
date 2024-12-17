package com.froren.aoc24.day17

typealias Integer = Int

class VM(
    val program: List<Int>,
) {
    constructor(vararg program: Int) : this(program.toList())

    var a: Integer = 0
    var b: Integer = 0
    var c: Integer = 0

    var pointer = 0

    val output = mutableListOf<Integer>()

    fun advance() {
        val opCode = OpCodes.entries[program[pointer]]

        val op = program[pointer+1]

        val opVal = if (opCode.isCombo) {
            when(op) {
                4 -> a
                5 -> b
                6 -> c
                else -> op
            }
        } else {
            op
        }

        opCode.operation(this, opVal)

        if (opCode.autoAdvancePointer)
            pointer += 2
    }

    fun isHalted() = pointer !in program.indices

    fun println() {
        val next = if (!isHalted()) {
            "${OpCodes.entries[program[pointer]]} ${program[pointer+1]}"
        } else {
            "HALT"
        }

        println("A: ${a.toString(8)}, B: ${(b).toString(8)}, C: ${(c).toString(8)}, pointer: $pointer, next: $next, output: $output")
    }

}


enum class OpCodes(
    val operation: VM.(Integer) -> Unit,
    val isCombo: Boolean = false,
    val autoAdvancePointer: Boolean = true,
) {
    ADV(VM::adv, isCombo = true),
    BXL(VM::bxl),
    BST(VM::bst, isCombo = true),
    JNZ(VM::jnz, autoAdvancePointer = false),
    BXC(VM::bxc),
    OUT(VM::out, isCombo = true),
    BDV(VM::bdv, isCombo = true),
    CDV(VM::cdv, isCombo = true),
}

fun VM.adv(op: Integer) {
    a = a ushr op
}

fun VM.bxl(op: Integer) {
    b = b xor op
}

fun VM.bst(op: Integer) {
    b = op % 8
}

fun VM.jnz(op: Integer) {
    if (a == 0)
        pointer += 2
    else
        pointer = op.toInt()
}

@Suppress("UNUSED_PARAMETER")
fun VM.bxc(op: Integer) {
    b = b xor c
}

fun VM.out(op: Integer) {
    output += op % 8
}

fun VM.bdv(op: Integer) {
    b = a ushr op
}

fun VM.cdv(op: Integer) {
    c = a ushr op
}
