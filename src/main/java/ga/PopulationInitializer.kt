package ga

import data.entity.bets.SingleBet
import ga.entity.CouponsGroup

interface PopulationInitializer {
    fun initPopulation(populationSize: Int, availableBets: List<SingleBet>): List<CouponsGroup>
}