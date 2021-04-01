package data.probability

import data.BetsConverter
import data.entity.bets.MenAlpineBets
import data.entity.bets.WomenAlpeinBets
import data.entity.statistics.Jump
import data.entity.statistics.JumperResults
import data.entity.statistics.Probability
import data.entity.statistics.TournamentType
import org.apache.commons.math3.distribution.NormalDistribution
import org.apache.commons.math3.distribution.PoissonDistribution
import org.apache.commons.math3.stat.descriptive.DescriptiveStatistics
import org.apache.commons.math3.stat.descriptive.SummaryStatistics
import org.apache.commons.math3.stat.descriptive.summary.Sum

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

    private fun calculateProbability2(jumper1: JumperResults, jumper2: JumperResults): Probability? {
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
                if(sample1< 0 )
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

    private fun calculateProbability(jumper1: JumperResults, jumper2: JumperResults): Probability? {
        val stats1 = DescriptiveStatistics()
        val stats2 = DescriptiveStatistics ()

        val repeatPlace = 6
        val minPlace = 1
        val maxPlace = 30

        if(Main.MODE == Main.Companion.Mode.Jump) {
            jumper1.places.forEach { stats1.addValue(it.toDouble()) }
            jumper2.places.forEach { stats2.addValue(it.toDouble()) }
        }
        else {
            var currentSkiType = ""
            if(Main.MODE == Main.Companion.Mode.MSki)
                currentSkiType = MenAlpineBets.currentSkiType ?: ""
            else if(Main.MODE == Main.Companion.Mode.WSki)
                currentSkiType = WomenAlpeinBets.currentSkiType ?: ""

            jumper1.jumps.forEach {
                it.value.place?.let { place ->
                    if (it.value.skiType == currentSkiType) {
                        for (i in 0 until repeatPlace) {
                            stats1.addValue(place.toDouble())
                        }
                    }
                    it.value.place?.let {
                        stats1.addValue(place.toDouble())
                    }
                }
            }

            jumper2.jumps.forEach {
                it.value.place?.let { place ->
                    if (it.value.skiType == currentSkiType) {
                        for (i in 0 until repeatPlace) {
                            stats2.addValue(place.toDouble())
                        }
                    }
                    it.value.place?.let {
                        stats2.addValue(place.toDouble())
                    }
                }
            }
        }
        val median1 = stats1.getPercentile(50.0)
        val median2 = stats2.getPercentile(50.0)

        val poisson1 = getPoissonDistribution(median1, minPlace, maxPlace)
        val poisson2 = getPoissonDistribution(median2, minPlace, maxPlace)

        var jumper1WinProb = 0.0
        var jumper2WinProb = 0.0

        for(i in poisson1.indices) {
            for(k in poisson2.indices) {
                val p1 = poisson1[i]
                val p2 = poisson2[k]
                if(i < k) {
                    jumper1WinProb += p1*p2
                }
                else if(i > k) {
                    jumper2WinProb += p1*p2
                }
                else if(i == k) {
                    jumper1WinProb += (p1*p2)/2
                    jumper2WinProb += (p1*p2)/2
                    // TODO jak rozwiazywac remisy
                //  if(median1 < median2)
//                        jumper1WinProb += p1*p2
//                    else
//                        jumper2WinProb = p1*p2
                }
            }
        }

        // draws
        val rest = 1 - jumper1WinProb - jumper2WinProb
        if(median1 < median2)
            jumper1WinProb += rest
        else
            jumper2WinProb += rest
//        jumper1WinProb += rest/2
//        jumper2WinProb += rest/2

        // draws
        if(stats1.sum == 0.0 || stats2.sum == 0.0) {
            val x = 2
        }

        return Probability(jumper1.name, jumper2.name, jumper1WinProb, jumper2WinProb)
    }

    private fun calculateProbability3(jumper1: JumperResults, jumper2: JumperResults): Probability? {
        val stats1 = DescriptiveStatistics()
        val stats2 = DescriptiveStatistics ()
        if(Main.MODE == Main.Companion.Mode.Jump) {
            jumper1.places.forEach { stats1.addValue(it.toDouble()) }
            jumper2.places.forEach { stats2.addValue(it.toDouble()) }
        }
        else {
            var currentSkiType = ""
            if(Main.MODE == Main.Companion.Mode.MSki)
                currentSkiType = MenAlpineBets.currentSkiType ?: ""
            else if(Main.MODE == Main.Companion.Mode.WSki)
                currentSkiType = WomenAlpeinBets.currentSkiType ?: ""

            val hasJumper1JumpsFromThisType = jumper1.jumps.filter { it.value.skiType == currentSkiType }.isNotEmpty()
            val hasJumper2JumpsFromThisType = jumper2.jumps.filter { it.value.skiType == currentSkiType }.isNotEmpty()

//            if(hasJumper1JumpsFromThisType && hasJumper2JumpsFromThisType) {
//                jumper1.jumps.filter { it.value.skiType == currentSkiType }.mapNotNull { it.value.place }.forEach { stats1.addValue(it.toDouble()) }
//                jumper2.jumps.filter { it.value.skiType == currentSkiType }.mapNotNull { it.value.place }.forEach { stats2.addValue(it.toDouble()) }
//            }
//            else {
//                jumper1.jumps.mapNotNull { it.value.place }.forEach { stats1.addValue(it.toDouble()) }
//                jumper2.jumps.mapNotNull { it.value.place }.forEach { stats2.addValue(it.toDouble()) }
//            }

            if(hasJumper1JumpsFromThisType) {
                jumper1.jumps.filter { it.value.skiType == currentSkiType }.mapNotNull { it.value.place }.forEach { stats1.addValue(it.toDouble()) }
            }
            else {
                jumper1.jumps.mapNotNull { it.value.place }.forEach { stats1.addValue(it.toDouble()) }
            }
            if(hasJumper2JumpsFromThisType) {
                jumper2.jumps.filter { it.value.skiType == currentSkiType }.mapNotNull { it.value.place }.forEach { stats2.addValue(it.toDouble()) }
            }
            else {
                jumper2.jumps.mapNotNull { it.value.place }.forEach { stats2.addValue(it.toDouble()) }
            }

        }
        val median1 = stats1.getPercentile(50.0)
        val median2 = stats2.getPercentile(50.0)

        val minPlace = 1
        val maxPlace = 30
        val poisson1 = getPoissonDistribution(median1, minPlace, maxPlace)
        val poisson2 = getPoissonDistribution(median2, minPlace, maxPlace)

        var jumper1WinProb = 0.0
        var jumper2WinProb = 0.0

        for(i in poisson1.indices) {
            for(k in poisson2.indices) {
                val p1 = poisson1[i]
                val p2 = poisson2[k]
                if(i < k) {
                    jumper1WinProb += p1*p2
                }
                else if(i > k) {
                    jumper2WinProb += p1*p2
                }
                else if(i == k) {
                    jumper1WinProb += (p1*p2)/2
                    jumper2WinProb += (p1*p2)/2
                }
            }
        }

        // draws
        val rest = 1 - jumper1WinProb - jumper2WinProb
        if(median1 < median2)
            jumper1WinProb += rest
        else
            jumper2WinProb += rest
        // draws
        if(stats1.sum == 0.0 || stats2.sum == 0.0) {
            val x = 2
        }

        return Probability(jumper1.name, jumper2.name, jumper1WinProb, jumper2WinProb)
    }

    private fun getPoissonDistribution(expectedValue : Double, minPlace : Int, maxPlace : Int) : List<Double> {
        val poissonDistribution = PoissonDistribution(expectedValue)
        val result = mutableListOf<Double>()
        for(i in minPlace .. maxPlace) {
            result.add(poissonDistribution.probability(i))
        }
        return result
    }

}