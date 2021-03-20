import chart.LineChart
import data.BetsConverter
import data.StatisticsConverter
import data.entity.bets.MenAlpineBets
import data.probability.ProbabilityCalculator
import data.entity.bets.SingleBet
import data.entity.bets.SkiJumpingBets
import data.entity.bets.WomenAlpeinBets
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
        val MAX_VALUE = 1.5
        val MIN_SINGLE_BET_ODD = 1.3
        val MAX_SINGLE_BET_ODD = 2.5
        val ITERANTIONS = 300
        val POPULATION_SIZE = 30
        val TOURNAMENT_SIZE = 4
        val ELITE_PERCENTAGE = 3
        val ELITE_COUNT : Int = if((POPULATION_SIZE * ELITE_PERCENTAGE * 0.01).toInt() > 0) (POPULATION_SIZE * ELITE_PERCENTAGE * 0.01).toInt() else 1
        val MINIMALIZATION = true
        val CROSSING_PROBABLITY = 0.8
        val MUTATION_PROBABILITY = 0.2
        var AVAILABLE_BETS: MutableList<SingleBet> = mutableListOf()
        var THE_SAME_BEST_ITERATIONS = ITERANTIONS/ 2
        var THE_SAME_BEST_ITERATIONS_TIMES = 4
        // EQUAL PROB ONLY
        val MIN_COUPON_SIZE = 1
        val MAX_COUPON_SIZE = 10
        val PROB_MEAN = 1 / 3.0

        // PROPORTIONAL RISK ONLY
        val BASE_ODD = 1.7
        val ODD_STEP = 0.4
        var PROPORTIONAL_IN_USE = false
        // CONST

        val MODE = Mode.Jump

        enum class Mode {
            Jump, MSki, WSki
        }

        @JvmStatic
        fun main(args: Array<String>) {
            simulateSystem()
        }

        fun getSingleBets(minValue: Double, maxValue: Double, minOdd: Double, maxOdd: Double, index : Int): List<SingleBet> {
            val converter = StatisticsConverter()
            val calculator = ProbabilityCalculator()
            val betsConverter = BetsConverter()

            val skiJumpingData = converter.getData(index)
            val skiJumpingResults = converter.getJumpersResults(skiJumpingData)
            val probabilities = calculator.getProbabilities(skiJumpingResults)
            val bets = betsConverter.getBets(probabilities, index)
            var singleBets = betsConverter.getSingeBets(bets)
            singleBets = singleBets.filter { it.value > minValue && it.value < maxValue && it.odd > minOdd && it.odd < maxOdd }.take(10)
            if (index == 9) {
                val x = 2
            }
            return singleBets
        }

        fun simulateSystem() {
            val eventsSize = getEventsSize()
            val gains = mutableListOf<Double>()
            val bets = mutableListOf<Int>()
            for(i in 0 until eventsSize ) {
                setLastTournament(i)
                val chosenBets = getSingleBets(MIN_VALUE, MAX_VALUE, MIN_SINGLE_BET_ODD, MAX_SINGLE_BET_ODD, i)
                AVAILABLE_BETS = chosenBets.toMutableList()
                val algorithm = Algorithm(ITERANTIONS,
                        POPULATION_SIZE,
                        chosenBets,
                        BasePopulationInitializer(),
                        RiskMinimizationRater(),
                        TournamentSelector(TOURNAMENT_SIZE),
                        BaseCrosser(),
                        listOf(DoubleBetSwapMutator(), SingleBetSwapMutator()),
                        LineChart())
//                val best = algorithm.run()
                val best = CouponsGroup()
                chosenBets.forEach {
                    val c = Coupon()
                    c.bets.add(it)
                    best.coupons.add(c)
                }
                if(PROPORTIONAL_IN_USE)
                    modifyContributions(best)
                best?.let {
                    gains.add(it.getGain())
                    bets.add(it.coupons.sumBy { it.bets.size })
                }
            }
            val totalGain = gains.sum()
            bets.forEach {
                println("bets: $it")
            }
            gains.forEach {
                println("Gain: $it")
            }
            println("Total gain: $totalGain")
        }

        fun setLastTournament (index : Int) {
            when(MODE) {
                Mode.Jump -> SkiJumpingBets.setLastTournament(index)
                Mode.MSki -> MenAlpineBets.setLastTournament(index)
                Mode.WSki -> WomenAlpeinBets.setLastTournament(index)
            }

        }

        fun getEventsSize(): Int {
            return when(MODE) {
                Mode.Jump -> SkiJumpingBets.bets.size
                Mode.MSki -> MenAlpineBets.bets.size
                Mode.WSki -> WomenAlpeinBets.bets.size
            }
        }

        fun modifyContributions(best: CouponsGroup?) {
            if(best == null)
                return
            val totalAmount : Double = best.coupons.size * 100.0
            val totalProbs  = best.coupons.sumByDouble { it.getProb() }
            best.coupons.forEach {
                it.contribution = ( (it.getProb()) / totalProbs) * totalAmount
            }
        }
    }
}