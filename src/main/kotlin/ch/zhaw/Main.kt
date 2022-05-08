package ch.zhaw

import ch.zhaw.TuringMachine.Mode
import ch.zhaw.config.*

fun main() {
    multiply(1, 1)
    add(1, 3)
}

fun multiply(x: Int, y: Int) {
    Encoder(MULTIPLICATION_AUTOMATON_CONFIGURATION, MULTIPLICATION_INPUT_FILE).call(x, y)
    simulate(MULTIPLICATION_INPUT_FILE, Mode.RUN)
}

fun add(x: Int, y: Int) {
    Encoder(ADDITION_AUTOMATON_CONFIGURATION, ADDITION_INPUT_FILE).call(x, y)
    simulate(ADDITION_INPUT_FILE, Mode.RUN)
}

private fun simulate(inputPath: String, mode: Mode) {
    val states: List<State> = Decoder(inputPath).getStates()
    val input: String = Decoder(inputPath).getInput()
    TuringMachine(input, states).run(mode)
}
