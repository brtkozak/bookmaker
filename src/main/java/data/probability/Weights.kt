package data.probability

import data.entity.statistics.Jump

interface Weights {
    fun getWeight(jump: Jump): Double
}