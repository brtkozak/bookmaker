package ga

import ga.entity.CouponsGroup
import kotlin.random.Random

abstract class PopulationMutator {

    abstract fun mutate(couponsGroup: CouponsGroup)

    fun mutatePopulation(population : List<CouponsGroup>, force : Boolean) : List<CouponsGroup> {
        val newPopulation = EliteSelector.selectElite(population)
        val temp = population.toMutableList()
        if(Main.MINIMALIZATION) {
            temp.sortBy { it.rate }
        }
        else {
            temp.sortByDescending { it.rate }
        }
        for(i in Main.ELITE_COUNT until temp.size ) {
            val mutate = Random.nextDouble(1.0)
            if (mutate < Main.CROSSING_PROBABLITY || force)
                mutate(temp[i])
            newPopulation.add(temp[i])
        }
        return newPopulation
    }

}