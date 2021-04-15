package data.probability

import data.entity.bets.MenAlpineBets
import data.entity.bets.WomenAlpeinBets
import data.entity.statistics.JumperResults
import data.entity.statistics.Probability
import org.apache.commons.math3.distribution.NormalDistribution
import org.apache.commons.math3.distribution.PoissonDistribution
import org.apache.commons.math3.stat.descriptive.DescriptiveStatistics
import org.apache.commons.math3.stat.descriptive.SummaryStatistics
import kotlin.math.max

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
                probabilities.add(calculateProbability01(jumpersResult[i], jumpersResult[j]))
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
                    var difference = 0.0
                    if(Main.MODE == Main.Companion.Mode.Jump)
                        difference = (it.value.points - jump2.points) * weights.getWeight(it.value)
                    else {
                        difference = (it.value.points - jump2.points)
                        var currentSkiType = ""
                        if(Main.MODE == Main.Companion.Mode.MSki)
                            currentSkiType = MenAlpineBets.currentSkiType ?: ""
                        else if(Main.MODE == Main.Companion.Mode.WSki)
                            currentSkiType = WomenAlpeinBets.currentSkiType ?: ""
                        if(it.value.skiType == currentSkiType)
                            difference *= 2
                    }
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

    private fun calculateProbability01(jumper1: JumperResults, jumper2: JumperResults): Probability? {
        val stats = DescriptiveStatistics()
        var commonJumps = 0
        jumper1.jumps.forEach {
            jumper2.jumps[it.key]?.let { jump2 ->
                if (it.value.points != 0.0 && jump2.points != 0.0) {
                    commonJumps++
                    var difference = 0.0
                    if(Main.MODE == Main.Companion.Mode.Jump)
                        difference = (it.value.points - jump2.points) * weights.getWeight(it.value)
                    else {
                        difference = (it.value.points - jump2.points) * weights.getWeight(it.value)
                        val p1 = it.value.points
                        val p2 = jump2.points
                        val x =2
                        var maxDif = 0.0
                        if(Main.MODE == Main.Companion.Mode.MSki) {
                           maxDif = 4000.0
                        }
                        else if(Main.MODE == Main.Companion.Mode.WSki) {
                            maxDif = 0.0
                        }
                        if(maxDif > 0.0) {
                            if(difference > maxDif)
                                difference = maxDif
                            else if (difference < maxDif * (-1))
                                difference = maxDif * (-1)
                        }
                    }
                    stats.addValue(difference)
                }
            }
        }

//        val mean = stats.mean
        val mean = stats.getPercentile(50.0)
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

    private fun calculateProbability2(jumper1: JumperResults, jumper2: JumperResults): Probability? {
        val stats1 = DescriptiveStatistics()
        val stats2 = DescriptiveStatistics()

        // MEN 2/20   WOMEN 6/30
        val repeatPlace = 2
        val minPlace = 1
        val maxPlace = 20

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
        // draws
        if(stats1.sum == 0.0 || stats2.sum == 0.0) {
            val x = 2
        }

        return Probability(jumper1.name, jumper2.name, jumper1WinProb, jumper2WinProb)
    }

    private fun calculateProbability3(jumper1: JumperResults, jumper2: JumperResults): Probability? {
        val stats1 = DescriptiveStatistics()
        val stats2 = DescriptiveStatistics()

        // MEN 2/20   WOMEN 6/30
        val repeatPlace = 4
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

        if(stats1.standardDeviation == 0.0 || stats2.standardDeviation == 0.0) {
            return Probability(jumper1.name, jumper2.name, 0.0, 0.0)
        }
        val normal1 = NormalDistribution(stats1.getPercentile(50.0), stats1.standardDeviation)
        val normal2 = NormalDistribution(stats2.getPercentile(50.0), stats2.standardDeviation)

        var jumper1WinProb = 0.0
        var jumper2WinProb = 0.0

        var jumper1Wins = 0.0
        var jumper2Wins = 0.0
        val places = 30

//        for(i in 1 .. places) {
//            for(k in 1 .. places) {
//                var p1 = normal1.density(i.toDouble())
//                var p2 = normal2.density(k.toDouble())
//                if(p1 == 0.0)
//                    p1 = 1.0
//                if(p2 == 0.0)
//                    p2 = 1.0
//
//                if(i < k) {
//                    jumper1WinProb += p1*p2
//                }
//                else if(i > k) {
//                    jumper2WinProb += p1*p2
//                }
//                else if(i == k) {
//                    val abs1 = Math.abs(stats1.mean - i)
//                    val abs2= Math.abs(stats2.mean - i)
//
//                    if(stats1.mean < stats2.mean) {
//                        jumper1WinProb += p1*p2
//                    }
//                    else {
//                        jumper2WinProb += p1*p2
//                    }
//                }
//            }
//        }
//
//        // draws
//        val rest = 1 - jumper1WinProb - jumper2WinProb
//        if(stats1.mean < stats2.mean)
//            jumper1WinProb += rest
//        else
//            jumper2WinProb += rest
//        // draws

        jumper1WinProb = 0.0
        jumper2WinProb = 0.0
        jumper1Wins = 0.0
        jumper2Wins = 0.0
        val samples = 1000
        for( i in 0 .. samples) {
            val s1 = normal1.sample()
            val s2 = normal2.sample()
            if(s1 < s2)
                jumper1Wins++
            else
                jumper2Wins++
        }

        jumper1WinProb = jumper1Wins / samples
        jumper2WinProb = jumper2Wins / samples

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