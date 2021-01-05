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
                    jump.tournament.contains("levi") -> 2.0
                    jump.tournament.contains("lech") -> 4.0
                    jump.tournament.contains("courchevel") -> 8.0
                    jump.tournament.contains("valdisere") -> 16.0
                    else -> 0.0
                }
            }
            WomenAlpeinBets.lastTournament.contains("semmering") -> {
                when {
                    jump.tournament.contains("soelden") -> 1.0
                    jump.tournament.contains("levi") -> 1.0
                    jump.tournament.contains("lech") -> 2.0
                    jump.tournament.contains("courchevel") -> 4.0
                    jump.tournament.contains("valdisere") -> 8.0
                    jump.tournament.contains("semmering") -> 16.0
                    else -> 0.0
                }
            }
            else -> return 0.0
        }
    }

}