package ga.equalprob

import ga.FixUtils
import ga.PopulationCrosser
import ga.entity.CouponsGroup

class BaseCrosser : PopulationCrosser() {

    override fun cross(parents: Pair<CouponsGroup, CouponsGroup>): Pair<CouponsGroup, CouponsGroup> {
        return onePointCrosser(parents)
    }

    override fun fixPopulation(population: List<CouponsGroup>) {
        population.forEach {
            FixUtils.removeBetsRepeats(it)
            FixUtils.addRemainingAvailableBets(it)
            FixUtils.handleSupportedBets(it)
        }
    }

}
