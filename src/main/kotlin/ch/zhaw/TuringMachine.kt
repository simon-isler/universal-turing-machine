package ch.zhaw

class TuringMachine(private val input: String, private val states: List<State>) {
    private val tape: Tape = Tape()
    private var accepted: Boolean = false

    enum class Mode {
        STEP, RUN
    }

    fun run(mode: Mode) {
        tape.initTape(input)
        var currentState: State = getStartState()
        var steps = 0

        while (!currentState.isAccepting()) {
            val readSymbol: Char = tape.read()
            val transition: Transition = currentState.getTransitionBySymbol(readSymbol)

            if (mode == Mode.STEP) {
                print(currentState, steps)
            }

            tape.move(transition.move, transition.write)
            currentState = transition.next
            steps++
        }

        accepted = currentState.isAccepting()

        print(currentState, steps)
        println("-----------------------------------------------------")
    }

    fun isAccepted(): Boolean {
        return accepted
    }

    fun getTape(): Tape {
        return tape
    }

    private fun getStartState(): State {
        return states.find(State::isStart) ?: throw IllegalStateException("No start state found")
    }

    private fun print(state: State, calculationSteps: Int) {
        println("-----------------------------------------------------")
        println("Current state: q${state.getId() - 1}")
        println("Steps: $calculationSteps")
        println("Accepted: $accepted\n")

        tape.print()

        if (accepted) {
            println("\nResult: ${tape.getResult()}")
            println("Finished! 🎉")
        }
    }
}
