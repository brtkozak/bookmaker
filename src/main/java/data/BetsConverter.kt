package data

import com.google.gson.Gson
import data.entity.bets.Bet
import data.entity.bets.BetDto
import data.entity.bets.BetsDto
import data.entity.bets.SingleBet
import data.entity.statistics.Probability

class BetsConverter {


    companion object {
        val nizny1 = "{\"bets\":[{\"names\":\"Eisenbichler M. - Granerud H.E.\",\"odd1\":\"1.85\",\"odd2\":\"1.85\",\"won\":2},{\"names\":\"Eisenbichler M. - Huber D.\",\"odd1\":\"1.22\",\"odd2\":\"3.85\",\"won\":2},{\"names\":\"Eisenbichler M. - Sato Y.\",\"odd1\":\"1.17\",\"odd2\":\"4.40\",\"won\":2},{\"names\":\"Eisenbichler M. - Johansson R.\",\"odd1\":\"1.17\",\"odd2\":\"4.40\",\"won\":2},{\"names\":\"Granerud H.E. - Huber D.\",\"odd1\":\"1.22\",\"odd2\":\"3.85\",\"won\":1},{\"names\":\"Granerud H.E. - Sato Y.\",\"odd1\":\"1.17\",\"odd2\":\"4.40\",\"won\":1},{\"names\":\"Granerud H.E. - Johansson R.\",\"odd1\":\"1.11\",\"odd2\":\"5.55\",\"won\":1},{\"names\":\"Granerud H.E. - Forfang J.A.\",\"odd1\":\"1.15\",\"odd2\":\"4.75\",\"won\":1},{\"names\":\"Huber D. - Sato Y.\",\"odd1\":\"1.67\",\"odd2\":\"2.05\",\"won\":1},{\"names\":\"Huber D. - Johansson R.\",\"odd1\":\"1.67\",\"odd2\":\"2.05\",\"won\":1},{\"names\":\"Huber D. - Forfang J.A.\",\"odd1\":\"1.55\",\"odd2\":\"2.30\",\"won\":1},{\"names\":\"Huber D. - Lanisek A.\",\"odd1\":\"1.33\",\"odd2\":\"3.04\",\"won\":1},{\"names\":\"Huber D. - Lindvik M.\",\"odd1\":\"1.45\",\"odd2\":\"2.55\",\"won\":1},{\"names\":\"Sato Y. - Johansson R.\",\"odd1\":\"1.85\",\"odd2\":\"1.85\",\"won\":2},{\"names\":\"Sato Y. - Forfang J.A.\",\"odd1\":\"1.60\",\"odd2\":\"2.20\",\"won\":1},{\"names\":\"Sato Y. - Lanisek A.\",\"odd1\":\"1.50\",\"odd2\":\"2.40\",\"won\":1},{\"names\":\"Sato Y. - Lindvik M.\",\"odd1\":\"1.50\",\"odd2\":\"2.40\",\"won\":1},{\"names\":\"Sato Y. - Tande D.A.\",\"odd1\":\"1.40\",\"odd2\":\"2.75\",\"won\":1},{\"names\":\"Johansson R. - Forfang J.A.\",\"odd1\":\"1.60\",\"odd2\":\"2.20\",\"won\":1},{\"names\":\"Johansson R. - Lanisek A.\",\"odd1\":\"1.50\",\"odd2\":\"2.40\",\"won\":1},{\"names\":\"Johansson R. - Lindvik M.\",\"odd1\":\"1.66\",\"odd2\":\"2.09\",\"won\":1},{\"names\":\"Johansson R. - Tande D.A.\",\"odd1\":\"1.40\",\"odd2\":\"2.75\",\"won\":1},{\"names\":\"Forfang J.A. - Lanisek A.\",\"odd1\":\"1.70\",\"odd2\":\"2.05\",\"won\":2},{\"names\":\"Forfang J.A. - Lindvik M.\",\"odd1\":\"1.70\",\"odd2\":\"2.05\",\"won\":1},{\"names\":\"Forfang J.A. - Tande D.A.\",\"odd1\":\"1.60\",\"odd2\":\"2.20\",\"won\":2},{\"names\":\"Forfang J.A. - Paschke P.\",\"odd1\":\"1.45\",\"odd2\":\"2.55\",\"won\":2},{\"names\":\"Lanisek A. - Lindvik M.\",\"odd1\":\"1.85\",\"odd2\":\"1.85\",\"won\":1},{\"names\":\"Lanisek A. - Tande D.A.\",\"odd1\":\"1.65\",\"odd2\":\"2.10\",\"won\":1},{\"names\":\"Lanisek A. - Paschke P.\",\"odd1\":\"1.50\",\"odd2\":\"2.40\",\"won\":2},{\"names\":\"Lanisek A. - Zajc T.\",\"odd1\":\"1.50\",\"odd2\":\"2.40\",\"won\":1},{\"names\":\"Lindvik M. - Tande D.A.\",\"odd1\":\"1.65\",\"odd2\":\"2.10\",\"won\":2},{\"names\":\"Lindvik M. - Paschke P.\",\"odd1\":\"1.50\",\"odd2\":\"2.40\",\"won\":2},{\"names\":\"Lindvik M. - Zajc T.\",\"odd1\":\"1.50\",\"odd2\":\"2.40\",\"won\":2},{\"names\":\"Lindvik M. - Klimov E.\",\"odd1\":\"1.40\",\"odd2\":\"2.75\",\"won\":2},{\"names\":\"Tande D.A. - Paschke P.\",\"odd1\":\"1.65\",\"odd2\":\"2.10\",\"won\":2},{\"names\":\"Tande D.A. - Zajc T.\",\"odd1\":\"1.65\",\"odd2\":\"2.10\",\"won\":1},{\"names\":\"Tande D.A. - Klimov E.\",\"odd1\":\"1.45\",\"odd2\":\"2.55\",\"won\":2},{\"names\":\"Paschke P. - Zajc T.\",\"odd1\":\"1.85\",\"odd2\":\"1.85\",\"won\":1},{\"names\":\"Paschke P. - Klimov E.\",\"odd1\":\"1.50\",\"odd2\":\"2.40\",\"won\":1},{\"names\":\"Zajc T. - Klimov E.\",\"odd1\":\"1.50\",\"odd2\":\"2.40\",\"won\":2},{\"names\":\"Klimov E. - Zniszczol A.\",\"odd1\":\"1.85\",\"odd2\":\"1.85\",\"won\":2},{\"names\":\"Klimov E. - Schmid C.\",\"odd1\":\"1.85\",\"odd2\":\"1.85\",\"won\":1},{\"names\":\"Zniszczol A. - Fettner M.\",\"odd1\":\"1.65\",\"odd2\":\"2.10\",\"won\":1},{\"names\":\"Schmid C. - Sato K.\",\"odd1\":\"1.55\",\"odd2\":\"2.30\",\"won\":2},{\"names\":\"Deschwanden G. - Jelar Z.\",\"odd1\":\"1.55\",\"odd2\":\"2.30\",\"won\":1},{\"names\":\"Wasek P. - Wolny J.\",\"odd1\":\"1.55\",\"odd2\":\"2.30\",\"won\":2},{\"names\":\"Wolny J. - Kot M.\",\"odd1\":\"1.50\",\"odd2\":\"2.40\",\"won\":1}]}"
        val nizny2 = "{\"bets\":[{\"names\":\"Eisenbichler M. - Granerud H.E.\",\"odd1\":\"1.75\",\"odd2\":\"1.96\",\"won\":2},{\"names\":\"Eisenbichler M. - Johansson R.\",\"odd1\":\"1.35\",\"odd2\":\"2.94\",\"won\":2},{\"names\":\"Eisenbichler M. - Huber D.\",\"odd1\":\"1.35\",\"odd2\":\"2.94\",\"won\":0},{\"names\":\"Eisenbichler M. - Sato Y.\",\"odd1\":\"1.12\",\"odd2\":\"5.31\",\"won\":1},{\"names\":\"Granerud H.E. - Johansson R.\",\"odd1\":\"1.20\",\"odd2\":\"4.04\",\"won\":1},{\"names\":\"Granerud H.E. - Huber D.\",\"odd1\":\"1.20\",\"odd2\":\"3.56\",\"won\":1},{\"names\":\"Granerud H.E. - Sato Y.\",\"odd1\":\"1.15\",\"odd2\":\"4.73\",\"won\":1},{\"names\":\"Johansson R. - Huber D.\",\"odd1\":\"1.85\",\"odd2\":\"1.85\",\"won\":0},{\"names\":\"Johansson R. - Sato Y.\",\"odd1\":\"1.45\",\"odd2\":\"2.55\",\"won\":1},{\"names\":\"Johansson R. - Paschke P.\",\"odd1\":\"1.30\",\"odd2\":\"3.21\",\"won\":1},{\"names\":\"Huber D. - Sato Y.\",\"odd1\":\"1.50\",\"odd2\":\"2.41\",\"won\":0},{\"names\":\"Huber D. - Paschke P.\",\"odd1\":\"1.25\",\"odd2\":\"3.56\",\"won\":0},{\"names\":\"Huber D. - Lanisek A.\",\"odd1\":\"1.25\",\"odd2\":\"3.56\",\"won\":0},{\"names\":\"Sato Y. - Paschke P.\",\"odd1\":\"1.55\",\"odd2\":\"2.29\",\"won\":2},{\"names\":\"Sato Y. - Lanisek A.\",\"odd1\":\"1.55\",\"odd2\":\"2.29\",\"won\":2},{\"names\":\"Paschke P. - Lanisek A.\",\"odd1\":\"1.80\",\"odd2\":\"1.90\",\"won\":1},{\"names\":\"Paschke P. - Tande D.A.\",\"odd1\":\"1.80\",\"odd2\":\"1.90\",\"won\":1},{\"names\":\"Lanisek A. - Tande D.A.\",\"odd1\":\"1.80\",\"odd2\":\"1.90\",\"won\":1},{\"names\":\"Lanisek A. - Forfang J.A.\",\"odd1\":\"1.50\",\"odd2\":\"2.41\",\"won\":2},{\"names\":\"Tande D.A. - Forfang J.A.\",\"odd1\":\"1.65\",\"odd2\":\"2.11\",\"won\":2},{\"names\":\"Tande D.A. - Lindvik M.\",\"odd1\":\"1.55\",\"odd2\":\"2.29\",\"won\":2},{\"names\":\"Lindvik M. - Zajc T.\",\"odd1\":\"1.60\",\"odd2\":\"2.19\",\"won\":1},{\"names\":\"Lindvik M. - Kobayashi R.\",\"odd1\":\"1.55\",\"odd2\":\"2.29\",\"won\":1},{\"names\":\"Lackner T. - Zniszczol A.\",\"odd1\":\"1.75\",\"odd2\":\"1.96\",\"won\":0},{\"names\":\"Lackner T. - Klimov E.\",\"odd1\":\"1.75\",\"odd2\":\"1.96\",\"won\":0},{\"names\":\"Zniszczol A. - Klimov E.\",\"odd1\":\"1.80\",\"odd2\":\"1.90\",\"won\":1},{\"names\":\"Zniszczol A. - Schmid C.\",\"odd1\":\"1.50\",\"odd2\":\"2.41\",\"won\":2},{\"names\":\"Klimov E. - Zajc T.\",\"odd1\":\"1.80\",\"odd2\":\"1.90\",\"won\":2},{\"names\":\"Klimov E. - Kobayashi R.\",\"odd1\":\"1.80\",\"odd2\":\"1.90\",\"won\":2},{\"names\":\"Zajc T. - Kobayashi R.\",\"odd1\":\"1.85\",\"odd2\":\"1.85\",\"won\":1},{\"names\":\"Kobayashi R. - Sato K.\",\"odd1\":\"1.60\",\"odd2\":\"2.19\",\"won\":2},{\"names\":\"Pavlovcic B. - Hamann M.\",\"odd1\":\"1.75\",\"odd2\":\"1.96\",\"won\":1},{\"names\":\"Schmid C. - Wasek P.\",\"odd1\":\"1.50\",\"odd2\":\"2.41\",\"won\":2},{\"names\":\"Hoerl J. - Bartol T.\",\"odd1\":\"1.45\",\"odd2\":\"2.55\",\"won\":0},{\"names\":\"Schiffner M. - Wolny J.\",\"odd1\":\"1.60\",\"odd2\":\"2.19\",\"won\":0},{\"names\":\"Wolny J. - Wasek P.\",\"odd1\":\"1.65\",\"odd2\":\"2.11\",\"won\":2},{\"names\":\"Wolny J. - Kot M.\",\"odd1\":\"1.35\",\"odd2\":\"2.94\",\"won\":2},{\"names\":\"Wasek P. - Kot M.\",\"odd1\":\"1.50\",\"odd2\":\"2.41\",\"won\":1},{\"names\":\"Kot M. - Pilch T.\",\"odd1\":\"1.45\",\"odd2\":\"2.55\",\"won\":1}]}"
        val planica1 = "{\"bets\":[{\"names\":\"Eisenbichler M. - Granerud H.E.\",\"odd1\":\"1.50\",\"odd2\":\"2.41\",\"won\":2},{\"names\":\"Eisenbichler M. - Hayboeck M.\",\"odd1\":\"1.40\",\"odd2\":\"2.73\",\"won\":1},{\"names\":\"Eisenbichler M. - Tande D.A.\",\"odd1\":\"1.30\",\"odd2\":\"3.21\",\"won\":1},{\"names\":\"Eisenbichler M. - Johansson R.\",\"odd1\":\"1.22\",\"odd2\":\"3.83\",\"won\":1},{\"names\":\"Eisenbichler M. - Stoch K.\",\"odd1\":\"1.22\",\"odd2\":\"3.83\",\"won\":1},{\"names\":\"Eisenbichler M. - Geiger K.\",\"odd1\":\"1.17\",\"odd2\":\"4.42\",\"won\":2},{\"names\":\"Eisenbichler M. - Żyła P.\",\"odd1\":\"1.17\",\"odd2\":\"4.42\",\"won\":1},{\"names\":\"Granerud H.E. - Hayboeck M.\",\"odd1\":\"1.80\",\"odd2\":\"1.90\",\"won\":1},{\"names\":\"Granerud H.E. - Tande D.A.\",\"odd1\":\"1.45\",\"odd2\":\"2.55\",\"won\":1},{\"names\":\"Granerud H.E. - Johansson R.\",\"odd1\":\"1.40\",\"odd2\":\"2.73\",\"won\":1},{\"names\":\"Granerud H.E. - Stoch K.\",\"odd1\":\"1.40\",\"odd2\":\"2.73\",\"won\":1},{\"names\":\"Granerud H.E. - Geiger K.\",\"odd1\":\"1.30\",\"odd2\":\"3.21\",\"won\":2},{\"names\":\"Granerud H.E. - Żyła P.\",\"odd1\":\"1.30\",\"odd2\":\"3.21\",\"won\":1},{\"names\":\"Hayboeck M. - Tande D.A.\",\"odd1\":\"1.60\",\"odd2\":\"2.19\",\"won\":1},{\"names\":\"Hayboeck M. - Johansson R.\",\"odd1\":\"1.50\",\"odd2\":\"2.41\",\"won\":1},{\"names\":\"Hayboeck M. - Stoch K.\",\"odd1\":\"1.50\",\"odd2\":\"2.41\",\"won\":1},{\"names\":\"Hayboeck M. - Geiger K.\",\"odd1\":\"1.40\",\"odd2\":\"2.73\",\"won\":2},{\"names\":\"Hayboeck M. - Żyła P.\",\"odd1\":\"1.40\",\"odd2\":\"2.73\",\"won\":1},{\"names\":\"Tande D.A. - Johansson R.\",\"odd1\":\"1.70\",\"odd2\":\"2.03\",\"won\":2},{\"names\":\"Tande D.A. - Stoch K.\",\"odd1\":\"1.70\",\"odd2\":\"2.03\",\"won\":2},{\"names\":\"Tande D.A. - Geiger K.\",\"odd1\":\"1.60\",\"odd2\":\"2.19\",\"won\":2},{\"names\":\"Tande D.A. - Żyła P.\",\"odd1\":\"1.60\",\"odd2\":\"2.19\",\"won\":2},{\"names\":\"Johansson R. - Stoch K.\",\"odd1\":\"1.85\",\"odd2\":\"1.85\",\"won\":1},{\"names\":\"Johansson R. - Geiger K.\",\"odd1\":\"1.70\",\"odd2\":\"2.03\",\"won\":2},{\"names\":\"Johansson R. - Żyła P.\",\"odd1\":\"1.70\",\"odd2\":\"2.03\",\"won\":1},{\"names\":\"Stoch K. - Geiger K.\",\"odd1\":\"1.70\",\"odd2\":\"2.03\",\"won\":2},{\"names\":\"Stoch K. - Żyła P.\",\"odd1\":\"1.70\",\"odd2\":\"2.03\",\"won\":2},{\"names\":\"Geiger K. - Żyła P.\",\"odd1\":\"1.85\",\"odd2\":\"1.85\",\"won\":1},{\"names\":\"Żyła P. - Kubacki D.\",\"odd1\":\"1.30\",\"odd2\":\"3.21\",\"won\":1},{\"names\":\"Sato Y. - Kubacki D.\",\"odd1\":\"2.03\",\"odd2\":\"1.70\",\"won\":1},{\"names\":\"Prevc D. - Zajc T.\",\"odd1\":\"1.80\",\"odd2\":\"1.90\",\"won\":1},{\"names\":\"Prevc D. - Pavlovcic B.\",\"odd1\":\"1.75\",\"odd2\":\"1.96\",\"won\":2},{\"names\":\"Zajc T. - Pavlovcic B.\",\"odd1\":\"1.80\",\"odd2\":\"1.90\",\"won\":2},{\"names\":\"Paschke P. - Lanisek A.\",\"odd1\":\"1.75\",\"odd2\":\"1.96\",\"won\":1},{\"names\":\"Pavlovcic B. - Aschenwald P.\",\"odd1\":\"1.57\",\"odd2\":\"2.25\",\"won\":2},{\"names\":\"Pavlovcic B. - Schmid C.\",\"odd1\":\"1.60\",\"odd2\":\"2.19\",\"won\":2},{\"names\":\"Pavlovcic B. - Stekala A.\",\"odd1\":\"1.55\",\"odd2\":\"2.29\",\"won\":2},{\"names\":\"Aschenwald P. - Schmid C.\",\"odd1\":\"1.75\",\"odd2\":\"1.96\",\"won\":2},{\"names\":\"Aschenwald P. - Stekala A.\",\"odd1\":\"2.03\",\"odd2\":\"1.70\",\"won\":2},{\"names\":\"Eriksen S.V. - Aalto A.\",\"odd1\":\"1.80\",\"odd2\":\"1.90\",\"won\":2},{\"names\":\"Klimov E. - Kobayashi R.\",\"odd1\":\"1.65\",\"odd2\":\"2.11\",\"won\":1},{\"names\":\"Boyd Clowes M. - Nakamura N.\",\"odd1\":\"1.85\",\"odd2\":\"1.85\",\"won\":1},{\"names\":\"Schlierenzauer G. - Aigro A.\",\"odd1\":\"1.70\",\"odd2\":\"2.03\",\"won\":1}]}"
        val planica2 = "{\"bets\":[{\"names\":\"Eisenbichler M. - Granerud H.E.\",\"odd1\":\"1.85\",\"odd2\":\"1.85\",\"won\":2},{\"names\":\"Eisenbichler M. - Geiger K.\",\"odd1\":\"1.40\",\"odd2\":\"2.73\",\"won\":2},{\"names\":\"Eisenbichler M. - Hayboeck M.\",\"odd1\":\"1.42\",\"odd2\":\"2.65\",\"won\":1},{\"names\":\"Eisenbichler M. - Johansson R.\",\"odd1\":\"1.25\",\"odd2\":\"3.56\",\"won\":1},{\"names\":\"Eisenbichler M. - Stoch K.\",\"odd1\":\"1.17\",\"odd2\":\"4.42\",\"won\":1},{\"names\":\"Eisenbichler M. - Żyła P.\",\"odd1\":\"1.15\",\"odd2\":\"4.73\",\"won\":1},{\"names\":\"Granerud H.E. - Geiger K.\",\"odd1\":\"1.48\",\"odd2\":\"2.47\",\"won\":1},{\"names\":\"Granerud H.E. - Hayboeck M.\",\"odd1\":\"1.36\",\"odd2\":\"2.89\",\"won\":1},{\"names\":\"Granerud H.E. - Johansson R.\",\"odd1\":\"1.26\",\"odd2\":\"3.48\",\"won\":1},{\"names\":\"Granerud H.E. - Stoch K.\",\"odd1\":\"1.17\",\"odd2\":\"4.42\",\"won\":1},{\"names\":\"Granerud H.E. - Żyła P.\",\"odd1\":\"1.15\",\"odd2\":\"4.73\",\"won\":1},{\"names\":\"Geiger K. - Hayboeck M.\",\"odd1\":\"1.70\",\"odd2\":\"2.03\",\"won\":1},{\"names\":\"Geiger K. - Johansson R.\",\"odd1\":\"1.50\",\"odd2\":\"2.41\",\"won\":1},{\"names\":\"Geiger K. - Stoch K.\",\"odd1\":\"1.37\",\"odd2\":\"2.85\",\"won\":1},{\"names\":\"Geiger K. - Żyła P.\",\"odd1\":\"1.35\",\"odd2\":\"2.94\",\"won\":1},{\"names\":\"Hayboeck M. - Johansson R.\",\"odd1\":\"1.60\",\"odd2\":\"2.19\",\"won\":2},{\"names\":\"Hayboeck M. - Stoch K.\",\"odd1\":\"1.45\",\"odd2\":\"2.55\",\"won\":1},{\"names\":\"Hayboeck M. - Żyła P.\",\"odd1\":\"1.42\",\"odd2\":\"2.65\",\"won\":1},{\"names\":\"Johansson R. - Stoch K.\",\"odd1\":\"1.65\",\"odd2\":\"2.11\",\"won\":1},{\"names\":\"Johansson R. - Żyła P.\",\"odd1\":\"1.60\",\"odd2\":\"2.19\",\"won\":1},{\"names\":\"Johansson R. - Sato Y.\",\"odd1\":\"1.45\",\"odd2\":\"2.55\",\"won\":1},{\"names\":\"Stoch K. - Żyła P.\",\"odd1\":\"1.80\",\"odd2\":\"1.90\",\"won\":2},{\"names\":\"Stoch K. - Sato Y.\",\"odd1\":\"1.60\",\"odd2\":\"2.19\",\"won\":2},{\"names\":\"Stoch K. - Kubacki D.\",\"odd1\":\"1.30\",\"odd2\":\"3.21\",\"won\":1},{\"names\":\"Stoch K. - Stekala A.\",\"odd1\":\"1.25\",\"odd2\":\"3.56\",\"won\":1},{\"names\":\"Żyła P. - Sato Y.\",\"odd1\":\"1.65\",\"odd2\":\"2.11\",\"won\":2},{\"names\":\"Żyła P. - Kubacki D.\",\"odd1\":\"1.31\",\"odd2\":\"3.15\",\"won\":1},{\"names\":\"Żyła P. - Stekala A.\",\"odd1\":\"1.26\",\"odd2\":\"3.48\",\"won\":1},{\"names\":\"Sato Y. - Tande D.A.\",\"odd1\":\"1.65\",\"odd2\":\"2.11\",\"won\":1},{\"names\":\"Sato Y. - Kubacki D.\",\"odd1\":\"1.60\",\"odd2\":\"2.19\",\"won\":1},{\"names\":\"Tande D.A. - Kubacki D.\",\"odd1\":\"1.85\",\"odd2\":\"1.85\",\"won\":1},{\"names\":\"Tande D.A. - Lanisek A.\",\"odd1\":\"1.45\",\"odd2\":\"2.55\",\"won\":2},{\"names\":\"Kubacki D. - Paschke P.\",\"odd1\":\"1.75\",\"odd2\":\"1.96\",\"won\":2},{\"names\":\"Kubacki D. - Stekala A.\",\"odd1\":\"1.75\",\"odd2\":\"1.96\",\"won\":2},{\"names\":\"Paschke P. - Stekala A.\",\"odd1\":\"1.85\",\"odd2\":\"1.85\",\"won\":2},{\"names\":\"Paschke P. - Klimov E.\",\"odd1\":\"1.70\",\"odd2\":\"2.03\",\"won\":2},{\"names\":\"Stekala A. - Klimov E.\",\"odd1\":\"1.85\",\"odd2\":\"1.85\",\"won\":2},{\"names\":\"Lanisek A. - Pavlovcic B.\",\"odd1\":\"1.85\",\"odd2\":\"1.85\",\"won\":1},{\"names\":\"Pavlovcic B. - Aschenwald P.\",\"odd1\":\"1.55\",\"odd2\":\"2.29\",\"won\":1},{\"names\":\"Aalto A. - Kobayashi R.\",\"odd1\":\"1.47\",\"odd2\":\"2.49\",\"won\":1},{\"names\":\"Kobayashi R. - Sato K.\",\"odd1\":\"1.65\",\"odd2\":\"2.11\",\"won\":1}]}"
        val planica3 = "{\"bets\":[{\"names\":\"Żyła P. - Tande D.A.\",\"odd1\":\"1.35\",\"odd2\":\"2.94\",\"won\":1},{\"names\":\"Żyła P. - Schmid C.\",\"odd1\":\"1.10\",\"odd2\":\"5.81\",\"won\":1},{\"names\":\"Tande D.A. - Schmid C.\",\"odd1\":\"1.40\",\"odd2\":\"2.73\",\"won\":1},{\"names\":\"Aschenwald P. - Sato K.\",\"odd1\":\"1.80\",\"odd2\":\"1.90\",\"won\":1},{\"names\":\"Aschenwald P. - Prevc D.\",\"odd1\":\"1.90\",\"odd2\":\"1.80\",\"won\":2},{\"names\":\"Sato K. - Prevc D.\",\"odd1\":\"1.96\",\"odd2\":\"1.75\",\"won\":2},{\"names\":\"Stekala A. - Paschke P.\",\"odd1\":\"1.85\",\"odd2\":\"1.85\",\"won\":1},{\"names\":\"Forfang J.A. - Prevc P.\",\"odd1\":\"1.55\",\"odd2\":\"2.29\",\"won\":2},{\"names\":\"Forfang J.A. - Schlierenzauer G.\",\"odd1\":\"1.35\",\"odd2\":\"2.94\",\"won\":1},{\"names\":\"Forfang J.A. - Nakamura N.\",\"odd1\":\"1.30\",\"odd2\":\"3.21\",\"won\":1},{\"names\":\"Prevc P. - Schlierenzauer G.\",\"odd1\":\"1.55\",\"odd2\":\"2.29\",\"won\":1},{\"names\":\"Prevc P. - Nakamura N.\",\"odd1\":\"1.55\",\"odd2\":\"2.29\",\"won\":1},{\"names\":\"Schlierenzauer G. - Nakamura N.\",\"odd1\":\"1.75\",\"odd2\":\"1.96\",\"won\":1},{\"names\":\"Eisenbichler M. - Johansson R.\",\"odd1\":\"1.45\",\"odd2\":\"2.55\",\"won\":1},{\"names\":\"Eisenbichler M. - Stoch K.\",\"odd1\":\"1.20\",\"odd2\":\"4.04\",\"won\":1},{\"names\":\"Johansson R. - Stoch K.\",\"odd1\":\"1.35\",\"odd2\":\"2.94\",\"won\":1},{\"names\":\"Pavlovcic B. - Kobayashi R.\",\"odd1\":\"1.65\",\"odd2\":\"2.11\",\"won\":2},{\"names\":\"Granerud H.E. - Geiger K.\",\"odd1\":\"1.55\",\"odd2\":\"2.29\",\"won\":1},{\"names\":\"Granerud H.E. - Hayboeck M.\",\"odd1\":\"1.20\",\"odd2\":\"4.04\",\"won\":1},{\"names\":\"Geiger K. - Hayboeck M.\",\"odd1\":\"1.25\",\"odd2\":\"3.56\",\"won\":1},{\"names\":\"Hayboeck M. - Sato Y.\",\"odd1\":\"1.65\",\"odd2\":\"2.11\",\"won\":1},{\"names\":\"Hayboeck M. - Lanisek A.\",\"odd1\":\"1.30\",\"odd2\":\"3.21\",\"won\":2},{\"names\":\"Sato Y. - Lanisek A.\",\"odd1\":\"1.43\",\"odd2\":\"2.62\",\"won\":2},{\"names\":\"Lanisek A. - Klimov E.\",\"odd1\":\"1.60\",\"odd2\":\"2.19\",\"won\":1},{\"names\":\"Kubacki D. - Aalto A.\",\"odd1\":\"1.42\",\"odd2\":\"2.65\",\"won\":1},{\"names\":\"Granerud H.E. - Eisenbichler M.\",\"odd1\":\"1.45\",\"odd2\":\"2.55\",\"won\":1},{\"names\":\"Geiger K. - Eisenbichler M.\",\"odd1\":\"1.65\",\"odd2\":\"2.11\",\"won\":1},{\"names\":\"Eisenbichler M. - Hayboeck M.\",\"odd1\":\"1.40\",\"odd2\":\"2.73\",\"won\":1},{\"names\":\"Hayboeck M. - Johansson R.\",\"odd1\":\"1.75\",\"odd2\":\"1.96\",\"won\":1},{\"names\":\"Stoch K. - Żyła P.\",\"odd1\":\"2.19\",\"odd2\":\"1.60\",\"won\":2},{\"names\":\"Stoch K. - Stekala A.\",\"odd1\":\"1.30\",\"odd2\":\"3.21\",\"won\":2},{\"names\":\"Żyła P. - Stekala A.\",\"odd1\":\"1.25\",\"odd2\":\"3.56\",\"won\":1}]}"
        val engelbergK ="{\"bets\":[{\"names\":\"Granerud H.E. - Eisenbichler M.\",\"odd1\":\"1.50\",\"odd2\":\"2.41\"},{\"names\":\"Granerud H.E. - Huber D.\",\"odd1\":\"1.25\",\"odd2\":\"3.56\"},{\"names\":\"Granerud H.E. - Johansson R.\",\"odd1\":\"1.20\",\"odd2\":\"4.04\"},{\"names\":\"Granerud H.E. - Hayboeck M.\",\"odd1\":\"1.20\",\"odd2\":\"4.04\"},{\"names\":\"Granerud H.E. - Sato Y.\",\"odd1\":\"1.15\",\"odd2\":\"4.73\"},{\"names\":\"Eisenbichler M. - Huber D.\",\"odd1\":\"1.45\",\"odd2\":\"2.55\"},{\"names\":\"Eisenbichler M. - Johansson R.\",\"odd1\":\"1.40\",\"odd2\":\"2.73\"},{\"names\":\"Eisenbichler M. - Hayboeck M.\",\"odd1\":\"1.40\",\"odd2\":\"2.73\"},{\"names\":\"Eisenbichler M. - Sato Y.\",\"odd1\":\"1.35\",\"odd2\":\"2.94\"},{\"names\":\"Huber D. - Johansson R.\",\"odd1\":\"1.80\",\"odd2\":\"1.90\"},{\"names\":\"Huber D. - Hayboeck M.\",\"odd1\":\"1.70\",\"odd2\":\"2.03\"},{\"names\":\"Huber D. - Sato Y.\",\"odd1\":\"1.65\",\"odd2\":\"2.11\"},{\"names\":\"Huber D. - Żyła P.\",\"odd1\":\"1.65\",\"odd2\":\"2.11\"},{\"names\":\"Johansson R. - Hayboeck M.\",\"odd1\":\"1.75\",\"odd2\":\"1.96\"},{\"names\":\"Johansson R. - Sato Y.\",\"odd1\":\"1.70\",\"odd2\":\"2.03\"},{\"names\":\"Johansson R. - Żyła P.\",\"odd1\":\"1.70\",\"odd2\":\"2.03\"},{\"names\":\"Johansson R. - Stoch K.\",\"odd1\":\"1.55\",\"odd2\":\"2.29\"},{\"names\":\"Hayboeck M. - Sato Y.\",\"odd1\":\"1.75\",\"odd2\":\"1.96\"},{\"names\":\"Hayboeck M. - Żyła P.\",\"odd1\":\"1.75\",\"odd2\":\"1.96\"},{\"names\":\"Hayboeck M. - Stoch K.\",\"odd1\":\"1.60\",\"odd2\":\"2.19\"},{\"names\":\"Hayboeck M. - Lanisek A.\",\"odd1\":\"1.55\",\"odd2\":\"2.29\"},{\"names\":\"Sato Y. - Żyła P.\",\"odd1\":\"1.85\",\"odd2\":\"1.85\"},{\"names\":\"Sato Y. - Stoch K.\",\"odd1\":\"1.70\",\"odd2\":\"2.03\"},{\"names\":\"Sato Y. - Lanisek A.\",\"odd1\":\"1.60\",\"odd2\":\"2.19\"},{\"names\":\"Żyła P. - Stoch K.\",\"odd1\":\"1.70\",\"odd2\":\"2.03\"},{\"names\":\"Żyła P. - Lanisek A.\",\"odd1\":\"1.60\",\"odd2\":\"2.19\"},{\"names\":\"Żyła P. - Aschenwald P.\",\"odd1\":\"1.50\",\"odd2\":\"2.41\"},{\"names\":\"Stoch K. - Lanisek A.\",\"odd1\":\"1.65\",\"odd2\":\"2.11\"},{\"names\":\"Stoch K. - Kubacki D.\",\"odd1\":\"1.65\",\"odd2\":\"2.11\"},{\"names\":\"Stoch K. - Paschke P.\",\"odd1\":\"1.55\",\"odd2\":\"2.29\"},{\"names\":\"Lanisek A. - Kubacki D.\",\"odd1\":\"1.85\",\"odd2\":\"1.85\"},{\"names\":\"Lanisek A. - Kobayashi R.\",\"odd1\":\"1.45\",\"odd2\":\"2.55\"},{\"names\":\"Kubacki D. - Stekala A.\",\"odd1\":\"1.55\",\"odd2\":\"2.29\"},{\"names\":\"Paschke P. - Klimov E.\",\"odd1\":\"1.50\",\"odd2\":\"2.41\"},{\"names\":\"Lindvik M. - Tande D.A.\",\"odd1\":\"1.60\",\"odd2\":\"2.19\"},{\"names\":\"Forfang J.A. - Prevc P.\",\"odd1\":\"1.55\",\"odd2\":\"2.29\"},{\"names\":\"Aschenwald P. - Tande D.A.\",\"odd1\":\"1.85\",\"odd2\":\"1.85\"},{\"names\":\"Schmid C. - Deschwanden G.\",\"odd1\":\"1.75\",\"odd2\":\"1.96\"},{\"names\":\"Kobayashi R. - Sato K.\",\"odd1\":\"1.85\",\"odd2\":\"1.85\"},{\"names\":\"Stekala A. - Muranka K.\",\"odd1\":\"1.60\",\"odd2\":\"2.19\"},{\"names\":\"Stekala A. - Zniszczol A.\",\"odd1\":\"1.45\",\"odd2\":\"2.55\"},{\"names\":\"Muranka K. - Zniszczol A.\",\"odd1\":\"1.55\",\"odd2\":\"2.29\"}]}"
        val bets = listOf(nizny1, nizny2, planica1, planica2, planica3, engelbergK)
        var lastTournament = "nizny"

        fun setLastTournament(index : Int) {
            when(index) {
                0 -> lastTournament = "nizny"
                1 -> lastTournament = "nizny"
                2 -> lastTournament = "planica"
                3 -> lastTournament = "planica"
                4 -> lastTournament = "planica"
                5 -> lastTournament = "planica"
            }
        }
    }



    fun getBets(probabilities: List<Probability>, betsIndex : Int): List<Bet> {
        val gson = Gson()
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
            if (it.won == 1 || it.won == 2 || it.won == 0) {
                val bet1 = SingleBet(index++, it.name1, it.name2, it.odd1, it.book1Prob, it.my1Prob, it.value1, it.won == 1)
                val bet2 = SingleBet(index++, it.name2, it.name1, it.odd2, it.book2Prob, it.my2Prob, it.value2, it.won == 2)
                result.add(bet1)
                result.add(bet2)
            }
        }
        result.sortByDescending { it.value }
        return result
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

}