package data.entity.statistics

data class JumperResults(
        val name: String,
        val jumps: HashMap<String, Jump>)

data class Jump(
        val tournament: String,
        val tournamentType: TournamentType,
        val points: Double
)

enum class TournamentType {
    T, K, P, Z
}