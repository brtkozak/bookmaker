package ga.rsikminimization

import ga.FixUtils
import ga.PopulationCrosser
import ga.entity.CouponsGroup

class BaseCrosserWithRepeats : PopulationCrosser() {

    override fun cross(parents: Pair<CouponsGroup, CouponsGroup>): Pair<CouponsGroup, CouponsGroup> {
        return onePointCrosser(parents)
    }

    override fun fixPopulation(population: List<CouponsGroup>) {
        population.forEach {
            FixUtils.removeCouponsRepeats(it)
            FixUtils.addRemainingAvailableBets(it)
        }
    }

}