package ch.zhaw

import ch.zhaw.MoveDirection.LEFT
import ch.zhaw.MoveDirection.RIGHT

private const val PRINT_OFFSET = 15

class Tape {
    private val tape = StringBuilder()
    private var headPosition = 0

    fun initTape(input: String) {
        tape.clear()
        tape.append(input)
    }

    fun read(): Char {
        return tape[headPosition]
    }

    fun move(direction: MoveDirection, write: Char) {
        tape.setCharAt(headPosition, write)
        when (direction) {
            LEFT -> headPosition--
            RIGHT -> headPosition++
        }

        if (headPosition < 0) {
            tape.insert(0, '1')
            headPosition++
        } else if (headPosition >= tape.length) {
            tape.append('1')
        }
    }

    fun print() {
        println(" ".repeat(PRINT_OFFSET) + "⬇️")

        val output = StringBuilder()
        for (i in headPosition - PRINT_OFFSET until headPosition) {
            if (i >= 0) {
                output.append(tape[i])
            } else {
                output.append(' ')
            }
        }

        for (i in headPosition until headPosition + PRINT_OFFSET) {
            if (i < tape.length) {
                output.append(tape[i])
            } else {
                output.append(' ')
            }
        }

        println(output.toString())
    }

    fun getResult(): Int {
        return tape.toString().count { it == '0' }
    }
}
