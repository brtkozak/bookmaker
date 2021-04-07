package data.entity

data class Result (
        val statesMeans: MutableList<Double> = mutableListOf<Double>(),
        val finalStates : MutableList<Double> = mutableListOf<Double>()
        )