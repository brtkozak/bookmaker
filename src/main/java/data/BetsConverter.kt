package data

import com.google.gson.Gson
import data.entity.bets.Bet
import data.entity.bets.BetDto
import data.entity.bets.BetsDto
import data.entity.bets.SingleBet
import data.entity.statistics.Probability

class BetsConverter {

    fun getBets(probabilities: List<Probability>): List<Bet> {
        val gson = Gson()
        val betsDto = gson.fromJson(planica3, BetsDto::class.java)
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
            val bet1 = SingleBet(index++, it.name1, it.name2, it.odd1, it.book1Prob, it.my1Prob, it.value1)
            val bet2 = SingleBet(index++, it.name2, it.name1, it.odd2, it.book2Prob, it.my2Prob, it.value2)
            result.add(bet1)
            result.add(bet2)
        }
        result.sortByDescending { it.value }
        return result
    }

    private fun getBet(betDto: BetDto): Bet {
        val names = getNames(betDto)
        val probabilities = getProbabilities(betDto)
        return Bet(name1 = names.first, name2 = names.second, odd1 = betDto.odd1.toDouble(), odd2 = betDto.odd2.toDouble(), book1Prob = probabilities.first, book2Prob = probabilities.second)
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

    val nizny1 = "{\"bets\":[{\"names\":\"Eisenbichler M. - Granerud H.E.\",\"odd1\":\"1.85\",\"odd2\":\"1.85\"},{\"names\":\"Eisenbichler M. - Huber D.\",\"odd1\":\"1.22\",\"odd2\":\"3.85\"},{\"names\":\"Eisenbichler M. - Sato Y.\",\"odd1\":\"1.17\",\"odd2\":\"4.40\"},{\"names\":\"Eisenbichler M. - Johansson R.\",\"odd1\":\"1.17\",\"odd2\":\"4.40\"},{\"names\":\"Granerud H.E. - Huber D.\",\"odd1\":\"1.22\",\"odd2\":\"3.85\"},{\"names\":\"Granerud H.E. - Sato Y.\",\"odd1\":\"1.17\",\"odd2\":\"4.40\"},{\"names\":\"Granerud H.E. - Johansson R.\",\"odd1\":\"1.11\",\"odd2\":\"5.55\"},{\"names\":\"Granerud H.E. - Forfang J.A.\",\"odd1\":\"1.15\",\"odd2\":\"4.75\"},{\"names\":\"Huber D. - Sato Y.\",\"odd1\":\"1.67\",\"odd2\":\"2.05\"},{\"names\":\"Huber D. - Johansson R.\",\"odd1\":\"1.67\",\"odd2\":\"2.05\"},{\"names\":\"Huber D. - Forfang J.A.\",\"odd1\":\"1.55\",\"odd2\":\"2.30\"},{\"names\":\"Huber D. - Lanisek A.\",\"odd1\":\"1.33\",\"odd2\":\"3.04\"},{\"names\":\"Huber D. - Lindvik M.\",\"odd1\":\"1.45\",\"odd2\":\"2.55\"},{\"names\":\"Sato Y. - Johansson R.\",\"odd1\":\"1.85\",\"odd2\":\"1.85\"},{\"names\":\"Sato Y. - Forfang J.A.\",\"odd1\":\"1.60\",\"odd2\":\"2.20\"},{\"names\":\"Sato Y. - Lanisek A.\",\"odd1\":\"1.50\",\"odd2\":\"2.40\"},{\"names\":\"Sato Y. - Lindvik M.\",\"odd1\":\"1.50\",\"odd2\":\"2.40\"},{\"names\":\"Sato Y. - Tande D.A.\",\"odd1\":\"1.40\",\"odd2\":\"2.75\"},{\"names\":\"Johansson R. - Forfang J.A.\",\"odd1\":\"1.60\",\"odd2\":\"2.20\"},{\"names\":\"Johansson R. - Lanisek A.\",\"odd1\":\"1.50\",\"odd2\":\"2.40\"},{\"names\":\"Johansson R. - Lindvik M.\",\"odd1\":\"1.66\",\"odd2\":\"2.09\"},{\"names\":\"Johansson R. - Tande D.A.\",\"odd1\":\"1.40\",\"odd2\":\"2.75\"},{\"names\":\"Forfang J.A. - Lanisek A.\",\"odd1\":\"1.70\",\"odd2\":\"2.05\"},{\"names\":\"Forfang J.A. - Lindvik M.\",\"odd1\":\"1.70\",\"odd2\":\"2.05\"},{\"names\":\"Forfang J.A. - Tande D.A.\",\"odd1\":\"1.60\",\"odd2\":\"2.20\"},{\"names\":\"Forfang J.A. - Paschke P.\",\"odd1\":\"1.45\",\"odd2\":\"2.55\"},{\"names\":\"Lanisek A. - Lindvik M.\",\"odd1\":\"1.85\",\"odd2\":\"1.85\"},{\"names\":\"Lanisek A. - Tande D.A.\",\"odd1\":\"1.65\",\"odd2\":\"2.10\"},{\"names\":\"Lanisek A. - Paschke P.\",\"odd1\":\"1.50\",\"odd2\":\"2.40\"},{\"names\":\"Lanisek A. - Zajc T.\",\"odd1\":\"1.50\",\"odd2\":\"2.40\"},{\"names\":\"Lindvik M. - Tande D.A.\",\"odd1\":\"1.65\",\"odd2\":\"2.10\"},{\"names\":\"Lindvik M. - Paschke P.\",\"odd1\":\"1.50\",\"odd2\":\"2.40\"},{\"names\":\"Lindvik M. - Zajc T.\",\"odd1\":\"1.50\",\"odd2\":\"2.40\"},{\"names\":\"Lindvik M. - Klimov E.\",\"odd1\":\"1.40\",\"odd2\":\"2.75\"},{\"names\":\"Tande D.A. - Paschke P.\",\"odd1\":\"1.65\",\"odd2\":\"2.10\"},{\"names\":\"Tande D.A. - Zajc T.\",\"odd1\":\"1.65\",\"odd2\":\"2.10\"},{\"names\":\"Tande D.A. - Klimov E.\",\"odd1\":\"1.45\",\"odd2\":\"2.55\"},{\"names\":\"Paschke P. - Zajc T.\",\"odd1\":\"1.85\",\"odd2\":\"1.85\"},{\"names\":\"Paschke P. - Klimov E.\",\"odd1\":\"1.50\",\"odd2\":\"2.40\"},{\"names\":\"Zajc T. - Klimov E.\",\"odd1\":\"1.50\",\"odd2\":\"2.40\"},{\"names\":\"Klimov E. - Zniszczol A.\",\"odd1\":\"1.85\",\"odd2\":\"1.85\"},{\"names\":\"Klimov E. - Schmid C.\",\"odd1\":\"1.85\",\"odd2\":\"1.85\"},{\"names\":\"Zniszczol A. - Fettner M.\",\"odd1\":\"1.65\",\"odd2\":\"2.10\"},{\"names\":\"Schmid C. - Sato K.\",\"odd1\":\"1.55\",\"odd2\":\"2.30\"},{\"names\":\"Deschwanden G. - Jelar Z.\",\"odd1\":\"1.55\",\"odd2\":\"2.30\"},{\"names\":\"Wasek P. - Wolny J.\",\"odd1\":\"1.55\",\"odd2\":\"2.30\"},{\"names\":\"Wolny J. - Kot M.\",\"odd1\":\"1.50\",\"odd2\":\"2.40\"}]}"
    val nizny2 = "{\"bets\":[{\"names\":\"Eisenbichler M. - Granerud H.E.\",\"odd1\":\"1.75\",\"odd2\":\"1.96\"},{\"names\":\"Eisenbichler M. - Johansson R.\",\"odd1\":\"1.35\",\"odd2\":\"2.94\"},{\"names\":\"Eisenbichler M. - Huber D.\",\"odd1\":\"1.35\",\"odd2\":\"2.94\"},{\"names\":\"Eisenbichler M. - Sato Y.\",\"odd1\":\"1.12\",\"odd2\":\"5.31\"},{\"names\":\"Granerud H.E. - Johansson R.\",\"odd1\":\"1.20\",\"odd2\":\"4.04\"},{\"names\":\"Granerud H.E. - Huber D.\",\"odd1\":\"1.20\",\"odd2\":\"3.56\"},{\"names\":\"Granerud H.E. - Sato Y.\",\"odd1\":\"1.15\",\"odd2\":\"4.73\"},{\"names\":\"Johansson R. - Huber D.\",\"odd1\":\"1.85\",\"odd2\":\"1.85\"},{\"names\":\"Johansson R. - Sato Y.\",\"odd1\":\"1.45\",\"odd2\":\"2.55\"},{\"names\":\"Johansson R. - Paschke P.\",\"odd1\":\"1.30\",\"odd2\":\"3.21\"},{\"names\":\"Huber D. - Sato Y.\",\"odd1\":\"1.50\",\"odd2\":\"2.41\"},{\"names\":\"Huber D. - Paschke P.\",\"odd1\":\"1.25\",\"odd2\":\"3.56\"},{\"names\":\"Huber D. - Lanisek A.\",\"odd1\":\"1.25\",\"odd2\":\"3.56\"},{\"names\":\"Sato Y. - Paschke P.\",\"odd1\":\"1.55\",\"odd2\":\"2.29\"},{\"names\":\"Sato Y. - Lanisek A.\",\"odd1\":\"1.55\",\"odd2\":\"2.29\"},{\"names\":\"Paschke P. - Lanisek A.\",\"odd1\":\"1.80\",\"odd2\":\"1.90\"},{\"names\":\"Paschke P. - Tande D.A.\",\"odd1\":\"1.80\",\"odd2\":\"1.90\"},{\"names\":\"Lanisek A. - Tande D.A.\",\"odd1\":\"1.80\",\"odd2\":\"1.90\"},{\"names\":\"Lanisek A. - Forfang J.A.\",\"odd1\":\"1.50\",\"odd2\":\"2.41\"},{\"names\":\"Tande D.A. - Forfang J.A.\",\"odd1\":\"1.65\",\"odd2\":\"2.11\"},{\"names\":\"Tande D.A. - Lindvik M.\",\"odd1\":\"1.55\",\"odd2\":\"2.29\"},{\"names\":\"Lindvik M. - Zajc T.\",\"odd1\":\"1.60\",\"odd2\":\"2.19\"},{\"names\":\"Lindvik M. - Kobayashi R.\",\"odd1\":\"1.55\",\"odd2\":\"2.29\"},{\"names\":\"Lackner T. - Zniszczol A.\",\"odd1\":\"1.75\",\"odd2\":\"1.96\"},{\"names\":\"Lackner T. - Klimov E.\",\"odd1\":\"1.75\",\"odd2\":\"1.96\"},{\"names\":\"Zniszczol A. - Klimov E.\",\"odd1\":\"1.80\",\"odd2\":\"1.90\"},{\"names\":\"Zniszczol A. - Schmid C.\",\"odd1\":\"1.50\",\"odd2\":\"2.41\"},{\"names\":\"Klimov E. - Zajc T.\",\"odd1\":\"1.80\",\"odd2\":\"1.90\"},{\"names\":\"Klimov E. - Kobayashi R.\",\"odd1\":\"1.80\",\"odd2\":\"1.90\"},{\"names\":\"Zajc T. - Kobayashi R.\",\"odd1\":\"1.85\",\"odd2\":\"1.85\"},{\"names\":\"Kobayashi R. - Sato K.\",\"odd1\":\"1.60\",\"odd2\":\"2.19\"},{\"names\":\"Pavlovcic B. - Hamann M.\",\"odd1\":\"1.75\",\"odd2\":\"1.96\"},{\"names\":\"Schmid C. - Wasek P.\",\"odd1\":\"1.50\",\"odd2\":\"2.41\"},{\"names\":\"Hoerl J. - Bartol T.\",\"odd1\":\"1.45\",\"odd2\":\"2.55\"},{\"names\":\"Schiffner M. - Wolny J.\",\"odd1\":\"1.60\",\"odd2\":\"2.19\"},{\"names\":\"Wolny J. - Wasek P.\",\"odd1\":\"1.65\",\"odd2\":\"2.11\"},{\"names\":\"Wolny J. - Kot M.\",\"odd1\":\"1.35\",\"odd2\":\"2.94\"},{\"names\":\"Wasek P. - Kot M.\",\"odd1\":\"1.50\",\"odd2\":\"2.41\"},{\"names\":\"Kot M. - Pilch T.\",\"odd1\":\"1.45\",\"odd2\":\"2.55\"}]}"

    val planica2 ="{\"bets\":[{\"names\":\"Eisenbichler M. - Granerud H.E.\",\"odd1\":\"1.85\",\"odd2\":\"1.85\"},{\"names\":\"Eisenbichler M. - Geiger K.\",\"odd1\":\"1.40\",\"odd2\":\"2.73\"},{\"names\":\"Eisenbichler M. - Hayboeck M.\",\"odd1\":\"1.42\",\"odd2\":\"2.65\"},{\"names\":\"Eisenbichler M. - Johansson R.\",\"odd1\":\"1.25\",\"odd2\":\"3.56\"},{\"names\":\"Eisenbichler M. - Stoch K.\",\"odd1\":\"1.17\",\"odd2\":\"4.42\"},{\"names\":\"Eisenbichler M. - Żyła P.\",\"odd1\":\"1.15\",\"odd2\":\"4.73\"},{\"names\":\"Granerud H.E. - Geiger K.\",\"odd1\":\"1.48\",\"odd2\":\"2.47\"},{\"names\":\"Granerud H.E. - Hayboeck M.\",\"odd1\":\"1.36\",\"odd2\":\"2.89\"},{\"names\":\"Granerud H.E. - Johansson R.\",\"odd1\":\"1.26\",\"odd2\":\"3.48\"},{\"names\":\"Granerud H.E. - Stoch K.\",\"odd1\":\"1.17\",\"odd2\":\"4.42\"},{\"names\":\"Granerud H.E. - Żyła P.\",\"odd1\":\"1.15\",\"odd2\":\"4.73\"},{\"names\":\"Geiger K. - Hayboeck M.\",\"odd1\":\"1.70\",\"odd2\":\"2.03\"},{\"names\":\"Geiger K. - Johansson R.\",\"odd1\":\"1.50\",\"odd2\":\"2.41\"},{\"names\":\"Geiger K. - Stoch K.\",\"odd1\":\"1.37\",\"odd2\":\"2.85\"},{\"names\":\"Geiger K. - Żyła P.\",\"odd1\":\"1.35\",\"odd2\":\"2.94\"},{\"names\":\"Hayboeck M. - Johansson R.\",\"odd1\":\"1.60\",\"odd2\":\"2.19\"},{\"names\":\"Hayboeck M. - Stoch K.\",\"odd1\":\"1.45\",\"odd2\":\"2.55\"},{\"names\":\"Hayboeck M. - Żyła P.\",\"odd1\":\"1.42\",\"odd2\":\"2.65\"},{\"names\":\"Johansson R. - Stoch K.\",\"odd1\":\"1.65\",\"odd2\":\"2.11\"},{\"names\":\"Johansson R. - Żyła P.\",\"odd1\":\"1.60\",\"odd2\":\"2.19\"},{\"names\":\"Johansson R. - Sato Y.\",\"odd1\":\"1.45\",\"odd2\":\"2.55\"},{\"names\":\"Stoch K. - Żyła P.\",\"odd1\":\"1.80\",\"odd2\":\"1.90\"},{\"names\":\"Stoch K. - Sato Y.\",\"odd1\":\"1.60\",\"odd2\":\"2.19\"},{\"names\":\"Stoch K. - Kubacki D.\",\"odd1\":\"1.30\",\"odd2\":\"3.21\"},{\"names\":\"Stoch K. - Stekala A.\",\"odd1\":\"1.25\",\"odd2\":\"3.56\"},{\"names\":\"Żyła P. - Sato Y.\",\"odd1\":\"1.65\",\"odd2\":\"2.11\"},{\"names\":\"Żyła P. - Kubacki D.\",\"odd1\":\"1.31\",\"odd2\":\"3.15\"},{\"names\":\"Żyła P. - Stekala A.\",\"odd1\":\"1.26\",\"odd2\":\"3.48\"},{\"names\":\"Sato Y. - Tande D.A.\",\"odd1\":\"1.65\",\"odd2\":\"2.11\"},{\"names\":\"Sato Y. - Kubacki D.\",\"odd1\":\"1.60\",\"odd2\":\"2.19\"},{\"names\":\"Tande D.A. - Kubacki D.\",\"odd1\":\"1.85\",\"odd2\":\"1.85\"},{\"names\":\"Tande D.A. - Lanisek A.\",\"odd1\":\"1.45\",\"odd2\":\"2.55\"},{\"names\":\"Kubacki D. - Paschke P.\",\"odd1\":\"1.75\",\"odd2\":\"1.96\"},{\"names\":\"Kubacki D. - Stekala A.\",\"odd1\":\"1.75\",\"odd2\":\"1.96\"},{\"names\":\"Paschke P. - Stekala A.\",\"odd1\":\"1.85\",\"odd2\":\"1.85\"},{\"names\":\"Paschke P. - Klimov E.\",\"odd1\":\"1.70\",\"odd2\":\"2.03\"},{\"names\":\"Stekala A. - Klimov E.\",\"odd1\":\"1.85\",\"odd2\":\"1.85\"},{\"names\":\"Lanisek A. - Pavlovcic B.\",\"odd1\":\"1.85\",\"odd2\":\"1.85\"},{\"names\":\"Pavlovcic B. - Aschenwald P.\",\"odd1\":\"1.55\",\"odd2\":\"2.29\"},{\"names\":\"Aalto A. - Kobayashi R.\",\"odd1\":\"1.47\",\"odd2\":\"2.49\"},{\"names\":\"Kobayashi R. - Sato K.\",\"odd1\":\"1.65\",\"odd2\":\"2.11\"}]}"
    val planica3= "{\"bets\":[{\"names\":\"Żyła P. - Tande D.A.\",\"odd1\":\"1.35\",\"odd2\":\"2.94\"},{\"names\":\"Żyła P. - Schmid C.\",\"odd1\":\"1.10\",\"odd2\":\"5.81\"},{\"names\":\"Tande D.A. - Schmid C.\",\"odd1\":\"1.40\",\"odd2\":\"2.73\"},{\"names\":\"Aschenwald P. - Sato K.\",\"odd1\":\"1.80\",\"odd2\":\"1.90\"},{\"names\":\"Aschenwald P. - Prevc D.\",\"odd1\":\"1.90\",\"odd2\":\"1.80\"},{\"names\":\"Sato K. - Prevc D.\",\"odd1\":\"1.96\",\"odd2\":\"1.75\"},{\"names\":\"Stekala A. - Paschke P.\",\"odd1\":\"1.85\",\"odd2\":\"1.85\"},{\"names\":\"Forfang J.A. - Prevc P.\",\"odd1\":\"1.55\",\"odd2\":\"2.29\"},{\"names\":\"Forfang J.A. - Schlierenzauer G.\",\"odd1\":\"1.35\",\"odd2\":\"2.94\"},{\"names\":\"Forfang J.A. - Nakamura N.\",\"odd1\":\"1.30\",\"odd2\":\"3.21\"},{\"names\":\"Prevc P. - Schlierenzauer G.\",\"odd1\":\"1.55\",\"odd2\":\"2.29\"},{\"names\":\"Prevc P. - Nakamura N.\",\"odd1\":\"1.55\",\"odd2\":\"2.29\"},{\"names\":\"Schlierenzauer G. - Nakamura N.\",\"odd1\":\"1.75\",\"odd2\":\"1.96\"},{\"names\":\"Eisenbichler M. - Johansson R.\",\"odd1\":\"1.45\",\"odd2\":\"2.55\"},{\"names\":\"Eisenbichler M. - Stoch K.\",\"odd1\":\"1.20\",\"odd2\":\"4.04\"},{\"names\":\"Johansson R. - Stoch K.\",\"odd1\":\"1.35\",\"odd2\":\"2.94\"},{\"names\":\"Pavlovcic B. - Kobayashi R.\",\"odd1\":\"1.65\",\"odd2\":\"2.11\"},{\"names\":\"Granerud H.E. - Geiger K.\",\"odd1\":\"1.55\",\"odd2\":\"2.29\"},{\"names\":\"Granerud H.E. - Hayboeck M.\",\"odd1\":\"1.20\",\"odd2\":\"4.04\"},{\"names\":\"Geiger K. - Hayboeck M.\",\"odd1\":\"1.25\",\"odd2\":\"3.56\"},{\"names\":\"Hayboeck M. - Sato Y.\",\"odd1\":\"1.65\",\"odd2\":\"2.11\"},{\"names\":\"Hayboeck M. - Lanisek A.\",\"odd1\":\"1.30\",\"odd2\":\"3.21\"},{\"names\":\"Sato Y. - Lanisek A.\",\"odd1\":\"1.43\",\"odd2\":\"2.62\"},{\"names\":\"Lanisek A. - Klimov E.\",\"odd1\":\"1.60\",\"odd2\":\"2.19\"},{\"names\":\"Kubacki D. - Aalto A.\",\"odd1\":\"1.42\",\"odd2\":\"2.65\"},{\"names\":\"Granerud H.E. - Eisenbichler M.\",\"odd1\":\"1.45\",\"odd2\":\"2.55\"},{\"names\":\"Geiger K. - Eisenbichler M.\",\"odd1\":\"1.65\",\"odd2\":\"2.11\"},{\"names\":\"Eisenbichler M. - Hayboeck M.\",\"odd1\":\"1.40\",\"odd2\":\"2.73\"},{\"names\":\"Hayboeck M. - Johansson R.\",\"odd1\":\"1.75\",\"odd2\":\"1.96\"},{\"names\":\"Stoch K. - Żyła P.\",\"odd1\":\"2.19\",\"odd2\":\"1.60\"},{\"names\":\"Stoch K. - Stekala A.\",\"odd1\":\"1.30\",\"odd2\":\"3.21\"},{\"names\":\"Żyła P. - Stekala A.\",\"odd1\":\"1.25\",\"odd2\":\"3.56\"}]}"

}