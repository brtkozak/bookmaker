package ga

import ga.entity.CouponsGroup

interface PopulationRater {

    fun ratePopulation(population: List<CouponsGroup>)

}