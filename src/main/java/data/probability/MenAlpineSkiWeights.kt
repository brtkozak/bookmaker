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
                    jump.tournament.contains("lech") -> 1.0
                    jump.tournament.contains("santacaterina") -> 2.0
                    jump.tournament.contains("valdisere") -> 8.0
                    jump.tournament.contains("valgardena") -> 16.0
                    else -> 0.0
                }
            }
            MenAlpineBets.lastTournament.contains("altabadia") -> {
                when {
                    jump.tournament.contains("soelden") -> 1.0
                    jump.tournament.contains("lech") -> 1.0
                    jump.tournament.contains("santacaterina") -> 1.0
                    jump.tournament.contains("valdisere") -> 2.0
                    jump.tournament.contains("valgardena") -> 8.0
                    jump.tournament.contains("altabadia") -> 16.0
                    else -> 0.0
                }
            }
            MenAlpineBets.lastTournament.contains("bormio") -> {
                when {
                    jump.tournament.contains("lech") -> 1.0
                    jump.tournament.contains("santacaterina") -> 1.0
                    jump.tournament.contains("valdisere") -> 1.0
                    jump.tournament.contains("valgardena") -> 1.0
                    jump.tournament.contains("altabadia") -> 2.0
                    jump.tournament.contains("madonna") -> 8.0
                    jump.tournament.contains("bormio") -> 16.0
                    else -> 0.0
                }
            }
            MenAlpineBets.lastTournament.contains("zagreb") -> {
                when {
                    jump.tournament.contains("santacaterina") -> 1.0
                    jump.tournament.contains("valdisere") -> 1.0
                    jump.tournament.contains("valgardena") -> 1.0
                    jump.tournament.contains("altabadia") -> 1.0
                    jump.tournament.contains("madonna") -> 2.0
                    jump.tournament.contains("bormio") -> 8.0
                    jump.tournament.contains("zagreb") -> 16.0
                    else -> 0.0
                }
            }
            MenAlpineBets.lastTournament.contains("adelboden") -> {
                when {
                    jump.tournament.contains("valdisere") -> 1.0
                    jump.tournament.contains("valgardena") -> 1.0
                    jump.tournament.contains("altabadia") -> 1.0
                    jump.tournament.contains("madonna") -> 1.0
                    jump.tournament.contains("bormio") -> 2.0
                    jump.tournament.contains("zagreb") -> 8.0
                    jump.tournament.contains("adelboden") -> 16.0
                    else -> 0.0
                }
            }
            MenAlpineBets.lastTournament.contains("kitzbuehel") -> {
                when {
                    jump.tournament.contains("altabadia") -> 1.0
                    jump.tournament.contains("madonna") -> 1.0
                    jump.tournament.contains("bormio") -> 1.0
                    jump.tournament.contains("zagreb") -> 1.0
                    jump.tournament.contains("adelboden") -> 2.0
                    jump.tournament.contains("flachau") -> 8.0
                    jump.tournament.contains("kitzbuehel") -> 16.0
                    else -> 0.0
                }
            }
            MenAlpineBets.lastTournament.contains("schladming") -> {
                when {
                    jump.tournament.contains("madonna") -> 1.0
                    jump.tournament.contains("bormio") -> 1.0
                    jump.tournament.contains("zagreb") -> 1.0
                    jump.tournament.contains("adelboden") -> 1.0
                    jump.tournament.contains("flachau") -> 2.0
                    jump.tournament.contains("kitzbuehel") -> 8.0
                    jump.tournament.contains("schladming") -> 16.0
                    else -> 0.0
                }
            }
            MenAlpineBets.lastTournament.contains("chamonix") -> {
                when {
                    jump.tournament.contains("bormio") -> 1.0
                    jump.tournament.contains("zagreb") -> 1.0
                    jump.tournament.contains("adelboden") -> 1.0
                    jump.tournament.contains("flachau") -> 1.0
                    jump.tournament.contains("kitzbuehel") -> 2.0
                    jump.tournament.contains("schladming") -> 8.0
                    jump.tournament.contains("chamonix") -> 16.0
                    else -> 0.0
                }
            }
            MenAlpineBets.lastTournament.contains("gapa") -> {
                when {
                    jump.tournament.contains("zagreb") -> 1.0
                    jump.tournament.contains("adelboden") -> 1.0
                    jump.tournament.contains("flachau") -> 1.0
                    jump.tournament.contains("kitzbuehel") -> 1.0
                    jump.tournament.contains("schladming") -> 2.0
                    jump.tournament.contains("chamonix") -> 8.0
                    jump.tournament.contains("gapa") -> 16.0
                    else -> 0.0
                }
            }
            MenAlpineBets.lastTournament.contains("cortina") -> {
                when {
                    jump.tournament.contains("adelboden") -> 1.0
                    jump.tournament.contains("flachau") -> 1.0
                    jump.tournament.contains("kitzbuehel") -> 1.0
                    jump.tournament.contains("schladming") -> 1.0
                    jump.tournament.contains("chamonix") -> 2.0
                    jump.tournament.contains("gapa") -> 8.0
                    jump.tournament.contains("cortina") -> 16.0
                    else -> 0.0
                }
            }
            MenAlpineBets.lastTournament.contains("saalbach") -> {
                when {
                    jump.tournament.contains("kitzbuehel") -> 1.0
                    jump.tournament.contains("schladming") -> 1.0
                    jump.tournament.contains("chamonix") -> 1.0
                    jump.tournament.contains("gapa") -> 1.0
                    jump.tournament.contains("cortina") -> 2.0
                    jump.tournament.contains("bansko") -> 8.0
                    jump.tournament.contains("saalbach") -> 16.0
                    else -> 0.0
                }
            }
            MenAlpineBets.lastTournament.contains("kranjska") -> {
                when {
                    jump.tournament.contains("schladming") -> 1.0
                    jump.tournament.contains("chamonix") -> 1.0
                    jump.tournament.contains("gapa") -> 1.0
                    jump.tournament.contains("cortina") -> 1.0
                    jump.tournament.contains("bansko") -> 2.0
                    jump.tournament.contains("saalbach") -> 8.0
                    jump.tournament.contains("kranjska") -> 16.0
                    else -> 0.0
                }
            }
            MenAlpineBets.lastTournament.contains("lenzerheide") -> {
                when {
                    jump.tournament.contains("chamonix") -> 1.0
                    jump.tournament.contains("gapa") -> 1.0
                    jump.tournament.contains("cortina") -> 1.0
                    jump.tournament.contains("bansko") -> 1.0
                    jump.tournament.contains("saalbach") -> 2.0
                    jump.tournament.contains("kranjska") -> 8.0
                    jump.tournament.contains("lenzerheide") -> 16.0
                    else -> 0.0
                }
            }
            else -> return 0.0
        }
    }

}