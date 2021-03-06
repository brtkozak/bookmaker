package data

import data.entity.statistics.Jump
import data.entity.statistics.JumperResults
import data.entity.statistics.Probability
import data.entity.statistics.TournamentType
import org.apache.commons.math3.distribution.NormalDistribution
import org.apache.commons.math3.stat.descriptive.SummaryStatistics

class ProbabilityCalculator {

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
                    val difference = (it.value.points - jump2.points) * getWeight(it.value)
                    stats.addValue(difference)
                }
            }
        }

        val mean = stats.mean
        val std = stats.standardDeviation
        if (commonJumps < 7 || std == 0.0)
            return null

        val normalDistribution = NormalDistribution(mean, std)
        var jumper1Wins = 0.0
        val samples = 2000
        for (i in 0..samples) {
            val sample1= normalDistribution.sample()
            val sample2 = normalDistribution.sample()
            if(sample1 + sample2 > 0)
                jumper1Wins++
        }
        val jumper1WinProbability = jumper1Wins / samples
        val jumper2WinProbability = 1 - jumper1WinProbability

        // test
        val name1 = "kobayashi r"
        val name2 = "aalto"
        if (jumper1.name.contains(name1) && jumper2.name.contains(name2) || jumper2.name.contains(name1) && jumper1.name.contains(name2)  ) {
            val x = 2
        }
        //test

        return Probability(jumper1.name, jumper2.name, jumper1WinProbability, jumper2WinProbability)
    }

    private fun getWeight(jump: Jump): Double {
        val x = getTournamentTypeWeight(jump) * getTournamentWeight(jump)
        return x
    }

    private fun getTournamentTypeWeight(jump: Jump): Double {
        return when (jump.tournamentType) {
            TournamentType.T -> 0.5
            TournamentType.K -> 0.8
            TournamentType.P -> 1.0
            TournamentType.Z -> 1.5
        }
    }

//    private fun getTournamentWeight(jump: Jump): Double {
//        var weight = when {
//            jump.tournament.contains("wisla") -> 1.0 * 0.5
//            jump.tournament.contains("ruka") -> 2.0 * 0.5
//            jump.tournament.contains("nizny") -> 4.0 * 0.5
//            jump.tournament.contains("planica") -> 8.0 * 0.5
//            else -> 0.0
//        }
//
//        if(jump.tournament.contains(BetsConverter.lastTournament))
//            weight *= 20
//
//        return weight
//    }

    private fun getTournamentWeight(jump: Jump): Double {
        var dayBonus = 1.0
        if(jump.tournament.contains(BetsConverter.lastTournament)) {
            dayBonus = when(jump.day) {
                1 -> 1.0
                2 -> 1.5
                3 -> 2.0
                else -> 1.0
            }
        }
        return dayBonus * when {
            BetsConverter.lastTournament.contains("nizny") -> {
                 when {
                    jump.tournament.contains("wisla") -> 1.0
                    jump.tournament.contains("ruka") -> 2.0
                    jump.tournament.contains("nizny") -> 4.0
                    else -> 0.0
                }
            }
            BetsConverter.lastTournament.contains("planica") -> {
                when {
                    jump.tournament.contains("wisla") -> 1.0
                    jump.tournament.contains("ruka") -> 2.0
                    jump.tournament.contains("nizny") -> 4.0
                    jump.tournament.contains("planica") -> 8.0
                    else -> 0.0
                }
            }
            else -> return 0.0
        }
    }


}