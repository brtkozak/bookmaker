package ga.rsikminimization

import ga.PopulationRater
import ga.dfstree.DfsTree
import ga.dfstree.Node
import ga.dfstree.Result
import ga.entity.CouponsGroup

class RiskMinimizationRater : PopulationRater() {

    val routes = mutableListOf<List<Node>>()
    var routesMap = mutableListOf<Pair<List<Int>, Double>>()  // pair( win bets in route, route probability)
    var couponsGroup: CouponsGroup? = null

    init {
        routes.addAll(DfsTree.buildTree(Main.AVAILABLE_BETS))
        routes.forEach { nodes ->
            val winBets = nodes.filter { it.result == Result.WIN }.mapNotNull { it.bet }
            val ids = winBets.map { it.id }
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
            routesMap.add(Pair(ids, routeProb))
        }
    }

    override fun rateCouponsGroup(couponsGroup: CouponsGroup) {
        this.couponsGroup = couponsGroup.copy()
        val result = processRoutes()
        couponsGroup.rate = result
        this.couponsGroup = null
    }

    private fun processRoutes(): Double {
        val totalContribution = couponsGroup?.getContribution() ?: 0.0
        var loseProbability = 0.0

        routesMap.forEach {pair ->
            var winCash = 0.0
            this.couponsGroup?.coupons?.forEach {
                if (pair.first.containsAll(it.bets.map { bet -> bet.id })) {
                    winCash += it.getWinCash()
                }
            }

            if(winCash < totalContribution) {
                loseProbability += pair.second
            }
        }

        return loseProbability
    }

}