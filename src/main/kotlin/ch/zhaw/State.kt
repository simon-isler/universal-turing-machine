package ch.zhaw

class IllegalTransitionException(s: String) : Throwable()

class State(
    private val id: Int, private val start: Boolean, private val accepting: Boolean,
    private val transitions: MutableList<Transition>
) {
    fun addTransition(transition: Transition) {
        transitions.add(transition)
    }

    @Throws(IllegalArgumentException::class)
    fun getTransitionBySymbol(symbol: Char): Transition {
        return transitions.find { it.read == symbol }
            ?: throw IllegalTransitionException("No transition for symbol $symbol")
    }

    fun getId(): Int {
        return id
    }

    fun isStart(): Boolean {
        return start
    }

    fun isAccepting(): Boolean {
        return accepting
    }
}
