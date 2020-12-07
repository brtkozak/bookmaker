package chart

data class ChartDataSet (
        val title : String,
        val xLabel: String,
        val yLabel: String,
        val series: MutableList<Series> = mutableListOf()
)

data class Series (
        val title : String,
        val data : MutableList<Pair<Double, Double>> = mutableListOf()
)