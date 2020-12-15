package ga.rsikminimization

import ga.PopulationRater
import ga.dfstree.DfsTree
import ga.dfstree.Node
import ga.dfstree.Result
import ga.entity.CouponsGroup

class RiskMinimizationRater : PopulationRater() {

    val routes = mutableListOf<List<Node>>()
    var couponsGroup: CouponsGroup? = null

    override fun rateCouponsGroup(couponsGroup: CouponsGroup) {
        this.couponsGroup = couponsGroup.copy()
        routes.clear()
        routes.addAll(DfsTree.buildTree(couponsGroup))
        val result = processRoutes()
        couponsGroup.rate = result
        this.couponsGroup = null
        routes.clear()
    }

    private fun processRoutes(): Double {
        val totalContribution = couponsGroup?.getContribution() ?: 0.0
        var loseProbability = 0.0

        routes.forEach { nodes ->
            val winBets = nodes.filter { it.result == Result.WIN }.mapNotNull { it.bet }
            var winCash = 0.0
            this.couponsGroup?.coupons?.forEach {
                if (winBets.map { winBet -> winBet.id }.containsAll(it.bets.map { bet -> bet.id })) {
                    winCash += it.getWinCash()
                }
            }

            if (winCash < totalContribution) {
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
                loseProbability += routeProb
            }
        }
        if(loseProbability == 0.0 || loseProbability ==0.1) {
            val x  =2
        }
        return loseProbability
    }

}