package data

import com.google.gson.Gson
import data.entity.statistics.*

class StatisticsConverter {

    fun getData(eventIndex : Int): SkiJumpingData {
        val gson = Gson()
        val statisticsCount = when(Main.MODE) {
            Main.Companion.Mode.Jump -> SkiJumpingStatistics.wrapEventIndexToStatisticsIndex(eventIndex)
            Main.Companion.Mode.WSki -> WomenAlpeinStatistics.wrapEventIndexToStatisticsIndex(eventIndex)
            Main.Companion.Mode.MSki -> MenAlpineStatistics.wrapEventIndexToStatisticsIndex(eventIndex)
        }

        val statistics = when(Main.MODE) {
            Main.Companion.Mode.Jump -> SkiJumpingStatistics.statistics
            Main.Companion.Mode.WSki -> WomenAlpeinStatistics.statistics
            Main.Companion.Mode.MSki -> MenAlpineStatistics.statistics
        }

        val result = SkiJumpingData(mutableListOf())
        for(i in 0 until statisticsCount) {
            val data = gson.fromJson(statistics[i], SkiJumpingData::class.java)
            result.tournaments.addAll(data.tournaments)
        }

        if(Main.MODE == Main.Companion.Mode.Jump) {
            // ABY NIE BRAC WSZYSTKICH ZAWODOW TYLKO KLIKA OSTATNICH TURNIEJI
            val temp = result.tournaments.reversed()
            var tempResult = mutableListOf<Tournament>()
            var currentTournaments = 0
            val maxTournaments = 5
            temp.forEach { tournament ->
                if (tempResult.map { it.name.dropLast(3) }.distinct().size <= maxTournaments) {
                    tempResult.add(tournament)
                }
            }
            if (tempResult.map { it.name.dropLast(3) }.distinct().size > maxTournaments)
                tempResult = tempResult.dropLast(1) as MutableList<Tournament>
            // ABY NIE BRAC WSZYSTKICH ZAWODOW TYLKO KLIKA OSTATNICH TURNIEJI
            return SkiJumpingData(tempResult)
        }
        else {
            return SkiJumpingData(result.tournaments)
        }
    }

    fun getJumpersResults(skiJumpingData: SkiJumpingData): List<JumperResults> {
        normalizeName(skiJumpingData)
        val jumperResults = mutableListOf<JumperResults>()
        skiJumpingData.tournaments.forEach { tournament ->
            tournament.results.forEach { result ->
                if (jumperResults.none { it.name == result.name }) {
                    val hashMap = HashMap<String, Jump>()
                    hashMap.put(tournament.name, getJump(tournament, result))
                    val jumperResult = JumperResults(result.name, hashMap)
                    jumperResults.add(jumperResult)
                } else {
                    val jumperResult = jumperResults.first { it.name == result.name }
                    jumperResult.jumps.put(tournament.name, getJump(tournament, result))
                }
            }
        }
        return jumperResults
    }

    private fun getJump(tournament: Tournament, result: Result): Jump {
        val hashMap = HashMap<String, Jump>()
        val type = when (tournament.type) {
            "T" -> TournamentType.T
            "K" -> TournamentType.K
            "P" -> TournamentType.P
            "Z" -> TournamentType.Z
            else -> TournamentType.T
        }
        var points = 0.0
        if (result.points == null || result.points == "") {
            points = 0.0
        }
        else if(Main.MODE == Main.Companion.Mode.Jump)
            points = result.points.toDouble()
        else {
            val minutesSplit = result.points.split(":")
            var minutes = 0
            var seconds = 0
            var miniSeconds = 0

            var secondsPart = minutesSplit[0]
            if (minutesSplit.size == 2) {
                minutes = minutesSplit[0].toDouble().toInt()
                secondsPart = minutesSplit[1]
            }

            val secondsSplit = secondsPart.split(".")
            seconds = secondsSplit[0].toInt()
            miniSeconds = secondsSplit[1].toInt()
            points = (minutes * 60000 + seconds * 1000 + miniSeconds).toDouble()
        }
        return Jump(tournament = tournament.name, tournamentType = type, points = points, day = tournament.day, skiType = tournament.skiType)
    }

    private fun normalizeName(skiJumpingData: SkiJumpingData) {
        skiJumpingData.tournaments.forEach { t ->
            t.results.forEach {
                // switch lastname with firstname for torunaments becous of another source (WP / FIS)
                if (t.type != "Z" && Main.MODE == Main.Companion.Mode.Jump) {
                    val temp = it.name.split(" ")
                    val lastName = temp[temp.size - 1]
                    var newName = "$lastName "
                    for (i in 0..temp.size - 2) {
                        newName += temp[i]
                        if (i != temp.size - 2)
                            newName += " "
                    }
                    it.name = newName
                }
                // change all names to format : last name and first letter of names
                if(!it.name.contains("ZAMPA")) {
                    val temp = it.name.split(" ")
                    val lastName = temp[0]
                    var newName = "$lastName "
                    for (i in 1..temp.size - 1) {
                        val firstLetter = temp[i][0]
                        newName += "$firstLetter."
                    }
                    it.name = newName
                }

                it.name = it.name
                        .toLowerCase()
                        .replace("ą", "a")
                        .replace("ć", "c")
                        .replace("ę", "e")
                        .replace("ł", "l")
                        .replace("ń", "n")
                        .replace("ś", "s")
                        .replace("ź", "z")
                        .replace("ż", "z")

                if (it.name.contains("klimow")) {
                    it.name = it.name.replace("klimow", "klimov")
                }
                if(it.name.contains("klimov j.")) {
                    it.name = it.name.replace("klimov j.", "klimov e.")
                }
            }
        }
    }

}