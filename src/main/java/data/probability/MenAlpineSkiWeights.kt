package data.probability

import data.entity.bets.MenAlpineBets
import data.entity.bets.WomenAlpeinBets
import data.entity.statistics.Jump

class MenAlpineSkiWeights : Weights {

    override fun getWeight(jump: Jump): Double {
        var weight = 1.0
        val skiTypeWeight = getSkiTypeWeight(jump)
        val tournamentWeight = getTournamentWeight(jump, skiTypeWeight > 1.5)
        return skiTypeWeight * tournamentWeight
//        return getTournamentWeight(jump)
    }

    private fun getSkiTypeWeight(jump: Jump) : Double {
        var weight = 1.0
        MenAlpineBets.currentSkiType?.let {
            if(it == jump.skiType)
                weight = 20.0 // 50
        }
        return weight
    }

    private fun getTournamentWeight(jump: Jump, sameSkiType: Boolean): Double {
        val w1 = 1.0
        val w2 = 2.0
        val w3 = 4.0
        val w4 = 8.0
        val wElse = if (sameSkiType)
            1.0
        else
            0.0
        return when {
            MenAlpineBets.lastTournament.contains("valgardena") -> {
                when {
                    jump.tournament.contains("soelden") -> w1
                    jump.tournament.contains("lech") -> w1
                    jump.tournament.contains("santacaterina") -> w2
                    jump.tournament.contains("valdisere") -> w3
                    jump.tournament.contains("valgardena") -> w4
                    else -> wElse
                }
            }
            MenAlpineBets.lastTournament.contains("altabadia") -> {
                when {
                    jump.tournament.contains("soelden") -> w1
                    jump.tournament.contains("lech") -> w1
                    jump.tournament.contains("santacaterina") -> w1
                    jump.tournament.contains("valdisere") -> w2
                    jump.tournament.contains("valgardena") -> w3
                    jump.tournament.contains("altabadia") -> w4
                    else -> wElse
                }
            }
            MenAlpineBets.lastTournament.contains("bormio") -> {
                when {
                    jump.tournament.contains("lech") -> w1
                    jump.tournament.contains("santacaterina") -> w1
                    jump.tournament.contains("valdisere") -> w1
                    jump.tournament.contains("valgardena") -> w1
                    jump.tournament.contains("altabadia") -> w2
                    jump.tournament.contains("madonna") -> w3
                    jump.tournament.contains("bormio") -> w4
                    else -> wElse
                }
            }
            MenAlpineBets.lastTournament.contains("zagreb") -> {
                when {
                    jump.tournament.contains("santacaterina") -> w1
                    jump.tournament.contains("valdisere") -> w1
                    jump.tournament.contains("valgardena") -> w1
                    jump.tournament.contains("altabadia") -> w1
                    jump.tournament.contains("madonna") -> w2
                    jump.tournament.contains("bormio") -> w3
                    jump.tournament.contains("zagreb") -> w4
                    else -> wElse
                }
            }
            MenAlpineBets.lastTournament.contains("adelboden") -> {
                when {
                    jump.tournament.contains("valdisere") -> w1
                    jump.tournament.contains("valgardena") -> w1
                    jump.tournament.contains("altabadia") -> w1
                    jump.tournament.contains("madonna") -> w1
                    jump.tournament.contains("bormio") -> w2
                    jump.tournament.contains("zagreb") -> w3
                    jump.tournament.contains("adelboden") -> w4
                    else -> wElse
                }
            }
            MenAlpineBets.lastTournament.contains("kitzbuehel") -> {
                when {
                    jump.tournament.contains("altabadia") -> w1
                    jump.tournament.contains("madonna") -> w1
                    jump.tournament.contains("bormio") -> w1
                    jump.tournament.contains("zagreb") -> w1
                    jump.tournament.contains("adelboden") -> w2
                    jump.tournament.contains("flachau") -> w3
                    jump.tournament.contains("kitzbuehel") -> w4
                    else -> wElse
                }
            }
            MenAlpineBets.lastTournament.contains("schladming") -> {
                when {
                    jump.tournament.contains("madonna") -> w1
                    jump.tournament.contains("bormio") -> w1
                    jump.tournament.contains("zagreb") -> w1
                    jump.tournament.contains("adelboden") -> w1
                    jump.tournament.contains("flachau") -> w2
                    jump.tournament.contains("kitzbuehel") -> w3
                    jump.tournament.contains("schladming") -> w4
                    else -> wElse
                }
            }
            MenAlpineBets.lastTournament.contains("chamonix") -> {
                when {
                    jump.tournament.contains("bormio") -> w1
                    jump.tournament.contains("zagreb") -> w1
                    jump.tournament.contains("adelboden") -> w1
                    jump.tournament.contains("flachau") -> w1
                    jump.tournament.contains("kitzbuehel") -> w2
                    jump.tournament.contains("schladming") -> w3
                    jump.tournament.contains("chamonix") -> w4
                    else -> wElse
                }
            }
            MenAlpineBets.lastTournament.contains("gapa") -> {
                when {
                    jump.tournament.contains("zagreb") -> w1
                    jump.tournament.contains("adelboden") -> w1
                    jump.tournament.contains("flachau") -> w1
                    jump.tournament.contains("kitzbuehel") -> w1
                    jump.tournament.contains("schladming") -> w2
                    jump.tournament.contains("chamonix") -> w3
                    jump.tournament.contains("gapa") -> w4
                    else -> wElse
                }
            }
            MenAlpineBets.lastTournament.contains("cortina") -> {
                when {
                    jump.tournament.contains("adelboden") -> w1
                    jump.tournament.contains("flachau") -> w1
                    jump.tournament.contains("kitzbuehel") -> w1
                    jump.tournament.contains("schladming") -> w1
                    jump.tournament.contains("chamonix") -> w2
                    jump.tournament.contains("gapa") -> w3
                    jump.tournament.contains("cortina") -> w4
                    else -> wElse
                }
            }
            MenAlpineBets.lastTournament.contains("saalbach") -> {
                when {
                    jump.tournament.contains("kitzbuehel") -> w1
                    jump.tournament.contains("schladming") -> w1
                    jump.tournament.contains("chamonix") -> w1
                    jump.tournament.contains("gapa") -> w1
                    jump.tournament.contains("cortina") -> w2
                    jump.tournament.contains("bansko") -> w3
                    jump.tournament.contains("saalbach") -> w4
                    else -> wElse
                }
            }
            MenAlpineBets.lastTournament.contains("kranjska") -> {
                when {
                    jump.tournament.contains("schladming") -> w1
                    jump.tournament.contains("chamonix") -> w1
                    jump.tournament.contains("gapa") -> w1
                    jump.tournament.contains("cortina") -> w1
                    jump.tournament.contains("bansko") -> w2
                    jump.tournament.contains("saalbach") -> w3
                    jump.tournament.contains("kranjska") -> w4
                    else -> wElse
                }
            }
            MenAlpineBets.lastTournament.contains("lenzerheide") -> {
                when {
                    jump.tournament.contains("chamonix") -> w1
                    jump.tournament.contains("gapa") -> w1
                    jump.tournament.contains("cortina") -> w1
                    jump.tournament.contains("bansko") -> w1
                    jump.tournament.contains("saalbach") -> w2
                    jump.tournament.contains("kranjska") -> w3
                    jump.tournament.contains("lenzerheide") -> w4
                    else -> wElse
                }
            }
            else -> return 1.0
        }
    }

}