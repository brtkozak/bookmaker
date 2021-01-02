package data.probability

import data.BetsConverter
import data.entity.bets.SkiJumpingBets
import data.entity.statistics.Jump
import data.entity.statistics.TournamentType

class SkiJumpingWeights : Weights {

    override fun getWeight(jump: Jump): Double {
        val x = getTournamentTypeWeight(jump) * getTournamentWeight(jump)
        return x
    }

    private fun getTournamentTypeWeight(jump: Jump): Double {
        return when (jump.tournamentType) {
            TournamentType.T -> 1.0
            TournamentType.K -> 1.0
            TournamentType.P -> 1.0
            TournamentType.Z -> 1.5
        }
    }

    private fun getTournamentWeight(jump: Jump): Double {
        var dayBonus = 1.0
        if(jump.tournament.contains(SkiJumpingBets.lastTournament)) {
            dayBonus = when(jump.day) {
                1 -> 1.0
                2 -> 1.5
                3 -> 2.0
                4 -> 2.5
                else -> 1.0
            }
        }
        return dayBonus * when {
            SkiJumpingBets.lastTournament.contains("nizny") -> {
                when {
                    jump.tournament.contains("wisla") -> 1.0
                    jump.tournament.contains("ruka") -> 2.0
                    jump.tournament.contains("nizny") -> 4.0
                    else -> 0.0
                }
            }
            SkiJumpingBets.lastTournament.contains("planica") -> {
                when {
                    jump.tournament.contains("wisla") -> 1.0
                    jump.tournament.contains("ruka") -> 2.0
                    jump.tournament.contains("nizny") -> 4.0
                    jump.tournament.contains("planica") -> 8.0
                    else -> 0.0
                }
            }
            SkiJumpingBets.lastTournament.contains("engelberg") -> {
                when {
                    jump.tournament.contains("wisla") -> 1.0
                    jump.tournament.contains("ruka") -> 2.0
                    jump.tournament.contains("nizny") -> 4.0
                    jump.tournament.contains("planica") -> 8.0
                    jump.tournament.contains("engelberg") -> 16.0
                    else -> 0.0
                }
            }
            SkiJumpingBets.lastTournament.contains("oberstdorf") -> {
                when {
                    jump.tournament.contains("wisla") -> 1.0
                    jump.tournament.contains("ruka") -> 2.0
                    jump.tournament.contains("nizny") -> 4.0
                    jump.tournament.contains("planica") -> 8.0
                    jump.tournament.contains("engelberg") -> 16.0
                    jump.tournament.contains("oberstdorf") -> 128.0
                    else -> 0.0
                }
            }
            SkiJumpingBets.lastTournament.contains("gapa") -> {
                when {
                    jump.tournament.contains("wisla") -> 1.0
                    jump.tournament.contains("ruka") -> 1.0
                    jump.tournament.contains("nizny") -> 1.0
                    jump.tournament.contains("planica") -> 1.0
                    jump.tournament.contains("engelberg") -> 1.0
                    jump.tournament.contains("oberstdorf") -> 8.0
                    jump.tournament.contains("gapa") -> 16.0
                    else -> 0.0
                }
            }
            else -> return 0.0
        }
    }
}