package ga

import ga.entity.CouponsGroup

abstract class PopulationMutator {

    abstract fun mutate(couponsGroup: CouponsGroup)

    fun mutatePopulation(population : List<CouponsGroup>) : List<CouponsGroup> {
        val newPopulation = EliteSelector.selectElite(population)
        val temp = population.toMutableList()
        if(Main.MINIMALIZATION) {
            temp.sortBy { it.rate }
        }
        else {
            temp.sortByDescending { it.rate }
        }
        for(i in Main.ELITE_COUNT until temp.size ) {
            mutate(temp[i])
            newPopulation.add(temp[i])
        }
        return newPopulation
    }

}