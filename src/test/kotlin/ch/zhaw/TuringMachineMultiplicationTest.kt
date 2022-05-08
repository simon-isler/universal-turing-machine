package ch.zhaw

import ch.zhaw.TuringMachine.Mode
import ch.zhaw.config.Decoder
import ch.zhaw.config.MULTIPLICATION_INPUT_FILE
import org.junit.jupiter.api.Test
import org.junit.jupiter.api.assertThrows
import kotlin.test.assertEquals
import kotlin.test.assertFalse
import kotlin.test.assertTrue

/*
  Tests the turing machine designed for multiplication.
 */
class TuringMachineMultiplicationTest {
    private var input = ""
    private val states = Decoder(MULTIPLICATION_INPUT_FILE).getStates()

    @Test
    fun `2 * 4`() {
        input = "0010000"
        val turingMachine = TuringMachine(input, states)
        turingMachine.run(Mode.RUN)

        assertTrue(turingMachine.isAccepted())
        assertEquals(8, turingMachine.getTape().getResult())
    }

    @Test
    fun `13 * 17`() {
        input = "0000000000000100000000000000000"
        val turingMachine = TuringMachine(input, states)
        turingMachine.run(Mode.RUN)

        assertTrue(turingMachine.isAccepted())
        assertEquals(221, turingMachine.getTape().getResult())
    }

    @Test
    fun `1 * 27`() {
        input = "01000000000000000000000000000"
        val turingMachine = TuringMachine(input, states)
        turingMachine.run(Mode.RUN)

        assertTrue(turingMachine.isAccepted())
        assertEquals(27, turingMachine.getTape().getResult())
    }

    @Test
    fun `23 * 0`() {
        input = "000000000000000000000001"
        val turingMachine = TuringMachine(input, states)
        turingMachine.run(Mode.RUN)

        assertTrue(turingMachine.isAccepted())
        assertEquals(0, turingMachine.getTape().getResult())
    }

    @Test
    fun stepMode() {
        input = "010"
        val turingMachine = TuringMachine(input, states)
        turingMachine.run(Mode.STEP)

        assertTrue(turingMachine.isAccepted())
        assertEquals(1, turingMachine.getTape().getResult())
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
