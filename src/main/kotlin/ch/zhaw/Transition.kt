package ch.zhaw

enum class MoveDirection {
    LEFT, RIGHT
}

data class Transition(val next: State, val read: Char, val write: Char, val move: MoveDirection)
