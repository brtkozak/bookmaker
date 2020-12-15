import chart.LineChart
import data.BetsConverter
import data.StatisticsConverter
import data.ProbabilityCalculator
import data.entity.bets.SingleBet
import ga.*
import ga.BasePopulationInitializer
import ga.DoubleBetSwapMutator
import ga.equalprob.EqualProbRater
import ga.propportionalodd.ProportionalOddRater
import ga.rsikminimization.BaseCrosserWithRepeats
import ga.rsikminimization.RiskMinimizationRater

class Main {
    companion object {

        // CONST

        // COMMON
        val MIN_VALUE = 1.0
        val MIN_SINGLE_BET_ODD = 1.5
        val MAX_SINGLE_BET_ODD = 2.5
        val ITERANTIONS = 10000
        val POPULATION_SIZE = 500
        val TOURNAMENT_SIZE = 4
        val ELITE_PERCENTAGE = 3
        val ELITE_COUNT : Int = (POPULATION_SIZE * ELITE_PERCENTAGE * 0.01).toInt()
        val MINIMALIZATION = true
        val CROSSING_PROBABLITY = 0.8
        val MUTATION_PROBABILITY = 0.2
        var AVAILABLE_BETS: MutableList<SingleBet> = mutableListOf()
        var THE_SAME_BEST_ITERATIONS = ITERANTIONS/ 4
        var THE_SAME_BEST_ITERATIONS_TIMES = 4
        // EQUAL PROB ONLY
        val MIN_COUPON_SIZE = 1
        val MAX_COUPON_SIZE = 10
        val PROB_MEAN = 1 / 3.0

        // RISK MINIMIZATION ONLY
        var CONTRIBUTION = 0.0

        // PROPORTIONAL RISK ONLY
        val BASE_ODD = 1.5
        val ODD_STEP = 0.5
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
                    ProportionalOddRater(),
                    TournamentSelector(TOURNAMENT_SIZE),
                    BaseCrosser(),
                    listOf(DoubleBetSwapMutator(), SingleBetSwapMutator()),
                    LineChart())
            algorithm.run()
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

        fun simulateSystem() {

        }
    }
}