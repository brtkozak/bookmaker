package data.entity.bets

data class Bet(
        val name1: String,
        val name2: String,
        val odd1: Double,
        val odd2: Double,
        val book1Prob: Double,
        val book2Prob: Double,
        var my1Prob: Double = 0.0,
        var my2Prob: Double = 0.0,
        var value1: Double = 0.0,
        var value2: Double = 0.0,
        val won: Int
)