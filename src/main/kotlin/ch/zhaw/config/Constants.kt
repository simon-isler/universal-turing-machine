package ch.zhaw.config

// delimiters
const val STATE_SEPARATOR = "1"
const val TRANSITION_SEPARATOR = "11"
const val INPUT_SEPARATOR = "111"

// mapping
val INPUT_SYMBOLS = hashMapOf("0" to "0", "1" to "00", "X" to "000")
val TAPE_SYMBOLS = hashMapOf("0" to "0", "1" to "00", "X" to "000")
val MOVEMENT_SYMBOLS = hashMapOf("L" to "0", "R" to "00")
val STATE_STATUS = hashMapOf("0" to "0", "1" to "00")

// files
const val MULTIPLICATION_AUTOMATON_CONFIGURATION = "src/main/resources/automaton-multiplication.json"
const val MULTIPLICATION_INPUT_FILE = "src/main/resources/multiplication.txt"

const val ADDITION_AUTOMATON_CONFIGURATION = "src/main/resources/automaton-addition.json"
const val ADDITION_INPUT_FILE = "src/main/resources/addition.txt"
