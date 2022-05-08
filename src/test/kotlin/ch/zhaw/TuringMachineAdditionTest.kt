package ch.zhaw

import ch.zhaw.TuringMachine.Mode
import ch.zhaw.config.ADDITION_INPUT_FILE
import ch.zhaw.config.Decoder
import org.junit.jupiter.api.Test
import org.junit.jupiter.api.assertThrows
import kotlin.test.assertEquals
import kotlin.test.assertFalse
import kotlin.test.assertTrue

/*
  Tests the turing machine designed for addition.
 */
class TuringMachineAdditionTest {
    private var input = ""
    private val states = Decoder(ADDITION_INPUT_FILE).getStates()

    @Test
    fun `2 + 4`() {
        input = "0010000"
        val turingMachine = TuringMachine(input, states)
        turingMachine.run(Mode.RUN)

        assertTrue(turingMachine.isAccepted())
        assertEquals(6, turingMachine.getTape().getResult())
    }

    @Test
    fun `12 + 8`() {
        input = "000000000000100000000"
        val turingMachine = TuringMachine(input, states)
        turingMachine.run(Mode.RUN)

        assertTrue(turingMachine.isAccepted())
        assertEquals(20, turingMachine.getTape().getResult())
    }

    @Test
    fun stepMode() {
        input = "010"
        val turingMachine = TuringMachine(input, states)
        turingMachine.run(Mode.STEP)

        assertTrue(turingMachine.isAccepted())
        assertEquals(2, turingMachine.getTape().getResult())
    }

    @Test
    fun invalidInput() {
        input = "X12"
        val turingMachine = TuringMachine(input, states)

        assertThrows<IllegalTransitionException> { turingMachine.run(Mode.RUN) }

        input = "11111"
        assertFalse(turingMachine.isAccepted())
        assertEquals(0, turingMachine.getTape().getResult())
    }
}
