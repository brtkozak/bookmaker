package data.probability

import data.BetsConverter
import data.entity.statistics.Jump
import data.entity.statistics.JumperResults
import data.entity.statistics.Probability
import data.entity.statistics.TournamentType
import org.apache.commons.math3.distribution.NormalDistribution
import org.apache.commons.math3.stat.descriptive.SummaryStatistics

class ProbabilityCalculator {

    private var weights : Weights = when(Main.MODE){
        Main.Companion.Mode.Jump -> SkiJumpingWeights()
        Main.Companion.Mode.MSki -> MenAlpineSkiWeights()
        Main.Companion.Mode.WSki -> WomenAlpeinWeights()
    }

    fun getProbabilities(jumpersResult: List<JumperResults>): List<Probability> {
        val probabilities = mutableListOf<Probability?>()
        for (i in 0..jumpersResult.size - 2) {
            for (j in i + 1 until jumpersResult.size) {
                probabilities.add(calculateProbability(jumpersResult[i], jumpersResult[j]))
            }
        }
        return probabilities.filterNotNull()
    }

    private fun calculateProbability(jumper1: JumperResults, jumper2: JumperResults): Probability? {
        val stats = SummaryStatistics()
        var commonJumps = 0
        jumper1.jumps.forEach {
            jumper2.jumps[it.key]?.let { jump2 ->
                if (it.value.points != 0.0 && jump2.points != 0.0) {
                    commonJumps++
                    val difference = (it.value.points - jump2.points) * weights.getWeight(it.value)
                    stats.addValue(difference)
                }
            }
        }

        val mean = stats.mean
        val std = stats.standardDeviation
        // test
        val name1 = "bennett"
        val name2 = "noel"
        if (jumper1.name.contains(name1) && jumper2.name.contains(name2) || jumper2.name.contains(name1) && jumper1.name.contains(name2)) {
            val x = 2
        }
        //test
        if (commonJumps < 5 || std == 0.0)
//            return null
            return Probability(jumper1.name, jumper2.name, 0.0, 0.0)

        val normalDistribution = NormalDistribution(mean, std)
        var jumper1Wins = 0.0
        val samples = 2000
        for (i in 0..samples) {
            val sample1= normalDistribution.sample()
            val sample2 = normalDistribution.sample()
            if(Main.MODE == Main.Companion.Mode.Jump) {
                if (sample1 + sample2 > 0)
                    jumper1Wins++
            }
            else {
                if(sample1 < 0 )
                    jumper1Wins ++
            }
        }
        val jumper1WinProbability = jumper1Wins / samples
        val jumper2WinProbability = 1 - jumper1WinProbability

        // test
        if (jumper1.name.contains(name1) && jumper2.name.contains(name2) || jumper2.name.contains(name1) && jumper1.name.contains(name2)) {
            val x = 2
        }
        if(jumper1WinProbability == 0.0 && jumper2WinProbability == 0.0) {
            val x = 2
        }
        //test
        return Probability(jumper1.name, jumper2.name, jumper1WinProbability, jumper2WinProbability)
    }

}