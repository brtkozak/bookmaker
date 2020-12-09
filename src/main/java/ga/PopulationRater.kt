package ga

import ga.entity.CouponsGroup

abstract class PopulationRater {

    abstract fun rateCouponsGroup(couponsGroup: CouponsGroup)

    fun ratePopulation(population: List<CouponsGroup>) {
        population.forEach {
            rateCouponsGroup(it)
        }
    }

}