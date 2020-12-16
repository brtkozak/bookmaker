package data.entity.statistics

import com.google.gson.annotations.SerializedName

data class SkiJumpingData(
        val tournaments: MutableList<Tournament>
)

data class Tournament(
        val name: String,
        val type: String,
        val day: Int = 0,
        @SerializedName("jumps")
        val results: List<Result>
)

data class Result(
        var name: String,
        val points: String
)