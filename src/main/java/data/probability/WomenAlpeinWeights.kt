package data.probability

import data.BetsConverter
import data.entity.bets.MenAlpineBets
import data.entity.bets.SkiJumpingBets
import data.entity.bets.WomenAlpeinBets
import data.entity.statistics.Jump

class WomenAlpeinWeights : Weights {
    override fun getWeight(jump: Jump): Double {
        var weight = 1.0
        val skiTypeWeight = getSkiTypeWeight(jump)
        val tournamentWeight = getTournamentWeight(jump, skiTypeWeight > 1.5)
        return skiTypeWeight * tournamentWeight
//        return getTournamentWeight(jump)
    }

    private fun getSkiTypeWeight(jump: Jump) : Double {
        var weight = 1.0
        WomenAlpeinBets.currentSkiType?.let {
            if(it == jump.skiType)
                weight = 1000.0
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
            WomenAlpeinBets.lastTournament.contains("valdisere") -> {
                when {
                    jump.tournament.contains("soelden") -> w1
                    jump.tournament.contains("levi") -> w1
                    jump.tournament.contains("lech") -> w2
                    jump.tournament.contains("courchevel") -> w3
                    jump.tournament.contains("valdisere") -> w4
                    else -> wElse
                }
            }
            WomenAlpeinBets.lastTournament.contains("semmering") -> {
                when {
                    jump.tournament.contains("soelden") -> w1
                    jump.tournament.contains("levi") -> w1
                    jump.tournament.contains("lech") -> w1
                    jump.tournament.contains("courchevel") -> w2
                    jump.tournament.contains("valdisere") -> w3
                    jump.tournament.contains("semmering") -> w4
                    else -> wElse
                }
            }
            WomenAlpeinBets.lastTournament.contains("zagreb") -> {
                when {
                    jump.tournament.contains("soelden") -> w1
                    jump.tournament.contains("levi") -> w1
                    jump.tournament.contains("lech") -> w1
                    jump.tournament.contains("courchevel") -> w1
                    jump.tournament.contains("valdisere") -> w2
                    jump.tournament.contains("semmering") -> w3
                    jump.tournament.contains("zagreb") -> w4
                    else -> wElse
                }
            }
            WomenAlpeinBets.lastTournament.contains("anton") -> {
                when {
                    jump.tournament.contains("levi") -> w1
                    jump.tournament.contains("lech") -> w1
                    jump.tournament.contains("courchevel") -> w1
                    jump.tournament.contains("valdisere") -> w1
                    jump.tournament.contains("semmering") -> w2
                    jump.tournament.contains("zagreb") -> w3
                    jump.tournament.contains("anton") -> w4
                    else -> wElse
                }
            }
            WomenAlpeinBets.lastTournament.contains("flachau") -> {
                when {
                    jump.tournament.contains("lech") -> w1
                    jump.tournament.contains("courchevel") -> w1
                    jump.tournament.contains("valdisere") -> w1
                    jump.tournament.contains("semmering") -> w1
                    jump.tournament.contains("zagreb") -> w2
                    jump.tournament.contains("anton") -> w3
                    jump.tournament.contains("flachau") -> w4
                    else -> wElse
                }
            }
            WomenAlpeinBets.lastTournament.contains("kranjska") -> {
                when {
                    jump.tournament.contains("courchevel") -> w1
                    jump.tournament.contains("valdisere") -> w1
                    jump.tournament.contains("semmering") -> w1
                    jump.tournament.contains("zagreb") -> w1
                    jump.tournament.contains("anton") -> w2
                    jump.tournament.contains("flachau") -> w3
                    jump.tournament.contains("kranjska") -> w4
                    else -> wElse
                }
            }
            WomenAlpeinBets.lastTournament.contains("crans") -> {
                when {
                    jump.tournament.contains("valdisere") -> w1
                    jump.tournament.contains("semmering") -> w1
                    jump.tournament.contains("zagreb") -> w1
                    jump.tournament.contains("anton") -> w1
                    jump.tournament.contains("flachau") -> w2
                    jump.tournament.contains("kranjska") -> w3
                    jump.tournament.contains("crans") -> w4
                    else -> wElse
                }
            }
            WomenAlpeinBets.lastTournament.contains("kronplatz") -> {
                when {
                    jump.tournament.contains("semmering") -> w1
                    jump.tournament.contains("zagreb") -> w1
                    jump.tournament.contains("anton") -> w1
                    jump.tournament.contains("flachau") -> w1
                    jump.tournament.contains("kranjska") -> w2
                    jump.tournament.contains("crans") -> w3
                    jump.tournament.contains("kronplatz") -> w4
                    else -> wElse
                }
            }
            WomenAlpeinBets.lastTournament.contains("gapa") -> {
                when {
                    jump.tournament.contains("zagreb") -> w1
                    jump.tournament.contains("anton") -> w1
                    jump.tournament.contains("flachau") -> w1
                    jump.tournament.contains("kranjska") -> w1
                    jump.tournament.contains("crans") -> w2
                    jump.tournament.contains("kronplatz") -> w3
                    jump.tournament.contains("gapa") -> w4
                    else -> wElse
                }
            }
            WomenAlpeinBets.lastTournament.contains("cortina") -> {
                when {
                    jump.tournament.contains("anton") -> w1
                    jump.tournament.contains("flachau") -> w1
                    jump.tournament.contains("kranjska") -> w1
                    jump.tournament.contains("crans") -> w1
                    jump.tournament.contains("kronplatz") -> w2
                    jump.tournament.contains("gapa") -> w3
                    jump.tournament.contains("cortina") -> w4
                    else -> wElse
                }
            }
            WomenAlpeinBets.lastTournament.contains("valdifassa") -> {
                when {
                    jump.tournament.contains("flachau") -> w1
                    jump.tournament.contains("kranjska") -> w1
                    jump.tournament.contains("crans") -> w1
                    jump.tournament.contains("kronplatz") -> w1
                    jump.tournament.contains("gapa") -> w2
                    jump.tournament.contains("cortina") -> w3
                    jump.tournament.contains("valdifassa") -> w4
                    else -> wElse
                }
            }
            WomenAlpeinBets.lastTournament.contains("slowacja") -> {
                when {
                    jump.tournament.contains("kranjska") -> w1
                    jump.tournament.contains("crans") -> w1
                    jump.tournament.contains("kronplatz") -> w1
                    jump.tournament.contains("gapa") -> w1
                    jump.tournament.contains("cortina") -> w2
                    jump.tournament.contains("valdifassa") -> w3
                    jump.tournament.contains("slowacja") -> w4
                    else -> wElse
                }
            }
            WomenAlpeinBets.lastTournament.contains("are") -> {
                when {
                    jump.tournament.contains("crans") -> w1
                    jump.tournament.contains("kronplatz") -> w1
                    jump.tournament.contains("gapa") -> w1
                    jump.tournament.contains("cortina") -> w1
                    jump.tournament.contains("valdifassa") -> w2
                    jump.tournament.contains("slowacja") -> w3
                    jump.tournament.contains("are") -> w4
                    else -> wElse
                }
            }
            WomenAlpeinBets.lastTournament.contains("lenzerheide") -> {
                when {
                    jump.tournament.contains("kronplatz") -> w1
                    jump.tournament.contains("gapa") -> w1
                    jump.tournament.contains("cortina") -> w1
                    jump.tournament.contains("valdifassa") -> w1
                    jump.tournament.contains("slowacja") -> w2
                    jump.tournament.contains("are") -> w3
                    jump.tournament.contains("lenzerheide") -> w4
                    else -> wElse
                }
            }
            else -> return 0.0
        }
    }

}