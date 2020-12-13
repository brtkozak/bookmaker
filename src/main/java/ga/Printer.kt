package ga

import ga.entity.CouponsGroup

class Printer {

    companion object {

        fun printPopulationStatistics(population: List<CouponsGroup>, iteration: Int) {
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

            print("Iteration ${"%3s".format(iteration)}  ")
            print("Avg: ${average.format(6, 4.2)}  ")
            print("Median: ${median.format(6, 4.2)}  ")
            print("Best: ${best.format(6, 4.6)}  ")
            println("Worst: ${worst.format(6, 4.2)}  ")
        }

        private fun Double.format(space: Int, digits: Double) =
                "%${space}s".format("%${digits}f".format(this))
    }
}