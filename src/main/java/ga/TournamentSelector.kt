package ga

import ga.entity.CouponsGroup
import kotlin.random.Random

class TournamentSelector(val tournamentSize: Int) : PopulationSelector {

    override fun select(population: List<CouponsGroup>): List<CouponsGroup> {
//        val newPopulation = EliteSelector.selectElite(population)
        val newPopulation = mutableListOf<CouponsGroup>()
        val temp = population.toMutableList()
        while (newPopulation.size < temp.size - Main.ELITE_COUNT) {
            val tournament = mutableListOf<CouponsGroup>()
            while (tournament.size < tournamentSize) {
                val participantIndex = Random.nextInt(0, temp.size - 1)
                tournament.add(temp[participantIndex])
            }
            val winner = if (!Main.MINIMALIZATION)
                tournament.maxBy { it.rate }
            else
                tournament.minBy { it.rate }
            winner?.let {
                newPopulation.add(winner.copy())
            }
        }
        return newPopulation
    }

}