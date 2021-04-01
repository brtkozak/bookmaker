package data.entity.statistics

data class JumperResults(
        val name: String,
        val jumps: HashMap<String, Jump>,
        val places : MutableList<Int> = mutableListOf() )

data class Jump(
        val tournament: String,
        val day : Int,
        val tournamentType: TournamentType,
        val points: Double,
        val skiType : String? = null,
        var place : Int? = null
)

enum class TournamentType {
    T, K, P, Z
}