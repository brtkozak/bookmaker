import chart.LineChart
import data.BetsConverter
import data.StatisticsConverter
import data.ProbabilityCalculator
import data.entity.bets.SingleBet
import ga.*
import ga.BasePopulationInitializer
import ga.DoubleBetSwapMutator
import ga.entity.Coupon
import ga.entity.CouponsGroup
import ga.equalprob.EqualProbRater
import ga.propportionalodd.ProportionalOddRater
import ga.rsikminimization.BaseCrosserWithRepeats
import ga.rsikminimization.RiskMinimizationRater

class Main {
    companion object {

        // CONST

        // COMMON
        val MIN_VALUE = 1.0
        val MAX_VALUE = 1.4
        val MIN_SINGLE_BET_ODD = 1.4
        val MAX_SINGLE_BET_ODD = 2.5
        val ITERANTIONS = 20
        val POPULATION_SIZE = 40
        val TOURNAMENT_SIZE = 4
        val ELITE_PERCENTAGE = 3
        val ELITE_COUNT : Int = if((POPULATION_SIZE * ELITE_PERCENTAGE * 0.01).toInt() < 1) 1 else (POPULATION_SIZE * ELITE_PERCENTAGE * 0.01).toInt()
        val MINIMALIZATION = true
        val CROSSING_PROBABLITY = 0.8
        val MUTATION_PROBABILITY = 0.2
        var AVAILABLE_BETS: MutableList<SingleBet> = mutableListOf()
        var THE_SAME_BEST_ITERATIONS = ITERANTIONS/ 2
        var THE_SAME_BEST_ITERATIONS_TIMES = 2
        // EQUAL PROB ONLY
        val MIN_COUPON_SIZE = 1
        val MAX_COUPON_SIZE = 10
        val PROB_MEAN = 1 / 3.0

        // PROPORTIONAL RISK ONLY
        val BASE_ODD = 1.4
        val ODD_STEP = 0.3
        // CONST

        @JvmStatic
        fun main(args: Array<String>) {
            simulateSystem()
//            val chosenBets = getSingleBets(MIN_VALUE, MIN_SINGLE_BET_ODD, MAX_SINGLE_BET_ODD)
//            AVAILABLE_BETS = chosenBets.toMutableList()
//            val algorithm = Algorithm(ITERANTIONS,
//                    POPULATION_SIZE,
//                    chosenBets,
//                    BasePopulationInitializer(),
//                    ProportionalOddRater(),
//                    TournamentSelector(TOURNAMENT_SIZE),
//                    BaseCrosser(),
//                    listOf(DoubleBetSwapMutator(), SingleBetSwapMutator()),
//                    LineChart())
//            algorithm.run()
        }

        fun getSingleBets(minValue: Double, maxValue: Double, minOdd: Double, maxOdd: Double, index : Int): List<SingleBet> {
            val converter = StatisticsConverter()
            val calculator = ProbabilityCalculator()
            val betsConverter = BetsConverter()

            val skiJumpingData = converter.getSkiJumpingData(index)
            val skiJumpingResults = converter.getJumpersResults(skiJumpingData)
            val probabilities = calculator.getProbabilities(skiJumpingResults)
            val bets = betsConverter.getBets(probabilities, index)
            var singleBets = betsConverter.getSingeBets(bets)
            singleBets = singleBets.filter { it.value > minValue && it.value < maxValue && it.odd > minOdd && it.odd < maxOdd }
            if(index == 3) { // planica 2 usunac tych dwoch bo pelno zakladow na nich stawialo
                singleBets = singleBets.filter { !it.name2.contains("geiger") }
                singleBets = singleBets.filter { !it.name2.contains("granerud") }
                val x = 2
            }
            return singleBets
        }

        fun simulateSystem() {
            val eventsSize = BetsConverter.bets.size
            val gains = mutableListOf<Double>()
            for(i in 0 until eventsSize) {
                BetsConverter.setLastTournament(i)
                val chosenBets = getSingleBets(MIN_VALUE, MAX_VALUE, MIN_SINGLE_BET_ODD, MAX_SINGLE_BET_ODD, i).take(12)
                AVAILABLE_BETS = chosenBets.toMutableList()
                val algorithm = Algorithm(ITERANTIONS,
                        POPULATION_SIZE,
                        chosenBets,
                        BasePopulationInitializer(),
                        RiskMinimizationRater(),
                        TournamentSelector(TOURNAMENT_SIZE),
                        BaseCrosserWithRepeats(),
                        listOf(DoubleBetSwapMutator(), SingleBetSwapMutator()),
                        LineChart())
                val best = algorithm.run()
//                val best = CouponsGroup()
//                chosenBets.forEach {
//                    val c = Coupon()
//                    c.bets.add(it)
//                    best.coupons.add(c)
//                }
                best?.let {
                    gains.add(it.getGain())
                }
            }
            val totalGain = gains.sum()
            gains.forEach {
                println("Gain: $it")
            }
            println("Total gain: $totalGain")
        }
    }
}