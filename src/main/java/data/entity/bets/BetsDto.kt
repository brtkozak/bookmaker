package data.entity.bets

data class BetsDto(
        val bets: List<BetDto>
)

data class BetDto(
        val names: String,
        val odd1: String,
        val odd2: String,
        val won: Int
)
