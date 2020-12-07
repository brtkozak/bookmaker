package data.entity.statistics

import com.google.gson.annotations.SerializedName

data class SkiJumpingData(
        val tournaments: MutableList<Tournament>
)

data class Tournament(
        val name: String,
        val type: String,
        @SerializedName("jumps")
        val results: List<Result>
)

data class Result(
        var name: String,
        val points: String
)