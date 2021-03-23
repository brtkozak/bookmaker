package ga

import chart.*
import data.entity.bets.SingleBet
import ga.entity.CouponsGroup
import utils.StackStrategy

class Algorithm(
        val iterations: Int,
        val populationSize: Int,
        val availableBets: List<SingleBet>,
        val populationInitializer: PopulationInitializer,
        val populationRater : PopulationRater,
        val populationSelector: PopulationSelector,
        val populationCrosser: PopulationCrosser,
        val populationMutators: List<PopulationMutator>,
        val chartDrawer: ChartDrawer,
        val stackStrategy: StackStrategy) {

    var population = mutableListOf<CouponsGroup>()

    private val bestChromosomeRateForIterationDataSet =
            ChartDataSet("Best chromosome rate for iteration", "Iteration", "Best chromosome rate")

    fun run() : CouponsGroup? {
        var iteration = 0
        population = populationInitializer.initPopulation(populationSize, availableBets) as MutableList<CouponsGroup>
        populationRater.ratePopulation(population)
        initChartDataset()
        updateChartsData(iteration)
        Printer.printPopulationStatistics(population, iteration)
        var bestCache = 0.0
        var theSameBestIterations = 0
        var forceOperators = false
        var theSameBestIterationsTimes = 0
        while(iteration < iterations) {
            // force mutate and cross if best is the same for a long time
            // at third time init new population
            val elite = EliteSelector.selectElite(population)
            val best = if(Main.MINIMALIZATION) population.minByOrNull { it.rate }?.rate else population.maxByOrNull { it.rate }?.rate
            if(best == bestCache){
                theSameBestIterations ++
            }
            else {
                best?.let {
                    bestCache = it
                }
                theSameBestIterations = 0
            }
            if(theSameBestIterations >= Main.THE_SAME_BEST_ITERATIONS) {
                forceOperators = true
                theSameBestIterations = 0
                theSameBestIterationsTimes ++
                println(" ------------------------------------------ FORCE OPERATORS ------------------------------------------")
                if(theSameBestIterationsTimes >= Main.THE_SAME_BEST_ITERATIONS_TIMES ){
                    println(" ------------------------------------------ INIT NEW POP ------------------------------------------")
                    forceOperators = false
                    theSameBestIterationsTimes = 0
                    population = populationInitializer.initPopulation(populationSize, availableBets) as MutableList<CouponsGroup>
                    populationRater.ratePopulation(population)
                    population.drop(elite.count())
                }
            }
            // end force mutate and cross
            population = populationSelector.select(population) as MutableList<CouponsGroup>
            population = populationCrosser.crossPopulation(population, forceOperators) as MutableList<CouponsGroup>
            populationMutators.forEach {
                population = it.mutatePopulation(population, forceOperators) as MutableList<CouponsGroup>
            }
            populationCrosser.fixPopulation(population)
            population.addAll(elite)
            populationRater.ratePopulation(population)
            iteration++
            Printer.printPopulationStatistics(population, iteration)
            updateChartsData(iteration)
            if(forceOperators)
                forceOperators = !forceOperators
        }
//        drawCharts()

        val best = population.minByOrNull { it.rate }
//        drawGainChart(best)
        printExpectedValue(best)

        val probs = mutableListOf<Double>()
        best?.coupons?.forEach { probs.add(it.getProb()) }
        val odds = mutableListOf<Double>()
        best?.coupons?.forEach { odds.add(it.getOdd()) }
        odds.sort()
        println("COUPONS: ${best?.coupons?.size}")
        println("ALL BETS: ${best?.coupons?.sumBy { it.bets.size }}")
        val x = 2

        return best
    }

    private fun drawGainChart(best : CouponsGroup?) {
        best?.let {
            val gainChart = GainChart(best, ScatterChart())
            gainChart.processCouponsGroup()
        }
    }

    private fun initChartDataset() {
        val seriesMedianRateIterationDataSet = Series("pk: ${Main.CROSSING_PROBABLITY} pm: ${Main.MUTATION_PROBABILITY}")
        bestChromosomeRateForIterationDataSet.series.add(seriesMedianRateIterationDataSet)
    }

    private fun updateChartsData(iteration: Int) {
        bestChromosomeRateForIterationDataSet.series.let {
            it[it.size - 1].data.add(Pair(iteration.toDouble(), getPopulationStatistics().best))
        }
    }
    private fun drawCharts() {
        chartDrawer.draw(bestChromosomeRateForIterationDataSet)
    }

    private fun printExpectedValue(couponsGroup: CouponsGroup?) {
        couponsGroup?.let {
            var expectedValue = 0.0
            it.coupons.forEach { coupon ->
                expectedValue += coupon.getProb() * coupon.getWinCash()
            }
            println("EXPECTED VALUE: $expectedValue")
        }
    }

    private fun getPopulationStatistics() : PopulationStatistics {
        var best = population[0].rate
        var worst = population[0].rate
        var sum = 0.0

        if(Main.MINIMALIZATION ) {
            population.forEach {
                if (it.rate < best)
                    best = it.rate
                else if (it.rate > worst)
                    worst = it.rate
                sum += it.rate
            }
        }
        else {
            population.forEach {
                if (it.rate > best)
                    best = it.rate
                else if (it.rate < worst)
                    worst = it.rate
                sum += it.rate
            }
        }

        val average = sum / population.size
        var median = 0.0
        population.sortedBy { it.rate }

        median = if (population.size % 2 == 0) {
            (population[population.size / 2].rate + population[population.size / 2 + 1].rate) / 2
        } else {
            population[population.size / 2].rate
        }

        return PopulationStatistics(average, median, best, worst)
    }

    data class PopulationStatistics(
            val average : Double,
            val median: Double,
            val best: Double,
            val worst: Double
    )

}