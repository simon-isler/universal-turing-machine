package ch.zhaw.config

import ch.zhaw.MoveDirection
import ch.zhaw.State
import ch.zhaw.Transition
import java.nio.file.Files
import java.nio.file.Path

class Decoder(path: String) {
    private val configuration = Files.readString(Path.of(path))

    fun getStates(): List<State> {
        val states = mutableListOf<State>()
        val transitions: List<String> = configuration.split(INPUT_SEPARATOR)[0].split(TRANSITION_SEPARATOR)

        for (transition in transitions) {
            val transitionParts = transition.split(STATE_SEPARATOR)
            val currentState = decodeId(transitionParts[0])

            if (states.any { it.getId() == currentState }) {
                continue
            }

            if (transitionParts.size == 2) {
                states.add(State(currentState, transitionParts[1] == STATE_STATUS["1"], true, mutableListOf()))
                continue
            }

            val (startState, finalState) = transitionParts.subList(5, 7)

            states.add(
                State(
                    currentState,
                    startState == STATE_STATUS["1"], finalState == STATE_STATUS["1"],
                    mutableListOf()
                )
            )
        }

        for (transition in transitions) {
            val transitionParts = transition.split(STATE_SEPARATOR)
            if (transitionParts.size == 2) {
                continue
            }

            val (currentState, readSymbol,
                nextState, writeSymbol, direction
            ) = transitionParts.subList(0, 6)

            states.find { it.getId() == decodeId(currentState) }?.addTransition(
                Transition(
                    states.find { it.getId() == decodeId(nextState) }!!,
                    decodeTapeSymbol(readSymbol),
                    decodeTapeSymbol(writeSymbol),
                    if (direction == MOVEMENT_SYMBOLS["L"]) MoveDirection.LEFT else MoveDirection.RIGHT
                )
            )
        }

        return states
    }

    fun getInput(): String {
        return configuration.split(INPUT_SEPARATOR)[1]
    }

    private fun decodeId(id: String): Int {
        return id.count { c -> c == '0' }
    }

    private fun decodeTapeSymbol(symbol: String): Char {
        return TAPE_SYMBOLS.entries.find { it.value == symbol }!!.key[0]
    }
}
