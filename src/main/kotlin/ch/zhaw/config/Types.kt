package ch.zhaw.config

import com.google.gson.annotations.SerializedName

data class Configuration(
    @SerializedName("automaton") var automaton: Automaton? = Automaton()
)

data class Automaton(
    @SerializedName("States") var States: ArrayList<States> = arrayListOf(),
    @SerializedName("Alphabet") var Alphabet: ArrayList<String> = arrayListOf(),
    @SerializedName("StackAlphabet") var StackAlphabet: ArrayList<String> = arrayListOf(),
)

data class States(
    @SerializedName("ID") var ID: Int? = null,
    @SerializedName("Name") var Name: String? = null,
    @SerializedName("Final") var Final: Boolean,
    @SerializedName("Transitions") var Transitions: ArrayList<Transitions> = arrayListOf(),
    @SerializedName("Start") var Start: Boolean,
)

data class Transitions(
    @SerializedName("Source") var Source: Int,
    @SerializedName("Target") var Target: Int,
    @SerializedName("Labels") var Labels: ArrayList<ArrayList<Char>> = arrayListOf()
)
