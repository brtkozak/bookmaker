package utils

import com.google.gson.Gson
import data.BetsConverter
import data.StatisticsConverter
import data.entity.bets.BetsDto
import data.entity.statistics.SkiJumpingData

class WinConverter {

    val statistics = ""
    val bets = "{\"bets\":[{\"names\":\"Deschwanden G. - Boyd Clowes M.\",\"odd1\":\"1.50\",\"odd2\":\"2.41\"},{\"names\":\"Hamann M. - Schmid C.\",\"odd1\":\"1.70\",\"odd2\":\"2.03\"},{\"names\":\"Boyd Clowes M. - Aalto A.\",\"odd1\":\"1.60\",\"odd2\":\"2.19\"},{\"names\":\"Freund S. - Forfang J.A.\",\"odd1\":\"1.55\",\"odd2\":\"2.29\"},{\"names\":\"Aalto A. - Zajc T.\",\"odd1\":\"1.45\",\"odd2\":\"2.55\"},{\"names\":\"Bartol T. - Kytosaho N.\",\"odd1\":\"1.60\",\"odd2\":\"2.19\"},{\"names\":\"Boyd Clowes M. - Wolny J.\",\"odd1\":\"1.75\",\"odd2\":\"1.96\"},{\"names\":\"Schmid C. - Freund S.\",\"odd1\":\"1.55\",\"odd2\":\"2.29\"},{\"names\":\"Granerud H.E. - Tande D.A.\",\"odd1\":\"1.24\",\"odd2\":\"3.64\"},{\"names\":\"Granerud H.E. - Stoch K.\",\"odd1\":\"1.23\",\"odd2\":\"3.73\"},{\"names\":\"Granerud H.E. - Eisenbichler M.\",\"odd1\":\"1.15\",\"odd2\":\"4.73\"},{\"names\":\"Granerud H.E. - Kubacki D.\",\"odd1\":\"1.15\",\"odd2\":\"4.73\"},{\"names\":\"Tande D.A. - Stoch K.\",\"odd1\":\"1.80\",\"odd2\":\"1.90\"},{\"names\":\"Tande D.A. - Eisenbichler M.\",\"odd1\":\"1.60\",\"odd2\":\"2.19\"},{\"names\":\"Tande D.A. - Kubacki D.\",\"odd1\":\"1.47\",\"odd2\":\"2.49\"},{\"names\":\"Tande D.A. - Johansson R.\",\"odd1\":\"1.70\",\"odd2\":\"2.03\"},{\"names\":\"Stoch K. - Eisenbichler M.\",\"odd1\":\"1.65\",\"odd2\":\"2.11\"},{\"names\":\"Stoch K. - Kubacki D.\",\"odd1\":\"1.65\",\"odd2\":\"2.11\"},{\"names\":\"Stoch K. - Lindvik M.\",\"odd1\":\"1.60\",\"odd2\":\"2.19\"},{\"names\":\"Stoch K. - Żyła P.\",\"odd1\":\"1.42\",\"odd2\":\"2.65\"},{\"names\":\"Eisenbichler M. - Kubacki D.\",\"odd1\":\"1.85\",\"odd2\":\"1.85\"},{\"names\":\"Eisenbichler M. - Lindvik M.\",\"odd1\":\"1.85\",\"odd2\":\"1.85\"},{\"names\":\"Eisenbichler M. - Pavlovcic B.\",\"odd1\":\"1.65\",\"odd2\":\"2.11\"},{\"names\":\"Kubacki D. - Lindvik M.\",\"odd1\":\"1.80\",\"odd2\":\"1.90\"},{\"names\":\"Kubacki D. - Johansson R.\",\"odd1\":\"1.85\",\"odd2\":\"1.85\"},{\"names\":\"Kubacki D. - Pavlovcic B.\",\"odd1\":\"1.70\",\"odd2\":\"2.03\"},{\"names\":\"Kubacki D. - Żyła P.\",\"odd1\":\"1.58\",\"odd2\":\"2.23\"},{\"names\":\"Lindvik M. - Johansson R.\",\"odd1\":\"1.85\",\"odd2\":\"1.85\"},{\"names\":\"Lindvik M. - Pavlovcic B.\",\"odd1\":\"1.44\",\"odd2\":\"2.59\"},{\"names\":\"Lindvik M. - Żyła P.\",\"odd1\":\"1.65\",\"odd2\":\"2.11\"},{\"names\":\"Johansson R. - Pavlovcic B.\",\"odd1\":\"1.70\",\"odd2\":\"2.03\"},{\"names\":\"Johansson R. - Żyła P.\",\"odd1\":\"1.58\",\"odd2\":\"2.23\"},{\"names\":\"Johansson R. - Kraft S.\",\"odd1\":\"1.52\",\"odd2\":\"2.36\"},{\"names\":\"Pavlovcic B. - Żyła P.\",\"odd1\":\"1.75\",\"odd2\":\"1.96\"},{\"names\":\"Pavlovcic B. - Huber D.\",\"odd1\":\"1.70\",\"odd2\":\"2.03\"},{\"names\":\"Pavlovcic B. - Kraft S.\",\"odd1\":\"1.42\",\"odd2\":\"2.65\"},{\"names\":\"Żyła P. - Kobayashi R.\",\"odd1\":\"1.85\",\"odd2\":\"1.85\"},{\"names\":\"Żyła P. - Huber D.\",\"odd1\":\"1.70\",\"odd2\":\"2.03\"},{\"names\":\"Żyła P. - Stękała A.\",\"odd1\":\"1.55\",\"odd2\":\"2.29\"},{\"names\":\"Kobayashi R. - Geiger K.\",\"odd1\":\"1.85\",\"odd2\":\"1.85\"},{\"names\":\"Kobayashi R. - Kraft S.\",\"odd1\":\"1.60\",\"odd2\":\"2.19\"},{\"names\":\"Geiger K. - Huber D.\",\"odd1\":\"1.85\",\"odd2\":\"1.85\"},{\"names\":\"Geiger K. - Kraft S.\",\"odd1\":\"1.70\",\"odd2\":\"2.03\"},{\"names\":\"Huber D. - Kraft S.\",\"odd1\":\"1.70\",\"odd2\":\"2.03\"},{\"names\":\"Huber D. - Stękała A.\",\"odd1\":\"1.65\",\"odd2\":\"2.11\"},{\"names\":\"Kraft S. - Lanisek A.\",\"odd1\":\"1.85\",\"odd2\":\"1.85\"},{\"names\":\"Kraft S. - Hayboeck M.\",\"odd1\":\"1.55\",\"odd2\":\"2.29\"},{\"names\":\"Lanisek A. - Stękała A.\",\"odd1\":\"1.85\",\"odd2\":\"1.85\"},{\"names\":\"Lanisek A. - Sato Y.\",\"odd1\":\"1.55\",\"odd2\":\"2.29\"},{\"names\":\"Stękała A. - Hayboeck M.\",\"odd1\":\"1.50\",\"odd2\":\"2.41\"},{\"names\":\"Stękała A. - Sato Y.\",\"odd1\":\"1.55\",\"odd2\":\"2.29\"},{\"names\":\"Hayboeck M. - Sato Y.\",\"odd1\":\"1.85\",\"odd2\":\"1.85\"},{\"names\":\"Hayboeck M. - Aschenwald P.\",\"odd1\":\"1.75\",\"odd2\":\"1.96\"},{\"names\":\"Hayboeck M. - Sato K.\",\"odd1\":\"1.65\",\"odd2\":\"2.11\"},{\"names\":\"Aschenwald P. - Ammann S.\",\"odd1\":\"1.76\",\"odd2\":\"1.95\"},{\"names\":\"Ammann S. - Sato K.\",\"odd1\":\"1.85\",\"odd2\":\"1.85\"},{\"names\":\"Sato K. - Paschke P.\",\"odd1\":\"1.70\",\"odd2\":\"2.03\"},{\"names\":\"Paschke P. - Wolny J.\",\"odd1\":\"1.75\",\"odd2\":\"1.96\"},{\"names\":\"Wolny J. - Murańka K.\",\"odd1\":\"1.70\",\"odd2\":\"2.03\"},{\"names\":\"Wolny J. - Zniszczoł A.\",\"odd1\":\"1.40\",\"odd2\":\"2.73\"},{\"names\":\"Murańka K. - Zniszczoł A.\",\"odd1\":\"1.60\",\"odd2\":\"2.19\"},{\"names\":\"Sato Y. - Aschenwald P.\",\"odd1\":\"1.75\",\"odd2\":\"1.96\"},{\"names\":\"Sato Y. - Sato K.\",\"odd1\":\"1.65\",\"odd2\":\"2.11\"}]}"

    fun convertData() {
        val statisticsConverter = StatisticsConverter()
        val betsConverter = BetsConverter()
        val gson = Gson()

        val skiJumpingData = gson.fromJson(statistics, SkiJumpingData::class.java)
        val skiJumpingResults = statisticsConverter.getJumpersResults(skiJumpingData)


        val betsDto = gson.fromJson(bets, BetsDto::class.java)
        val bets = betsDto.bets.map { bet -> betsConverter.getBet(bet) }
        var singleBets = betsConverter.getSingeBets(bets)

        val x = 2
    }

}