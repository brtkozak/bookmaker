import chart.LineChart
import data.BetsConverter
import data.StatisticsConverter
import data.ProbabilityCalculator
import data.entity.bets.SingleBet
import ga.*
import ga.equalprob.BaseCrosser
import ga.BasePopulationInitializer
import ga.equalprob.EqualProbRater
import ga.equalprob.SingleCouponSwapMutator
import ga.rsikminimization.BaseCrosserWithRepeats
import ga.rsikminimization.RiskMinimizationRater

class Main {
    companion object {

        // CONST

        // COMMON
        val MIN_VALUE = 1.1
        val MIN_SINGLE_BET_ODD = 1.6
        val MAX_SINGLE_BET_ODD = 2.5
        val ITERANTIONS = 2000
        val POPULATION_SIZE = 200
        val TOURNAMENT_SIZE = 4
        val ELITE_PERCENTAGE = 3
        val ELITE_COUNT : Int = (POPULATION_SIZE * ELITE_PERCENTAGE * 0.01).toInt()
        val MINIMALIZATION = true
        val CROSSING_PROBABLITY = 0.8
        val MUTATION_PROBABILITY = 0.2
        var AVAILABLE_BETS: MutableList<SingleBet> = mutableListOf()
        // EQUAL PROB ONLY
        val MIN_COUPON_SIZE = 1
        val MAX_COUPON_SIZE = 5
        val PROB_MEAN = 1 / 4.0

        // RISK MINIMIZATION ONLY
        var CONTRIBUTION = 0.0
        // CONST

        @JvmStatic
        fun main(args: Array<String>) {
            val chosenBets = getSingleBets(MIN_VALUE, MIN_SINGLE_BET_ODD, MAX_SINGLE_BET_ODD)
            AVAILABLE_BETS = chosenBets.toMutableList()
            CONTRIBUTION = AVAILABLE_BETS.size.toDouble()
            val algorithm = Algorithm(ITERANTIONS,
                    POPULATION_SIZE,
                    chosenBets,
                    BasePopulationInitializer(),
                    EqualProbRater(PROB_MEAN),
                    TournamentSelector(TOURNAMENT_SIZE),
                    BaseCrosser(),
                    SingleCouponSwapMutator(),
                    LineChart())
            algorithm.run()
            val x = 2
        }

        fun getSingleBets(minValue: Double, minOdd: Double, maxOdd: Double): List<SingleBet> {
            val converter = StatisticsConverter()
            val calculator = ProbabilityCalculator()
            val betsConverter = BetsConverter()

            val skiJumpingData = converter.getSkiJumpingData()
            val skiJumpingResults = converter.getJumpersResults(skiJumpingData)
            val probabilities = calculator.getProbabilities(skiJumpingResults)
            val bets = betsConverter.getBets(probabilities)
            var singleBets = betsConverter.getSingeBets(bets)
            singleBets = singleBets.filter { it.value > minValue && it.odd > minOdd && it.odd < maxOdd }
            return singleBets
        }
    }
}