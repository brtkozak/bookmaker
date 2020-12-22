package data.entity.bets

data class SingleBet (
        val id: Int,
        val name1: String,
        val name2: String,
        val odd: Double,
        val bookProb: Double,
        var myProb: Double,
        var value: Double,
        val betResult: BetResult
)

enum class BetResult {
    Win, Lose, Unknown
}