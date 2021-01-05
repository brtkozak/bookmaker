package data.probability

import data.entity.bets.MenAlpineBets
import data.entity.bets.WomenAlpeinBets
import data.entity.statistics.Jump

class MenAlpineSkiWeights : Weights {

    override fun getWeight(jump: Jump): Double {
        return getTournamentWeight(jump)
    }

    private fun getTournamentWeight(jump: Jump): Double {
        return when {
            MenAlpineBets.lastTournament.contains("valgardena") -> {
                when {
                    jump.tournament.contains("soelden") -> 1.0
                    jump.tournament.contains("lech") -> 2.0
                    jump.tournament.contains("santacaterina") -> 4.0
                    jump.tournament.contains("valdisere") -> 8.0
                    jump.tournament.contains("valgardena") -> 32.0
                    else -> 0.0
                }
            }
            MenAlpineBets.lastTournament.contains("altabadia") -> {
                when {
                    jump.tournament.contains("soelden") -> 1.0
                    jump.tournament.contains("lech") -> 1.0
                    jump.tournament.contains("santacaterina") -> 2.0
                    jump.tournament.contains("valdisere") -> 4.0
                    jump.tournament.contains("valgardena") -> 8.0
                    jump.tournament.contains("altabadia") -> 16.0
                    else -> 0.0
                }
            }
            MenAlpineBets.lastTournament.contains("bormio") -> {
                when {
                    jump.tournament.contains("soelden") -> 1.0
                    jump.tournament.contains("lech") -> 1.0
                    jump.tournament.contains("santacaterina") -> 1.0
                    jump.tournament.contains("valdisere") -> 1.0
                    jump.tournament.contains("valgardena") -> 2.0
                    jump.tournament.contains("altabadia") -> 4.0
                    jump.tournament.contains("madonna") -> 8.0
                    jump.tournament.contains("bormio") -> 16.0
                    else -> 0.0
                }
            }
            else -> return 0.0
        }
    }

}