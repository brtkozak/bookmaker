package ga

import ga.entity.CouponsGroup
import kotlin.random.Random

abstract class PopulationCrosser {

    protected abstract fun cross(parents: Pair<CouponsGroup, CouponsGroup>): Pair<CouponsGroup, CouponsGroup>

    fun crossPopulation(population: List<CouponsGroup>): List<CouponsGroup> {
        val newPopulation = mutableListOf<CouponsGroup>()
        val oldPopulation = population.toMutableList()
        while (newPopulation.size < population.size) {
            val firstParent = oldPopulation[0]
            val shouldCross = Random.nextDouble(0.0, 1.0)
            if (shouldCross < Main.CROSSING_PROBABLITY && Main.POPULATION_SIZE - Main.ELITE_COUNT - newPopulation.size != 1 ) {
                val secondParent = oldPopulation[1]
                val children = cross(Pair(firstParent, secondParent))
                newPopulation.addAll(listOf(children.first, children.second))
                oldPopulation.remove(secondParent)
            } else {
                newPopulation.add(firstParent.copy())
            }
            oldPopulation.remove(firstParent)
        }

        return newPopulation
    }
}