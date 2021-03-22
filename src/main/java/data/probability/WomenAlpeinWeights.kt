package data.probability

import data.BetsConverter
import data.entity.bets.SkiJumpingBets
import data.entity.bets.WomenAlpeinBets
import data.entity.statistics.Jump

class WomenAlpeinWeights : Weights {
    override fun getWeight(jump: Jump): Double {
        return getTournamentWeight(jump)
    }

    private fun getTournamentWeight(jump: Jump): Double {
        return when {
            WomenAlpeinBets.lastTournament.contains("valdisere") -> {
                when {
                    jump.tournament.contains("soelden") -> 1.0
                    jump.tournament.contains("levi") -> 1.0
                    jump.tournament.contains("lech") -> 2.0
                    jump.tournament.contains("courchevel") -> 8.0
                    jump.tournament.contains("valdisere") -> 16.0
                    else -> 0.0
                }
            }
            WomenAlpeinBets.lastTournament.contains("semmering") -> {
                when {
                    jump.tournament.contains("soelden") -> 1.0
                    jump.tournament.contains("levi") -> 1.0
                    jump.tournament.contains("lech") -> 1.0
                    jump.tournament.contains("courchevel") -> 2.0
                    jump.tournament.contains("valdisere") -> 8.0
                    jump.tournament.contains("semmering") -> 16.0
                    else -> 0.0
                }
            }
            WomenAlpeinBets.lastTournament.contains("zagreb") -> {
                when {
                    jump.tournament.contains("soelden") -> 1.0
                    jump.tournament.contains("levi") -> 1.0
                    jump.tournament.contains("lech") -> 1.0
                    jump.tournament.contains("courchevel") -> 1.0
                    jump.tournament.contains("valdisere") -> 2.0
                    jump.tournament.contains("semmering") -> 8.0
                    jump.tournament.contains("zagreb") -> 16.0
                    else -> 0.0
                }
            }
            WomenAlpeinBets.lastTournament.contains("anton") -> {
                when {
                    jump.tournament.contains("levi") -> 1.0
                    jump.tournament.contains("lech") -> 1.0
                    jump.tournament.contains("courchevel") -> 1.0
                    jump.tournament.contains("valdisere") -> 1.0
                    jump.tournament.contains("semmering") -> 2.0
                    jump.tournament.contains("zagreb") -> 8.0
                    jump.tournament.contains("anton") -> 16.0
                    else -> 0.0
                }
            }
            WomenAlpeinBets.lastTournament.contains("flachau") -> {
                when {
                    jump.tournament.contains("lech") -> 1.0
                    jump.tournament.contains("courchevel") -> 1.0
                    jump.tournament.contains("valdisere") -> 1.0
                    jump.tournament.contains("semmering") -> 1.0
                    jump.tournament.contains("zagreb") -> 2.0
                    jump.tournament.contains("anton") -> 8.0
                    jump.tournament.contains("flachau") -> 16.0
                    else -> 0.0
                }
            }
            WomenAlpeinBets.lastTournament.contains("kranjska") -> {
                when {
                    jump.tournament.contains("courchevel") -> 1.0
                    jump.tournament.contains("valdisere") -> 1.0
                    jump.tournament.contains("semmering") -> 1.0
                    jump.tournament.contains("zagreb") -> 1.0
                    jump.tournament.contains("anton") -> 2.0
                    jump.tournament.contains("flachau") -> 8.0
                    jump.tournament.contains("kranjska") -> 16.0
                    else -> 0.0
                }
            }
            WomenAlpeinBets.lastTournament.contains("crans") -> {
                when {
                    jump.tournament.contains("valdisere") -> 1.0
                    jump.tournament.contains("semmering") -> 1.0
                    jump.tournament.contains("zagreb") -> 1.0
                    jump.tournament.contains("anton") -> 1.0
                    jump.tournament.contains("flachau") -> 2.0
                    jump.tournament.contains("kranjska") -> 8.0
                    jump.tournament.contains("crans") -> 16.0
                    else -> 0.0
                }
            }
            WomenAlpeinBets.lastTournament.contains("kronplatz") -> {
                when {
                    jump.tournament.contains("semmering") -> 1.0
                    jump.tournament.contains("zagreb") -> 1.0
                    jump.tournament.contains("anton") -> 1.0
                    jump.tournament.contains("flachau") -> 1.0
                    jump.tournament.contains("kranjska") -> 2.0
                    jump.tournament.contains("crans") -> 8.0
                    jump.tournament.contains("kronplatz") -> 16.0
                    else -> 0.0
                }
            }
            WomenAlpeinBets.lastTournament.contains("gapa") -> {
                when {
                    jump.tournament.contains("zagreb") -> 1.0
                    jump.tournament.contains("anton") -> 1.0
                    jump.tournament.contains("flachau") -> 1.0
                    jump.tournament.contains("kranjska") -> 1.0
                    jump.tournament.contains("crans") -> 2.0
                    jump.tournament.contains("kronplatz") -> 8.0
                    jump.tournament.contains("gapa") -> 16.0
                    else -> 0.0
                }
            }
            WomenAlpeinBets.lastTournament.contains("cortina") -> {
                when {
                    jump.tournament.contains("anton") -> 1.0
                    jump.tournament.contains("flachau") -> 1.0
                    jump.tournament.contains("kranjska") -> 1.0
                    jump.tournament.contains("crans") -> 1.0
                    jump.tournament.contains("kronplatz") -> 2.0
                    jump.tournament.contains("gapa") -> 8.0
                    jump.tournament.contains("cortina") -> 16.0
                    else -> 0.0
                }
            }
            WomenAlpeinBets.lastTournament.contains("valdifassa") -> {
                when {
                    jump.tournament.contains("flachau") -> 1.0
                    jump.tournament.contains("kranjska") -> 1.0
                    jump.tournament.contains("crans") -> 1.0
                    jump.tournament.contains("kronplatz") -> 1.0
                    jump.tournament.contains("gapa") -> 2.0
                    jump.tournament.contains("cortina") -> 8.0
                    jump.tournament.contains("valdifassa") -> 16.0
                    else -> 0.0
                }
            }
            WomenAlpeinBets.lastTournament.contains("slowacja") -> {
                when {
                    jump.tournament.contains("kranjska") -> 1.0
                    jump.tournament.contains("crans") -> 1.0
                    jump.tournament.contains("kronplatz") -> 1.0
                    jump.tournament.contains("gapa") -> 1.0
                    jump.tournament.contains("cortina") -> 2.0
                    jump.tournament.contains("valdifassa") -> 8.0
                    jump.tournament.contains("slowacja") -> 16.0
                    else -> 0.0
                }
            }
            WomenAlpeinBets.lastTournament.contains("are") -> {
                when {
                    jump.tournament.contains("crans") -> 1.0
                    jump.tournament.contains("kronplatz") -> 1.0
                    jump.tournament.contains("gapa") -> 1.0
                    jump.tournament.contains("cortina") -> 1.0
                    jump.tournament.contains("valdifassa") -> 2.0
                    jump.tournament.contains("slowacja") -> 8.0
                    jump.tournament.contains("are") -> 16.0
                    else -> 0.0
                }
            }
            WomenAlpeinBets.lastTournament.contains("lenzerheide") -> {
                when {
                    jump.tournament.contains("kronplatz") -> 1.0
                    jump.tournament.contains("gapa") -> 1.0
                    jump.tournament.contains("cortina") -> 1.0
                    jump.tournament.contains("valdifassa") -> 1.0
                    jump.tournament.contains("slowacja") -> 2.0
                    jump.tournament.contains("are") -> 8.0
                    jump.tournament.contains("lenzerheide") -> 16.0
                    else -> 0.0
                }
            }
            else -> return 0.0
        }
    }

}