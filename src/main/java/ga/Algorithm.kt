package ga

import chart.ChartDataSet
import chart.ChartDrawer
import chart.Series
import data.entity.bets.SingleBet
import ga.entity.CouponsGroup

class Algorithm(
        val iterations: Int,
        val populationSize: Int,
        val availableBets: List<SingleBet>,
        val populationInitializer: PopulationInitializer,
        val populationRater : PopulationRater,
        val populationSelector: PopulationSelector,
        val populationCrosser: PopulationCrosser,
        val populationMutator: PopulationMutator,
        val chartDrawer: ChartDrawer) {

    var population = mutableListOf<CouponsGroup>()

    private val bestChromosomeRateForIterationDataSet =
            ChartDataSet("Best chromosome rate for iteration", "Iteration", "Best chromosome rate")

    fun run() {
        var iteration = 0
        population = populationInitializer.initPopulation(populationSize, availableBets) as MutableList<CouponsGroup>
        populationRater.ratePopulation(population)
        initChartDataset()
        updateChartsData(iteration)
        Printer.printPopulationStatistics(population, iteration)
        while(iteration < iterations) {
            val elite = EliteSelector.selectElite(population)
            population = populationSelector.select(population) as MutableList<CouponsGroup>
            population = populationCrosser.crossPopulation(population) as MutableList<CouponsGroup>
            population = populationMutator.mutatePopulation(population) as MutableList<CouponsGroup>
            population.addAll(elite)
            populationRater.ratePopulation(population)
            iteration++
            Printer.printPopulationStatistics(population, iteration)
            updateChartsData(iteration)
        }
        drawCharts()

        val best = population.minByOrNull { it.rate }
        val probs = mutableListOf<Double>()
        best?.coupons?.forEach { probs.add(it.getProb()) }
        val x = 2
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