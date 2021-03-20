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
//        if(jump.tournament.contains(SkiJumpingBets.lastTournament)) {
//            dayBonus = when(jump.day) {
//                1 -> 1.0
//                2 -> 1.5
//                3 -> 2.0
//                4 -> 2.5
//                else -> 1.0
//            }
//        }
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
                    jump.tournament.contains("ruka") -> 1.0
                    jump.tournament.contains("nizny") -> 1.0
                    jump.tournament.contains("planica") -> 1.0
                    jump.tournament.contains("engelberg") -> 4.0
                    jump.tournament.contains("oberstdorf") -> 32.0
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
            SkiJumpingBets.lastTournament.contains("innsbruck") -> {
                when {
                    jump.tournament.contains("wisla") -> 1.0
                    jump.tournament.contains("ruka") -> 1.0
                    jump.tournament.contains("nizny") -> 1.0
                    jump.tournament.contains("planica") -> 1.0
                    jump.tournament.contains("engelberg") -> 2.0
                    jump.tournament.contains("oberstdorf") -> 2.0
                    jump.tournament.contains("gapa") -> 8.0
                    jump.tournament.contains("innsbruck") -> 16.0
                    else -> 0.0
                }
            }
            SkiJumpingBets.lastTournament.contains("bischofshofen") -> {
                when {
                    jump.tournament.contains("wisla") -> 1.0
                    jump.tournament.contains("ruka") -> 1.0
                    jump.tournament.contains("nizny") -> 1.0
                    jump.tournament.contains("planica") -> 1.0
                    jump.tournament.contains("engelberg") -> 1.0
                    jump.tournament.contains("oberstdorf") -> 1.0
                    jump.tournament.contains("gapa") -> 2.0
                    jump.tournament.contains("innsbruck") -> 8.0
                    jump.tournament.contains("bischofshofen") -> 16.0
                    else -> 0.0
                }
            }
            SkiJumpingBets.lastTournament.contains("titise") -> {
                when {
                    jump.tournament.contains("ruka") -> 1.0
                    jump.tournament.contains("nizny") -> 1.0
                    jump.tournament.contains("planica") -> 1.0
                    jump.tournament.contains("engelberg") -> 1.0
                    jump.tournament.contains("oberstdorf") -> 1.0
                    jump.tournament.contains("gapa") -> 1.0
                    jump.tournament.contains("innsbruck") -> 2.0
                    jump.tournament.contains("bischofshofen") -> 8.0
                    jump.tournament.contains("titise") -> 16.0
                    else -> 0.0
                }
            }
            SkiJumpingBets.lastTournament.contains("zakopane") -> {
                when {
                    jump.tournament.contains("engelberg") -> 1.0
                    jump.tournament.contains("oberstdorf") -> 1.0
                    jump.tournament.contains("gapa") -> 1.0
                    jump.tournament.contains("innsbruck") -> 1.0
                    jump.tournament.contains("bischofshofen") -> 2.0
                    jump.tournament.contains("titise") -> 8.0
                    jump.tournament.contains("zakopane") -> 16.0
                    else -> 0.0
                }
            }
            SkiJumpingBets.lastTournament.contains("lahti") -> {
                when {
                    jump.tournament.contains("oberstdorf") -> 1.0
                    jump.tournament.contains("gapa") -> 1.0
                    jump.tournament.contains("innsbruck") -> 1.0
                    jump.tournament.contains("bischofshofen") -> 1.0
                    jump.tournament.contains("titise") -> 2.0
                    jump.tournament.contains("zakopane") -> 8.0
                    jump.tournament.contains("lahti") -> 16.0
                    else -> 0.0
                }
            }
            SkiJumpingBets.lastTournament.contains("willingen") -> {
                when {
                    jump.tournament.contains("gapa") -> 1.0
                    jump.tournament.contains("innsbruck") -> 1.0
                    jump.tournament.contains("bischofshofen") -> 1.0
                    jump.tournament.contains("titise") -> 1.0
                    jump.tournament.contains("zakopane") -> 2.0
                    jump.tournament.contains("lahti") -> 8.0
                    jump.tournament.contains("willingen") -> 16.0
                    else -> 0.0
                }
            }
            SkiJumpingBets.lastTournament.contains("klingenthal") -> {
                when {
                    jump.tournament.contains("innsbruck") -> 1.0
                    jump.tournament.contains("bischofshofen") -> 1.0
                    jump.tournament.contains("titise") -> 1.0
                    jump.tournament.contains("zakopane") -> 1.0
                    jump.tournament.contains("lahti") -> 2.0
                    jump.tournament.contains("willingen") -> 8.0
                    jump.tournament.contains("klingenthal") -> 16.0
                    else -> 0.0
                }
            }
            SkiJumpingBets.lastTournament.contains("zakopane") -> {
                when {
                    jump.tournament.contains("bischofshofen") -> 1.0
                    jump.tournament.contains("titise") -> 1.0
                    jump.tournament.contains("zakopane") -> 1.0
                    jump.tournament.contains("lahti") -> 1.0
                    jump.tournament.contains("willingen") -> 2.0
                    jump.tournament.contains("klingenthal") -> 8.0
                    jump.tournament.contains("zakopane") -> 16.0
                    else -> 0.0
                }
            }
            SkiJumpingBets.lastTournament.contains("rasnov") -> {
                when {
                    jump.tournament.contains("titise") -> 1.0
                    jump.tournament.contains("zakopane") -> 1.0
                    jump.tournament.contains("lahti") -> 1.0
                    jump.tournament.contains("willingen") -> 1.0
                    jump.tournament.contains("klingenthal") -> 2.0
                    jump.tournament.contains("zakopane") -> 8.0
                    jump.tournament.contains("rasnov") -> 16.0
                    else -> 0.0
                }
            }
            else -> return 0.0
        }
    }
}