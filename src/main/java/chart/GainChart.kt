package chart

import ga.dfstree.DfsTree
import ga.dfstree.Node
import ga.dfstree.Result
import ga.entity.CouponsGroup

class GainChart(val couponsGroup: CouponsGroup, val chartDrawer: ChartDrawer) {

    private val gainDataSet =
            ChartDataSet("Gain from the probability of obtaining it ", "Gain", "Probability")

    private val series = Series("")

    init {
        gainDataSet.series.add(series)
    }

    fun processCouponsGroup() {
        val routes = DfsTree.buildTree(couponsGroup)
        val gainMap = processRoutes(routes)
        gainMap.forEach { k, v ->
            series.data.add(Pair(k, v))
        }
        chartDrawer.draw(chartDataSet = gainDataSet)
    }

    private fun processRoutes(routes: MutableList<List<Node>>): MutableMap<Double, Double> {
        val totalContribution = couponsGroup.getContribution()
        val gainMap = mutableMapOf<Double, Double>()

        var loseProb = 0.0
        var winProb = 0.0

        routes.forEach { nodes ->
            val winBets = nodes.filter { it.result == Result.WIN }.mapNotNull { it.bet }
            var winCash = 0.0
            this.couponsGroup?.coupons?.forEach {
                if (winBets.map { winBet -> winBet.id }.containsAll(it.bets.map { bet -> bet.id })) {
                    winCash += it.getWinCash()
                }
            }

            var routeProb = 1.0
            nodes.forEach {
                if (it.bet?.myProb != null) {
                    if (it.result == Result.WIN) {
                        routeProb *= it.bet.myProb
                    } else if (it.result == Result.LOSE) {
                        routeProb *= (1 - it.bet.myProb)
                    }
                }
            }
            if (winCash < totalContribution)
                loseProb += routeProb
            else
                winProb += routeProb

            winCash -= totalContribution


            gainMap[winCash] = gainMap[winCash]?.plus(routeProb) ?: routeProb
        }
        println("LOSE PROB: $loseProb")
        println("WIN PROB: $winProb")
        return gainMap
    }

}

