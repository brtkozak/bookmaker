package data

import com.google.gson.Gson
import data.entity.bets.*
import data.entity.statistics.Probability
import data.entity.statistics.SkiJumpingStatistics
import data.entity.statistics.WomenAlpeinStatistics

class BetsConverter {

    fun getBets(probabilities: List<Probability>, betsIndex : Int): List<Bet> {
        val gson = Gson()
        val bets = when(Main.MODE) {
            Main.Companion.Mode.Jump -> SkiJumpingBets.bets
            Main.Companion.Mode.WSki -> WomenAlpeinBets.bets
            Main.Companion.Mode.MSki -> MenAlpineBets.bets
        }
        val betsDto = gson.fromJson(bets[betsIndex], BetsDto::class.java)
        val result = mutableListOf<Bet>()
        betsDto.bets.forEach {
            result.add(getBet(it))
        }
        mergeProbabilities(result, probabilities)
        return result
    }

    fun getSingeBets(bets: List<Bet>): List<SingleBet> {
        val result = mutableListOf<SingleBet>()
        var index = 0
        bets.forEach {
                val bet1 = SingleBet(index++, it.name1, it.name2, it.odd1, it.book1Prob, it.my1Prob, it.value1, getBetResult(it.won, 1))
                val bet2 = SingleBet(index++, it.name2, it.name1, it.odd2, it.book2Prob, it.my2Prob, it.value2, getBetResult(it.won, 2))
                result.add(bet1)
                result.add(bet2)
        }
        result.sortByDescending { it.value }
        return result
    }

//    private fun getBetResult(value : Int, jumper: Int) : BetResult{
//        return when(value) {
//            1 -> BetResult.Win
//            2 -> BetResult.Lose
//            else -> BetResult.Unknown
//        }
//    }

    private fun getBetResult(value : Int, jumper: Int) : BetResult{
        return when(value) {
            1 ->  {
                if(jumper == 1) {
                    BetResult.Win
                }
                else BetResult.Lose
            }
            2 ->  {
                if(jumper == 2) {
                    BetResult.Win
                }
                else BetResult.Lose
            }
            else -> BetResult.Unknown
        }
    }

    private fun getBet(betDto: BetDto): Bet {
        val names = getNames(betDto)
        val probabilities = getProbabilities(betDto)
        return Bet(name1 = names.first, name2 = names.second, odd1 = betDto.odd1.toDouble(), odd2 = betDto.odd2.toDouble(), book1Prob = probabilities.first, book2Prob = probabilities.second, won = betDto.won)
    }

    private fun getNames(betDto: BetDto): Pair<String, String> {
        val temp = betDto.names.split("-")
        var name1 = temp[0]
        name1 = normalizePolishChars(name1)
        name1 = name1.dropLast(1).toLowerCase()
        var name2 = temp[1]
        name2 = normalizePolishChars(name2)
        name2 = name2.drop(1).toLowerCase()
        return Pair(name1, name2)
    }

    private fun normalizePolishChars(name : String) : String {
        return name
                .toLowerCase()
                .replace("ą", "a")
                .replace("ć", "c")
                .replace("ę", "e")
                .replace("ł", "l")
                .replace("ń", "n")
                .replace("ś", "s")
                .replace("ź", "z")
                .replace("ż", "z")
                .replace("á", "a")
                .replace("gut behrami", "gut-behrami")
                .replace("cochran siegle", "cochran-siegle")
                .replace("innerhofer ch", "innerhofer c")
                .replace("mair ch", "mair c")
                .replace("nestvold haugen", "nestvold-haugen")
                .replace("foss solevaag", "foss-solevaag")
                .replace("st germain", "st-germain")
                .replace("mckennis a.", "mckennis d.a.")
    }

    private fun getProbabilities(betDto: BetDto): Pair<Double, Double> {
        val odd1 = betDto.odd1.toDouble()
        val odd2 = betDto.odd2.toDouble()
        val bookProb1 = 1.0 / odd1
        val bookProb2 = 1.0 / odd2
        val margin = bookProb1 + bookProb2 - 1
        val prob1 = (1 - margin) * bookProb1
        val prob2 = (1 - margin) * bookProb2
        return Pair(prob1, prob2)
    }

    private fun mergeProbabilities(bets: List<Bet>, probabilities: List<Probability>) {
        bets.forEach { bet ->
            val probability = probabilities.firstOrNull {
                (it.jumper1 == bet.name1 && it.jumper2 == bet.name2) || (it.jumper1 == bet.name2 && it.jumper2 == bet.name1)
            }
            if(probability == null){
                val x = 2
            }
            probability?.let {
                if (it.jumper1 == bet.name1 && it.jumper2 == bet.name2) {
                    bet.my1Prob = probability.jumper1WinProbability
                    bet.my2Prob = probability.jumper2WinProbability
                    bet.value1 = bet.odd1 * bet.my1Prob
                    bet.value2 = bet.odd2 * bet.my2Prob
                } else {
                    bet.my1Prob = probability.jumper2WinProbability
                    bet.my2Prob = probability.jumper1WinProbability
                    bet.value1 = bet.odd1 * bet.my1Prob
                    bet.value2 = bet.odd2 * bet.my2Prob
                }

                if (((it.jumper1.contains("stoch") || it.jumper2.contains("stoch")) && (bet.name1.contains("geiger") || bet.name2.contains("geiger")))
                        || ((it.jumper1.contains("geiger") || it.jumper2.contains("geiger")) && (bet.name1.contains("stoch") || bet.name2.contains("stoch")))) {
                    val x = 2
                }
            }
        }
    }

}