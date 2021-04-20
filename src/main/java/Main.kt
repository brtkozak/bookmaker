import chart.LineChart
import com.google.gson.Gson
import data.BetsConverter
import data.StatisticsConverter
import data.entity.Result
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
import org.apache.commons.math3.util.Decimal64.NAN
import utils.*
import kotlin.math.round
import java.math.BigDecimal
import java.math.RoundingMode


class Main {
    companion object {

        // CONST

        // COMMON
        val MIN_VALUE = 1.0
        val MAX_VALUE = 1.6
        val MIN_SINGLE_BET_ODD = 1.3
        val MAX_SINGLE_BET_ODD = 2.6
        val ITERANTIONS = 500
        val POPULATION_SIZE = 100
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
        val  PROB_MEAN = 1/2.0

        // PROPORTIONAL RISK ONLY
        val BASE_ODD = 2.0
        val ODD_STEP = 0.4
        var PROPORTIONAL_IN_USE = false
        // CONST

        val MODE = Mode.WSki

        enum class Mode {
            Jump, MSki, WSki
        }

        @JvmStatic
        fun main(args: Array<String>) {
            val results = mutableListOf<List<Double>>()
            val iterations = 100
            for(i in 1  .. iterations){
                simulateSystem().let {
                    results.add(it)
                }
                println(i)
            }
            val resultObject = getResultObject(results)
            val gson = Gson()
            val resultJson = gson.toJson(resultObject)
            val x = 10
            val z =2
        }

        private fun getResultObject(results: List<List<Double>>) : Result {
            val resultObject = Result()
            results[0].forEach {
                resultObject.statesMeans.add(0.0)
            }
            results.forEach {
                for(i in it.indices) {
                    resultObject.statesMeans[i] += it[i]
                }
                resultObject.finalStates.add(it.last())
            }
            for (i in 0 until resultObject.statesMeans.size) {
                resultObject.statesMeans[i] = resultObject.statesMeans[i]/ results.size
            }

            // set 2 precision
            for (i in 0 until resultObject.statesMeans.size) {
                resultObject.statesMeans[i] = BigDecimal( resultObject.statesMeans[i]).setScale(2, RoundingMode.HALF_UP).toDouble()
            }

            for (i in 0 until resultObject.finalStates.size) {
                resultObject.finalStates[i] = BigDecimal( resultObject.finalStates[i]).setScale(2, RoundingMode.HALF_UP).toDouble()
            }
            // set 2 precision

            return resultObject
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
//            val singleBetsFiltered = singleBets.filter { it.value > 1}
            val singleBetsFiltered = singleBets.filter { it.value > minValue && it.value < maxValue && it.odd > minOdd && it.odd < maxOdd }.take(10)
            if(index == 22){
                val x = 2
            }
            if(singleBetsFiltered.isEmpty()){
                val x = 2
            }
            return singleBetsFiltered
        }

        fun simulateSystem() : List<Double> {
            val eventsSize = getEventsSize()
            val gains = mutableListOf<Double>()
            val bets = mutableListOf<Int>()
            val stackStrategy : StackStrategy = PercentageStack(1000.0, 10.0)
            for(i in 0 until eventsSize ) {
                setLastTournament(i)
                val chosenBets = getSingleBets(MIN_VALUE, MAX_VALUE, MIN_SINGLE_BET_ODD, MAX_SINGLE_BET_ODD, i)
                AVAILABLE_BETS = chosenBets.toMutableList()
                val algorithm = Algorithm(ITERANTIONS,
                        POPULATION_SIZE,
                        chosenBets,
                        BasePopulationInitializer(),
                        EqualProbRater(PROB_MEAN),
                        TournamentSelector(TOURNAMENT_SIZE),
                        BaseCrosser(),
                        listOf(DoubleBetSwapMutator(), SingleBetSwapMutator()),
                        LineChart(),
                        stackStrategy = stackStrategy)
                val best = algorithm.run() ?:  return listOf<Double>()
//                val best = CouponsGroup()
//                chosenBets.forEach {
//                    val c = Coupon()
//                    c.bets.add(it)
//                    best.coupons.add(c)
//                }
                stackStrategy.modifyContribution(best, true)
                stackStrategy.updateBankroll(best)
                best?.let {
                    gains.add(it.getGain())
                    bets.add(it.coupons.sumBy { it.bets.size })
                }
            }
            val totalGain = gains.sum()
//            bets.forEach {
//                println("bets: $it")
//            }
//            gains.forEach {
//                println("Gain: $it")
//            }
//            println("Total gain: $totalGain")
            return stackStrategy.bankrollStates
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

        // to bylo uzywane w polaczeniu z proporcjonalnym budowaneim kuponow aby stawki byle proporcjonalne do kursu
//        fun modifyContributions(best: CouponsGroup?) {
//            if(best == null)
//                return
//            val totalAmount : Double = best.coupons.size * 100.0
//            val totalProbs  = best.coupons.sumByDouble { it.getProb() }
//            best.coupons.forEach {
//                it.contribution = ( (it.getProb()) / totalProbs) * totalAmount
//            }
//        }
    }
}