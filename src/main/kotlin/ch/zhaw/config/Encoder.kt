package ch.zhaw.config

import com.google.gson.Gson
import java.io.File

class Encoder(private val configPath: String, private val inputPath: String) {
    fun call(x: Int, y: Int) {
        val encodedConfiguration = encode()

        encodedConfiguration.append("0".repeat(x))
        encodedConfiguration.append(STATE_SEPARATOR)
        encodedConfiguration.append("0".repeat(y))

        File(inputPath).writeText(encodedConfiguration.toString())
    }

    private fun encode(): StringBuilder {
        val configuration: String = File(configPath).readText(Charsets.UTF_8)
        val (automaton) = Gson().fromJson(configuration, Configuration::class.java)
        val states: List<States> = automaton?.States ?: listOf()

        val encodedConfiguration = StringBuilder()

        for (state in states) {
            if (state.Transitions.isEmpty() && state.Final) {
                encodedConfiguration.append("0".repeat(state.ID!!))
                encodedConfiguration.append(STATE_SEPARATOR)
                encodedConfiguration.append(if (state.Start) STATE_STATUS["1"] else STATE_STATUS["0"])
                encodedConfiguration.append(TRANSITION_SEPARATOR)
                continue
            }

            for (transition in state.Transitions) {
                for (label in transition.Labels) {
                    // transition
                    encodedConfiguration.append(encodeTransition(transition.Source, transition.Target, label))

                    // status
                    encodedConfiguration.append(if (state.Start) STATE_STATUS["1"] else STATE_STATUS["0"])
                    encodedConfiguration.append(STATE_SEPARATOR)
                    encodedConfiguration.append(if (state.Final) STATE_STATUS["1"] else STATE_STATUS["0"])
                    encodedConfiguration.append(TRANSITION_SEPARATOR)
                }
            }
        }

        // additional 1 for input separator
        encodedConfiguration.append(STATE_SEPARATOR)

        return encodedConfiguration
    }

    private fun encodeTransition(currentStateId: Int, nextStateId: Int, label: ArrayList<Char>): String {
        val encodedTransition = StringBuilder()
        val readSymbol = label[0]
        val writeSymbol = label[1]
        val direction = label[2]

        encodedTransition.append("0".repeat(currentStateId))
        encodedTransition.append(STATE_SEPARATOR)

        when (readSymbol) {
            '0' -> encodedTransition.append(INPUT_SYMBOLS["0"])
            '1' -> encodedTransition.append(INPUT_SYMBOLS["1"])
            'X' -> encodedTransition.append(INPUT_SYMBOLS["X"])
        }
        encodedTransition.append(STATE_SEPARATOR)

        encodedTransition.append("0".repeat(nextStateId))
        encodedTransition.append(STATE_SEPARATOR)

        when (writeSymbol) {
            '0' -> encodedTransition.append(TAPE_SYMBOLS["0"])
            '1' -> encodedTransition.append(TAPE_SYMBOLS["1"])
            'X' -> encodedTransition.append(TAPE_SYMBOLS["X"])
        }
        encodedTransition.append(STATE_SEPARATOR)

        when (direction) {
            'L' -> encodedTransition.append(MOVEMENT_SYMBOLS["L"])
            'R' -> encodedTransition.append(MOVEMENT_SYMBOLS["R"])
        }

        encodedTransition.append(STATE_SEPARATOR)
        return encodedTransition.toString()
    }
}
